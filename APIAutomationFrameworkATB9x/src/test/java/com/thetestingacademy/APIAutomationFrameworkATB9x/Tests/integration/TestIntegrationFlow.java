package com.thetestingacademy.APIAutomationFrameworkATB9x.Tests.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.thetestingacademy.APIAutomationFrameworkATB9x.base.BaseTest;
import com.thetestingacademy.APIAutomationFrameworkATB9x.endpoints.APIConstants;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.BookingResponse;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.Booking;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import junit.framework.Assert;

public class TestIntegrationFlow extends BaseTest{
		
			//  Test Integration Scenario 1
		
		    //  1. Create a Booking -> bookingID
		
		    // 2. Create Token -> token
		
		    // 3. Verify that the Create Booking is working - GET Request to bookingID
		
		    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request
		
		    // 5. Delete the Booking - Need to get the token, bookingID from above request
			
			@Test(groups = "qa", priority = 1)
		    @Owner("febil")
		    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
		    public void testCreateBooking(ITestContext iTestContext){

				//Request Preparation
		        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		        
		        //API Request Execution
		        response = RestAssured.given(requestSpecification)
		                .when().body(payloadManager.createPayloadBookingAsString()).post();
		        
		        //Response Validation
		        validatableResponse = response.then().log().all();
		        validatableResponse.statusCode(200);
		        
		        //Response Parsing & Assertion
		        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
		        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "James");
		        System.out.println(bookingResponse.getBookingid());
		        
		        // Storing Booking ID
		        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
		
		    }
			
			@Test(groups = "qa", priority = 2)
		    @Owner("febil")
		    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
		    public void testVerifyBookingId(ITestContext iTestContext){
				
				//Context Data Retrieval
		        System.out.println(iTestContext.getAttribute("bookingid"));
		        Assert.assertTrue(true);

		        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

		        // GET Request 
		        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
		        System.out.println(basePathGET);
		        
		        //API Request Execution
		        requestSpecification.basePath(basePathGET);
		        response = RestAssured
		                .given(requestSpecification)
		                .when().get();
		        
		        // Response Validation
		        validatableResponse = response.then().log().all();
		        validatableResponse.statusCode(200);
		        
		        //Response Conversion to Java Object
		        Booking booking = payloadManager.getResponseFromJSON(response.asString());
		        
		        //Assertions
		        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
		        assertThat(booking.getFirstname()).isEqualTo("James");
		    }
			
			
			@Test(groups = "qa", priority = 3)
		    @Owner("febil")
		    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
		    public void testUpdateBookingByID(ITestContext iTestContext){
				
				//Context Data Retrieval
		        System.out.println(iTestContext.getAttribute("bookingid")); //getAttribute() returns an Object type
		        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid"); //Type casting is happening
		        
		        //Token Generation
		        String token = getToken();
		        iTestContext.setAttribute("token",token);
		        
		        //URL Setup for PUT request
		        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
		        System.out.println(basePathPUTPATCH);
		        requestSpecification.basePath(basePathPUTPATCH);
		        
		        //API Request Execution
		        response = RestAssured
		                .given(requestSpecification).cookie("token", token)
		                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

		        //Response Validation
		        validatableResponse = response.then().log().all();
		        validatableResponse.statusCode(200);
		        
		        //Response Processing - response conversion to Booking Object
		        Booking booking = payloadManager.getResponseFromJSON(response.asString());
		        
		        //Assertions
		        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
		        assertThat(booking.getFirstname()).isEqualTo("febil");
		        assertThat(booking.getLastname()).isEqualTo("jose");
		}
			
			@Test(groups = "qa", priority = 4)
		    @Owner("febil")
		    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
		    public void testDeleteBookingById(ITestContext iTestContext){
				
				//token & Booking ID for Delete Method

		        String token = (String)iTestContext.getAttribute("token");
		        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

		        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

		        requestSpecification.basePath(basePathDELETE).cookie("token", token);
		        validatableResponse = RestAssured.given().spec(requestSpecification)
		                .when().delete().then().log().all();
		        validatableResponse.statusCode(201);
			}
}
package com.thetestingacademy.APIAutomationFrameworkATB9x.Tests.crud;

import org.testng.annotations.Test;

import com.thetestingacademy.APIAutomationFrameworkATB9x.base.BaseTest;
import com.thetestingacademy.APIAutomationFrameworkATB9x.endpoints.APIConstants;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.BookingResponse;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;

public class testCreateBooking extends BaseTest{
			@Owner("febil")
		    @TmsLink("https://google.com")
		    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
		    @Issue("JIRA_RBT-4")
		    @Description("Verify that POST request is working fine.")
		    @Test(groups = "qa")
		    public void testVerifyCreateBookingPOST01() {
		        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		
		        response = RestAssured.given(requestSpecification)
		                .when().body(payloadManager.createPayloadBookingAsString()).post();
		
		        validatableResponse = response.then().log().all();
		        validatableResponse.statusCode(200);
		        
		        //Deserialization
		        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
		        
		        //Assertion
		        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "James");
			}
}
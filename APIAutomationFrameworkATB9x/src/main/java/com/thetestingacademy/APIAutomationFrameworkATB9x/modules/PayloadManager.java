package com.thetestingacademy.APIAutomationFrameworkATB9x.modules;

import com.google.gson.Gson;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.Auth;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.Booking;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.BookingResponse;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.Bookingdates;
import com.thetestingacademy.APIAutomationFrameworkATB9x.pojos.TokenResponse;

public class PayloadManager {
	
			 // Converting the JAVA object to the String--Serialization	
			Gson gson;
		    public String createPayloadBookingAsString() {
		
		        Booking booking = new Booking();
		        booking.setFirstname("James");
		        booking.setLastname("Brown");
		        booking.setTotalprice(111);
		        booking.setDepositpaid(true);
		
		        Bookingdates bookingdates = new Bookingdates();
		        bookingdates.setCheckin("2024-02-01");
		        bookingdates.setCheckout("2024-02-01");
		
		        booking.setBookingdates(bookingdates);
		        booking.setAdditionalneeds("Breakfast");
		
		        System.out.println(booking);
		        gson = new Gson();
		        String jsonStringpayload = gson.toJson(booking);
		        System.out.println(jsonStringpayload);
		        return jsonStringpayload;
		    }
		    
			  // Converting the String to the JAVA Object-- De-serialization
		    public BookingResponse bookingResponseJava(String responseString) {
		        gson = new Gson();
		        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
		        return bookingResponse;
		    }
		    
		    public Booking getResponseFromJSON(String getResponse){
		        gson = new Gson();
		        Booking booking = gson.fromJson(getResponse,Booking.class);
		        return booking;
		    }
		    
		     //  ---- Token -----
		     // JAVA to JSON
		    public String setAuthPayload(){
		        Auth auth = new Auth();
		        auth.setUsername("admin");
		        auth.setPassword("password123");
		        
		        gson = new Gson();
		        String jsonPayloadString = gson.toJson(auth);
		        System.out.println("Payload set to the -> " + jsonPayloadString);
		        return jsonPayloadString;

		    }
		    
		    	// JSON to Java
		    public String getTokenFromJSON(String tokenResponse){
		        gson = new Gson();
		        TokenResponse tokenResponse1  = gson.fromJson(tokenResponse, TokenResponse.class);
		        return tokenResponse1.getToken();

		    }
		    
		    // update full pay load
		    public String fullUpdatePayloadAsString() {
		        Booking booking = new Booking();
		        booking.setFirstname("febil");
		        booking.setLastname("jose");
		        booking.setTotalprice(112);
		        booking.setDepositpaid(true);

		        Bookingdates bookingdates = new Bookingdates();
		        bookingdates.setCheckin("2024-02-01");
		        bookingdates.setCheckout("2024-02-05");
		        booking.setBookingdates(bookingdates);
		        booking.setAdditionalneeds("Breakfast");
		        return gson.toJson(booking);


		    }
}
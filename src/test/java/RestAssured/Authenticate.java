package RestAssured;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.BaseClass;
import Constants.FrameworkConstants;
import Entities.ToDoItem;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
//import utility.AllureLogger;
import utility.ExcelLib;
import utility.FrameworkUtility;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import org.json.JSONObject;
import org.json.simple.JSONObject;


public class Authenticate extends BaseClass
{
	public String post_CreateAuth()
	{
	/*******************************************************
	 * POST request to authenticate
	 * and check that the response has HTTP status code 200
	 ******************************************************/
		String token = null;
		try
		{
			test = extent.createTest("Successful Authentication Testing");
		
			JSONObject jsonObject = returDefaultPayLoadObject(FrameworkConstants.POSTRequest_AUTH_DEFAULT_REQUEST);

			test.log(Status.INFO, "Attempting for Successful Authentication");
			log.info("Attempting for Successful Authentication");
			
			Response response = given().
					spec(requestSpec).
					contentType("application/json").
					body(jsonObject.toJSONString()).
				when().
					post("/api/login");
		
				test.log(Status.INFO, "Asserting the response if the status code returned is 200");
				response.then().spec(responseSpec);
				
				int statusCode = response.getStatusCode();
				log.info("Return status code "+statusCode);  
				Assert.assertEquals(statusCode,200, "Login Successfull with status code " + statusCode);					  
				  
				test.log(Status.INFO, "Status code: " + statusCode);
				token = response.then().extract().path("token");
				  
				// Find for Cookie
				test.log(Status.INFO, "Fetching Cookies");
				String tokenC = null;
				Cookie cookie = response.getDetailedCookie("JWT");
				if (cookie != null) 
				{
			         // Extract the value of the token
			         tokenC = cookie.getValue();
			         System.out.println("Token: " + tokenC);
						log.info("Token: " + tokenC);
			    } 
				else 
			    {
			         System.out.println("Token not found in cookie");
						log.info("Token not found in cookie");
			    }
				
				test.log(Status.INFO, "Requested Cookie value: "+tokenC);
				test.pass("Authentication Successful passed");
				log.info("Authentication Successful passed");
				Assert.assertTrue(true);			
				
		}
		catch (Exception e) 
		{
			log.info(e.getLocalizedMessage());
		}
		return token;
	}

	public void post_CreateInvalidAuth()
	{
	/*******************************************************
	 * POST request to authenticate
	 * and check that the response has HTTP status code 200
	 ******************************************************/
		String token = null;
		try
		{
			test = extent.createTest("Unsuccessful Authentication Testing");
			test.log(Status.INFO, "Attempting for Unsuccessful Authentication");
		
			JSONObject jsonObject = new JSONObject();
			String username = readConfigurationFile("email");
			String password = readConfigurationFile("password");
		
			jsonObject.put("password", password);
			jsonObject.put("email", username);
			
			Response response = given().
					spec(requestSpec).
					contentType("application/json").
					body(jsonObject.toJSONString()).
					//body(jsonObject.toString()).
				when().
					post("/api/login");
		
			test.log(Status.INFO, "Asserting the response if the status code returned is 400");
				//response.then().spec(responseSpec);
				
				  int statusCode = response.getStatusCode();
				  
				  Assert.assertEquals(statusCode,400, "Invalid credentials. Return Status code " + statusCode);					  

				test.pass("Authentication Unsuccessful Tested");  
				Assert.assertTrue(true);			
		}
		catch (Exception e) 
		{
			log.info(e.getLocalizedMessage());
		}
	}	
	
	@Test
	public void CheckAuth()
	{
		String newAuthToken=post_CreateAuth();
		test.pass("Current Token is: " + newAuthToken);
		log.info("Current Token is: " + newAuthToken);
	}
	@Test
	public void CheckInvalidAuth()
	{
		post_CreateInvalidAuth();
	}	
}

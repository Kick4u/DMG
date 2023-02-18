package RestAssured;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.BaseClass;
import Entities.ToDoItem;
import io.restassured.response.Response;
import utility.ExcelLib;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class FetchTODOList extends BaseClass
{
		
  @Test
  public void FetchTODOList() throws Exception 
  {
	  try {
		test = extent.createTest("Fetching all TODO Items");
		test.log(Status.INFO, "Fetching all TODO Items");
		log.info("Fetching all TODO Items");
		Response response = given(). 
		spec(requestSpec).
		contentType("application/json").log().body(). 
		  
		when().
		get("/api/users");
	
		  int statusCode = response.getStatusCode();
		  test.log(Status.INFO, "Asserting the response if the status code returned is 200");
		  log.info("Asserting the response if the status code returned is 200");
		  Assert.assertEquals(statusCode,200, "Expected Status Code 200 but get " + statusCode);
		  String responseBody = response.getBody().asString();
	
		  test.pass("Fetched Items successfully");  
		  log.info("Fetched Items successfully");
		  Assert.assertTrue(true);
	  }
	  catch (Exception e) {
		// TODO: handle exception
		  log.info(e.getLocalizedMessage());
	}
  }
}
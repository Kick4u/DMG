package RestAssured;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Base.BaseClass;
import Entities.ToDoItem;
import Entities.ToDoItemEmp;
import io.restassured.response.Response;
//import utility.AllureLogger;
import utility.ExcelLib;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import java.util.HashMap;

public class DeleteTODO extends BaseClass
{
  public static String newID = "";
	

  @DataProvider (name="DataFromExcel") 
  public Object[][] data() throws Exception 
  { 
	  ExcelLib xl = new ExcelLib("DeleteTODO", this.getClass().getSimpleName()); 
	  return xl.getTestdata(); 
  }
 
		
  @Test(priority=1, dataProvider="DataFromExcel", description="Create and Delete ToDo") 
  public void CreateTempTODO(String first_name, String email, String dontUseThis) 
  {
	  try
	  {
		  test = extent.createTest("Delete ToDo Item");
		  test.log(Status.INFO, "Starting the test to Delete ToDo Item");
		  test.log(Status.INFO, "Temporary creates new ToDo Item");
			
		  ToDoItemEmp todo = new ToDoItemEmp();
		  todo.setFirst_Name(first_name);
		  todo.setEmail(email);
		  
		  Response response = 			  
		  given(). 
		  spec(requestSpec).
		  contentType("application/json"). body(todo).log().body(). 
		  
		  when().
		  post("/api/users");
		  
		  int statusCode = response.getStatusCode();
		  Assert.assertEquals(statusCode,201, "Unable to Create. Status code " + statusCode);
	
			
		  test.log(Status.INFO, "Asserting the response if the status code returned is 201");
		  
		  newID =response.then().extract().path("id").toString();
	
	
		  test.log(Status.INFO, "Retrieved TODO id : " + newID);
	  }
	  catch (Exception e) 
	  {
		  log.info(e.getLocalizedMessage());
	  }
  }
  
	@Test(priority=2, dependsOnMethods= {"CreateTempTODO"})
	void DeleteTODO()
	{	
		try
		{
				test.log(Status.INFO, "Deleting newly retrieved TODO id : " + newID);
				  
				  Response response = given(). 
						  header("Content-Type", "application/json").
						  //header("Cookie", cookieValue). 
						  spec(requestSpec). 
						  pathParam("id", newID).
				  when(). 
				  		delete("/api/users/{id}");
				  
				  		  
				  int statusCode = response.getStatusCode();
				  Assert.assertEquals(statusCode,204, "Unable to Delete. Status code " +
				  statusCode);
				  
				  test.log(Status.INFO, "Asserting the response if the status code returned is 204");
				 
				  Response responseFind = 
						  given(). 
							  header("Content-Type", "application/json").
							  pathParam("id", newID). 
							  spec(requestSpec).  
						  when(). 
							  get("/api/users/{id}");
				  
				  System.out.println("Record Checking"); 
				  int statusCodeFind = responseFind.getStatusCode(); 
				  Assert.assertEquals(statusCodeFind,404, "Checking for Record existance. Status code " + statusCodeFind);
				  
				  test.log(Status.INFO, "Checking for Record existance. and Asserting the response if the status code returned is 404");

		}
		catch (Exception e) {
			// TODO: handle exception
			  log.info(e.getLocalizedMessage());
		}
}	  	
}

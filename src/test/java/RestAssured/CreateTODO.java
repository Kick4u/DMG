package RestAssured;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class CreateTODO extends BaseClass

{
  public static String newID = "";
	

  @DataProvider (name="DataFromExcel") 
  public Object[][] data() throws Exception 
  { 	  
	  ExcelLib xl = new ExcelLib("CreateTODO", this.getClass().getSimpleName()); 
	  return xl.getTestdata(); 
  }
 
		
  @Test(dataProvider="DataFromExcel", description="Create New ToDo") 
  public void CreateTODO(String first_name, String email, String dontUseThis) 
  {
	try
	{
		  test = extent.createTest("Create New ToDo");
		  test.log(Status.INFO, "Starting the test to create new ToDo");
			
	
		  ToDoItemEmp todo = new ToDoItemEmp();
		  todo.setFirst_Name(first_name);
		  todo.setEmail(email);
		  //todo.setComplete(complete.equalsIgnoreCase("true"));
		  
		  Response response = 
				  
		  given(). 
		  spec(requestSpec).
		  contentType("application/json"). 
		  body(todo).log().body().
		  
		  when().
		  post("/api/users");
		  
		  int statusCode = response.getStatusCode();
		  Assert.assertEquals(statusCode,201, "Unable to Create. Status code " + statusCode);					  
		  
		  test.log(Status.INFO, "Asserting the response if the status code returned is 201");
		  newID =response.then().extract().path("id").toString();
		  test.log(Status.INFO, "Created successfully with new ID: " + newID);
		}
	catch (Exception e) 
	{
		// TODO: handle exception
		log.info(e.getLocalizedMessage());
	}
  }
}

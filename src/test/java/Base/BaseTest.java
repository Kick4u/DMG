package Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
/*import utility.AllureLogger;*/
import utility.FrameworkUtility;

public abstract class BaseTest extends FrameworkUtility
{
	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;

	@BeforeSuite
	public void setBaseURI() {
		
        //AllureLogger.logToAllure("The base URI is : "+readConfigurationFile("Base_URI"));
		requestSpec = new RequestSpecBuilder().
						setBaseUri(readConfigurationFile("Auth_URI")).
                		build();
	}
	
	

	/*****************************************************************************************************************/
//	@AfterSuite
	public void afterSuite() {

	}
	
	/****************************************************************************************************************/
//	@BeforeClass
	public void beforeClass() {
	}
	
	/****************************************************************************************************************/	
//	@AfterClass
	public void afterClass(){

	}
	
	/************************************************************************************************************************/
	@BeforeMethod
	public void beforeMethod() {
    	responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();        
	}

//	@AfterMethod
	public void afterMethod() {

	}
}

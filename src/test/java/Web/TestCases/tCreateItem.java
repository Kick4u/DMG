package Web.TestCases;

import Base.BaseClass;
import Web.PageObjects.CreateItem;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class tCreateItem extends BaseClass

{
	/*
	 * protected static Properties properties; public static WebDriver driver;
	 */
	
	@Test(priority=1)
	public void CreateItemTest()
	{
		try
		{	
			
		  String weburl = readConfigurationFile("WEB_URL");
		  WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		  driver.get(weburl);
			 	  
			
			test = extent.createTest("Create new todo item from frontend ");
			
			CreateItem t = new CreateItem(driver);
			t.clickCreateItem();
			
			Date d = new Date();
			t.setTitle("Review Meeting at " + d.toString());
			
			t.selectDropdown(1);
			
			t.clickSubmit();
			
			test.log(Status.INFO, "Created new todo item from frontend");
			log.info("Created new todo item from frontend");

			//driver.close();
		}
		catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
	}
}

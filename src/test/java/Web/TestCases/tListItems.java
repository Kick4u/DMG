package Web.TestCases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Base.BaseClass;
import Web.PageObjects.ListItems;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.Status;

public class tListItems extends BaseClass
{
	@Test(priority=1)
	public void ListItems()
	{
		try
		{				  
			test = extent.createTest("Listing all todo item from frontend ");
			
			ListItems t = new ListItems(driver);
			t.tblInfo();
			
			test.log(Status.INFO, "Listing all todo item from frontend");
			log.info("Listing all todo item from frontend");	

			driver.close();
		}
		catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
	}
}

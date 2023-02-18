package Web.TestCases;

import Base.BaseClass;
import Web.PageObjects.DeleteItem;
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

public class tDeleteItem extends BaseClass
{
	
	@Test(priority=1)
	public void DeleteItemTest()
	{
		try
		{	
			test = extent.createTest("Deleting todo item from frontend ");

			System.out.println("Item deleting");
			DeleteItem t = new DeleteItem(driver);
			t.clickDelete();
			t.clickSubmit();
			
			test.log(Status.INFO, "Deleted todo item from frontend");
			log.info("Deleted todo item from frontend");		
			
			//driver.close();
		}
		catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
	}
}

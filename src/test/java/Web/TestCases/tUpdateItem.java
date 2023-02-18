package Web.TestCases;
import org.testng.annotations.Test;
import Base.BaseClass;
import Web.PageObjects.UpdateItem;
import com.aventstack.extentreports.Status;
public class tUpdateItem extends BaseClass
{
	@Test(priority=1)
	public void UpdateItemTest()
	{
		try
		{	
			test = extent.createTest("Updating todo item from frontend ");
			
			System.out.println("Item deleting");
			UpdateItem t = new UpdateItem(driver);
			t.clickEdit();
			t.selectDropdown(2);
			t.clickSubmit();
			
			test.log(Status.INFO, "Updated todo item from frontend");
			log.info("Updated todo item from frontend");
			//driver.close();
		}
		catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
	}
}

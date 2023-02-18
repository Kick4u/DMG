package Web.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UpdateItem 
{
	WebDriver driver;
	
	public UpdateItem(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr[2]/td[3]//a[contains(text(),'Edit')]")	
	WebElement btnEdit;
	
	// Select Completeness
	@FindBy(xpath="//*[@id=\"complete\"]")
	WebElement ddlComplete;	

	@FindBy(xpath="//input[@type='submit']") 
	WebElement btnSubmit;			
	
	public void clickEdit()
	{
		btnEdit.click();
	}
	
	public void selectDropdown(int complete)
	{
		WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"complete\"]"));
		Select select = new Select(ddlComplete);
		select.selectByIndex(complete);
	}	
	
	public void clickSubmit()
	{
		btnSubmit.click();
	}			
	
	
}

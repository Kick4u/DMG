package Web.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class CreateItem
{
	WebDriver driver;
	
	public CreateItem(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Enter New Todo Item
	@FindBy(xpath="//a[contains(text(),\"Create New\")]")	
	WebElement createNewItem;
	
	@FindBy(xpath="//input[@name='title']") 
	WebElement txtTitle;
	
	// Select Completeness
	@FindBy(xpath="//*[@id=\"complete\"]")
	WebElement ddlComplete;
	
	@FindBy(xpath="//input[@type='submit']") 
	WebElement btnSubmit;	
	
	public void selectDropdown(int complete)
	{
		WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"complete\"]"));
		Select select = new Select(ddlComplete);
		select.selectByIndex(complete);
	}
	
	public void clickCreateItem()
	{
		createNewItem.click();
	}
	
	public void setTitle(String title)
	{	
		txtTitle.sendKeys(title);
	}	
	
	public void clickSubmit()
	{
		btnSubmit.click();
	}	
}

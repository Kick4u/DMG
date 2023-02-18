package Web.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.BaseClass;

public class DeleteItem
{
	WebDriver driver;
	
	public DeleteItem(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr[2]/td[3]//a[contains(text(),'Delete')]")	
	WebElement btnDelete;
		
	@FindBy(xpath="//input[@type='submit']") 
	WebElement btnSubmit;		
	
	
	public void clickDelete()
	{
		System.out.println("Calculating Total Todo Items");
		//System.out.println("Total Todo Items " + noOfRows);
		btnDelete.click();
	}	
	
	public void clickSubmit()
	{
		btnSubmit.click();
	}		
}

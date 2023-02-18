package Web.PageObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import DBTesting.MSSQL;

public class ListItems
{
	WebDriver driver;
	
	public ListItems(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> noOfRows;
	
	@FindBy(xpath="//table/tbody/tr/td[1]")
	List<WebElement> allItems;	
	

	
	public void tblInfo() throws ClassNotFoundException, SQLException
	{
		//Row Count
		System.out.println(noOfRows.size());

		// List of Todo Items		
		Boolean dataStatus = false;
		for(WebElement ele:allItems)
		{
			String value = ele.getText();
			System.out.println(value);
		}
		System.out.println("Total Records count from Frontend Table " + noOfRows.size());
		
		MSSQL obj = new MSSQL();		
		ResultSet rs= obj.CheckTodoItemsMSSQL();
		int size = 0;
		while(rs.next()) 
		{
			System.out.println(rs.getString("title") + "   " + rs.getString("complete")); 
			size++;
		}

		//Check for Both Records have same count
		System.out.println("Total Records count from DB " + size);

		rs= obj.CheckTodoItemsMSSQL();
		//Check First column of the Todo Items with Database
		System.out.println("Ticking");
		for(WebElement ele:allItems)
		{
			rs.next();
			System.out.println(ele.getText());
			System.out.println(rs.getString("title"));
			
			if(ele.getText().equals(rs.getString("title")))
			{
				System.out.println(rs.getString("title") + " and  " + ele.getText()); 
			}
			else
			{
				System.out.println("Front end data is not matching with database");
			}
		}		
	}
}

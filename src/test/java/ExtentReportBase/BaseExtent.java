package ExtentReportBase;

import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BaseExtent 
{
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setup()
	{
		Date d = new Date();
		String fileName = ".\\reports\\Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		
		htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);        
		
		extent = new  ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Division", "Sai Kishore");
        extent.setSystemInfo("Organization", "DMG Block Chain");
        extent.setSystemInfo("Build no", "W2A-0003");
		extent.attachReporter(htmlReporter);
	}
	
	  @AfterMethod 
	  public void updateResults(ITestResult result) 
	  {
		  if(result.getStatus()==ITestResult.SUCCESS) {
		  
		  String methodName = result.getMethod().getMethodName(); Markup m =
		  MarkupHelper.createLabel(methodName.toUpperCase()+" -PASS",
		  ExtentColor.GREEN); test.pass(m);
		  
		  }else if(result.getStatus()==ITestResult.FAILURE) {
		  
		  String methodName = result.getMethod().getMethodName(); Markup m =
		  MarkupHelper.createLabel(methodName.toUpperCase()+" -FAILED",
		  ExtentColor.RED); test.fail(m);
		  
		  }else if(result.getStatus()==ITestResult.SKIP) {
		  
		  String methodName = result.getMethod().getMethodName(); Markup m =
		  MarkupHelper.createLabel(methodName.toUpperCase()+" -SKIPPED",
		  ExtentColor.AMBER); test.skip(m);	  
		  }
	  }
	  
	  @AfterSuite
	  public void tearDown()
	  {
		  extent.flush();
	  }
}

package AuditLog;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class AuditLogTestCases extends BaseClass {

	public WebDriver driver;
	@BeforeMethod
	public void invokeBrowser() throws IOException, InterruptedException
	{
		// Start Chromedriver
		driver = initializeDriver();
	}

	@Test(priority = 1)
	public void resetFields() throws IOException, InterruptedException {
		auditLog.resetButton();
		
	}
	@Test(priority = 2)
	public void submitWithoutMandatoryFields() throws IOException, InterruptedException {
		auditLog.mandatoryFields();
		
	}
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
}

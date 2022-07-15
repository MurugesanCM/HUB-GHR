package action_Requests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class ActionRequets_MY_REQUESTS_Filter extends BaseClass {

	public WebDriver driver;
	@BeforeMethod
	public void invokeBrowser() throws IOException, InterruptedException
	{
		// Start Chromedriver
		driver = initializeDriver();
	}

	@Test(priority = 1)
	public void filterByOpenStatus() throws IOException, InterruptedException {
		actionRequest.FilterWithStatus("MY REQUESTS","Open");
		driver.quit();
	}
	@Test(priority = 2)
	public void filterByRejectedStatus() throws IOException, InterruptedException {
		actionRequest.FilterWithStatus("MY REQUESTS","Rejected");
		driver.quit();
	}
	@Test(priority = 3)
	public void filterByCancelledStatus() throws IOException, InterruptedException {
		actionRequest.FilterWithStatus("MY REQUESTS","Cancelled");

	}
	@Test(priority = 4)
	public void filterByApprovedStatus() throws IOException, InterruptedException {

		actionRequest.FilterWithStatus("MY REQUESTS","Approved");
	}
	@AfterMethod
	public void Quit()
	{
		driver.quit();
	}
}

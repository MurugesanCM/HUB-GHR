package action_Requests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Cancel_Record_In_My_Requests extends BaseClass{
	@BeforeMethod
	public void invokeBrowser() throws IOException, InterruptedException
	{
	// Start Chromedriver
	driver = initializeDriver();
	}
	@Test 
	public void cancelRecord() throws InterruptedException
	{
		actionRequest.CancelRecord("MY REQUESTS","Cancelled");
	}

}

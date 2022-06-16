package EHUB.GHR_CreateEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Delete_Multiple_Drafts extends BaseClass {

	public WebDriver driver;

	@Test(priority = 2)
	public void saveDraft() throws IOException, InterruptedException {
		//select the drafts to delete
		for(int i=1;i<5;i++)
		{
		hubhome.clickonselectDrafts(i).click();
		hubhome.selectValueFromFilter("Test Draft"+i).click();
		}
		hubhome.clickOnTickButton("TickButtondeleteDraft").click();
	    //capture the popup and evaluate
		String popup = neosuite.popUp().getText();
		Assert.assertEquals(popup, "Deleted successfully");
		driver.close();
	}
	@Test(priority = 1)
	public void deleteDraftClearAll() throws IOException, InterruptedException
	{// Start Chromedriver
		driver = initializeDriver();
		// Enter URL
		login.URL("UAT");
		// Type User name,Password and click on login
        login.login();
		// Switch to GHR Role
		wait.until(ExpectedConditions.visibilityOf(neosuite.OpenEhubApplication()));
		login.switchToGHRRole();
		wait.until(ExpectedConditions.stalenessOf(neosuite.OpenEhubApplication()));
		// Open EHUB Application
		neosuite.OpenEhubApplication().click();
		// Wait for the visibility of the Employee Creation ICON.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		hubhome.ehubIcon().click();
		hubhome.ClickOnForm("HIRE").click();
		//Click on delete draft from the popup window
		hubhome.deleteDraft().click();
		//select the drafts to delete
		for(int i=1;i<5;i++)
		{
		hubhome.clickonselectDrafts(i).click();
		hubhome.selectValueFromFilter("Test Draft"+i).click();
		}
		Actions action = new Actions(driver);
		action.moveToElement(hubhome.clickOnClearAll());
		hubhome.clickOnClearAll().click();
		try {
			hubhome.clickOnClearAll();
			Assert.fail("Test Case failed - Clear All not working");
		}
		catch(Exception e) {Assert.assertTrue(true,"Test Case Passed - Clear All button working");}
	}
}

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

	@Test(priority = 1)
	public void deleteDraftClearAll() throws IOException, InterruptedException {// Start Chromedriver
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
		login.changeWaitTime(3);
		try {
			// Click on delete draft from the popup window
			hubhome.deleteDraft().click();
		} catch (Exception e) {
			Assert.fail("No Draft to delete");
		}
		login.changeWaitTime(30);
		// select the drafts to delete
		for (int i = 1; i < 5; i++) {
			hubhome.clickonselectDrafts(i).click();
			hubhome.selectValueFromFilter("Test Draft" + i).click();
		}
		Actions action = new Actions(driver);
		action.moveToElement(hubhome.clickOnClearAll());
		hubhome.clickOnClearAll().click();
		login.changeWaitTime(3);
		try {
			hubhome.clickOnClearAll();
			Assert.fail("Test Case failed - Clear All not working");
		} catch (Exception e) {
			Assert.assertTrue(true, "Test Case Passed - Clear All button working");
		}
		login.changeWaitTime(30);
	}

	@Test(priority = 2)
	public void deleteDrafts() throws IOException, InterruptedException {
		// select the drafts to delete
		// Using Try catch since there is a possibility of clear all button not working.
		login.changeWaitTime(5);
		try {
			for (int i = 1; i < 5; i++) {
				hubhome.clickonselectDrafts(i).click();
				hubhome.selectValueFromFilter("Test Draft" + i).click();
			}
		} catch (Exception e) {
			// capture the popup and evaluate
			hubhome.clickOnTickButton("TickButtondeleteDraft").click();
			String popup = neosuite.popUp().getText();
			Assert.assertEquals(popup, "Deleted successfully");
			wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
		}
		driver.close();
	}
}

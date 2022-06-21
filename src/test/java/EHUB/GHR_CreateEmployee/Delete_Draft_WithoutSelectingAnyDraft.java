package EHUB.GHR_CreateEmployee;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;
@Test
public class Delete_Draft_WithoutSelectingAnyDraft extends BaseClass {

	public WebDriver driver;

	@Test
	public void delete_Draft_WithoutSelectingAnyDraft() throws IOException, InterruptedException {
		// Start Chromedriver
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
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on the employee creation ICON
		hubhome.ehubIcon().click();
		// Click on HIRE Form
		hubhome.ClickOnForm("HIRE").click();
		//Click on delete draft from the popup window
		hubhome.deleteDraft().click();
		//select the drafts to delete
		hubhome.clickOnTickButton("TickButtondeleteDraft").click();
	    //capture the popup and evaluate
		String popup = neosuite.popUp().getText();
		Assert.assertEquals(popup, "Select Drafts for Deletion","Test Case Failed-Wrong popup when we delete without selecting any drafts");
		driver.close();
	}
	
}

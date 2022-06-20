package EHUB.GHR_CreateEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;
@Test
public class DeleteDraftCloseBtn extends BaseClass {
	public WebDriver driver;

	@Test
	public void deletDraftCloseBtn() throws IOException, InterruptedException {
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		hubhome.ehubIcon().click();
		// Click on HIRE Form
		hubhome.ClickOnForm("HIRE").click();
		wait.until(ExpectedConditions.visibilityOf(hubhome.deleteDraft()));
		//Click on delete draft from the pop up window
		hubhome.deleteDraft().click();
		wait.until(ExpectedConditions.visibilityOf(hubhome.closedraftpopup("deleteDraftModal")));
		hubhome.closedraftpopup("deleteDraftModal").click();
		Assert.assertTrue(hubhome.closedraftpopup("draftViewModal").isDisplayed(),"Close Btn in deletedraft window is not working");
		driver.close();
}
}

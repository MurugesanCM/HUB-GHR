package EHUB.GHR_ViewEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class ViewEmployee_BackToEventForms extends BaseClass {

	public WebDriver driver;

	@Test
	public void backToForm() throws IOException, InterruptedException {
		// Start Chromedriver
		driver=initializeDriver();
		//Enter URL
		login.URL("UAT");
		//Type User name,Password and click on login
		login.login();
		//Switch to GHR Role
		wait.until(ExpectedConditions.visibilityOf(neosuite.OpenEhubApplication()));
		login.switchToGHRRole();
		wait.until(ExpectedConditions.stalenessOf(neosuite.OpenEhubApplication()));
		//Open EHUB Application
		neosuite.OpenEhubApplication().click();
		//Wait for the visibility of the Employee Creation ICON.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		//Wait for the widget to load fully
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		//switch to Employee List View Widget
		hubhome.selectWidget("Employee List View").click();
		wait.until(ExpectedConditions.visibilityOf(hubhome.ScrollButton()));
		String colcount = hubhome.getcolCount();
		//Click on View Record
		hubhome.clickOnViewRecord("2",colcount).click();
		hubhome.ClickOnForm("Job Data").click();
		wait.until(ExpectedConditions.visibilityOf(hubhome.selectfield("Job Type")));
		//Click on back button
		hubhome.backButtoninEventForm().click();
		// Verify whether the functionality is working.
		Assert.assertTrue(hubhome.ClickOnForm("Job Data").isDisplayed(), "Back to events forms screen not working");
		driver.quit();
	}
}

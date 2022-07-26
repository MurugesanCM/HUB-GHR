package EHUB.GHR_ViewEmployee;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Editandupdate_ViewEmployee_OnetoManySection extends BaseClass {
	public WebDriver driver;

	@Test
	public void editandupdateonetomanysection() throws IOException, InterruptedException, ParseException {
		driver = initializeDriver();
		login.URL("UAT");
		// Type User name,Password and click on login
		login.loginWithParameter("AT0006", "Neeyamo@123");
		// Switch to GHR Role
		wait.until(ExpectedConditions.visibilityOf(neosuite.OpenEhubApplication()));
		login.switchToGHRRole();
		wait.until(ExpectedConditions.stalenessOf(neosuite.OpenEhubApplication()));
		// Open EHUB Application
		neosuite.OpenEhubApplication().click();
		// Wait for the visibility of the Employee Creation ICON.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Wait for the widget to load fully
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// switch to Employee List View Widget
		hubhome.selectWidget("Employee List View").click();
		wait.until(ExpectedConditions.visibilityOf(hubhome.ScrollButton()));
		String colcount = hubhome.getcolCount();
		// Click on View Record
		hubhome.clickOnViewRecord("2", colcount).click();
		// Click on Job Data Form
		hubhome.ClickOnForm("Rehire").click();
		hubhome.clickOnTab("Education").click();
		// Click on Edit Form
		hubhome.clickOnEditButton().click();
		hubhome.addSection().click();
		//wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
		login.changeWaitTime(5);
		try {
		boolean condition = hubhome.selectfield("Qualification").isDisplayed();
		Assert.assertTrue(condition, "add button in one to many section working");}
		catch(Exception e)
		{
			Assert.fail("Test Case Failed - Add button in one to many section not working");
		}
login.changeWaitTime(30);
		driver.quit();
	}

}

package EHUB.GHR_CreateEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class CreateEmployee_WithduplicateglobalID_proceed extends BaseClass {

	public WebDriver driver;

	@Test
	public void createEmployee() throws IOException, InterruptedException {
		// Start Chromedriver
		driver = initializeDriver();
		// Get the necessary values from properties File
		String country = "India";
		String legalEntity = "Neeyamo Enterprise Solutions";
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
		login.changeWaitTime(3);
		try{hubhome.clickOnCountryFilter();}
		catch(Exception e)
		{hubhome.CreateNewDraft().click();}
		login.changeWaitTime(30);
		// Select the Country
		hubhome.clickOnCountryFilter().sendKeys(country);
		hubhome.selectValueFromFilter(country).click();
		// Select the legal entity
		hubhome.clickOnlegalEntityFilter().sendKeys(legalEntity);
		hubhome.selectValueFromFilter(legalEntity).click();
		// Click on Tick button to filter the selection.
		hubhome.clickOnTickButton("TickButton").click();
		// Click on Edit Form
		hubhome.clickOnEditButton().click();
		// Fill all the mandatory fields
		hubhome.selectfield("Preferred Name").sendKeys("Murugesan");
		hubhome.selectfield("Title").click();
		hubhome.selectDrpdwnValue("Mr").click();
		hubhome.selectfield("Employee Global ID").sendKeys("ET111087");
		// Click on save button
	    hubhome.saveButton().click();
		// Verify whether the functionality is working.
		String popup = neosuite.popUp().getText();
		Assert.assertEquals(popup, "Duplicate record found","Duplicate record found message is not popped up");
		wait.until(ExpectedConditions.elementToBeClickable(hubhome.ProceedButton()));
		hubhome.ProceedButton().click();
		wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
        hubhome.tabList().click();
		hubhome.clickOnTab("National ID").click();
		hubhome.addSection().click();
		hubhome.selectfield("National ID").sendKeys("ET111087");
		hubhome.tabList().click();
		hubhome.clickOnTab("Employee Information").click();
		hubhome.saveButton().click();
		popup = neosuite.popUp().getText();
		Assert.assertEquals(popup, "Saved successfully","Duplicate record is not updated");
		driver.quit();
	}
}

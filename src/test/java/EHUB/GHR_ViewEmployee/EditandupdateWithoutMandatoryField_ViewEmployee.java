package EHUB.GHR_ViewEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class EditandupdateWithoutMandatoryField_ViewEmployee extends BaseClass {
	public WebDriver driver;

	@Test
	public void editandupdate() throws IOException, InterruptedException {
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
		hubhome.ClickOnForm("Personal Data 2").click();
		// Click on Edit Form
		hubhome.clickOnEditButton().click();
		// wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
		hubhome.selectfield("Last Name").click();
		while (!hubhome.selectfield("Last Name").getAttribute("value").equals("")) {
			hubhome.selectfield("Last Name").sendKeys(Keys.BACK_SPACE);
		}
		hubhome.selectfield("Middle Name").clear();
		hubhome.selectfield("Middle Name").sendKeys("Bell");
		hubhome.selectfield("Gender").sendKeys("Male");
		hubhome.selectDrpdwnValue("Male").click();
		// Enter Preferred name
		/*
		 * hubhome.selectfield("Operational Entity(1)").sendKeys("Operations");
		 * hubhome.selectDrpdwnValue("Operations").click();
		 * hubhome.selectfield("Division (2)").sendKeys("IT");
		 * hubhome.selectDrpdwnValue("IT").click();
		 * hubhome.selectfield("Department (3)").sendKeys("Management");
		 * Thread.sleep(3000); hubhome.selectDrpdwnValue("Management").click();
		 * hubhome.selectfield("Contract Type").sendKeys("Permanent");
		 * hubhome.selectDrpdwnValue("Permanent").click();
		 * hubhome.selectfield("Employee Type").sendKeys("Employee Agent");
		 * hubhome.selectDrpdwnValue("Employee Agent").click();
		 * hubhome.selectfield("Transfer Reason").sendKeys("None of your Business");
		 * //employeelistview.AdvanceFilterByDoj("7-26-2022",hubhome.
		 * selectfield("Effective From Date"));
		 * hubhome.selectfield("Billing Code").sendKeys("L1 Support Agent / Remote AR");
		 * //Thread.sleep(4000);
		 * hubhome.selectDrpdwnValue("L1 Support Agent / Remote AR").click();
		 * hubhome.selectfield("Project").sendKeys("Coco");
		 * hubhome.selectDrpdwnValue("Coco").click();
		 * hubhome.selectfield("Employee Work Location").sendKeys("India");
		 * hubhome.selectDrpdwnValue("India").click();
		 * hubhome.selectfield("Verint/Non Verint?").sendKeys("Schedule on Verint");
		 * hubhome.selectDrpdwnValue("Schedule on Verint").click();
		 */
		hubhome.saveButton().click();
		// hubhome.YesSaveChanges().click();
		String popup = neosuite.popUp().getText();
		Assert.assertTrue(popup.contains("required"),
				"Test case failed form is getting saved without filling mandatory fields");
		driver.quit();
	}

}

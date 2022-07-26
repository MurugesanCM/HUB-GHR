package EHUB.GHR_ViewEmployee;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class View_Record_Particular_Employee extends BaseClass {

	public WebDriver driver;

	@Test
	public void backToForm() throws IOException, InterruptedException {
		String UserName = "AT0006";
		// Start Chromedriver
		driver=initializeDriver();
		//Enter URL
		login.URL("UAT");
		//Type User name,Password and click on login
		login.loginWithParameter(UserName,"Neeyamo@123");
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
		employeelistview.UniversalSearch(UserName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='View']")));
		//Click on View Record
		//List <WebElement> records = driver.findElements(
				//By.xpath("//button[@title='View']//parent::span//parent::span//parent::td//parent::tr"));
		driver.findElement(By.xpath("//button[@title='View']")).click();
		Assert.assertTrue(hubhome.ClickOnForm("Rehire").isDisplayed(), "Test Case Failed - View Button not working");
		hubhome.ClickOnForm("Rehire").click();
		wait.until(ExpectedConditions.visibilityOf(hubhome.selectfield("Employee Global ID")));
		String EmployeeID = hubhome.selectfield("Employee Global ID").getAttribute("value");
		//Click on back button
		// Verify whether the functionality is working.
		Assert.assertTrue(UserName.equals(EmployeeID),"Test Case Failed its showing the wrong record");
		driver.quit();
	}
}

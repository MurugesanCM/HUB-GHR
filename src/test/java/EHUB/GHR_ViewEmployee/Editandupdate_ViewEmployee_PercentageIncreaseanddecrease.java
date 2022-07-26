package EHUB.GHR_ViewEmployee;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Editandupdate_ViewEmployee_PercentageIncreaseanddecrease extends BaseClass {
	public WebDriver driver;
	String requiredString1;
	String requiredString2;
	String requiredString3;
	@Test
	public void editandupdateProgressBarIncrease() throws IOException, InterruptedException, ParseException {
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
		hubhome.ClickOnForm("Personal Data").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='progressbar'][@style]"))));
		String s1=driver.findElement(By.xpath("//div[@role='progressbar'][@style]")).getAttribute("style");
		System.out.println(s1);
		requiredString1 = s1.substring(s1.indexOf(": ") + 2, s1.indexOf("%"));
		System.out.println(requiredString1);
		hubhome.clickOnEditButton().click();
		hubhome.selectfield("Middle Name").sendKeys("Murugesan");
		hubhome.selectfield("Last Name").click();
		Thread.sleep(5000);
		String s2=driver.findElement(By.xpath("//div[@role='progressbar'][@style]")).getAttribute("style");
		System.out.println(s2);
		requiredString2 = s2.substring(s2.indexOf(":") + 2, s2.indexOf("%"));
		System.out.println(requiredString2);
		Assert.assertTrue(Integer.parseInt(requiredString1)<Integer.parseInt(requiredString2),"Test Case Failed Progress Bar is not increased");
		//hubhome.clickOnTab("Education").click();
		// Click on Edit Form
		//wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
		//driver.quit();
	}
	@Test(priority = 2)
	public void editandupdateProgressBardecrease() throws IOException, InterruptedException, ParseException {
		hubhome.selectfield("Middle Name").click();
		while (!hubhome.selectfield("Middle Name").getAttribute("value").equals("")) {
			hubhome.selectfield("Middle Name").sendKeys(Keys.BACK_SPACE);
		}
		hubhome.clickOnTab("PAYROLL").click();
		Thread.sleep(5000);
		String s3=driver.findElement(By.xpath("//div[@role='progressbar'][@style]")).getAttribute("style");
		System.out.println(s3);
		requiredString3 = s3.substring(s3.indexOf(":") + 2, s3.indexOf("%"));
		System.out.println(requiredString3);
		Assert.assertTrue(Integer.parseInt(requiredString1)==Integer.parseInt(requiredString3),"Test Case Failed Progress Bar is not decreased");
	}
	
	
	}



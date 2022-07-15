package EHUB.GHR_ViewEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Editandupdate_ViewEmployee extends BaseClass {
	public WebDriver driver;
	@Test
public void editandupdate() throws IOException, InterruptedException
{					
	//Start Chromedriver
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
	//Click on Job Data Form
	hubhome.ClickOnForm("Job Data").click();
	//Click on Edit Form
	hubhome.clickOnEditButton().click();
	//Enter Preferred name
	hubhome.selectfield("Designation/Job Title").click();
	hubhome.selectfield("Designation/Job Title").sendKeys("Business Analyst");
	hubhome.selectDrpdwnValue("Business Analyst").click();
	String initialValue = hubhome.selectfield("Designation/Job Title").findElement(By.xpath("//parent::div//div[contains(text(),' Business Analyst ')]")).getText();
	System.out.println(initialValue);
	hubhome.saveButton().click();
	String updatedValue = hubhome.selectfield("Designation/Job Title").findElement(By.xpath("//parent::div//div[contains(text(),' Business Analyst ')]")).getText();
	Assert.assertEquals(initialValue, updatedValue);
	//driver.quit();
}

}

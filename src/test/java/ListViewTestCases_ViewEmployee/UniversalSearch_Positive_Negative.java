package ListViewTestCases_ViewEmployee;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;
public class UniversalSearch_Positive_Negative extends BaseClass{
	public WebDriver driver;
@Test
public void SearchWithValidData() throws IOException, InterruptedException
{
	driver = initializeDriver();
	login.URL("UAT");
	login.login();
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
	String searchedData = employeelistview.UniversalSearch("NYM105276");
	Assert.assertEquals(searchedData, "NYM105276");
}
@Test
public void SearchWithInValidData() throws InterruptedException, IOException
{
	driver = initializeDriver();
	login.URL("UAT");
	login.login();
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
	String searchedData = employeelistview.UniversalSearch("NYM105276");
	Assert.assertEquals(searchedData, "NYM105276");
}
}

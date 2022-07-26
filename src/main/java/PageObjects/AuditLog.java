package PageObjects;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AuditLog {
	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	public LoginPage login;
	public NeosuiteHomePage neosuite;
	public generalFunctions generalFunction;
	public EHUBHome hubhome;
	public AuditLog(WebDriver driver, Properties prop, WebDriverWait wait) throws IOException {
		this.driver = driver;
		this.prop = prop;
		this.wait = wait;
		login = new LoginPage(driver, wait);
		neosuite = new NeosuiteHomePage(driver, wait);
		generalFunction = new generalFunctions(driver, prop);
		hubhome = new EHUBHome(driver, prop);
		
	}

	By getLogList = By.xpath("//div[.='Get Log List']");
	By ResetButton = By.xpath("//div[@id='btnResetData']");
	

	public WebElement selectField(String fieldName) throws InterruptedException {
		return driver.findElement(By.xpath("//div[*[.='"+fieldName+"']]//input"));
	}
	public WebElement selectDrpdwnValue(String drpdwnvalue) {
		// TODO Auto-generated method stub
		login.changeWaitTime(5);
		try
		{
		return driver.findElement(By.xpath("//div[@role='option'][.='"+ drpdwnvalue +"']"));
		}
				catch(Exception e)
		{
			return 	driver.findElement(By.xpath("//div[@role='option']//span[.='"+ drpdwnvalue +"']"));	
		}
	}
	public void resetButton() throws InterruptedException
	{
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
		hubhome.selectWidget("Audit Log").click();
		selectField("Country").sendKeys("India");
		selectDrpdwnValue("India").click();
		selectField("Business Unit").sendKeys("NW_IND");
		selectDrpdwnValue("NW_IND").click();
		login.changeWaitTime(3);
		driver.findElement(ResetButton).click();
		try {
			driver.findElement(By.xpath("//div[*[.='Country']]//input//parent::div//parent::div//parent::div[contains(@class,'ng-has-value')]"));
			Assert.fail("Test Case Failed - Reset Button not working");
		}
		catch(Exception e)
		{
			if(e.getMessage().contains("elementNotFoundException"))
			{
				Assert.assertTrue(true);
			}
		}
		
		
	}
	public void mandatoryFields() throws InterruptedException
	{
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
		hubhome.selectWidget("Audit Log").click();
		selectField("Country").sendKeys("India");
		selectDrpdwnValue("India").click();
		selectField("Business Unit").sendKeys("NW_IND");
		selectDrpdwnValue("NW_IND").click();
		login.changeWaitTime(3);
		driver.findElement(getLogList).click();
		String popup = neosuite.popUp().getText();
		Assert.assertEquals(popup, "Select Event","get loglist working without filling mandatory fields");
		
		
		
	}
	
}

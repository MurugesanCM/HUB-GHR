package EHUB.GHR_CreateEmployee;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;
@Test
public class Savedraft_multipletimes_UniqueName extends BaseClass {

	public WebDriver driver;
		@Test
	public void savedraft_multipletimes_UniqueName() throws IOException, InterruptedException
	{					
		//Start Chromedriver
		driver=initializeDriver();
		//Enter URL
		login.URL("UAT");
		// Type User name,Password and click on login
        login.login();
		//Switch to GHR Role
		wait.until(ExpectedConditions.visibilityOf(neosuite.OpenEhubApplication()));
		login.switchToGHRRole();
		wait.until(ExpectedConditions.stalenessOf(neosuite.OpenEhubApplication()));
		//Open EHUB Application
		neosuite.OpenEhubApplication().click();
		//Wait for the visibility of the Employee Creation ICON.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		//Click on the employee creation ICON
		hubhome.ehubIcon().click();
		//Click on HIRE Form
		hubhome.ClickOnForm("HIRE").click();
		login.changeWaitTime(3);
		try{
			hubhome.clickOnCountryFilter();
		Assert.fail("No Draft is available to open");
		}
		catch(Exception e)
		{}
		login.changeWaitTime(30);
		//Select the draft
		hubhome.clickonselectDraft().click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", hubhome.selectValueFromFilter("Murugesan"));
		hubhome.openFromDraft().click();
		//Click on Edit Form
		hubhome.clickOnEditButton().click();
		//Save Draft Flow
		hubhome.deHazeButton().click();
		hubhome.saveForLaterButton().click();
		wait.until(ExpectedConditions.elementToBeClickable(hubhome.enterDraftName()));
        hubhome.enterDraftName().clear();
		hubhome.enterDraftName().sendKeys("Test Draft1");
		wait.until(ExpectedConditions.visibilityOf(hubhome.clickOnTickButton("TickButtondraft")));
		hubhome.clickOnTickButton("TickButtondraft").click();
		//Verify whether the functionality is working.
	    String popup = neosuite.popUp().getText();
	    Assert.assertEquals(popup, "Draft Saved");
	    wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
	    driver.close();
	}
}

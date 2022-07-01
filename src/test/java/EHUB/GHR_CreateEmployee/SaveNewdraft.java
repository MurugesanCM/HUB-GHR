package EHUB.GHR_CreateEmployee;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;
@Test
public class SaveNewdraft extends BaseClass {

	public WebDriver driver;
		@Test
	public void saveNewDraft() throws IOException, InterruptedException
	{					
		//Start Chromedriver
		driver=initializeDriver();
		//Get the necessary values from properties File
		String country = "Mexico";
		String legalEntity = "5CA Mexico S. DE R.L.DE C.V.";	//Enter URL
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
		try{hubhome.clickOnCountryFilter();}
		catch(Exception e)
		{hubhome.CreateNewDraft().click();}
		login.changeWaitTime(30);
		//Select the Country 
		try {
		hubhome.clickOnCountryFilter().sendKeys(country);
		hubhome.selectValueFromFilter(country).click();
		//Select the legal entity
		hubhome.clickOnlegalEntityFilter().sendKeys(legalEntity);
		hubhome.selectValueFromFilter(legalEntity).click();}
		catch(Exception e) {Assert.fail("Create new draft button not working");}
		//Click on Tick button to filter the selection.
		hubhome.clickOnTickButton("TickButton").click();
		//Click on Edit Form
		hubhome.clickOnEditButton().click();
		//Fill all the mandatory fields
		hubhome.selectfield("Preferred Name").sendKeys("Murugesan");
		hubhome.selectfield("Title").click();
		hubhome.selectDrpdwnValue("Mr").click();
		//Save Draft Flow
		hubhome.deHazeButton().click();
		hubhome.saveForLaterButton().click();
		wait.until(ExpectedConditions.elementToBeClickable(hubhome.enterDraftName()));
       // hubhome.enterDraftName().click();
		hubhome.enterDraftName().sendKeys("Test Draft1");
		wait.until(ExpectedConditions.visibilityOf(hubhome.clickOnTickButton("TickButtondraft")));
		hubhome.clickOnTickButton("TickButtondraft").click();
		//Verify whether the functionality is working.
	    String popup = neosuite.popUp().getText();
	    Assert.assertEquals(popup, "Draft Saved");
	    wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
	    hubhome.ClickOnForm("HIRE").click();
	    hubhome.clickonselectDraft().click();
	    Assert.assertTrue(hubhome.selectValueFromFilter("Murugesan").isDisplayed(),"Created Draft not visible in drop down");
	    driver.close();
	}
}

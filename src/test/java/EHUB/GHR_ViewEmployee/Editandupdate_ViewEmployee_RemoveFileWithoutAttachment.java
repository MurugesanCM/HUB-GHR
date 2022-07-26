package EHUB.GHR_ViewEmployee;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Editandupdate_ViewEmployee_RemoveFileWithoutAttachment extends BaseClass {
	public WebDriver driver;
	String requiredString1;
	String requiredString2;
	String requiredString3;
	@Test
	public void editandupdateDocumentUploadRemoveWithoutAttachment() throws IOException, InterruptedException, ParseException {
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
		hubhome.clickOnEditButton().click();
		hubhome.SelectAttachmentForField("Date Of Birth").click();
		hubhome.viewDocument().click();
		hubhome.removeDocument().click();
		//hubhome.clickOnTab("Education").click();
		// Click on Edit Form
		String Popup = neosuite.popUp().getText();
		Assert.assertEquals(Popup, "File Not Found");
		//driver.quit();
	}
	
	
	}



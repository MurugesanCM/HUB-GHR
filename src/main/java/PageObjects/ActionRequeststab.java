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

public class ActionRequeststab {
	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	public LoginPage login;
	public NeosuiteHomePage neosuite;
	public generalFunctions generalFunction;
	public EHUBHome hubhome;

	public ActionRequeststab(WebDriver driver, Properties prop, WebDriverWait wait) throws IOException {
		this.driver = driver;
		this.prop = prop;
		this.wait = wait;
		login = new LoginPage(driver, wait);
		neosuite = new NeosuiteHomePage(driver, wait);
		generalFunction = new generalFunctions(driver, prop);
		hubhome = new EHUBHome(driver, prop);
	}

	By listViewUniversalSearch = By.xpath("//input[@placeholder='Search'][@name='universalListSearch']");
	By UniversalSearchIcon = By.xpath("//a[@class='searchField secondary-class downloadCircle']//i[.='search']");
	By ResetButton = By.xpath("//button[@title='Reset']");
	By AdvanceFilter = By.xpath("//*[@id=\"addWhite\"]/span");
	By ApplyButton = By.xpath("//button[.='Apply']");
	By preLoaderGif = By.xpath("//div[@id='preloderdiv']");
	By filterReset = By.xpath("//div[@id='oed_advance_filter']//button[.='Reset']");
	By Enablecomments = By.xpath("//label[@for='eventName']");
	By Comments = By.xpath("//input[@id='eventName']");
	By TickButton = By.xpath("//i[@id='saveModalIcon']");
	By ApproveButton = By.xpath("//span[.='Approve']");
	By RejectButton = By.xpath("//span[.='Reject']");

	public int ColumnFilter(String columnName) throws InterruptedException {
		login.changeWaitTime(30);
		ConfigColumn();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//span//p[contains(text(),'" + columnName + "')]"))));
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//span//p[contains(text(),'" + columnName + "')]"))));
		WebElement ConfigColumnSearch = driver
				.findElement(By.xpath("//input[@placeholder='Search'][@attr.name='filterSearchColumnName']"));
		ConfigColumnSearch.sendKeys(columnName);
		driver.findElement(By.xpath("//span//p[contains(text(),'" + columnName + "')]")).click();
		driver.findElement(By.xpath("//span[@title='Save']")).click();
		wait.until(ExpectedConditions.visibilityOf(neosuite.popUp()));
		wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
		login.changeWaitTime(5);
		return driver.findElements(By.xpath("//table//td//b[contains(text(),'" + columnName + "')]")).size();
	}

	public void ConfigColumn() {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//td//span//span//span"))));
		driver.findElement(By.xpath("//div[@id='configColumnsnormal']//a//i")).click();
	}

	public int ConfigureColumnSearch(String columnName) {
		ConfigColumn();
		WebElement ConfigColumnSearch = driver
				.findElement(By.xpath("//input[@placeholder='Search'][@attr.name='filterSearchColumnName']"));
		ConfigColumnSearch.sendKeys(columnName);
		List<WebElement> elements = driver.findElements(By.xpath("//li//label[input[@type]]//span//p"));
		boolean condition = elements.size() == 1 && elements.get(0).getText().equals(columnName);
		if (condition) {
			ConfigColumnSearch.clear();
			ConfigColumn();
			return 1;
		} else {
			ConfigColumnSearch.clear();
			ConfigColumn();
			return 0;
		}

	}

	public String UniversalSearch(String Input) throws InterruptedException {
		driver.findElement(listViewUniversalSearch).sendKeys(Input);
		driver.findElement(UniversalSearchIcon).click();
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='preloderdiv']"))));
		login.changeWaitTime(5);
		try {
			return driver.findElement(By.xpath("//span[@id='PexEmployeeListtableDataColumn00']//span")).getText();
		} catch (Exception e) {
			try {
				return driver.findElement(By.xpath("//*[@id=\"PexEmployeeListscrollId\"]/div/span")).getText();
			} catch (Exception e1) {
				login.changeWaitTime(30);
				return "";
			}
		}
	}

	public void selectAll() throws InterruptedException {
		// TODO Auto-generated method stub
		int fail = 1;
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"PexEmployeeListheaderCheckbox\"]//parent::label//span")));
		driver.findElement(By.xpath("//*[@id=\"PexEmployeeListheaderCheckbox\"]//parent::label//span")).click();
		wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//*[@id=\"PexEmployeeListheaderCheckbox\"]"),
				true));
		List<WebElement> checkBoxes = driver
				.findElements(By.xpath("//*[contains(@id,\"PexEmployeeListselectedRow\")]//input"));
		for (WebElement i : checkBoxes) {
			if (i.isEnabled() != true) {
				fail = 0;
				break;
			}
		}
		if (fail == 0) {
			Assert.fail("Select All Button Not Working Properly");
		} else {
			Assert.assertTrue(true);
		}

	}

	public void lvcReset() {
		// TODO Auto-generated method stub
		driver.findElement(ResetButton).click();

	}

	public void AdvanceFilter(String country, String legalEntity, String employeeStatus, String doj, int filterType,
			boolean ResetFilter) {
		// TODO Auto-generated method stub
		Actions action = new Actions(driver);
		if (filterType == 0 || filterType == 1 || filterType == 2) {
			action.moveToElement(driver.findElement(AdvanceFilter)).perform();
			driver.findElement(AdvanceFilter).click();
			driver.findElement(By.xpath("//div[.='Select Country']//div[@class='ng-input']//input")).sendKeys(country);
			driver.findElement(By.xpath("//span[.='" + country + "']//parent::div")).click();
			if (filterType == 1 || filterType == 2) {
				driver.findElement(By.xpath("//div[.='Select Businessunit Name']//div[@class='ng-input']//input"))
						.sendKeys(legalEntity);
				driver.findElement(By.xpath("//span[.='" + legalEntity + "']//parent::div")).click();
			}
		}
		if (filterType == 3) {
			action.moveToElement(driver.findElement(AdvanceFilter)).perform();
			driver.findElement(AdvanceFilter).click();
			driver.findElement(By.xpath("//div[.='Select Employee Status']//div[@class='ng-input']//input"))
					.sendKeys(employeeStatus);
			driver.findElement(By.xpath("//span[.='" + employeeStatus + "']//parent::div")).click();
		}
		if (filterType == 4) {
			action.moveToElement(driver.findElement(AdvanceFilter)).perform();
			driver.findElement(AdvanceFilter).click();
			AdvanceFilterByDoj(doj);

		}
		if (ResetFilter == false)
			driver.findElement(ApplyButton).click();
		else {
			driver.findElement(filterReset).click();
		}

	}

	public List<WebElement> getElementsWithStatus(String Value) {
		List<WebElement> elements = driver
				.findElements(By.xpath("//table//td//span//span//span[contains(text(),' " + Value + " ')]"));
		return elements;
	}

	public String[] getListViewCount() {
		// TODO Auto-generated method stub
		String Text = driver.findElement(By.xpath("//*[@id=\"collapsibleHeaderId\"]/div[4]/div[1]/div/span")).getText();
		String[] strSplit = Text.split(" ");
		System.out.println(Text);
		return strSplit;
	}

	public void CompareWithpageCount(String Value) {
		String[] Values = getListViewCount();
		int initialCount = Integer.valueOf(Values[1]);
		int totalCount = Integer.valueOf(Values[3]);
		int noOfpages;
		if ((totalCount % initialCount) > 0) {
			noOfpages = (totalCount / initialCount) + 1;
		} else
			noOfpages = (totalCount / initialCount);
//Check the count adding "Open" Records from all the pages
		int statusCount = 0;
		int finalcount = initialCount;
		login.changeWaitTime(10);
		for (int i = 0; i < noOfpages; i++) {
			try {
				driver.findElement(By.xpath("//text()[.=' > ']//parent::a")).click();
			} catch (Exception e) {
				break;
			}
			List<WebElement> status1 = getElementsWithStatus(Value);
			statusCount = status1.size();
			/*
			 * if(statusCount==0) {
			 * driver.findElement(By.xpath("//a//span[.='"+noOfpages+"']")).click(); status1
			 * = getElementsWithStatus(status); statusCount = status1.size(); }
			 */
			finalcount = statusCount + finalcount;
		}

		Assert.assertEquals(finalcount, totalCount);

	}

	public void clickonAdvanceFilter() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(AdvanceFilter)).perform();
		driver.findElement(AdvanceFilter).click();
	}

	public void AdvanceFilterByCountry(String country) {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//div[.='Select Country']//div[@class='ng-input']//input")).sendKeys(country);
		driver.findElement(By.xpath("//span[.='" + country + "']//parent::div")).click();
	}

	public void AdvanceFilterByLegalEntity(String legalEntity) {
		driver.findElement(By.xpath("//div[.='Select Businessunit Name']//div[@class='ng-input']//input"))
				.sendKeys(legalEntity);
		driver.findElement(By.xpath("//span[.='" + legalEntity + "']//parent::div")).click();
	}

	public void AdvanceFilterByDoj(String Doj) {
		driver.findElement(By.xpath("//div[.='Date of joining']//div//input")).click();
		String date_MM_dd_yyyy[] = Doj.split("/");
		String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		WebElement midLink = driver.findElement(By.xpath("//button[@aria-label='Choose month and year']"));
		midLink.click();
		String year = "//td[contains(@aria-label,'" + date_MM_dd_yyyy[2] + "')]//span";
		WebElement RequiredYear = driver.findElement(By.xpath(year));
		wait.until(ExpectedConditions.visibilityOf(RequiredYear));
		RequiredYear.click();
		String month = "//td[contains(@aria-label,'" + months[Integer.parseInt(date_MM_dd_yyyy[0]) - 1] + "')]//span";
		WebElement RequiredMonth = driver.findElement(By.xpath(month));
		wait.until(ExpectedConditions.elementToBeClickable(RequiredMonth));
		RequiredMonth.click();
		String RequiredDay = "//td[contains(@aria-label,'" + date_MM_dd_yyyy[1] + "')]//span";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RequiredDay)));
		driver.findElement(By.xpath(RequiredDay)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(RequiredDay)));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void AdvanceFilterByemployeeStatus(String employeeStatus) {
		driver.findElement(By.xpath("//div[.='Select Employee Status']//div[@class='ng-input']//input"))
				.sendKeys(employeeStatus);
		driver.findElement(By.xpath("//span[.='" + employeeStatus + "']//parent::div")).click();
	}

	public void applyFilterButton() {
		// TODO Auto-generated method stub
		driver.findElement(ApplyButton).click();

	}

	public void ResetFilter() {
		// TODO Auto-generated method stub
		driver.findElement(filterReset).click();
	}

	public void switchScreen(String screenName) {
		driver.findElement(By.xpath("//span[@class='viewTicketTabSpan'][.='" + screenName + "']")).click();
	}

	public void FilterWithStatus(String WidgetName, String Status) throws InterruptedException {
		// TODO Auto-generated method stubdriver = initializeDriver();
		// Get the necessary values from properties File
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
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on HIRE Form
		hubhome.selectWidget("Action Requests View").click();
		switchScreen(WidgetName);
		WebElement FilterCount = driver.findElement(By.xpath("//div[@id='filterCol_" + Status + "']"));
		String CountString = FilterCount.getAttribute("title");
		String[] Values = CountString.split(" ");
		int StatusCount = Integer.parseInt(Values[1]);
		FilterCount.click();
		int listViewCount = getElementsWithStatus("").size();
		Assert.assertEquals(listViewCount, StatusCount,
				"Test Case Failed" + Status + "Filter is not Working in+" + WidgetName);
	}

	public void ApproveRecordListView(String WidgetName, String Status) throws InterruptedException {
		// TODO Auto-generated method stubdriver = initializeDriver();
		// Get the necessary values from properties File
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
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on HIRE Form
		hubhome.selectWidget("Action Requests View").click();
		switchScreen(WidgetName);
		List<WebElement> PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Approve Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int initialSize = PendingApprovals.size();
		PendingApprovals.get(0).findElement(By.xpath("//*[@title='Approve Ticket']")).click();
		driver.findElement(By.xpath("//div[@id='approvalListModal']")).findElement(Enablecomments).click();
		driver.findElement(By.xpath("//div[@id='approvalListModal']")).findElement(Comments).sendKeys("Test Approval");
		driver.findElement(TickButton).click();
		String popup = neosuite.popUp().getText();
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(popup, "Request approved", "Approved successfully message not shown");
		PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Approve Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int AfterSize = PendingApprovals.size();
		softassert.assertEquals(AfterSize, initialSize - 1, "ApprovalFailed");
		WebElement FilterCount = driver.findElement(By.xpath("//div[@id='filterCol_" + Status + "']"));
		String CountString = FilterCount.getAttribute("title");
		String[] Values = CountString.split(" ");
		int StatusCount = Integer.parseInt(Values[1]);
		int listViewCount = getElementsWithStatus(Status).size();
		softassert.assertEquals(listViewCount, StatusCount, "Approved Record Count is not updated in filter");
		softassert.assertAll("Test Case Failed");
	}

	public void RejectRecordListView(String WidgetName, String Status) throws InterruptedException {
		// TODO Auto-generated method stubdriver = initializeDriver();
		// Get the necessary values from properties File
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
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on HIRE Form
		hubhome.selectWidget("Action Requests View").click();
		switchScreen(WidgetName);
		List<WebElement> PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Reject Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int initialSize = PendingApprovals.size();
		PendingApprovals.get(0).findElement(By.xpath("//*[@title='Reject Ticket']")).click();
		driver.findElement(Enablecomments).click();
		driver.findElement(Comments).sendKeys("Test Approval");
		driver.findElement(TickButton).click();
		String popup = neosuite.popUp().getText();
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(popup, "Request rejected", "Approved successfully message not shown");
		PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Reject Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int AfterSize = PendingApprovals.size();
		softassert.assertEquals(AfterSize, initialSize - 1, "Falied to reject Record");
		WebElement FilterCount = driver.findElement(By.xpath("//div[@id='filterCol_" + Status + "']"));
		String CountString = FilterCount.getAttribute("title");
		String[] Values = CountString.split(" ");
		int StatusCount = Integer.parseInt(Values[1]);
		int listViewCount = getElementsWithStatus(Status).size();
		softassert.assertEquals(listViewCount, StatusCount, "Rejected Record Count is not updated in filter");
		softassert.assertAll("Test Case Failed");
	}
	public void ApproveRecord(String WidgetName, String Status) throws InterruptedException {
		// TODO Auto-generated method stubdriver = initializeDriver();
		// Get the necessary values from properties File
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
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on HIRE Form
		hubhome.selectWidget("Action Requests View").click();
		switchScreen(WidgetName);
		List<WebElement> PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Approve Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int initialSize = PendingApprovals.size();
		PendingApprovals.get(0).findElement(By.xpath("//button[@title='View']")).click();
		driver.findElement(ApproveButton);
		driver.findElement(By.xpath("//div[@id='approval']")).findElement(Enablecomments).click();
		driver.findElement(By.xpath("//div[@id='approval']")).findElement(Comments).sendKeys("Test Approval");
		driver.findElement(TickButton).click();
		String popup = neosuite.popUp().getText();
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(popup, "Request approved", "Approved successfully message not shown");
		PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Approve Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int AfterSize = PendingApprovals.size();
		softassert.assertEquals(AfterSize, initialSize - 1, "ApprovalFailed");
		WebElement FilterCount = driver.findElement(By.xpath("//div[@id='filterCol_" + Status + "']"));
		String CountString = FilterCount.getAttribute("title");
		String[] Values = CountString.split(" ");
		int StatusCount = Integer.parseInt(Values[1]);
		int listViewCount = getElementsWithStatus(Status).size();
		softassert.assertEquals(listViewCount, StatusCount, "Approved Record Count is not updated in filter");
		softassert.assertAll("Test Case Failed");
	}

	public void RejectRecord(String WidgetName, String Status) throws InterruptedException {
		// TODO Auto-generated method stubdriver = initializeDriver();
		// Get the necessary values from properties File
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
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on HIRE Form
		hubhome.selectWidget("Action Requests View").click();
		switchScreen(WidgetName);
		List<WebElement> PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Reject Ticket']//parent::span//parent::span//parent::td//parent::tr//button[@title='View']"));
		int initialSize = PendingApprovals.size();
		PendingApprovals.get(0).click();
		driver.findElement(RejectButton).click();
		//driver.findElement(By.xpath("//div[@id='approval']")).findElement(Enablecomments);
		driver.findElement(By.xpath("//div[@id='approval']")).findElement(Comments).sendKeys("Test Approval");
		driver.findElement(TickButton).click();
		String popup = neosuite.popUp().getText();
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(popup, "Request rejected", "Approved successfully message not shown");
		PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Reject Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int AfterSize = PendingApprovals.size();
		softassert.assertEquals(AfterSize, initialSize - 1, "Falied to reject Record");
		WebElement FilterCount = driver.findElement(By.xpath("//div[@id='filterCol_" + Status + "']"));
		String CountString = FilterCount.getAttribute("title");
		String[] Values = CountString.split(" ");
		int StatusCount = Integer.parseInt(Values[1]);
		int listViewCount = getElementsWithStatus(Status).size();
		softassert.assertEquals(listViewCount, StatusCount, "Rejected Record Count is not updated in filter");
		softassert.assertAll("Test Case Failed");
	}
	public void CancelRecord(String WidgetName, String Status) throws InterruptedException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stubdriver = initializeDriver();
		// Get the necessary values from properties File
		// Enter URL
		login.URL("UAT");
		// Type User name,Password and click on login
		login.loginWithParameter("AT0008", "Neeyamo@123");
		// Switch to GHR Role
		wait.until(ExpectedConditions.visibilityOf(neosuite.OpenEhubApplication()));
		login.switchToGHRRole();
		wait.until(ExpectedConditions.stalenessOf(neosuite.OpenEhubApplication()));
		// Open EHUB Application
		neosuite.OpenEhubApplication().click();
		// Wait for the visibility of the Employee Creation ICON.
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Employee']//parent::div//i")));
		// Click on the employee creation ICON
		wait.until(ExpectedConditions.visibilityOf(hubhome.ehubIcon()));
		// Click on HIRE Form
		hubhome.selectWidget("Action Requests View").click();
		switchScreen(WidgetName);
		List<WebElement> PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Cancel Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int initialSize = PendingApprovals.size();
		PendingApprovals.get(0).findElement(By.xpath("//*[@title='Cancel Ticket']")).click();
		driver.findElement(Enablecomments).click();
		driver.findElement(Comments).sendKeys("Test Approval");
		driver.findElement(TickButton).click();
		String popup = neosuite.popUp().getText();
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(popup, "Request cancelled", "Approved successfully message not shown");
		PendingApprovals = driver.findElements(
				By.xpath("//button[@title='Cancel Ticket']//parent::span//parent::span//parent::td//parent::tr"));
		int AfterSize = PendingApprovals.size();
		softassert.assertEquals(AfterSize, initialSize - 1, "Falied to cancel Request");
		WebElement FilterCount = driver.findElement(By.xpath("//div[@id='filterCol_" + Status + "']"));
		String CountString = FilterCount.getAttribute("title");
		String[] Values = CountString.split(" ");
		int StatusCount = Integer.parseInt(Values[1]);
		int listViewCount = getElementsWithStatus(Status).size();
		softassert.assertEquals(listViewCount, StatusCount, "Cancelled Record Count is not updated in filter");
		softassert.assertAll("Test Case Failed");
	}
}

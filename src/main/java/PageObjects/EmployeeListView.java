package PageObjects;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class EmployeeListView {
	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	public LoginPage login;
	public NeosuiteHomePage neosuite;
	public generalFunctions generalFunction;
	public EHUBHome hubhome;
public EmployeeListView(WebDriver driver,Properties prop,WebDriverWait wait) throws IOException
{
	this.driver=driver;
	this.prop=prop;
	this.wait=wait;
	login = new LoginPage(driver,wait);
	neosuite = new NeosuiteHomePage(driver,wait);
	generalFunction = new generalFunctions(driver,prop);
	hubhome = new EHUBHome(driver, prop);
}
By listViewUniversalSearch = By.xpath("//input[@placeholder='Search'][@name='universalListSearch']");
By UniversalSearchIcon = By.xpath("//a[@class='searchField secondary-class downloadCircle']//i[.='search']");
By ResetButton = By.xpath("//button[@title='Reset']");
By AdvanceFilter = By.xpath("//*[@id=\"addWhite\"]/span");
By ApplyButton = By.xpath("//button[.='Apply']");
By preLoaderGif = By.xpath("//div[@id='preloderdiv']");
By filterReset = By.xpath("//div[@id='oed_advance_filter']//button[.='Reset']");
public int ColumnFilter(String columnName) throws InterruptedException
{
	login.changeWaitTime(30);
	ConfigColumn();
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span//p[contains(text(),'"+columnName+"')]"))));
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span//p[contains(text(),'"+columnName+"')]"))));
	WebElement ConfigColumnSearch = driver.findElement(By.xpath("//input[@placeholder='Search'][@attr.name='filterSearchColumnName']"));
	ConfigColumnSearch.sendKeys(columnName);
	driver.findElement(By.xpath("//span//p[contains(text(),'"+columnName+"')]")).click();
	driver.findElement(By.xpath("//span[@title='Save']")).click();
	wait.until(ExpectedConditions.visibilityOf(neosuite.popUp()));
	wait.until(ExpectedConditions.invisibilityOf(neosuite.popUp()));
	login.changeWaitTime(5);
    return driver.findElements(By.xpath("//table//td//b[contains(text(),'"+columnName+"')]")).size();
}
public void ConfigColumn()
{
	wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//td//span//span//span"))));
	driver.findElement(By.xpath("//div[@id='configColumnsnormal']//a//i")).click();
}
public int ConfigureColumnSearch(String columnName)
{
    ConfigColumn();
	WebElement ConfigColumnSearch = driver.findElement(By.xpath("//input[@placeholder='Search'][@attr.name='filterSearchColumnName']"));
	ConfigColumnSearch.sendKeys(columnName);
	List<WebElement> elements = driver.findElements(By.xpath("//li//label[input[@type]]//span//p"));
    boolean condition = elements.size()==1&&elements.get(0).getText().equals(columnName);
    if(condition)
    {
    	ConfigColumnSearch.clear();
    	ConfigColumn();
    	return 1;
    }
    else
    {
    	ConfigColumnSearch.clear();
    	ConfigColumn();
    	return 0;
    }
	
}
public String UniversalSearch(String Input) throws InterruptedException
{
	driver.findElement(listViewUniversalSearch).sendKeys(Input);
	driver.findElement(UniversalSearchIcon).click();
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='preloderdiv']"))));
	login.changeWaitTime(5);
	try {
	return driver.findElement(By.xpath("//span[@id='PexEmployeeListtableDataColumn00']//span")).getText();}
	catch(Exception e)
	{
		try {
		return driver.findElement(By.xpath("//*[@id=\"PexEmployeeListscrollId\"]/div/span")).getText();}
		catch(Exception e1)
		{
			login.changeWaitTime(30);
			return "";
		}
	}
}
public void selectAll() throws InterruptedException {
	// TODO Auto-generated method stub
	int fail = 1;
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"PexEmployeeListheaderCheckbox\"]//parent::label//span")));
	driver.findElement(By.xpath("//*[@id=\"PexEmployeeListheaderCheckbox\"]//parent::label//span")).click();
	wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//*[@id=\"PexEmployeeListheaderCheckbox\"]"), true));
	List<WebElement> checkBoxes = driver.findElements(By.xpath("//*[contains(@id,\"PexEmployeeListselectedRow\")]//input"));
	for(WebElement i:checkBoxes)
	{
		if(i.isEnabled()!=true)
		{
			fail=0;
			break;
		}
	}
	if(fail == 0)
	{
		Assert.fail("Select All Button Not Working Properly");
	}
	else
	{
		Assert.assertTrue(true);
	}
		
}
public void lvcReset() {
	// TODO Auto-generated method stub
	driver.findElement(ResetButton).click();
	
}
public void AdvanceFilter(String country, String legalEntity, String employeeStatus, String doj, int filterType,boolean ResetFilter) throws ParseException {
	// TODO Auto-generated method stub
	Actions action = new Actions(driver);
	if(filterType==0||filterType==1||filterType==2)
	{
	action.moveToElement(driver.findElement(AdvanceFilter)).perform();
	driver.findElement(AdvanceFilter).click();
	driver.findElement(By.xpath("//div[.='Select Country']//div[@class='ng-input']//input")).sendKeys(country);
	driver.findElement(By.xpath("//span[.='"+country+"']//parent::div")).click();
	if(filterType==1||filterType==2)
	{
		driver.findElement(By.xpath("//div[.='Select Businessunit Name']//div[@class='ng-input']//input")).sendKeys(legalEntity);
		driver.findElement(By.xpath("//span[.='"+legalEntity+"']//parent::div")).click();
	}
	}
	if(filterType==3)
	{
		action.moveToElement(driver.findElement(AdvanceFilter)).perform();
		driver.findElement(AdvanceFilter).click();
		driver.findElement(By.xpath("//div[.='Select Employee Status']//div[@class='ng-input']//input")).sendKeys(employeeStatus);
		driver.findElement(By.xpath("//span[.='"+employeeStatus+"']//parent::div")).click();	
	}
	if(filterType==4)
	{
		action.moveToElement(driver.findElement(AdvanceFilter)).perform();
		driver.findElement(AdvanceFilter).click();
		AdvanceFilterByDoj(doj,driver.findElement(By.xpath("//div[.='Date of joining']//div//input")));
		
	}
	if(ResetFilter==false)
	driver.findElement(ApplyButton).click();
	else
	{
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
public void CompareWithpageCount(String Value)
{
String[] Values = getListViewCount();
int initialCount = Integer.valueOf(Values[1]);
int totalCount=Integer.valueOf(Values[3]);
int noOfpages;
if((totalCount%initialCount)>0)
{
noOfpages=(totalCount/initialCount)+1;}
else
	noOfpages=(totalCount/initialCount);
//Check the count adding "Open" Records from all the pages
int statusCount=0;
int finalcount = initialCount;
	login.changeWaitTime(10);
	for(int i=0;i<noOfpages;i++) {
		try {
		driver.findElement(By.xpath("//text()[.=' > ']//parent::a")).click();}
		catch(Exception e) {break;}
		List<WebElement> status1 = getElementsWithStatus(Value);
		statusCount = status1.size();
		/*if(statusCount==0)
		{
			driver.findElement(By.xpath("//a//span[.='"+noOfpages+"']")).click();
			status1 = getElementsWithStatus(status);
			statusCount = status1.size();
		}*/
		finalcount = statusCount + finalcount;
	}
	
	Assert.assertEquals(finalcount, totalCount);
	
}
public void clickonAdvanceFilter()
{
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(AdvanceFilter)).perform();
	driver.findElement(AdvanceFilter).click();
}
public void AdvanceFilterByCountry(String country) {
	// TODO Auto-generated method stub
	driver.findElement(By.xpath("//div[.='Select Country']//div[@class='ng-input']//input")).sendKeys(country);
	driver.findElement(By.xpath("//span[.='"+country+"']//parent::div")).click();
	}
public void AdvanceFilterByLegalEntity(String legalEntity)
{
	driver.findElement(By.xpath("//div[.='Select Businessunit Name']//div[@class='ng-input']//input")).sendKeys(legalEntity);
	driver.findElement(By.xpath("//span[.='"+legalEntity+"']//parent::div")).click();
}
public void AdvanceFilterByDoj(String Doj,WebElement DateField) throws ParseException
{
	SimpleDateFormat sdformat = new SimpleDateFormat("MM-dd-yyyy");
	Date Fromdate = sdformat.parse(Doj);
	String fromdate = sdformat.format(Fromdate);
	System.out.println(fromdate);
	DateField.click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(@aria-label,'20')]//span")));
	String date_MM_dd_yyyy[] = fromdate.split("-");
	String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	WebElement midLink = driver.findElement(By.xpath("//button[@aria-label='Choose month and year']"));
	midLink.click();
	String year = "//td[contains(@aria-label,'"+date_MM_dd_yyyy[2]+"')]//span";
	WebElement RequiredYear = driver.findElement(By.xpath(year));
	wait.until(ExpectedConditions.visibilityOf(RequiredYear));
	RequiredYear.click();
	String month = "//td[contains(@aria-label,'"+months[Integer.parseInt(date_MM_dd_yyyy[0]) - 1]+"')]//span";
	WebElement RequiredMonth = driver.findElement(By.xpath(month));
	wait.until(ExpectedConditions.elementToBeClickable(RequiredMonth));
	RequiredMonth.click();
	sdformat = new SimpleDateFormat("MMMM dd, YYYY");
    String day = sdformat.format(Fromdate);
	String RequiredDay = "//td[@aria-label='"+day+"']//span[contains(text(),' " + date_MM_dd_yyyy[1] + " ')]";
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RequiredDay)));
	driver.findElement(By.xpath(RequiredDay)).click();
 
	//Actions action = new Actions(driver);
	//action.sendKeys(Keys.ESCAPE).build().perform();
}
public void AdvanceFilterByemployeeStatus(String employeeStatus)
{
	driver.findElement(By.xpath("//div[.='Select Employee Status']//div[@class='ng-input']//input")).sendKeys(employeeStatus);
	driver.findElement(By.xpath("//span[.='"+employeeStatus+"']//parent::div")).click();	
}
public void applyFilterButton() {
	// TODO Auto-generated method stub
	driver.findElement(ApplyButton).click();
	
}
public void ResetFilter() {
	// TODO Auto-generated method stub
	driver.findElement(filterReset).click();
}
}

package ListViewTestCases_ViewEmployee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class ListView_TestCases extends BaseClass{
	public WebDriver driver;
	
@Test(priority = 1)
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
	String searchedData = employeelistview.UniversalSearch("AT0008");
	Assert.assertEquals(searchedData, "AT0008");
	driver.close();
}
@Test(priority = 2)
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
	//Enter Text in the search Field and Click on Search Icon
	String searchedData = employeelistview.UniversalSearch("yysnwyns");
	Assert.assertEquals(searchedData, "No Matches Found","No matches found message is not shown");
	driver.close();
}
@Test(priority = 3)
public void configureColumn() throws InterruptedException, IOException
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
	int ConditionEnable = employeelistview.ColumnFilter("Employee Status");
	int ConditionDisable = employeelistview.ColumnFilter("Employee Status");
	if(ConditionDisable == 0 && ConditionEnable == 1)
		Assert.assertTrue(true);
	else
		Assert.fail("Test Case Failed - Column Enable Disable Feature not working");
	driver.close();
}
@Test(priority = 4)
public void SelectAll() throws IOException, InterruptedException
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
	employeelistview.selectAll();
	driver.close();
}
@Test(priority = 5)
public void ResetListView() throws InterruptedException, IOException
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
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id=\"PexEmployeeListscrollId\"]"))));
	List<WebElement> table1 = new ArrayList<WebElement>();
	List<String> table1Values = new ArrayList<String>();
	table1 = driver.findElements(By.xpath("//*[@id=\"PexEmployeeListscrollId\"]/table//tr//td//span//span//span"));
	for(WebElement i:table1)
	{
		table1Values.add(i.getText());
	}
	employeelistview.UniversalSearch("--------");
	employeelistview.lvcReset();
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id=\"PexEmployeeListscrollId\"]"))));
	wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id=\"PexEmployeeListscrollId\"]/table//tr//td//span//span//span")))));
	List<WebElement> table2 = new ArrayList<WebElement>();
	table2 = driver.findElements(By.xpath("//*[@id=\"PexEmployeeListscrollId\"]/table//tr//td//span//span//span"));
	System.out.println(table1.size());
	List<String> table2Values = new ArrayList<String>();
	for(WebElement i:table2)
	{
		table2Values.add(i.getText());
	}
	Assert.assertTrue(table2Values.equals(table1Values),"Test Case Failed - Reset Button Not Working");
	driver.close();
}
@Test(priority = 6)
public void configureColumnSearchValid() throws IOException, InterruptedException
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
	int condition = employeelistview.ConfigureColumnSearch("Global Id");
	Assert.assertTrue(condition==1,"Test Case Failed - Configure column search not working");
}
@Test(priority = 7)
public void configureColumnSearchInValid() throws IOException, InterruptedException
{
	int condition = employeelistview.ConfigureColumnSearch("-----");
	if(condition==0)
		Assert.assertTrue(true);
	else
		Assert.fail("Search With invalid Data Test Case Failed");
}
@Test(priority = 8)
public void atozsort() throws IOException, InterruptedException
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
	wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//td//span//span//span"))));
	driver.findElement(By.xpath("//b[.='Global Id']//parent::span//parent::td//span[2]")).click();
	generalFunction.javascriptclick(driver.findElement(By.xpath("//b[.='Global Id']//parent::span//parent::td//span[.='Sort A to Z']")));
	List<WebElement> table1 = driver.findElements(By.xpath("//*[@id=\"collapsibleHeaderId\"]/div[3]/table//tr[@class='table-row-not-expanded']"));
	List<String> obtainedList = new ArrayList<String>();
	 for(WebElement we:table1){
	       obtainedList.add(we.getText());
	    }
	List<String> sortedList = new ArrayList<String>();
	for(String s:obtainedList){
	       sortedList.add(s);
	    }
Collections.sort(sortedList);
Assert.assertTrue(obtainedList.equals(sortedList),"A to Z Sorting not working");
}
@Test(priority = 9)
public void ztoasort() throws IOException, InterruptedException
{
	generalFunction.javascriptclick(driver.findElement(By.xpath("//b[.='Global Id']//parent::span//parent::td//span[.='Sort Z to A']")));
	List<WebElement> table1 = driver.findElements(By.xpath("//*[@id=\"collapsibleHeaderId\"]/div[3]/table//tr[@class='table-row-not-expanded']"));
	List<String> obtainedList = new ArrayList<String>();
	 for(WebElement we:table1){
	       obtainedList.add(we.getText());
	    }
	List<String> sortedList = new ArrayList<String>();
	for(String s:obtainedList){
	       sortedList.add(s);
	    }
Collections.sort(sortedList);
Collections.reverse(sortedList);
System.out.println(sortedList);
System.out.println(Keys.ENTER);
System.out.println(obtainedList);
Assert.assertTrue(obtainedList.equals(sortedList),"Z to A Sorting not working");
}
@Test(priority = 10)
public void backToTop() throws IOException, InterruptedException
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
	wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//td//span//span//span"))));
	List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"collapsibleHeaderId\"]/div[3]/table//tr[@class='table-row-not-expanded']"));
	int noOfRecords = table.size();
	generalFunction.scrollIntoView(table.get(noOfRecords-1));
	Assert.assertTrue(driver.findElement(By.xpath("//i[contains(@title,'Back To Top')]")).isDisplayed(), "Back To Top Button not available");
	//driver.findElement(By.xpath("//i[contains(@title,'Back To Top')]")).click();
	Assert.assertTrue(table.get(0).isDisplayed(), "Test Case Failed - Back to Top button not working");	
}
@Test(priority = 11)
public void AdvanceFilterByCountry() throws IOException, InterruptedException
{
	String country = "India";
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
	// 0 means filter with country,1 means filter with country and legal entity,2 means filter with country,legal entity and location ,3 means filter with DOJ,4 Employee status
	employeelistview.clickonAdvanceFilter();
	employeelistview.AdvanceFilterByCountry(country);
	employeelistview.applyFilterButton();
    employeelistview.CompareWithpageCount(country);	
}
@Test(priority = 12)
public void AdvanceFilterByCountryandLegalEntity() throws IOException, InterruptedException
{
	String country  = "India";
	String legalEntity = "NW_IND";
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
	// 0 means filter with country,1 means filter with country and legal entity,2 means filter with country,legal entity and location ,3 means filter with DOJ,4 Employee status
	employeelistview.clickonAdvanceFilter();
	employeelistview.AdvanceFilterByCountry(country);
	employeelistview.AdvanceFilterByLegalEntity(legalEntity);
	employeelistview.applyFilterButton();
    employeelistview.CompareWithpageCount(country);	
	employeelistview.CompareWithpageCount(legalEntity);
}
/*@Test(priority = 13)
public void AdvanceFilterByCountryLegalEntityandLocation() throws IOException, InterruptedException
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
	// 0 means filter with country,1 means filter with country and legal entity,2 means filter with country,legal entity and location ,3 means filter with DOJ,4 Employee status
	employeelistview.AdvanceFilter("India","NYM_IND","Active","12/30/2021","Bangalore", 2, false);
}*/
@Test(priority = 14)
public void AdvanceFilterByDoj() throws IOException, InterruptedException
{
	driver = initializeDriver();
	String Doj = "12/1/2020";
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
	employeelistview.clickonAdvanceFilter();
	// 0 means filter with country,1 means filter with country and legal entity,2 means filter with country,legal entity and location ,3 means filter with DOJ,4 Employee status
	employeelistview.AdvanceFilterByDoj(Doj);
	employeelistview.applyFilterButton();
	employeelistview.CompareWithpageCount(Doj);	
}
@Test(priority = 15)
public void AdvanceFilterByEmployeeStatus() throws IOException, InterruptedException
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
	//Thread.sleep(3000);
	//switch to Employee List View Widget
	hubhome.selectWidget("Employee List View").click();
	employeelistview.clickonAdvanceFilter();
	// 0 means filter with country,1 means filter with country and legal entity,2 means filter with country,legal entity and location ,3 means filter with DOJ,4 Employee status
	employeelistview.AdvanceFilterByemployeeStatus("Active");
	employeelistview.applyFilterButton();
	employeelistview.CompareWithpageCount("Active");	
}
@Test(priority = 16)
public void AdvanceFilterReset() throws IOException, InterruptedException
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
	wait.until(ExpectedConditions.visibilityOfAllElements(employeelistview.getElementsWithStatus("India")));
	String[] Values = employeelistview.getListViewCount();
	int Count1=Integer.valueOf(Values[3]);
	// 0 means filter with country,1 means filter with country and legal entity,2 means filter with country,legal entity and location ,3 means filter with DOJ,4 Employee status
	employeelistview.AdvanceFilter("India","IN_NW_AT","Active","12/11/2020", 4, false);
	wait.until(ExpectedConditions.invisibilityOfAllElements(employeelistview.getElementsWithStatus("India")));
	Values = employeelistview.getListViewCount();
	int FilterCount=Integer.valueOf(Values[3]);
	employeelistview.clickonAdvanceFilter();
	employeelistview.ResetFilter();
	wait.until(ExpectedConditions.visibilityOfAllElements(employeelistview.getElementsWithStatus("India")));
	Values = employeelistview.getListViewCount();
	int Count2=Integer.valueOf(Values[3]);
	Assert.assertTrue(FilterCount==0&&Count1==Count2,"Test Case Failed - Filter Reset Not Working");	
}

}

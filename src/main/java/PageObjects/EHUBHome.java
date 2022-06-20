package PageObjects;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EHUBHome {
	public WebDriver driver;
	public Properties prop;
public EHUBHome(WebDriver driver,Properties prop2) throws IOException
{
	this.driver=driver;
	this.prop=prop2;
}
public WebElement ehubIcon()
{
	return driver.findElement(By.xpath(prop.getProperty("EHUB")));
	
}

public WebElement ClickOnForm(String formName) {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//p[.='"+ formName +"']//parent::div//parent::div//div[1]//i"));
}

public WebElement clickOnCountryFilter()
{
	return driver.findElement(By.xpath(prop.getProperty("countryFilter")));
}

public WebElement clickOnlegalEntityFilter() {
	// Click on download button in Template page
	return driver.findElement(By.xpath(prop.getProperty("legalEntityFilter")));
}

public WebElement clickOnTickButton(String windowname) {
	// Select the language in template section
	return driver.findElement(By.xpath(prop.getProperty(windowname)));
}
public WebElement clickOnEditButton() {
	// Select the language in template section
	return driver.findElement(By.xpath(prop.getProperty("editButton")));
}

public WebElement SelectLanguage(String language)
{
	return driver.findElement(By.xpath("//span[@class='ng-option-label'][text()='"+language+"']"));
	
}
public WebElement clickOnPreferredName() {
	return driver.findElement(By.xpath(prop.getProperty("preferredName")));
	
}
public WebElement saveButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath(prop.getProperty("saveButton")));
}

public WebElement selectValueFromFilter(String value) {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//div[@class='scrollable-content']//div//span[.='"+ value +"']"));
}
public WebElement resetButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath(prop.getProperty("reset")));
}
public WebElement getText()
{
	return driver.findElement(By.xpath("//span[@title='Legal Entity']//parent::div//parent::div//parent::legend//parent::fieldset//input//parent::div//parent::div//div[2][contains(text(),' NW_IND ')]"));
}
public WebElement selectfield(String fieldName)
{
	return driver.findElement(By.xpath("//span[@title='"+ fieldName + "']//parent::div//parent::div//parent::legend//parent::fieldset//input"));
}

public WebElement selectDrpdwnValue(String drpdwnvalue) {
	// TODO Auto-generated method stub
	try
	{
	return driver.findElement(By.xpath("//div[@role='option'][.=' "+ drpdwnvalue +" ']"));
	}
			catch(Exception e)
	{
		return 	driver.findElement(By.xpath("//div[@role='option']//span[.='"+ drpdwnvalue +"']"));	
	}
}

public WebElement clearField(String fieldName) {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//span[@title='"+ fieldName + "']//parent::div//parent::div//parent::legend//parent::fieldset//div[@class='ng-input']//parent::div//parent::div//span[@title='Clear all']"));

}
public WebElement selectWidget(String widgetName)
{
	return driver.findElement(By.xpath("//*[@id=\"advHomeScroll\"]/app-advanced-app/div[1]/div[2]/div/div/div/div/div[2]/div[@title='" + widgetName +"']"));
}
public WebElement clickOnViewRecord(String i, String colcount) {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("/html/body/app-root/app-advanced-home-layout/div[1]/div/main/app-advanced-app/div[1]/div[1]/div[3]/app-view-employee-list/div/universal-list-view/div/ul/div[3]/table/tr[" + i + "]/td[" + colcount + "]/span/span[2]/button"));
}
public String getcolCount() {
	int count = 0;
	// TODO Auto-generated method stub
	while(count<=1)
	count =  driver.findElements(By.xpath("//tr[2]//td")).size();
	System.out.println(count);
	return String.valueOf(count);
}
public WebElement ScrollButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//*[@id=\"PexEmployeeListscrollId\"]"));
}
public WebElement deHazeButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//i[.='dehaze']"));
}
public WebElement saveForLaterButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//i[@title='Save For Later']"));
}
public WebElement enterDraftName() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//*[@id='draftName']"));
}
public WebElement clickonselectDraft()
{
	return driver.findElement(By.xpath(prop.getProperty("selectDraft(openDraft)"))); 
}
public WebElement deleteDraft()
{
	return driver.findElement(By.xpath("//span[.='Delete Drafts']"));
}
public WebElement clickonselectDrafts(int i) {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//*[@id=\"deleteDraftModal\"]/div[2]/div/div/ng-select/div/div/div["+(i+1)+"]/input"));
}
public WebElement CreateNewDraft()
{
	return driver.findElement(By.xpath(prop.getProperty("createDraft")));
}
public WebElement openFromDraft()
{
	return driver.findElement(By.xpath(prop.getProperty("openFromDraft")));
}
public WebElement ProceedButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath(prop.getProperty("ProceedBtn")));
}
public WebElement DiscardButton() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath(prop.getProperty("DiscardBtn")));
}
public WebElement tabList() {
	// TODO Auto-generated method stub
	return driver.findElement(By.id("tabExpand"));
}
public WebElement clickOnTab(String tabName) {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath("//label[@title='"+tabName+"']"));
}
public WebElement addSection() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath(prop.getProperty("addSectionBtn")));
}
public WebElement clickOnClearAll() {
	// TODO Auto-generated method stub
	return driver.findElement(By.xpath(prop.getProperty("ResetSelectedDrafts")));
}
public WebElement deselectSelectedDraft(String DraftName)
{
	return driver.findElement(By.xpath("//span[.='"+DraftName+"']//parent::div//span[.='×']"));
}
public WebElement closedraftpopup(String draftwindowtype)
{
	return driver.findElement(By.xpath("//div[@id='"+draftwindowtype+"']//i[@id='closeModalIcon']"));
}
public WebElement DuplicateRecordsWindow()
{
	return driver.findElement(By.xpath("//div[.='Duplicate Records']"));
}
}

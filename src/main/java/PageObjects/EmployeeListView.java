package PageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeListView {
	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
public EmployeeListView(WebDriver driver,Properties prop,WebDriverWait wait)
{
	this.driver=driver;
	this.prop=prop;
	this.wait=wait;
}
By listViewUniversalSearch = By.xpath("//input[@placeholder='Search'][@name='universalListSearch']");
By UniversalSearchIcon = By.xpath("//a[@class='searchField secondary-class downloadCircle']//i[.='search']");








public String UniversalSearch(String Input) throws InterruptedException
{
	driver.findElement(listViewUniversalSearch).sendKeys(Input);
	driver.findElement(UniversalSearchIcon).click();
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='preloderdiv']"))));
	try {
	return driver.findElement(By.xpath("//span[@id='PexEmployeeListtableDataColumn00']//span")).getText();}
	catch(Exception e)
	{
		return driver.findElement(By.xpath("")).getText();
	}
}
}

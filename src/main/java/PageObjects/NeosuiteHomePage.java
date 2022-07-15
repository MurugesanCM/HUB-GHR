package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NeosuiteHomePage {
	public WebDriver driver;
	public WebDriverWait wait;
public NeosuiteHomePage(WebDriver driver,WebDriverWait wait)
{
	this.driver=driver;
	this.wait=wait;
}

By Compliance = By.xpath("//div[@title='Compliance Client']");
By HUB = By.xpath("//div[@title='Hub']");
By Popup = By.xpath("//*[@id='toast-container']//div");
public WebElement OpenComplianceApplication()
{
	//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(Hub))));
	return driver.findElement(Compliance);
}
public WebElement popUp()
{
	return driver.findElement(Popup);
}
public WebElement OpenEhubApplication() {
	// TODO Auto-generated method stub
	return driver.findElement(HUB);
}

}

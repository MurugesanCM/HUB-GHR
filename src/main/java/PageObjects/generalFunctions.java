package PageObjects;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class generalFunctions {
	public WebDriver driver;
	public Properties prop;
public generalFunctions(WebDriver driver,Properties prop2) throws IOException
{
	this.driver=driver;
	this.prop=prop2;
}
	public void javascriptclick(WebElement element) {
		// TODO Auto-generated method stub
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

}

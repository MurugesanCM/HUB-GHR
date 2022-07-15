package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public void javascriptenterkeys(WebElement element,String GID)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].value='"+GID+"';",element);
	}
	public void waitForPageToLoad() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(new ExpectedCondition<Boolean>() {

		public Boolean apply(WebDriver wdriver) {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		}
		});
}
public void scrollIntoView(WebElement element) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element );
	}
public  Boolean isVisibleInViewport(WebElement element) {

	  return (Boolean)((JavascriptExecutor)driver).executeScript(
	      "var elem = arguments[0],                 " +
	      "  box = elem.getBoundingClientRect(),    " +
	      "  cx = box.left + box.width / 2,         " +
	      "  cy = box.top + box.height / 2,         " +
	      "  e = document.elementFromPoint(cx, cy); " +
	      "for (; e; e = e.parentElement) {         " +
	      "  if (e === elem)                        " +
	      "    return true;                         " +
	      "}                                        " +
	      "return false;                            "
	      , element);
	}
}

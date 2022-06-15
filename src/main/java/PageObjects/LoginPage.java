package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class will store all the locators and methods of login page
 */
public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("kc-login");

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.wait = wait;
	}

	/*
	 * public LoginPage(WebDriver driver) { this.driver=driver; }
	 */
	public void getUserName(String uid) {
		driver.findElement(username).sendKeys(uid);
	}

	public void getPassword(String Password) {
		driver.findElement(password).sendKeys(Password);
	}
	public void login()
	{
		driver.findElement(username).sendKeys("AT0006");
		driver.findElement(password).sendKeys("Neeyamo@123");
		driver.findElement(loginButton).click();
	}

	public void switchToGHRRole() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@data-target='setting_SideBar_out']"))));
		driver.findElement(By.xpath("//a[@data-target='setting_SideBar_out']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='collapsible-header']"))));
		driver.findElement(By.xpath("//a[@class='collapsible-header']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@title='Hub']"))));
		driver.findElement(By.xpath("//span[@title='Hub']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@title='Hub']//parent::div//parent::div[1]//parent::li//child::div[@class='collapsible-body']//div[@class='row']//child::div//child::div//span[@title='Global HR']//parent::div//parent::div//div[2]//label//span[@class='lever']"))));
		driver.findElement(By.xpath("//span[@title='Hub']//parent::div//parent::div[1]//parent::li//child::div[@class='collapsible-body']//div[@class='row']//child::div//child::div//span[@title='Global HR']//parent::div//parent::div//div[2]//label//span[@class='lever']"))
		.click();
		WebElement element = driver.findElement(By.xpath("//a[@title='SAVE APP ROLE']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//a[@title='SAVE APP ROLE']")).click();	}

	public void logOut() throws InterruptedException {
		driver.findElement(By.xpath("//a[@data"
				+ "-target='setting_SideBar_out']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@title='Logout']")).click();
	}

	public void URL(String URL) {
		if (URL.equals("UAT"))
			driver.get("https://neosuiteuat.neeyamo.works/login");
		else if (URL.equals("UAT - 5ca"))
			driver.get(
					"https://neosuiteuat5ca.neeyamo.works/login");
		else if (URL.equals("preprod"))
			driver.get("https://neosuitepreprodneeyamo.neeyamo.com/login");
		else if (URL.equals("LT"))
			driver.get("https://neosuitelt.neeyamo.works/login");
	}
	public Timeouts changeWaitTime(int duration)
	{
		return driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
}

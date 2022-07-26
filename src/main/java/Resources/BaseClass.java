package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.ActionRequeststab;
import PageObjects.AuditLog;
import PageObjects.EHUBHome;
import PageObjects.EmployeeListView;
import PageObjects.LoginPage;
import PageObjects.NeosuiteHomePage;
import PageObjects.generalFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public LoginPage login;
	public NeosuiteHomePage neosuite;
	public EHUBHome hubhome;
	public WebDriverWait wait;
	public Properties prop;
	public generalFunctions generalFunction;
	public EmployeeListView employeelistview;
	public ActionRequeststab actionRequest;
	public AuditLog auditLog;
public WebDriver initializeDriver() throws IOException, InterruptedException
{
	String ProjectFolderPath = System.getProperty("user.dir");
	prop = new Properties();
	FileInputStream fis = new FileInputStream(ProjectFolderPath+"\\src\\main\\java\\Resources\\data.properties");
	prop.load(fis);
	String browserName = prop.getProperty("browser");
	if(browserName.equals("chrome"))
	{
	/*
	ChromeOptions op = new ChromeOptions();
	 op.addArguments("headless");
		 */
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
	}
	else if(browserName.equals("firefox"))
	{
	WebDriverManager.firefoxdriver().setup();
	driver = new FirefoxDriver();
	}
    else if(browserName.equals("edge"))
	{
	WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	driver.manage().window().maximize();
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	login = new LoginPage(driver, wait);
	neosuite = new NeosuiteHomePage(driver,wait);
	hubhome = new EHUBHome(driver, prop);
	generalFunction = new generalFunctions(driver,prop);
	employeelistview = new EmployeeListView(driver,prop,wait);
	actionRequest = new ActionRequeststab(driver,prop, wait);
	auditLog = new AuditLog(driver, prop, wait);
	return driver;
}
public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
{
	TakesScreenshot fs = (TakesScreenshot)driver;
	File source = fs.getScreenshotAs(OutputType.FILE);
	String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	System.out.println(destinationFile);
	FileUtils.copyFile(source,new File(destinationFile));
	return destinationFile;
}
}

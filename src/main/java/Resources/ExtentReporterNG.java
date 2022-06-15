package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	static ExtentReports extent;
	static ExtentSparkReporter htmlreporter;
public static ExtentReports getReportObject()
{
	String path = System.getProperty("user.dir") + "\\reports\\index.html";
    htmlreporter = new ExtentSparkReporter(path);
    htmlreporter.config().setDocumentTitle("HUB Application");
    htmlreporter.config().setReportName("GHR Role");
    htmlreporter.config().setTheme(Theme.DARK);
    extent = new ExtentReports();        
    extent.attachReporter(htmlreporter);
    extent.setSystemInfo("Application", "HUB-GHR");
    extent.setSystemInfo("Operating System", "Windows");
    extent.setSystemInfo("Browser Name", "Chrome Version 102+");
    extent.setSystemInfo("Tester Name", "Murugesan");    
    extent.setSystemInfo("Enviroment", "UAT");
	return extent;	
}
}

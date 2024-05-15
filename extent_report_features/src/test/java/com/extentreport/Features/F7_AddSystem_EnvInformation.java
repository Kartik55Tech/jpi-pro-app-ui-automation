package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class F7_AddSystem_EnvInformation {
	
	private static WebDriver driver ;

	public static void main(String[] args) throws IOException  
	{
		 
		driver = new ChromeDriver();
		Capabilities capability = ((RemoteWebDriver)driver).getCapabilities();
		System.out.println(capability.getBrowserName());
		System.getProperties().list(System.out);
		
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ConfigurationsTest.html");
		
		extentReports.setSystemInfo("OS",System.getProperty("os.name"));
		extentReports.setSystemInfo("Java version",System.getProperty("java.version"));
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("Browser",capability.getBrowserName()+capability.getBrowserVersion());
		extentReports.setSystemInfo("App URL","www.google.com");
	
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Report name");
		sparkReporter.config().setDocumentTitle("Doc Title");
		sparkReporter.config().setTimeStampFormat("dd-mm-yyyy hh:mm:ss");
		sparkReporter.config().setCss(".badge-primary{background-color:#f44f04;}");
		sparkReporter.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");
	
		extentReports.attachReporter(sparkReporter);
		
		extentReports.createTest("Test 1" , "Test description")
		.assignAuthor("Kartik")
		.assignCategory("Smoke")
		.assignDevice("Google Chrome")
		.pass("This is a passed test");
		
		extentReports.createTest("Test 2" , "Test description")
		.assignAuthor("John")
		.assignCategory("Regression")
		.assignDevice("Edge")
		.fail("This is a fail test");
		
		extentReports.createTest("Test 3" , "Test description")
		.assignCategory("Sanity")
		.assignDevice("Edge")
		.fail("This is a fail test");
		
		extentReports.createTest("Test 4" , "Test description")
		.assignAuthor("Kartik")
		.assignAuthor("John")
		.assignCategory("Smoke")
		.assignCategory("Regression")
		.assignDevice("Chrome 98")
		.assignDevice("Chrome 99")
		.pass("This is a passed test");
		
		extentReports.createTest("Test 5" , "Test description")
		.assignAuthor("Kartik" , "John" , "Steve")
		.assignCategory("Smoke","Regression","Sanity")
		.assignDevice("Chrome 98","Mozilla","Edge")
		.pass("This is a passed test");
		
		extentReports.createTest("Test 6" , "Test description")
		.assignAuthor(new String[]{"Kartik" , "John" , "Steve"})
		.assignCategory(new String[]{"Smoke","Regression","Sanity"})
		.assignDevice(new String[]{"Chrome 98","Mozilla","Edge"})
		.pass("This is a passed test");
		
		extentReports.flush();
		
		//Automatically opens the located report file.
		try {
			Desktop.getDesktop().browse(new File("ConfigurationsTest.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}

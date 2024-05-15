package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class F1_BasicExtentReport {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("basicreport.html");
		
		extentReports.attachReporter(sparkReporter);
		
		//extentReports.createTest("Test 1"); //by default it will show pass status
		
		
		
		ExtentTest test1 = extentReports.createTest("Test 1");
		test1.pass("This is passed.");
		
		ExtentTest test2 = extentReports.createTest("Test 2");
		test2.log(Status.FAIL, "This is failed.");
		
		extentReports.createTest("Test3").skip("This is skipped");
		
		
		
		extentReports.flush();
		
		//Automatically opens the located report file.
		
		Desktop.getDesktop().browse(new File("basicreport.html").toURI());
		

	}

}

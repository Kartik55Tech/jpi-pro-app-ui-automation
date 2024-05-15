package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class F2_LogLevels {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("logLevel.html");
		
		extentReports.attachReporter(sparkReporter);
		
		extentReports.createTest("Test 1")
		
		.log(Status.INFO,"info1")
		.log(Status.INFO,"info2");
		//.log(Status.PASS,"pass")
		//.log(Status.WARNING,"warning")
		//.log(Status.SKIP,"skip")
//		.log(Status.FAIL,"fail1")
//		.log(Status.FAIL,"fail2")
		
		
		//Log Level 
		/*
		 Fail
		 Skip
		 Warning 
		 Pass
		 Info  
		 */
		 
		extentReports.flush();
		
		//Automatically opens the located report file.
		Desktop.getDesktop().browse(new File("logLevel.html").toURI());
		

	}

}

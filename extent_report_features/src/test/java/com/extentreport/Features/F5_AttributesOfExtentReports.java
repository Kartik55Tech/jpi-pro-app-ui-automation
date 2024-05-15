package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class F5_AttributesOfExtentReports {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Attributes.html");
		
		extentReports.attachReporter(sparkReporter);
		
		//Attributes of Extent Reports[Author ,  Category , Device]		
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
		Desktop.getDesktop().browse(new File("Attributes.html").toURI());
		

	}

}

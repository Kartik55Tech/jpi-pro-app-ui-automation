package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class F8_SortFailed_Skipped_Report {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("AllTests.html");

		ExtentSparkReporter sparkReporter_fail = new ExtentSparkReporter("FailedTests.html");

		// Applying filter to sort failed test cases
		
		sparkReporter_fail.filter().statusFilter().as(new Status[] {Status.FAIL }).apply();

		ExtentSparkReporter sparkReporter_skipWarning = new ExtentSparkReporter("SkipAndWarningTests.html");
		// Applying filter to sort skipped and warning test cases
		sparkReporter_skipWarning.filter().statusFilter().as(new Status[] 
				{ Status.SKIP,
				  Status.WARNING,
		}).apply();

		extentReports.attachReporter(sparkReporter_all, sparkReporter_fail, sparkReporter_skipWarning);

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
		.skip("This is skipped test");
		
		extentReports.createTest("Test 4" , "Test description")
		.assignAuthor("Kartik")
		.assignAuthor("John")
		.assignCategory("Smoke")
		.assignCategory("Regression")
		.assignDevice("Chrome 98")
		.assignDevice("Chrome 99")
		.warning("This is a passed test");
		
		extentReports.createTest("Test 5" , "Test description")
		.assignAuthor("Kartik" , "John" , "Steve")
		.assignCategory("Smoke","Regression","Sanity")
		.assignDevice("Chrome 98","Mozilla","Edge")
		.pass("This is a passed test");
		
		extentReports.createTest("Test 6" , "Test description")
		.assignAuthor(new String[]{"Kartik" , "John" , "Steve"})
		.assignCategory(new String[]{"Smoke","Regression","Sanity"})
		.assignDevice(new String[]{"Chrome 98","Mozilla","Edge"})
		.fail("This is a failed test");
		
		extentReports.flush();

		// Automatically opens the located report file.
		Desktop.getDesktop().browse(new File("AllTests.html").toURI());
		Desktop.getDesktop().browse(new File("FailedTests.html").toURI());
		Desktop.getDesktop().browse(new File("SkipAndWarningTests.html").toURI());

	}

}

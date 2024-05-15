package org.nibejpi.app.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AzureTestCaseListener implements ITestListener, IInvokedMethodListener {

	// Variables to handle Extent Reports
	private ExtentReports extentReports;
	private ExtentTest extentTest;

	// Executed when a new test case starts
	@Override
	public void onTestStart(ITestResult result) {
		// Get test case description and Azure test case ID
		String testCaseDescription = result.getMethod().getDescription();
		String testCaseId = getAzureTestCaseId(result.getMethod());
		String[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class)
				.groups();

		  // Print the AzureTestCase ID and description in the console
        System.out.println("AzureTestCase ID: " + testCaseId);
        System.out.println("Test Description: " + testCaseDescription);
        
		// Create an Extent Test for the current test case
		extentTest = extentReports.createTest(testCaseDescription);
		extentTest.assignAuthor(testCaseDescription);
		extentTest.assignCategory(categories);
				
	}

	// Executed when a test case passes
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.pass("Test Passed");
	}

	// Executed when a test case fails
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.fail("Test Failed");

		// Get the exception thrown during the test failure
		Throwable exception = result.getThrowable();

		// Log the exception details in the report
		extentTest.fail(exception);

		// Check for app crashes and server errors
		if (checkForAppCrashAndServerError()) {
			extentTest.fail("App Crashed or Server Errors Detected");
		}

		// Capture the screenshot from the TestBase class and attach it to the report
		if (TestBase.driver != null) {
			String testName = result.getMethod().getMethodName();
			TestBase.captureScreenshotAndAddToReport(testName, extentTest);
		}
	}

	// Executed when a test case is skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.skip("Test Skipped");
	}

	// Executed before the test suite starts
	@Override
	public void onStart(ITestContext context) {
		String pageName = getPageNameFromTestClass(context);
		extentReports = createExtentReports(pageName);
	}

	// Executed after the test suite finishes
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

	// Create Extent Reports and configure the reporter
	private ExtentReports createExtentReports(String pageName) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String date = dateFormat.format(new Date());

		String reportPath = "reports/" + pageName + "_" + date + ".html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("myUplink Test Automation Report");
		sparkReporter.config().setReportName("myUplink Pro Automation Report");
		

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Application:"," myUplink Pro");
		extentReports.setSystemInfo("Platform:"," Android");
		extentReports.setSystemInfo("Environment:"," Staging");
		return extentReports;
	}

	// Get the Azure test case ID from the TestNG method using custom annotation
	private String getAzureTestCaseId(ITestNGMethod method) {
		AzureTestCaseID annotation = method.getConstructorOrMethod().getMethod().getAnnotation(AzureTestCaseID.class);
		if (annotation != null) {
			return annotation.ID()[0];
		}
		return "";
	}

	// Get the page name from the test class to include in the Extent Report file
	// name
	private String getPageNameFromTestClass(ITestContext context) {
		List<ITestNGMethod> allTestMethods = context.getSuite().getAllMethods();
		if (!allTestMethods.isEmpty()) {
			ITestNGMethod testMethod = allTestMethods.get(0);
			Class<?> testClass = testMethod.getRealClass();
			return testClass.getSimpleName();
		}
		return "default";
	}

	// Method to capture logcat logs and check for app crashes and server errors
	private boolean checkForAppCrashAndServerError() {
		try {
			Process process = Runtime.getRuntime().exec("adb logcat -d");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder logcatLogs = new StringBuilder(); // To capture logcat logs

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("FATAL EXCEPTION") || line.contains("ANR in")) {
					// App crash detected
					return true;
				} else if (line.contains("SERVER_ERROR_KEYWORD")) {
					// Server error detected, customize the keyword as needed
					logcatLogs.append(line).append("\n");
				}
			}

			if (logcatLogs.length() > 0) {
				// If server errors are found, add them to the report
				extentTest.fail("Server Errors Detected");
				extentTest.fail(logcatLogs.toString());
			}
		} catch (Exception e) {
			Logger.getLogger(AzureTestCaseListener.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
	}

	// Not used in this implementation, but required by the IInvokedMethodListener
	// interface
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Not used in this implementation
	}

	// Not used in this implementation, but required by the IInvokedMethodListener
	// interface
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// Not used in this implementation
	}
}

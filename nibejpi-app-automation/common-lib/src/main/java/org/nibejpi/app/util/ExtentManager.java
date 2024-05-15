package org.nibejpi.app.util;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
 
    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance("extent.html");
        }
        return extent;
    }
 
    private static ExtentReports createInstance(String fileName) {
        sparkReporter = new ExtentSparkReporter(fileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }
 
    public static ExtentTest createTest(String testName, String testDescription) {
        return extent.createTest(testName, testDescription);
    }
 
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
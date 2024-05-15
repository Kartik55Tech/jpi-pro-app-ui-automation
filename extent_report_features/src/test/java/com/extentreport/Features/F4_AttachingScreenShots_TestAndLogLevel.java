package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class F4_AttachingScreenShots_TestAndLogLevel {

    static WebDriver driver;

    public static void main(String[] args) throws IOException {
        driver = new ChromeDriver();
        driver.get("https://www.google.co.in/");

        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("AttachScreenshots.html");

        extentReports.attachReporter(sparkReporter);
        
        String base64Code = captureScreenshot();
        String path = captureScreenshot("Google.jpg");
        
        //Attaching screenshots at Test level
        
        extentReports.createTest("Screenshot Test 1","This is a test to attach a screenshot at the Test level")
        .info("This is a info message.")
        .addScreenCaptureFromBase64String(base64Code);
        
        extentReports.createTest("Screenshot Test 2","This is a test to attach a screenshot at the Test level")
        .info("This is a info message.")
        .addScreenCaptureFromBase64String(base64Code,"Google HomePage");
        
        extentReports.createTest("Screenshot Test 3","This is a test to attach a screenshot at the Test level")
        .info("This is a info message.")
        .addScreenCaptureFromPath(path);
        
        extentReports.createTest("Screenshot Test 4","This is a test to attach a screenshot at the Test level")
        .info("This is a info message.")
        .addScreenCaptureFromPath(path,"Google HomePage");
        
        extentReports.createTest("Screenshot Test 5","This is a test to attach a screenshot at the Test level")
        .info("This is a info message.")
        .addScreenCaptureFromBase64String(base64Code,"Google HomePage1")
        .addScreenCaptureFromBase64String(base64Code,"Google HomePage2")
        .addScreenCaptureFromBase64String(base64Code,"Google HomePage3")
        .addScreenCaptureFromBase64String(base64Code,"Google HomePage4");
        
        extentReports.createTest("Screenshot Test 6","This is a test to attach a screenshot at the Test level")
        .info("This is a info message.")
        .addScreenCaptureFromPath(path,"Google HomePage1")
        .addScreenCaptureFromPath(path,"Google HomePage2")
        .addScreenCaptureFromPath(path,"Google HomePage3")
        .addScreenCaptureFromPath(path,"Google HomePage4");
        
        
        //Attaching screenshots at Log level
        extentReports.createTest("Screenshot Test 7","This is a test to attach a screenshot at the log level")
        .info("This is a info message.")
        
        .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
        .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,"Google HomePage").build());
        
        extentReports.createTest("Screenshot Test 8","This is a test to attach a screenshot at the log level")
        .info("This is a info message.")
        .fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build())
        .fail(MediaEntityBuilder.createScreenCaptureFromPath(path,"Google HomePage").build());
        
        extentReports.createTest("Screenshot Test 9","This is a test to attach a screenshot at the log level")
        .info("This is a info message.")
        .fail("This is a info message.", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
        .fail("This is a info message.", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,"Google HomePage").build());
        
        extentReports.createTest("Screenshot Test 10","This is a test to attach a screenshot at the log level")
        .info("This is a info message.")
        .fail("This is a info message.",MediaEntityBuilder.createScreenCaptureFromPath(path).build())
        .fail("This is a info message.",MediaEntityBuilder.createScreenCaptureFromPath(path,"Google HomePage").build());
        
        Throwable t = new Throwable("This is throwable exception.");
        extentReports.createTest("Screenshot Test 11","This is a test to attach a screenshot at the log level")
        .info("This is a info message.")
        .fail(t, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
        .fail(t, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,"Google HomePage").build());
        
        extentReports.createTest("Screenshot Test 12","This is a test to attach a screenshot at the log level")
        .info("This is a info message.")
        .fail(t,MediaEntityBuilder.createScreenCaptureFromPath(path).build())
        .fail(t,MediaEntityBuilder.createScreenCaptureFromPath(path,"Google HomePage").build());
        
        extentReports.flush();
        driver.quit();

        // Automatically opens the located report file.
        Desktop.getDesktop().browse(new File("AttachScreenshots.html").toURI());
    }

    public static String captureScreenshot(String fileName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + fileName;
        File dest = new File(destination);
        try {
        	FileUtils.copyFile(source, dest);
        } catch (Exception e) {
            e.getMessage();
        }
        return dest.getAbsolutePath();
    }

    public static String captureScreenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        String base64Code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        return base64Code;
    }
}

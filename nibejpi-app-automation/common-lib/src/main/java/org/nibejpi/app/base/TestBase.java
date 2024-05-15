package org.nibejpi.app.base;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.apache.commons.io.FileUtils;

import static org.nibejpi.app.util.JsonParser.getConfig;

import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.enumeration.ConfigJson;
import org.nibejpi.app.util.ExtentManager;
import org.openqa.selenium.OutputType;

public class TestBase {

	// AndroidDriver instance to interact with the Android application.
	public static AppiumDriver driver;

	// ExtentTest instance to manage the Extent Report.
	protected static ExtentTest extentTest;

	// Declare a thread-local variable to store the test method name
	private static ThreadLocal<String> currentTestMethodName = new ThreadLocal<>();

	// Method to set the name of the test method in the thread-local variable
	@BeforeMethod
	public void setTestMethodName(Method method) {
		currentTestMethodName.set(method.getName());
	}

	// Method to clear the thread-local variable after each test method execution
	@AfterMethod
	public void clearTestMethodName() {
		currentTestMethodName.remove();
	}

	static {
		try {
			// Initialize driver only once when the class is loaded.
			initializeDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to find a free port starting from the specified port number.
	public static int findFreePort(int startPort) throws IOException {
		int port = startPort;
		while (!isPortAvailable(port)) {
			port++;
		}
		return port;
	}

	// Method to check if a specific port is available or in use.
	private static boolean isPortAvailable(int port) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			serverSocket.setReuseAddress(true);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	// Method to initialize the driver instance.
	private static void initializeDriver() throws IOException {
		UiAutomator2Options options = new UiAutomator2Options();

		// Setting up desired capabilities for the AndroidDriver.
		options.setDeviceName(
				Optional.ofNullable(System.getProperty("deviceName")).orElse(getConfig(ConfigJson.ANDROID_DEVICE_NAME)))
				.setUdid(Optional.ofNullable(System.getProperty("udid")).orElse(getConfig(ConfigJson.ANDROID_UDID)))
				.setApp(Constants.PRO_ANDROID_APK_PATH).setAppPackage(getConfig(ConfigJson.ANDROID_APP_PACKAGE))
				.setAppActivity(getConfig(ConfigJson.ANDROID_APP_ACTIVITY)).autoGrantPermissions()
				.setSystemPort(findFreePort(8200)).setAutomationName("uiautomator2");

		// If running on an Android emulator, additional configurations are set.
		if (getConfig(ConfigJson.ANDROID_EMULATOR).equalsIgnoreCase("yes")) {
			options.setAvd(Optional.ofNullable(System.getProperty("deviceName"))
					.orElse(getConfig(ConfigJson.ANDROID_DEVICE_NAME))).setAvdLaunchTimeout(
							Duration.of(Long.parseLong(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)), ChronoUnit.MILLIS));
		}

		// Creating the AndroidDriver with the specified Appium URL and options.
		driver = new AndroidDriver(new URL(getConfig(ConfigJson.APPIUM_URL)), options);

		// Setting implicit wait time for finding elements on the app.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
	}

	// Method to perform test tear-down activities after the suite execution.
	@AfterSuite(alwaysRun = true) 
	public void tearDown() {
		if (driver != null) {
			if (extentTest != null && extentTest.getStatus() == Status.FAIL) {
				// If the test fails, capture a screenshot with timestamp and save it with the
				// name of the test method
				String testName = currentTestMethodName.get(); // Retrieve the name of the test method
				if (testName != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String timestamp = sdf.format(new Date());
					captureScreenshotAndAddToReport("Failure_Screenshot_" + testName + "_" + timestamp, extentTest);
				}
			}
			// Perform other teardown activities such as quitting the driver.
			driver.quit();
		}
		// Flush the Extent Reports to save the test results.
		ExtentManager.flushReports();
	}

	// Method to capture a screenshot and add it to the Extent Report in case of
	// test failures.
	public static void captureScreenshotAndAddToReport(String testName, ExtentTest extentTest) {
		try {
			if (driver != null) {
				File screenshot = driver.getScreenshotAs(OutputType.FILE);
				File destination = new File("screenshots/" + testName + ".png");
				FileUtils.copyFile(screenshot, destination);

				// Add the screenshot to the Extent Report using the provided extentTest object.
				extentTest.log(Status.FAIL, "Test Failed - See Screenshot below:",
						MediaEntityBuilder.createScreenCaptureFromPath(destination.getAbsolutePath()).build());
			} else {
				extentTest.log(Status.WARNING, "Driver instance is null. Cannot capture screenshot.");
			}
		} catch (IOException e) {
			// Handle IOException and log the error in the Extent Report.
			extentTest.log(Status.WARNING, "Failed to capture and add screenshot to report.");
		}
	}

}

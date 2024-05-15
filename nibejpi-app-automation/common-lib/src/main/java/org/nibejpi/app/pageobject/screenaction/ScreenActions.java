package org.nibejpi.app.pageobject.screenaction;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.PowerACState;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

public class ScreenActions {
	public AppiumDriver driver;
	@SuppressWarnings("unused")
	private WebDriverWait wait;

	public ScreenActions(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	// Other utility methods for performing screen actions...
	/**
	 * Get the status of the server.
	 */
	protected void getServerStatus() {
		driver.getStatus();
	}

	/**
	 * Get the currently active element.
	 *
	 * @return The currently active element.
	 */
	protected WebElement getActiveElement() {
		return driver.switchTo().activeElement();
	}

	/**
	 * Move the mouse to the specified element with the specified offset.
	 *
	 * @param element The element to move the mouse to.
	 * @param xOffset The horizontal offset.
	 * @param yOffset The vertical offset.
	 */
	protected void moveMouseToElement(WebElement element, int xOffset, int yOffset) {
		new Actions(driver).moveToElement(element, xOffset, yOffset).perform();
	}

	/**
	 * Click on the specified element.
	 *
	 * @param element The element to click on.
	 */
	public void click(WebElement element) {
		int maxRetries = 3;
		int attempts = 0;

		while (attempts < maxRetries) {
			try {
				waitForElementToBeVisible(element);
				waitForElementToBeClickable(element);
				Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
				Assert.assertTrue(element.isEnabled(), "Element is not clickable");
				element.click();
				break; // If successful, exit the loop
			} catch (Exception e) {
				System.out.println("Attempt " + (attempts + 1) + " failed. Retrying...");
			}
			attempts++;
		}

		if (attempts == maxRetries) {
			System.out.println("Element could not be clicked even after " + maxRetries + " attempts.");
		}
	}

	/**
	 * Check if the specified text is present in the current page source.
	 *
	 * @param containsText The text to check.
	 * @return true if the text is present, false otherwise.
	 */
	protected boolean isTextPresent(String containsText) {
		return driver.getPageSource().contains(containsText);
	}

	/**
	 * Set the power state of the Android device.
	 *
	 * @param powerACState The power state to set.
	 */
	protected void powerStateAndroid(PowerACState powerACState) {
		Consumer<PowerACState> powerStateConsumer = state -> ((AndroidDriver) driver).setPowerAC(state);
		powerStateConsumer.accept(powerACState);
	}

	public void swipeLeft(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", "left", "percent", 1.0));
	}

	/**
	 * Accept the alert dialog.
	 */
	protected void acceptAlert() {
		driver.executeScript("mobile:acceptAlert");
	}

	/**
	 * Dismiss the alert dialog.
	 */
	protected void dismissAlert() {
		driver.executeScript("mobile:dismissAlert");
	}

	/**
	 * Perform a scroll down gesture.
	 */
	public void scrolldown() {
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 100,
				"width", 200, "height", 200, "direction", "down", "percent", 5.0));
	}

	public void verify(Runnable verificationSteps) {
		SoftAssert softAssert = new SoftAssert();
		verificationSteps.run();
		softAssert.assertAll();
	}

	// Helper method to verify input fields
	public void verifyInputField(WebElement element, String fieldName) {
	    waitForElementToBeVisible(element);
		Assert.assertTrue(element.isDisplayed(), fieldName + " field is not displayed.");
	    Assert.assertTrue(element.isEnabled(), fieldName + " field is not enabled.");
	}
	
	public void verifySpinner(WebElement element, String spinnerName)
	{
		Assert.assertTrue(element.isDisplayed(), spinnerName + " dropdown is not displayed.");
	    Assert.assertTrue(element.isEnabled(), spinnerName + " dropdown is not enabled.");
	}
	
	public void verifyButton(WebElement element, String buttonName)
	{
		Assert.assertTrue(element.isDisplayed(), buttonName + " button is not displayed.");
	    Assert.assertTrue(element.isEnabled(), buttonName + " button is not enabled.");
	}
	
	// Helper method to verify warning message display
	public void verifyWarningMessageDisplayed(WebElement element, String expectedMessage, String elementName) {
		waitForElementToBeVisible(element);
		Assert.assertTrue(element.isDisplayed(), elementName + "is not displayed.");
		Assert.assertEquals(element.getText(), expectedMessage, elementName + "string is mismatched.");
		System.out.println("Warning message :" + expectedMessage + "is displayed for" + element);
	}

	public void verifyTextEquals(WebElement element, String expectedText, String elementName) {
		String actualText = element.getAttribute("text");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualText, expectedText,
				elementName + " does not match expected value: " + expectedText);
	}

	public void verifyTextContains(WebElement element, String expectedText, String elementName) {
		String actualText = element.getAttribute("text");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(actualText.contains(expectedText),
				elementName + " does not contain expected text: " + expectedText);
	}

	/**
	 * Scroll to the end of the content.
	 */
	public void scrollToEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	/**
	 * Perform a scroll up gesture.
	 */
	public void scrollup() {
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 100,
				"width", 200, "height", 200, "direction", "up", "percent", 4.0));
	}
	
	
	

	/**
	 * Scroll to an element with the specified text.
	 *
	 * @param TargetText The text to scroll to.
	 */

	public void scrollToText(String targetText) {
	    try {
	        String uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
	                + ".scrollIntoView(new UiSelector().textContains(\"" + targetText + "\").instance(0));";
	        boolean scrolled = false;
	        while (!scrolled) {
	            try {
	                driver.findElement(AppiumBy.androidUIAutomator(uiAutomator));
	                scrolled = true; // If element is found, exit the loop
	            } catch (NoSuchElementException e) {
	                scrolldown();
	                scrollToEnd();
	                longWait(1);; // Add a short delay to allow content to scroll
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error while scrolling to text: " + targetText);
	        e.printStackTrace();
	        // Handle the exception as needed, e.g., log, throw, or ignore
	    }
	}


	/**
	 * Wait for an element to be visible.
	 *
	 * @param element The element to wait for.
	 * @return The visible element.
	 */
	public WebElement waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Helper method for a long wait
    public void longWait(long durationInMinutes) {
        try {
            long durationInSeconds = durationInMinutes * 60; // Convert minutes to seconds
            Thread.sleep(durationInSeconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Wait for an element to become stale.
	 *
	 * @param element The element to wait for staleness.
	 */
	public void waitForStaleness(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	/**
	 * Verify the UI of an element with expected text and name.
	 *
	 * @param element      The element to verify.
	 * @param expectedText The expected text.
	 * @param elementName  The name of the element.
	 */
	public void verifyUIofElement(WebElement element, String expectedText, String elementName) {
		waitForElementToBeVisible(element);
		Assert.assertTrue(element.isDisplayed(), elementName + " is not displayed.");
		waitForElementToBeClickable(element);
		Assert.assertTrue(element.isEnabled(), elementName + " is not clickable.");
		Assert.assertEquals(element.getAttribute("text"), expectedText);
	}

	/**
	 * Verify the UI of a disabled element with expected text and name.
	 *
	 * @param element      The disabled element to verify.
	 * @param expectedText The expected text.
	 * @param elementName  The name of the element.
	 */
	public void verifyUIofDisabledElement(WebElement element, String expectedText, String elementName) {
		waitForElementToBeVisible(element);
		Assert.assertTrue(element.isDisplayed(), elementName + " is not displayed.");
		Assert.assertFalse(element.isEnabled(), elementName + " is clickable.");
		Assert.assertEquals(element.getAttribute("text"), expectedText);
	}

	/**
	 * Wait for an element to be clickable.
	 *
	 * @param element The element to wait for.
	 * @return The clickable element.
	 */
	public WebElement waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait for an element's text to be present.
	 *
	 * @param element        The element to wait for.
	 * @param expectedString The expected text.
	 * @return true if the text is present, false otherwise.
	 */
	public boolean waitForTextToBePresent(WebElement element, String expectedString) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(4));
			return wait.until(ExpectedConditions.textToBePresentInElement(element, expectedString));
		} catch (TimeoutException e) {
			System.out.println("Timeout waiting for element's text to be: " + expectedString);
			return false;
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Waits for the text content of the given WebElement to be available and
	 * returns it. Handles dynamic strings and potential
	 * StaleElementReferenceException.
	 *
	 * @param element The WebElement to wait for.
	 * @return The text content of the WebElement, or null if not found.
	 */
	public String waitForDynamicTextContent(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(4));
			return wait.until(driver -> {
				try {
					String text = element.getText();
					System.out.println("Text fetched after wait: " + text);
					return text;
				} catch (StaleElementReferenceException e) {
					return null; // Element is no longer attached to the DOM
				}
			});
		} catch (TimeoutException e) {
			System.out.println("Timeout waiting for element's text");
			return null;
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Check if the given list of strings is sorted in ascending order.
	 *
	 * @param listToSort The list to check.
	 * @return true if the list is sorted, false otherwise.
	 */
	protected boolean checkListIsSorted(List<String> listToSort) {
		if (!listToSort.isEmpty()) {
			return Ordering.natural().isOrdered(listToSort);
		}
		return false;
	}

}

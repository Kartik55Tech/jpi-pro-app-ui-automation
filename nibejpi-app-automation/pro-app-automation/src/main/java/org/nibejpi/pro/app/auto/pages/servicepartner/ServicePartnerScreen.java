package org.nibejpi.pro.app.auto.pages.servicepartner;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ServicePartnerScreen extends ScreenActions implements ServicePartner {
	public ServicePartnerScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
  
	 // Element representing the "Add New Service Partner" button
	@AndroidFindBy(id = Add_NEWSP)
	WebElement addSP;

	// Click on the "Add New Service Partner" button
	public void clickOnAddSPButton() {
		click(addSP);
	}

	// Set a specific Service Partner's name by scrolling and finding the element
	public void setServicePartnerName(String servicePartnerName) {
	    boolean isElementFound = false;
	    int attempts = 0;
	    
	    while (!isElementFound && attempts < 3) { // Retry up to 3 times
	        try {
	            waitForElementToBeVisible(addSP);
	            String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
	                    + ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))", servicePartnerName);
	            driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
	            isElementFound = true; // Set flag to true if element is found
	        } catch (Exception e) {
	            // Handle stale element exception by refreshing the reference to the 'addSP' element
	            attempts++;
	            addSP = driver.findElement(By.id("com.myuplink.pro:id/imageButton3")); // Refresh the reference to 'addSP' element
	        }
	    }
	    
	    if (!isElementFound) {
	        throw new NoSuchElementException("Element not found after retries.");
	    }
	}


}

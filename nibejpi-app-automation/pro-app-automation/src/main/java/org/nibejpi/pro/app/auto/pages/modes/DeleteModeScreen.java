package org.nibejpi.pro.app.auto.pages.modes;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;

public class DeleteModeScreen extends ScreenActions implements Modes
{

	public DeleteModeScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = YES_BTN)
	WebElement yes_Btn;

	@AndroidFindBy(id = NO_BTN)
	WebElement no_Btn;

	@AndroidFindBy(id = ALERT_MSG)
	WebElement alrt_Msg;

	@AndroidFindBy(id = ALERT_TITLE)
	WebElement alrt_Title;
	
	public void swipeToDeleteMode(String ModeName)  
	{
		String xpath = "//android.widget.TextView[@text=\"" + ModeName + "\"]";
		// Find the element and perform the click
		WebElement mode = driver.findElement(By.xpath(xpath));
		waitForElementToBeVisible(mode);
		swipeLeft(mode);
	}

	 /**
     * Confirms Mode deletion by clicking "Yes" on the alert.
     */
	public void confirmModeDeletionByClickingYes() {
		waitForElementToBeVisible(alrt_Title);
		Assert.assertEquals(alrt_Title.getAttribute("text"), "Delete Mode");
		waitForElementToBeVisible(alrt_Msg);
		Assert.assertEquals(alrt_Msg.getAttribute("text"), "Are you sure you wish to delete the selected mode?");
		waitForElementToBeVisible(yes_Btn);
		Assert.assertEquals(yes_Btn.getAttribute("text"), "Yes");
		click(yes_Btn);
		System.out.println("Mode deletion is successful.");
	}
	
	 /**
     * Cancels Mode deletion by clicking "No" on the alert.
     */
	public void cancelModeDeletionByClickingNo()
	{
		waitForElementToBeVisible(alrt_Title);
		Assert.assertEquals(alrt_Title.getAttribute("text"), "Delete Mode");
		waitForElementToBeVisible(alrt_Msg);
		Assert.assertEquals(alrt_Msg.getAttribute("text"), "Are you sure you wish to delete the selected mode?");
		waitForElementToBeVisible(no_Btn);
		Assert.assertEquals(no_Btn.getAttribute("text"), "No");
		click(no_Btn);
		System.out.println("Mode delete cancellation process is successful.");
	}
	
	public void verifyModeIsDeleted(String ModeName) throws InterruptedException {
		Thread.sleep(2000);
		try {
			// Attempt to find the element
			String xpath = "//android.widget.TextView[@text=\"" + ModeName + "\"]";
			// Find the element and perform the click
			@SuppressWarnings("unused")
			WebElement tag = driver.findElement(By.xpath(xpath));

			// If the element is found, fail the test
			Assert.fail("Mode is not deleted.");
		} catch (NoSuchElementException e) {
			System.out.println(ModeName + " Mode is successfully deleted and verified.");
			// The element is not found, which is the desired behavior
			// No action needed, absence of the element is expected
			// Test will pass if the element is not found
		}
	}


	
	
	

}

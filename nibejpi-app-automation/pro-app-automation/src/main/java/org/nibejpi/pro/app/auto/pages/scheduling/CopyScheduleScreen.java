package org.nibejpi.pro.app.auto.pages.scheduling;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CopyScheduleScreen extends ScreenActions implements Scheduling
{

	public CopyScheduleScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = COPY_BUTTON)
	WebElement copyButton;
	
	@AndroidFindBy(id = CANCEL)
	WebElement cancelButton;
	
	@AndroidFindBy(id =COPY_FROM_TEXT_VIEW )
	WebElement copyFromTextView;
	
	@AndroidFindBy(id =COPY_TO_TEXT_VIEW )
	WebElement copyToTextView;
	
	@AndroidFindBy(id =CHECKS)
	WebElement checkImage;
	
	@AndroidFindBy(xpath =COPIED_EVENT)
	WebElement copiedEvent;
	
	public void selectDayToCopy(String dayName)
	{
		waitForElementToBeVisible(copyButton);
		// Use the 'ServicePartnerName' parameter in the UiSelector to scroll and find the element
		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))", dayName);
		// Scroll to the element with the specified text and click
		
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
		Assert.assertTrue(checkImage.isDisplayed(), "Day is not selected.");
	}
	
	public void clickOnCopyButton()
	{
		click(copyButton);
	}
	
	public void clickOnCancelButton()
	{
		click(cancelButton);
	}
	
	public void verifyScheduledIsCopied()
	{
		Assert.assertTrue(copiedEvent.isDisplayed(), "Schedule is not copied.");
	}
	
	

	
	
	
}

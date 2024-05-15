package org.nibejpi.pro.app.auto.pages.scheduling;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DeleteScheduleScreen extends ScreenActions implements Scheduling
{

	public DeleteScheduleScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


	@AndroidFindBy(id = ALERT_TITLE )
	WebElement alertTitle;
	
	@AndroidFindBy(id = ALERT_MSG )
	WebElement alertMessage;
	
	@AndroidFindBy(id = DELETE_BUTTON )
	WebElement deleteButton;
	
	@AndroidFindBy(id = DISCARD_ALRT )
	WebElement cancelButton;
	
	@AndroidFindBy(xpath = DELETED_EVENT )
	WebElement deletedEvent;
	
	public void verifyDeleteScheduleAlertIsDisplayed()
	{
		waitForElementToBeVisible(alertTitle);
		Assert.assertTrue(alertTitle.isDisplayed(), "Alert title is not displayed");
		Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed");
		waitForElementToBeVisible(deleteButton);
		Assert.assertTrue(deleteButton.isDisplayed(), "Delete button is not displayed");
		Assert.assertTrue(cancelButton.isDisplayed(), "Cancel button is not displayed");
		Assert.assertEquals(alertTitle.getAttribute("text"),"Do you want to delete events?" );		
	}
	
	public void tapOnDeleteButton()
	{
		click(deleteButton);
	}
	
	public void tapOnCancelButton()
	{
		click(cancelButton);
	}
	
	public void verifyThatScheduledEventIsDeleted()
	{
		Assert.assertTrue(deletedEvent.isDisplayed(), "Event is not deleted.");
	}
	
	
}

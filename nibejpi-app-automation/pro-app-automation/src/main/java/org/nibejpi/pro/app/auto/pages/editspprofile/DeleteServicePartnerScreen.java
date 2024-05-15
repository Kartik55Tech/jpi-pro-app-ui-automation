package org.nibejpi.pro.app.auto.pages.editspprofile;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DeleteServicePartnerScreen extends ScreenActions implements ServicePartnerProfile

{

	public DeleteServicePartnerScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = WARNING_TEXT)
	WebElement warningText;
	
	@AndroidFindBy(id = DELETE_BTN)
	WebElement deleteBtn;
	
	@AndroidFindBy(id = ALERT_MSG)
	WebElement alertMsg;
	
	@AndroidFindBy(id =PASSWORD )
	WebElement password;
	
	@AndroidFindBy(id = CANCEL_ALERTBTN  )
	WebElement cancel_AlertBtn;
	
	@AndroidFindBy(id = DELETE_ALERTBTN)
	WebElement delete_AlertBtn;
	
	@AndroidFindBy(id = ERROR_MSG)
	WebElement error_Msg;
	
	@AndroidFindBy(id = OK_BUTTON)
	WebElement okBtn;
	
	public void clickOnCancelButton()
	{
		click(cancel_AlertBtn);
	}
	
	public void clickDeleteButtonOnAlert()
	{
		click(delete_AlertBtn);
	}
	
	public void clickOnDeleteButton()
	{
		click(deleteBtn);
	}
	
	public void enterPassword(String enterPassword)
	{
		password.sendKeys(enterPassword);
	}
	
	public void clickOnOkButtonOnAlert()
	{
		click(okBtn);
	}
	
	public void verifyInvalidPasswordErrorPopupIsDisplayedForIncorrectPassword() throws InterruptedException
	{
		Thread.sleep(4000);
		waitForElementToBeVisible(error_Msg);
		Assert.assertEquals(error_Msg.getAttribute("text"), "Invalid password.");
		Assert.assertTrue(okBtn.isDisplayed(), "Ok button on Alert is not displayed.");
	}
	
	public void verifyAlertPopupIsDisplayedAfterClickingDeleteButton()
	{
		Assert.assertEquals(alertMsg.getAttribute("text"),"To delete you must enter your password");
		Assert.assertTrue(password.isDisplayed(), "Password input box on Alert is not displayed.");
		Assert.assertTrue(cancel_AlertBtn.isDisplayed(), "Cancel button on Alert is not displayed.");
		Assert.assertTrue(delete_AlertBtn.isDisplayed(), "Delete button on Alert is not displayed.");
		Assert.assertEquals(delete_AlertBtn.getAttribute("text"),"Delete");
	}
	
	public void verifyDeleteServicePartnerButtonIsNotDisplayedWhenMultipleUsersAreAdded()
	{
		waitForElementToBeVisible(warningText);
		Assert.assertTrue(warningText.isDisplayed(),"Delete SP text is not displayed. ");
		try {
		    Assert.assertFalse(deleteBtn.isDisplayed(), "Delete button is displayed");
		} catch (org.openqa.selenium.NoSuchElementException e) {
		    // The button is not displayed, which is the desired behavior.
		}		 
	}
	
	public void verifyDeleteServicePartnerButtonIsDisplayedForSingleUsers()
	{
		waitForElementToBeVisible(warningText);
		Assert.assertTrue(warningText.isDisplayed(),"Delete SP text is not displayed. ");
		waitForElementToBeVisible(deleteBtn);
		Assert.assertTrue(deleteBtn.isDisplayed(), "Delete button is not displayed.");
	}

}

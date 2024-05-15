package org.nibejpi.pro.app.auto.pages.editspprofile;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LeaveServicePartnerScreen extends ScreenActions implements ServicePartnerProfile
{

	public LeaveServicePartnerScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = WARNING_TEXT)
	WebElement warningText;
	
	@AndroidFindBy(id = LEAVE_BTN)
	WebElement leaveBtn;
	
	@AndroidFindBy(id = ALERT_MSG)
	WebElement alertMsg;
	
	@AndroidFindBy(id =PASSWORD )
	WebElement password;
	
	@AndroidFindBy(id = CANCEL_ALERTBTN  )
	WebElement cancel_AlertBtn;
	
	@AndroidFindBy(id = LEAVE_ALERTBTN)
	WebElement leave_AlertBtn;
	
	@AndroidFindBy(id = ERROR_MSG)
	WebElement error_Msg;
	
	@AndroidFindBy(id = OK_BUTTON)
	WebElement okBtn;
	
	public void verifyAlertPopupIsDisplayedAfterClickingLeaveButton()
	{
		Assert.assertEquals(alertMsg.getAttribute("text"),"To leave you must enter your password");
		Assert.assertTrue(password.isDisplayed(), "Password input box on Alert is not displayed.");
		Assert.assertTrue(cancel_AlertBtn.isDisplayed(), "Cancel button on Alert is not displayed.");
		Assert.assertTrue(leave_AlertBtn.isDisplayed(), "Leave button on Alert is not displayed.");
		
	}
	
	public void clickOnCancelButton()
	{
		click(cancel_AlertBtn);
	}
	
	public void clickOnLeaveAlertButton()
	{
		click(leave_AlertBtn);
	}
	
	public void clickOnLeaveButton()
	{
		click(leaveBtn);
	}
	
	public void enterPassword(String enterPassword)
	{
		password.sendKeys(enterPassword);
	}
	
	public void clickOnOkButtonOnAlert()
	{
		click(okBtn);
	}
	
	public void verifyInvalidPasswordErrorPopupIsDisplayedForIncorrectPassword()
	{
		waitForElementToBeVisible(okBtn);
		Assert.assertEquals(error_Msg.getAttribute("text"), "Invalid password.");
		Assert.assertTrue(okBtn.isDisplayed(), "Ok button on Alert is not displayed.");
		
	}
	
	public void verifyLeaveServicePartnerButtonIsNotDisplayedForSingleUser()
	{
		waitForElementToBeVisible(warningText);
		Assert.assertTrue(warningText.isDisplayed(),"Leave SP text is not displayed. ");
		try {
		    Assert.assertFalse(leaveBtn.isDisplayed(), "Leave button is displayed");
		} catch (org.openqa.selenium.NoSuchElementException e) {
		    // The button is not displayed, which is the desired behavior.
		}		
	}

	
}

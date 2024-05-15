package org.nibejpi.pro.app.auto.pages.profile;

import org.nibejpi.app.constant.ErrorMessage;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DeleteAccountScreen extends ScreenActions implements Profile,ErrorMessage
{

	public DeleteAccountScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy (xpath=PASSWORD)
	WebElement password;
	
	@AndroidFindBy (id=DELETE_ACCNT_BTN)
	WebElement deleteAccountBtn;
	
	@AndroidFindBy (id=ALERTCANCEL)
	WebElement cancelAlert;
	
	@AndroidFindBy(xpath = FIELD_ERROR_MSG)
	WebElement fieldErrorMessage;
	
	@AndroidFindBy(id = ALERTMESSAGE)
	WebElement alertMessage;
	
	@AndroidFindBy(id = ALERTOK)
	WebElement okButton;
	
	public void enterPassword(String Password)
	{
		password.sendKeys(Password);
	}
	
	public void clickDeleteAccountButton()
	{
		click(deleteAccountBtn);
	}
	
	public void confirmDeleteOnAlert()
	{
		click(okButton);
	}
	
	public void cancelDeleteOnAlert()
	{
		click(cancelAlert);
	}
	
	public void verifyDeleteAccountScreenUI()
	{
		Assert.assertTrue(password.isDisplayed(), "Password input box is not displayed");
		Assert.assertTrue(deleteAccountBtn.isDisplayed(), "Delete Account Button is not displayed");
		Assert.assertTrue(deleteAccountBtn.isEnabled(), "Delete Account Button is not clickable.");
	}
	
	public void verifyEmptyPassowordFieldValidationMessageIsDisplayed()
	{
		Assert.assertTrue(fieldErrorMessage.isDisplayed(), "Empty Field validation message is not displayed");
		Assert.assertEquals(fieldErrorMessage.getAttribute("text"),EMPTY_FIELD,"Validation message mismatched.");
	}
	
	public void verifyIncorrectPasswordErrorMessageIsDisplayed()
	{
		waitForElementToBeVisible(alertMessage);
		Assert.assertTrue(alertMessage.isDisplayed(), "Alert is not displayed");
		Assert.assertEquals(alertMessage.getAttribute("text"),"Invalid password.","Validation message mismatched.");
		click(okButton);
	}
	
	public void verifyDeleteAccountCancellationProcessByCancelButtonOnAlert()
	{
		waitForElementToBeVisible(cancelAlert);
		click(cancelAlert);
		Assert.assertTrue(password.isDisplayed(),"User not returned to Delete Account screen after cancel.");
	}
}

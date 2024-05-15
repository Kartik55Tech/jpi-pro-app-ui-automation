package org.nibejpi.pro.app.auto.pages.profile;

import org.nibejpi.app.constant.ErrorMessage;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChangePasswordScreen extends ScreenActions implements Profile,ErrorMessage
{

	public ChangePasswordScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy (xpath=CURRENTPASSWORD)
	WebElement currentPassword;
	
	@AndroidFindBy (id=NEWPASSWORD)
	WebElement newPassword;
	
	@AndroidFindBy (xpath=CONFIRMNEWPASSWORD)
	WebElement confirmNewPassword;
	
	@AndroidFindBy (id=CHANGE_BTN)
	WebElement changeBtn;
	
	@AndroidFindBy (xpath=TOASTMSG)
	WebElement toastMessage;
	
	@AndroidFindBy (id=TITLETEXT)
	WebElement topNavTitle;
	
	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;
	
	@AndroidFindBy(xpath = FIELD_ERROR_MSG)
	WebElement fieldErrorMessage;
	
	@AndroidFindBy(id = ALERTMESSAGE)
	WebElement alertMessage;
	
	@AndroidFindBy(id = ALERTOK)
	WebElement okButton;
	
	public void navigateBack()
	{
		click(backButton);
	}
	
	public void enterCurrentPassword(String CurrentPassword)
	{
		waitForElementToBeVisible(currentPassword);
		currentPassword.clear();
		currentPassword.sendKeys(CurrentPassword);
	}
	
	public void enterNewPassword(String NewPassword)
	{
		waitForElementToBeVisible(newPassword);
		newPassword.clear();
		newPassword.sendKeys(NewPassword);
	}
	
	public void enterConfirmNewPassword(String ConfirmNewPassword)
	{
		waitForElementToBeVisible(confirmNewPassword);
		confirmNewPassword.clear();
		confirmNewPassword.sendKeys(ConfirmNewPassword);
	}
	
	public void clickOnChangeButton()
	{
		
		click(changeBtn);
	}
	
	public void verifyUIofElementsOnChangePasswordScreen()
	{
		Assert.assertTrue(currentPassword.isDisplayed(),"Current password field is not displayed.");
		Assert.assertEquals(currentPassword.getAttribute("text"),"Current password","Current password Placeholder is incorrect");
		Assert.assertTrue(newPassword.isDisplayed(),"New password field is not displayed.");
		Assert.assertEquals(newPassword.getAttribute("text"),"New password","New password Placeholder is incorrect");
		Assert.assertTrue(confirmNewPassword.isDisplayed(),"Confirm New password field is not displayed.");
		Assert.assertEquals(confirmNewPassword.getAttribute("text"),"Confirm new password","Confirm New password Placeholder is incorrect");
		Assert.assertTrue(changeBtn.isDisplayed(),"Change Button is not displayed.");
		Assert.assertEquals(changeBtn.getAttribute("text"),"Change");
	}
	
	public void verifyToastMessageIsVisibleAfterChangingPasswordSuccessfully()
	{
		String toastmsg = driver.findElement(By.xpath(TOASTMSG)).getAttribute("name");
		System.out.println("Toast message :"+ toastmsg);
		Assert.assertEquals(toastmsg,"Your password has successfully changed.");
	}
	
	public void verifyUserSuccessfullyLoginWithChangedPassword()
	{
		waitForElementToBeVisible(topNavTitle);
		Assert.assertTrue(topNavTitle.isDisplayed(),"Toast message is not displayed.");
		Assert.assertEquals(topNavTitle.getAttribute("text"),"Systems");
	}
	
	public void verifyEmptyFieldValidationMessageIsDisplayed()
	{
		Assert.assertTrue(fieldErrorMessage.isDisplayed(), "Empty Field validation message is not displayed");
		Assert.assertEquals(fieldErrorMessage.getAttribute("text"),EMPTY_FIELD,"Validation message mismatched.");
	}
	
	public void verifyEmptyFieldValidationForCurrentPassword()
	{
		waitForElementToBeVisible(fieldErrorMessage);
		Assert.assertTrue(fieldErrorMessage.isDisplayed(), "Empty Field validation message is not displayed");
		Assert.assertEquals(fieldErrorMessage.getAttribute("text"),EMPTY_FIELD,"Validation message mismatched.");
	}
	
	public void verifyErrorMessageAlertIsDisplayed()
	{
		waitForElementToBeVisible(alertMessage);
		Assert.assertTrue(alertMessage.isDisplayed(), "Alert is not displayed");
		Assert.assertEquals(alertMessage.getAttribute("text"),"It seems like your old password is incorrect.","Validation message mismatched.");
		click(okButton);
	}
	
	public void verifyErrorMessageValidationForMissingConfirmNewPassword()
	{
		waitForElementToBeVisible(fieldErrorMessage);
		Assert.assertTrue(fieldErrorMessage.isDisplayed(), "Empty Field validation message is not displayed");
		Assert.assertEquals(fieldErrorMessage.getAttribute("text"),EMPTY_FIELD,"Validation message mismatched.");
	}
	
	public void verifyIncorrectConfirmPasswordFieldValidation()
	{
		waitForElementToBeVisible(fieldErrorMessage);
		Assert.assertTrue(fieldErrorMessage.isDisplayed(), "Empty Field validation message is not displayed");
		Assert.assertEquals(fieldErrorMessage.getAttribute("text"),INCORRECT_AUTHORIZATION ,"Validation message mismatched.");
	}
}

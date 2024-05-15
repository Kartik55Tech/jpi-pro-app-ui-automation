package org.nibejpi.pro.app.auto.pages.forgotpassword;

import org.nibejpi.app.constant.ErrorMessage;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ForgotPasswordScreen extends ScreenActions implements ForgotPassword {

	// Web elements defined using @AndroidFindBy
	@AndroidFindBy(xpath = INPUT_EMAIL)
	WebElement EnterEmail;

	@AndroidFindBy(id = FORGOT_PWD_LINK)
	WebElement ForgotPass;

	@AndroidFindBy(id = RESET_BTN)
	WebElement resetBtn;

	@AndroidFindBy(id = EMPTY_FIELD_ERROR_MSG)
	WebElement emptyFieldMsg;

	@AndroidFindBy(xpath = INVALID_EMAILADD)
	WebElement invalidEmailMsg;

	public ForgotPasswordScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Method to click the "Forgot Password" link
	public void clickForgetPassword() {
		click(ForgotPass);
	}

	// Method to enter an email
	public void enterEmail(String email) {
		EnterEmail.sendKeys(email);
	}

	// Method to click the "Reset" button
	public void clickOnResetButton() {
		click(resetBtn);
	}

	// Method to verify if the "Forgot Password" link is clickable
	public void verifyForgotPassLink() {
		boolean forgotPasslinkClickable = ForgotPass.isEnabled();
		System.out.println("Link is clickable :" + forgotPasslinkClickable);

	}

	// Validation method to verify the error message for empty email field
	public void verifyEmptyFieldMessage() {
		String expected = ErrorMessage.EMPTY_FIELD;
		String actual = emptyFieldMsg.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Empty field message verified :" + expected);
	}

	// Validation method to verify the error message for an invalid email
	public void verifyInvalidEmailMessage() {
		String expected = ErrorMessage.INVALID_EMAIL;
		String actual = emptyFieldMsg.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Invalid email message verified :" + expected);
	}

	// Method to verify the UI elements on the Forgot Password screen
	public void verifyUIofForgotPasswordScreen() {
		Assert.assertTrue(EnterEmail.isDisplayed(), "Email input box is not displayed");
		verifyUIofElement(resetBtn, "Reset", "Reset button");
	}

}

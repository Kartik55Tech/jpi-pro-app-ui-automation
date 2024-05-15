package org.nibejpi.pro.app.auto.pages.register;
import org.nibejpi.app.constant.ErrorMessage;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterScreenOne extends ScreenActions implements Register
{
	// Constructor
	public RegisterScreenOne(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	//WebElements using @AndroidFindBy annotation
	@AndroidFindBy (xpath = TITLE_TXT_SCR_ONE )
	WebElement titleText;
	
	@AndroidFindBy (id = ERROR_MSG)
	WebElement errorMsg;
	
	@AndroidFindBy (id = PASSWORD_REQ )
	WebElement passwordRequirement;
	
	@AndroidFindBy (xpath = EMAIL)
	WebElement email;
	
	@AndroidFindBy (id = PASSWORD)
	WebElement password;
	
	@AndroidFindBy (xpath = CONFIRM_PWD)
	WebElement confirmPass;
	
	@AndroidFindBy (id = TOS_RAD_BTN)
	WebElement tosRadiobtn;
	
	@AndroidFindBy (id = PP_RAD_BTN)
	WebElement privacyRadiobtn;
	
	@AndroidFindBy (id = NEXT_BTN)
	WebElement nextBtn;
	
	@AndroidFindBy (id = BACK_BTN)
	WebElement backBtn;
	
	@AndroidFindBy (id = TOS_TXT_LBL )
	WebElement termsLabelText;
	
	@AndroidFindBy (id = PP_TXT_LBL )
	WebElement privacyLabelText; 
	
	@AndroidFindBy (xpath = EMAIL_EMPTYFIELD )
	WebElement email_Empty_Msg ;
	
	@AndroidFindBy (xpath = PASSWORD_EMPTYFIELD )
	WebElement pwd_Empty_Msg ;
	
	@AndroidFindBy (xpath = CONFIRM_PWD_EMPTYFIELD )
	WebElement cnfrm_Pwd_Empty_Msg ;
	
	@AndroidFindBy (id = TOS_WARNING_MSG )
	WebElement tos_Warning_Msg ;
	
	@AndroidFindBy (id = PP_WARNING_MSG )
	WebElement pp_Warning_Msg ;
	
	@AndroidFindBy (xpath = ALREADY_REG_EMAIL_WARNING)
	WebElement already_RegEmail_Warning_Msg;
	
	// Method to enter an email on the Register screen
	public RegisterScreenOne enterEmail(String Email)
	{
		waitForElementToBeVisible(email);
		email.clear();
		email.sendKeys(Email);
		return this;
	}
	
	// Method to enter a password on the Register screen
	public RegisterScreenOne enterPassword(String pass)
	{
		password.clear();
		password.sendKeys(pass);
		return this;
	}
	
	// Method to enter and confirm a password on the Register screen
	public RegisterScreenOne confirmPassword(String ConfirmPass)
	{
		confirmPass.clear();
		confirmPass.sendKeys(ConfirmPass);
		return this;
	}
	
	// Method to click on the Terms of Service radio button
	public void checkTermsOfServiceButton()
	{
		click(tosRadiobtn);
	}
	
	// Method to click on the Privacy Policy radio button
	public void checkPrivacyPolicyButton()
	{
		click(privacyRadiobtn);
	}
	
	// Method to click on the Next button
	public void tapOnNextButton()
	{
		click(nextBtn);
	}
	
	public void navigateBack()
	{
		click(backBtn);
	}
	
	public void verifyUserIsNotAbleToRegisterWithInvalidPassword()
	{
		verifyWarningMessageDisplayed(passwordRequirement,ErrorMessage.PASSWORD_RULE,"Password rule");
	}
	
	public void verifyUserIsNotAbleToRegisterWithDifferentPasswords()
	{
		verifyWarningMessageDisplayed(errorMsg, ErrorMessage.PASSWORD_MISMATCH,"Password Mismatch string");
	}
	//Verify the UI of Register screen
	public void verifyRegistrationScreenOneUI()
	{
		waitForElementToBeVisible(email);
		//Title text
		Assert.assertTrue(titleText.isDisplayed(), "Create your account title is not displayed.");
		Assert.assertEquals(titleText.getText(), "Create your account");
		
		//Email input box 
		boolean isEmailInputBoxVisible = email.isDisplayed();
		boolean isEmailInputBoxEnabled = email.isEnabled();
		System.out.println("Email Input box is displayed:"+ isEmailInputBoxVisible );
		System.out.println("Email Input box is enabled:"+ isEmailInputBoxEnabled );
		
		//Password input box
		boolean isPasswordInputBoxVisible = password.isDisplayed();
		boolean isPasswordInputBoxEnabled = password.isEnabled();
		System.out.println("Password Input box is displayed:"+ isPasswordInputBoxVisible );
		System.out.println("Password Input box is enabled:"+ isPasswordInputBoxEnabled );
		
		//Confirm Password input box
		boolean isConfirmPassInputBoxVisible = confirmPass.isDisplayed();
		boolean isConfirmPassInputBoxEnabled = confirmPass.isEnabled();
		System.out.println("Confirm Password Input box is displayed:"+ isConfirmPassInputBoxVisible );
		System.out.println("Confirm Password Input box is enabled:"+ isConfirmPassInputBoxEnabled );
		
		//Radio buttons for Terms of Service and Privacy policy. 
		
		Assert.assertTrue(tosRadiobtn.isDisplayed(),"Terms of Service radio button is not displayed." );
		Assert.assertTrue(tosRadiobtn.isEnabled(), "Terms of Service radio button is not enabled.");
		Assert.assertFalse(tosRadiobtn.isSelected(), "Terms of Service radio is selected by default.");
		
		Assert.assertTrue(privacyRadiobtn.isDisplayed(), "Privacy policy Radio button is not displayed.");
		Assert.assertTrue(privacyRadiobtn.isEnabled(), "Privacy policy Radio button is not enabled.");
		Assert.assertFalse(privacyRadiobtn.isSelected(), "Privacy policy Radio button is selected by default.");
		
		//verify that On clicking corresponding radio buttons are selected and deselected 
	
		tosRadiobtn.click();
		Assert.assertTrue(tosRadiobtn.isSelected(), "Terms of Service radio button is not selected after clicking.");
		tosRadiobtn.click();
		Assert.assertFalse(tosRadiobtn.isSelected(), "Terms of Service radio button is not deselected after clicking.");
		
		privacyRadiobtn.click();
		Assert.assertTrue(privacyRadiobtn.isSelected(), "Privacy policy radio button is not selected after clicking.");
		privacyRadiobtn.click();
		Assert.assertFalse(privacyRadiobtn.isSelected(), "Privacy policy radio button is not deselected after clicking.");
		
		//verify that radio buttons are properly labeled.
		Assert.assertEquals(termsLabelText.getText() ,"I accept the Terms of Service");
		Assert.assertEquals(privacyLabelText.getText(),"I have read and understood the privacy policy");
		
		//verify that labels are clickable.
		Assert.assertTrue(termsLabelText.isEnabled(), "Terms of Service link text is not clickable.");
		Assert.assertTrue(privacyLabelText.isEnabled(), "Privacy policy link text is not clickable.");
		
		//Next button
		Assert.assertTrue(nextBtn.isDisplayed(),"Next button is not displayed.");
		Assert.assertTrue(nextBtn.isEnabled(),"Next button is not enabled.");	
		
	}
	
	// Helper method to verify warning message display
	public void verifyWarningMessageDisplayed(WebElement element, String expectedMessage, String errorMessage) {
	    waitForElementToBeVisible(element);
	    Assert.assertTrue(element.isDisplayed(), errorMessage);
	    Assert.assertEquals(element.getText(), expectedMessage, errorMessage);
	    System.out.println("Warning message displayed :" + expectedMessage);
	}
	
	// Method to verify warning messages when TOS and Privacy Policy are not selected
	public void verifyUserRecievesWarningMsgWhenTOSandPrivacyPolicyNotSelected()
	{
		waitForElementToBeVisible(tos_Warning_Msg);
		Assert.assertTrue(tos_Warning_Msg.isDisplayed(), "Terms of Service warning message is not displayed.");
		Assert.assertEquals(tos_Warning_Msg.getText(), "Please accept Terms of Service agreement.");
		waitForElementToBeVisible(pp_Warning_Msg);
		Assert.assertTrue(pp_Warning_Msg.isDisplayed(), "Privacy policy warning message is not displayed.");
		Assert.assertEquals(pp_Warning_Msg.getText(), "Please accept Privacy Policy agreement.");
	}
	
	
	// Method to verify empty field messages on the Register screen
	public void verifyEmptyFieldScreenOne() {
	    verifyWarningMessageDisplayed(email_Empty_Msg, ErrorMessage.EMPTY_FIELD, "E-mail field ");
	    verifyWarningMessageDisplayed(pwd_Empty_Msg, ErrorMessage.EMPTY_FIELD, "Password field");
	    verifyWarningMessageDisplayed(cnfrm_Pwd_Empty_Msg,ErrorMessage.EMPTY_FIELD, "Confirm Password field message ");
	    verifyWarningMessageDisplayed(tos_Warning_Msg, "Please accept Terms of Service agreement.", "Terms of Service warning message ");
	    verifyWarningMessageDisplayed(pp_Warning_Msg, "Please accept Privacy Policy agreement.", "Privacy policy warning message ");
	}

	
	// Method to verify warning message for an already registered email
	public void verifyUserReceivesWarningMessageForAlreadyRegisteredEmail() {
	    verifyWarningMessageDisplayed(already_RegEmail_Warning_Msg,ErrorMessage.ALREADY_REG_EMAIL , "already registered email ");
	}

	// Method to verify registration with an invalid email format
	public void verifyRegistrationWithInvalidEmailFormat() {
	    verifyWarningMessageDisplayed(errorMsg, ErrorMessage.INVALID_EMAIL, "nvalid email address format ");
	}


		
}

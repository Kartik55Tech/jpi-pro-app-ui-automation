package org.nibejpi.pro.app.auto.pages.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.google.common.collect.ImmutableMap;
import static org.testng.Assert.assertTrue;
import org.nibejpi.app.constant.ErrorMessage;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;

public class LoginScreen extends ScreenActions implements Login {
	private AppiumDriver driver;

	// Constructor to initialize the driver and PageFactory
	public LoginScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/* Page factory pattern */
	@AndroidFindBy(xpath = NEXT_BUTTON)
	WebElement nextBtn;

	@AndroidFindBy(id = MYUPLINK_LOGO)
	WebElement myuplinkLogo;

	@AndroidFindBy(xpath = ENVIRONMENT)
	WebElement envSelection;

	@AndroidFindBy(className = SELECT_ENV)
	WebElement selectEnvironment;

	@AndroidFindBy(id = CLOSE_BUTTON)
	WebElement closeBtn;

	@AndroidFindBy(xpath = EMAIL)
	WebElement usernameField;

	@AndroidFindBy(xpath = PASSWORD)
	WebElement passwordField;

	@AndroidFindBy(id = LOGIN_BUTTON)
	WebElement loginBtn;

	@AndroidFindBy(xpath = HAMBURGER_MENU)
	WebElement hamburgerMenuBtn;

	@AndroidFindBy(xpath = USER_SETTINGS)
	WebElement userSettingsBtn;

	@AndroidFindBy(xpath = LOGOUT_BUTTON)
	WebElement logoutBtn;

	@AndroidFindBy(id = REGISTER_BUTTON)
	WebElement registerBtn;

	@AndroidFindBy(id = FORGOT_PWD_LINK)
	WebElement forgotPwdLink;

	@AndroidFindBy(id = HELP)
	WebElement helpBtn;

	@AndroidFindBy(id = ABOUT)
	WebElement aboutBtn;

	@AndroidFindBy(id = OK_BUTTON)
	WebElement okBtn;

	/* Assertions */
	@AndroidFindBy(id = INVALID_EMAIL)
	WebElement invalidEmailError;

	@AndroidFindBy(xpath = EMPTY_EMAIL_FIELD_ERROR_MSG)
	WebElement emptyEmailFieldError;

	@AndroidFindBy(xpath = EMPTY_PWD_FIELD_ERROR_MSG)
	WebElement emptyPwdFieldError;

	@AndroidFindBy(id = REGISTRED_EMAIL_INCORRECT_PWD)
	WebElement registredEmailIncorrectPwdMsg;

	@AndroidFindBy(id = NON_REGISTERED_USER_ERROR_MSG)
	WebElement nonRegistredUserErrorMsg;

	@AndroidFindBy(xpath = HOME_BUTTON)
	WebElement homeBtn;

	@AndroidFindBy(xpath = PASS_TOGGLE_VISIBLITY_BTN)
	WebElement passVisibilityBtn;

	@AndroidFindBy(xpath = DEVPICKER)
	WebElement devPicker;

	@AndroidFindBy(xpath = STAGEPICKER)
	WebElement stagePicker;

	// Method to tap on the Next button
	public void tapOnNextButton() {
		for (int i = 0; i < 3; i++) {
			click(nextBtn);
		}
	}

	// Method to tap on the MyUplink logo and select the environment
	public void tapOnMyUplinkLogo()
	{
	    waitForElementToBeVisible(myuplinkLogo);
	    
	    for (int i = 0; i < 10; i++) {
	        try {
	            myuplinkLogo.click();
	        } catch (Exception e) {
	            // Handle any exceptions that occur when clicking the element
	            e.printStackTrace();
	        }
	    }
	}

	// Method to select the environment
	public void selectEnvironment() throws InterruptedException {
		waitForElementToBeVisible(envSelection);
		// Perform a series of steps to select the environment.
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) envSelection).getId()));
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) devPicker).getId()));
		Thread.sleep(1000);
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) stagePicker).getId()));
		Thread.sleep(1000);
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) closeBtn).getId()));
	}

	// Method to set the username in the email input box
	public void setUsername(String username) {
		waitForElementToBeVisible(usernameField);
		usernameField.clear();
		usernameField.sendKeys(username);
	}
	
	public void clearField(String text)
	{
		String xpath = "//android.widget.EditText[@text=\"" + text + "\"]";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
	}

	// Method to set the password in the password input box
	public void setPassword(String password) {
		waitForElementToBeVisible(passwordField);
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	// Method to click on the Login button
	public void clickOnLoginButton() {
		click(loginBtn);
	}

	// Method to tap on the Ok button on the error popup
	public void clickOnOkButton() {
		click(okBtn);
	}

	// Method to tap on the Logout button
	public void tapOnLogoutButton() {
		click(logoutBtn);
	}

	// Method to tap on the Register button
	public void tapOnRegisterButton() {
		click(registerBtn);
	}

	// Method to click on About button
	public void tapOnAboutButton() {
		click(aboutBtn);
	}

	// Method to click on Help button
	public void tapOnHelpButton() {
		click(helpBtn);
	}

	// Method to verify the login screen
	public void verifyLoginScreen() {
		boolean logoDisplayed = myuplinkLogo.isDisplayed();
		assertTrue(logoDisplayed, "No logo exists on the login screen");
		System.out.println("User redirected to the login screen: " + logoDisplayed);
	}

	// Method to verify the invalid email format error message
	public void verifyLoginWithInvalidEmailFormatValidPassword() {
		waitForElementToBeVisible(invalidEmailError);
		String expected = ErrorMessage.INVALID_EMAIL;
		String actual = invalidEmailError.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Error message verified: " + expected);
	}

	// Method to verify the empty email field error message
	public void verifyEmptyEmailFieldMessage() throws InterruptedException {
		waitForElementToBeVisible(emptyEmailFieldError);
		String expected = ErrorMessage.EMPTY_FIELD;
		String actual = emptyEmailFieldError.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Email Empty message verified: " + expected);
	}

	// Method to verify the empty password field error message
	public void verifyEmptyPasswordFieldMessage() {
		waitForElementToBeVisible(emptyPwdFieldError);
		String expected = ErrorMessage.EMPTY_FIELD;
		String actual = emptyPwdFieldError.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Password Empty message verified: " + expected);
	}

	// Method to verify login with non-registered email and invalid password
	public void verifyLoginWithNonRegisteredEmailAndInvalidPassword() {
		waitForElementToBeVisible(nonRegistredUserErrorMsg);
		String expected = ErrorMessage.INCORRECT_AUTHORIZATION;
		String actual = nonRegistredUserErrorMsg.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Error message verified: " + expected);
	}
	
	// Method to verify login with non-registered email and invalid password
		public void verifyLoginWithNonRegisteredEmailAndValidPassword() {
			waitForElementToBeVisible(nonRegistredUserErrorMsg);
			String expected = ErrorMessage.INCORRECT_AUTHORIZATION;
			String actual = nonRegistredUserErrorMsg.getText();
			Assert.assertEquals(actual, expected);
			System.out.println("Error message verified: " + expected);
		}

	// Method to verify login with a registered email and invalid password
	public void verifyLoginWithRegisteredEmailAndInvalidPassword() {
		waitForElementToBeVisible(registredEmailIncorrectPwdMsg);
		String expected = ErrorMessage.INCORRECT_AUTHORIZATION;
		String actual = registredEmailIncorrectPwdMsg.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Error message verified: " + expected);
	}

	// Method to verify the successful login screen
	public void verifySuccessfullLoginScreen() {
		waitForElementToBeVisible(homeBtn);
		boolean homeBtnPresent = homeBtn.isDisplayed();
		System.out.println("User successfully logged in: " + homeBtnPresent);
	}

	// Method to verify the successful logout screen
	public void verifySuccessfulLogoutScreen() throws InterruptedException {
		boolean isMyUplinkLogoPresent = myuplinkLogo.isDisplayed();
		System.out.println("User successfully logged out: " + isMyUplinkLogoPresent);
		Thread.sleep(5000);
	}

	// Verify the visibility of elements on the login screen
	public void verifyElementsOnLoginScreen() throws InterruptedException {
		// Verify the visibility of the myUplink logo
		boolean ismyUplinkLogoVisible = myuplinkLogo.isDisplayed();
		System.out.println("myUplink logo visibility: " + ismyUplinkLogoVisible);

		// Verify the visibility of the "Forgot your password" link
		boolean isForgotPasswordLinkVisible = forgotPwdLink.isDisplayed();
		System.out.println("Forgot your password link visibility: " + isForgotPasswordLinkVisible);

		// Verify the visibility of the register button
		boolean isRegisterButtonVisible = registerBtn.isDisplayed();
		System.out.println("Register button visibility: " + isRegisterButtonVisible);

		// Verify the visibility of the "About" link
		boolean isAboutLinkVisible = aboutBtn.isDisplayed();
		System.out.println("About link visibility: " + isAboutLinkVisible);

		// Verify the visibility of the "Help" link
		boolean isHelpLinkVisible = helpBtn.isDisplayed();
		System.out.println("Help link visibility: " + isHelpLinkVisible);

		// Verify that Password visibility icon is visible and clickable.
		boolean isPassVisibilityToggleVisible = passVisibilityBtn.isDisplayed();
		boolean isPassVisibilityToggleClickable = passVisibilityBtn.isEnabled();
		System.out.println("Password Visibility Toggle buttton is displayed :" + isPassVisibilityToggleVisible);
		System.out.println("Password Visibility Toggle buttton is clickable :" + isPassVisibilityToggleClickable);
		Thread.sleep(4000);

	}
}

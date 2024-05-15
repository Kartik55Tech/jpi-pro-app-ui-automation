package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.app.util.RandomUtils;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.register.RegisterScreenOne;
import org.nibejpi.pro.app.auto.pages.register.RegisterScreenThree;
import org.nibejpi.pro.app.auto.pages.register.RegisterScreenTwo;
import org.testng.annotations.*;

@Listeners(AzureTestCaseListener.class)
public class RegistrationTest extends TestBase
{
	private LoginScreen login;
	private LoginHelper loginhelper;
	private RegisterScreenOne registerScreenOne;
	private RegisterScreenTwo registerScreenTwo;
	private RegisterScreenThree registerScreenThree;
	private RandomUtils random=new RandomUtils();
	
	@BeforeClass
	public void Setup() throws Exception
	{
		login = new LoginScreen(driver);
		loginhelper = new LoginHelper(driver);
		registerScreenOne = new RegisterScreenOne(driver);
		registerScreenTwo = new RegisterScreenTwo(driver);
		registerScreenThree = new RegisterScreenThree(driver);
		loginhelper.performLoginSetup();
		login.tapOnRegisterButton();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Register");
	}
	
	/**
	 * Test Case: Verify UI of the Registration Page.
	 *
	 * This test case navigates to the Registration Page and verifies the user interface elements.
	 * 
	 * Test Steps:
	 * 1. Click on the "Register" button on the login screen.
	 * 2. Verify the UI elements on the Registration Page.
	 */
	@Test(priority =1, description = "Verify UI of the Registration screen.")
	@AzureTestCaseID(ID = { "4684" })
	public void testRegistrationScreenUI()
	{
		registerScreenOne.verifyRegistrationScreenOneUI();
	}
	
	/**
	 * Test Case: Verify registration process using valid data for mandatory fields.
	 *
	 * This test case covers the registration process by entering valid data for mandatory fields.
	 * 
	 * Test Steps:
	 * 1. Enter valid data for email, password, confirm password, and accept Terms of Service and Privacy Policy.
	 * 2. Verify redirection to the address screen.
	 * 3. Enter valid data for address-related fields.
	 * 4. Click on the Register button.
	 * 5. Verify the email verification toast message.
	 * 6. Verify the UI elements on the final registration screen.
	 */
	@Test(priority =2,description = "Verify registration process using valid data for mandatory fields.")
	@AzureTestCaseID(ID = { "4691" })
	public void testRegisterWithValidMandatoryData() throws Exception
	{
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(1, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(1, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenTwo.verifyRedirectionToAddressScreenAfterNextButtonClick();
		registerScreenTwo.verifyRegistrationScreenTwoUI();
		registerScreenTwo.setFullName(random.generateRandomName());
		registerScreenTwo.setAddressLine1(ExcelUtils.getCellData(1, 5));
		registerScreenTwo.setAddressLine2(ExcelUtils.getCellData(1, 6));
		registerScreenTwo.setZipCode(random.generateRandomZipCode());
		registerScreenTwo.setCity(ExcelUtils.getCellData(1, 8));
		registerScreenTwo.selectCountry(ExcelUtils.getCellData(1, 10));
		registerScreenTwo.selectState(ExcelUtils.getCellData(1, 9));
		registerScreenTwo.tapOnRegisterButton();
		registerScreenThree.verifyEmailVerificationToastMessage();
		registerScreenThree.verifyRegistrationScreenThreeUI();
	}
	
	/**
	 * Test Case: Validate the registration process using valid data for optional fields.
	 *
	 * This test case covers the registration process by entering valid data for optional fields.
	 * 
	 * Test Steps:
	 * 1. Enter valid data for email, password, confirm password, and accept Terms of Service and Privacy Policy.
	 * 2. Verify redirection to the address screen.
	 * 3. Enter valid data for optional address-related fields.
	 * 4. Click on the Register button.
	 * 5. Verify the email verification toast message.
	 * 6. Verify the UI elements on the final registration screen.
	 */
	@Test(priority =3,description = "Validate the registration process using valid data for optional fields.")
	@AzureTestCaseID(ID = { "4823" })
	public void testRegisterWithValidOptionalData() throws Exception
	{
		//Bug fix APPS - 2914
		login.tapOnRegisterButton();
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(2, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(2, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenTwo.verifyRedirectionToAddressScreenAfterNextButtonClick();
		registerScreenTwo.verifyRegistrationScreenTwoUI();
		registerScreenTwo.setFullName(random.generateRandomName());
		registerScreenTwo.setAddressLine1(ExcelUtils.getCellData(1, 5));
		registerScreenTwo.setAddressLine2(ExcelUtils.getCellData(1, 6));
		registerScreenTwo.setZipCode(random.generateRandomZipCode());
		registerScreenTwo.setCity(ExcelUtils.getCellData(2, 8));
		registerScreenTwo.selectCountry(ExcelUtils.getCellData(2, 10));
		registerScreenTwo.tapOnRegisterButton();
		registerScreenThree.verifyEmailVerificationToastMessage();
		registerScreenThree.verifyRegistrationScreenThreeUI();
	}
	
	/**
	 * Test Case: Validate user is not able to register with Empty Fields.
	 *
	 * This test case covers the scenario where the user attempts to register with empty fields.
	 * 
	 * Test Steps:
	 * 1. Click on the "Next" button without entering any data.
	 * 2. Verify the empty field messages on the first registration screen.
	 * 3. Enter email, password, confirm password, and accept Terms of Service and Privacy Policy.
	 * 4. Click on the "Next" button.
	 * 5. Verify the empty field messages on the second registration screen with both optional and mandatory state.
	 */
	@Test(priority =4,description = "Validate user is not able to register with Empty Fields.")
	@AzureTestCaseID(ID = { "4685" })
	public void testRegistrationWithEmptyFields() throws Exception
	{
		login.tapOnRegisterButton();
		registerScreenOne.tapOnNextButton();
		registerScreenOne.verifyEmptyFieldScreenOne();
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(2, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(2, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenTwo.verifyEmptyFieldScreenTwoWithOptionalState();
		registerScreenTwo.verifyEmptyFieldScreenTwoWithMandatoryState();	
	}
	
	/**
	 * Test Case: Verify that user is not able to register without accepting Terms & Services and Privacy policy.
	 *
	 * This test case covers the scenario where the user tries to register without accepting the Terms & Services and Privacy policy.
	 * 
	 * Test Steps:
	 * 1. Enter valid data for email and password.
	 * 2. Click on the "Next" button without accepting Terms & Services and Privacy policy.
	 * 3. Verify the warning message.
	 */
	@Test(priority =5,description = "Verify that user is not able to register without accepting Terms & Services and Privacy policy")
	@AzureTestCaseID(ID = { "4689" })
	public void testRegisterWithoutAcceptingTOSandPP() throws Exception
	{
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(4, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(4, 3));
		registerScreenOne.tapOnNextButton();
		registerScreenOne.verifyUserRecievesWarningMsgWhenTOSandPrivacyPolicyNotSelected();
	}
	
	/**
	 * Test Case: Verify that user is not able to register with an already registered account.
	 *
	 * This test case covers the scenario where the user tries to register with an already registered email address.
	 * 
	 * Test Steps:
	 * 1. Enter an email address that is already registered.
	 * 2. Enter a password and confirm password.
	 * 3. Accept Terms of Service and Privacy Policy.
	 * 4. Click on the "Next" button.
	 * 5. Verify the warning message for an already registered email.
	 */
	@Test(priority =6,description = "Verify that user is not able to register with an already registered account.")
	@AzureTestCaseID(ID = { "4687" })
	public void testRegisterWithAlreadyRegisteredEmail() throws Exception
	{
		registerScreenOne.enterEmail(ExcelUtils.getCellData(5, 1));
		registerScreenOne.enterPassword(ExcelUtils.getCellData(5, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(5, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenOne.verifyUserReceivesWarningMessageForAlreadyRegisteredEmail();
	}
	
	/**
	 * Test Case: Verify registration with invalid email format.
	 *
	 * This test case covers the scenario where the user tries to register with an invalid email format.
	 * 
	 * Test Steps:
	 * 1. Enter an invalid email address.
	 * 2. Enter a password and confirm password.
	 * 3. Accept Terms of Service and Privacy Policy.
	 * 4. Click on the "Next" button.
	 */
	@Test(priority =7,description = "Verify registration with invalid email format.")
	@AzureTestCaseID(ID = { "4686" })
	public void testRegisterWithInvalidEmailFormat() throws Exception
	{
		registerScreenOne.enterEmail(ExcelUtils.getCellData(6, 1));
		registerScreenOne.enterPassword(ExcelUtils.getCellData(6, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(6, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenOne.verifyRegistrationWithInvalidEmailFormat();
	}
	
	/**
	 * Test Case: Verify that user is not able to register without Address, City, Country, and Zip code.
	 *
	 * This test case covers the scenario where the user tries to register without entering mandatory address details.
	 * 
	 * Test Steps:
	 * 1. Enter valid data for email, password, confirm password, and accept Terms of Service and Privacy Policy.
	 * 2. Verify redirection to the address screen.
	 * 3. Click on the "Register" button without entering mandatory address details.
	 * 4. Verify the user is not able to register without Address, City, Country, and Zip code.
	 */
	@Test(priority =8,description = "Verify that user is not able to register without mandatory address details.(Address , City , Country and Zip code)")
	@AzureTestCaseID(ID = { "4824" })
	public void testRegisterWithoutMandatoryAddressDetails() throws Exception
	{
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(7, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(7, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenTwo.verifyUserNotAbleToRegisterWithoutAddressZipCity();
			
	}
	
	/**
	 * Test Case: Verify the navigation of Registration functionality.
	 *
	 * This test case covers the navigation within the Registration functionality.
	 * 
	 * Test Steps:
	 * 1. Navigate back from the second registration screen to the first.
	 * 2. Verify the UI elements on the first registration screen.
	 * 3. Navigate back to the login screen.
	 * 4. Click on the "Register" button again.
	 * 5. Verify the UI elements on the first registration screen.
	 * 6. Enter valid data for email and password.
	 * 7. Accept Terms of Service and Privacy Policy.
	 * 8. Click on the "Next" button.
	 * 9. Verify the redirection to the second registration screen.
	 * 10. Navigate back to the first registration screen.
	 * 11. Verify the UI elements on the first registration screen.
	 */
	@Test(priority = 9,description = "Verify the navigation of Registration functionality.")
	@AzureTestCaseID(ID = { "4825" })
	public void testNavigationWithinRegistration() throws Exception
	{
		registerScreenOne.navigateBack();
		login.verifyLoginScreen();
		login.tapOnRegisterButton();
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(8, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(8, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenTwo.verifyRegistrationScreenTwoUI();
		registerScreenTwo.navigateBack(); 
		registerScreenOne.verifyRegistrationScreenOneUI();
		registerScreenOne.navigateBack();
	}
	
	/**
	 * Test Case: Verify registration with invalid Password format.
	 *
	 * This test case covers the scenario where the user tries to register with an invalid password format.
	 * 
	 * Test Steps:
	 * 1. Click on the "Register" button.
	 * 2. Enter valid data for email and invalid password format.
	 * 3. Verify the behavior during registration.
	 */
	@Test(priority = 10,description = "Verify registration with invalid Password.")
	@AzureTestCaseID(ID = { "4688" })
	public void testRegisterWithInvalidPassword() throws Exception
	{
		login.tapOnRegisterButton();
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(9, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(9, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		//registerScreenOne.click_Next(); 
		//BugFix APPS-2912
		//registerScreenOne.verifyUserIsNotAbleToRegisterWithInvalidPassword();
	}
	
	/**
	 * Test Case: Verify registration with different passwords.
	 *
	 * This test case covers the scenario where the user tries to register with different passwords.
	 * 
	 * Test Steps:
	 * 1. Click on the "Register" button.
	 * 2. Enter valid data for email and different passwords.
	 * 3. Verify the behavior during registration.
	 */
	@Test(priority = 11,description = "Verify registration with different passwords.")
	@AzureTestCaseID(ID = { "4826" })
	public void testRegisterWithDifferentPasswords() throws Exception
	{
		registerScreenOne.enterEmail(random.generateRandomEmail());
		registerScreenOne.enterPassword(ExcelUtils.getCellData(10, 2));
		registerScreenOne.confirmPassword(ExcelUtils.getCellData(10, 3));
		registerScreenOne.checkTermsOfServiceButton();
		registerScreenOne.checkPrivacyPolicyButton();
		registerScreenOne.tapOnNextButton();
		registerScreenOne.verifyUserIsNotAbleToRegisterWithDifferentPasswords();
	}
}

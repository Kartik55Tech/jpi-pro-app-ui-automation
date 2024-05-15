 package profileTest;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.profile.ChangePasswordScreen;
import org.nibejpi.pro.app.auto.pages.profile.ProfileScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.settings.SettingsScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest extends TestBase
{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private SettingsScreen settings; 
	private ProfileScreen profile;
	private ChangePasswordScreen changepassword;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		settings = new SettingsScreen(driver);
		profile = new ProfileScreen(driver);
		changepassword = new ChangePasswordScreen(driver);
		

		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA,"Profile");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(7, 1));
		login.setPassword(ExcelUtils.getCellData(7, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(7, 3));
	}

	@Test(priority = 1, description = "Verify UI of Change Password screen.")
	@AzureTestCaseID(ID = { "3902" })
	public void verifyChangePasswordScreenUI()
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.verifyUIofElementsOnChangePasswordScreen();	
	}
	
	@Test(priority = 2, description = "Verify Change Password functionality. ")
	@AzureTestCaseID(ID = { "3903" })
	public void verifyChangePasswordFunctionality() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.enterCurrentPassword(ExcelUtils.getCellData(7, 4));
		changepassword.enterNewPassword(ExcelUtils.getCellData(7, 5));
		changepassword.enterConfirmNewPassword(ExcelUtils.getCellData(7, 6));
		changepassword.clickOnChangeButton();
		changepassword.verifyToastMessageIsVisibleAfterChangingPasswordSuccessfully();
		profile.navigateBack();
		settings.clickOnLogoutButton();
		login.setUsername(ExcelUtils.getCellData(7, 1));
		login.setPassword(ExcelUtils.getCellData(7, 5));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(7, 3));
		changepassword.verifyUserSuccessfullyLoginWithChangedPassword();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.enterCurrentPassword(ExcelUtils.getCellData(7, 5));
		changepassword.enterNewPassword(ExcelUtils.getCellData(7, 4));
		changepassword.enterConfirmNewPassword(ExcelUtils.getCellData(7, 4));
		changepassword.clickOnChangeButton();
	}
	
	@Test(priority = 3, description = "Verify the fields with empty details.")
	@AzureTestCaseID(ID = { "3904" })
	public void verifyEmptyFieldsValidations() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.clickOnChangeButton();
		changepassword.verifyEmptyFieldValidationMessageIsDisplayed();
	}
	
	@Test(priority = 4, description = "Verify the current password field without filling any data .")
	@AzureTestCaseID(ID = { "3905" })
	public void validateEmptyCurrentPasswordField() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.enterCurrentPassword(ExcelUtils.getCellData(9, 4));
		changepassword.enterNewPassword(ExcelUtils.getCellData(9, 5));
		changepassword.enterConfirmNewPassword(ExcelUtils.getCellData(9, 6));
		changepassword.clickOnChangeButton();
		changepassword.verifyEmptyFieldValidationForCurrentPassword();
	}
	
	@Test(priority = 5, description = "Verify the current password field with Incorrect password.")
	@AzureTestCaseID(ID = { "3906" })
	public void verifyCurrentPasswordFieldWithIncorrectPassword() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.enterCurrentPassword(ExcelUtils.getCellData(10, 4));
		changepassword.enterNewPassword(ExcelUtils.getCellData(10, 5));
		changepassword.enterConfirmNewPassword(ExcelUtils.getCellData(10, 6));
		changepassword.clickOnChangeButton();
		changepassword.verifyErrorMessageAlertIsDisplayed();
	}
	
	@Test(priority = 6, description = "Verify Password Change with missing Confirm New Password.")
	@AzureTestCaseID(ID = { "3907" })
	public void verifyPasswordChangeWithMissingConfirmation() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.enterCurrentPassword(ExcelUtils.getCellData(11, 4));
		changepassword.enterNewPassword(ExcelUtils.getCellData(11, 5));
		changepassword.enterConfirmNewPassword(ExcelUtils.getCellData(11, 6));
		changepassword.clickOnChangeButton();
		//APPS - 2566
	}
	
	@Test(priority = 7, description = "Verify change password with incorrect data in Confirm password field.")
	@AzureTestCaseID(ID = { "3908" })
	public void verifyChangePasswordWithIncorrectConfirmPassword() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnChangePassword();
		changepassword.enterCurrentPassword(ExcelUtils.getCellData(12, 4));
		changepassword.enterNewPassword(ExcelUtils.getCellData(12, 5));
		changepassword.enterConfirmNewPassword(ExcelUtils.getCellData(12, 6));
		changepassword.clickOnChangeButton();
		changepassword.verifyIncorrectConfirmPasswordFieldValidation();
	}
	
}

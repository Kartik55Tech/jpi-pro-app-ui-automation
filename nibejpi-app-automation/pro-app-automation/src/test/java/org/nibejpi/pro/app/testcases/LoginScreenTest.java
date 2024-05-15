package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.settings.SettingsScreen;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class LoginScreenTest extends TestBase {
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private SettingsScreen settings;
	private LoginHelper loginHelper;

	@BeforeClass
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		loginHelper = new LoginHelper(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		settings = new SettingsScreen(driver);
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "ProLogin");
		loginHelper.performLoginSetup();

	}

	// Test to verify the UI of the login page
	@Test(priority = 1, description = "Verify the UI of the login page")
	@AzureTestCaseID(ID = { "4680" })
	public void testVerifyLoginPageUI() throws Exception {
		login.verifyElementsOnLoginScreen();
	}

	// Test to verify login with valid credentials
	@Test(priority = 2, description = "Verify login with valid credentials")
	@AzureTestCaseID(ID = { "4674" })
	public void testLoginWithValidCredentials() throws Exception {
		login.setUsername(ExcelUtils.getCellData(1, 1));
		login.setPassword(ExcelUtils.getCellData(1, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1, 3));
		login.verifySuccessfullLoginScreen();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnLogoutButton();
	}

	//Test to verify login with non-registered email and invalid password
	@Test(priority = 3, description = "Verify login with non-registered email and invalid password.")
	@AzureTestCaseID(ID = { "4675" })
	public void testLoginWithNonRegisteredEmailAndInvalidPassword() throws Exception {
		login.setUsername(ExcelUtils.getCellData(2, 1));
		login.setPassword(ExcelUtils.getCellData(2, 2));
		login.clickOnLoginButton();
		login.verifyLoginWithNonRegisteredEmailAndInvalidPassword();
		login.clickOnOkButton();
		login.clearField(ExcelUtils.getCellData(2, 1));
		login.clearField(ExcelUtils.getCellData(2, 2));
	}

	// Test to verify login with non-registered email and valid password
	@Test(priority = 4, description = "Verify login with non-registered email and valid password")
	@AzureTestCaseID(ID = { "4747" })
	public void testLoginWithNonRegisteredEmailAndValidPassword() throws Exception {
		login.setUsername(ExcelUtils.getCellData(3, 1));
		login.setPassword(ExcelUtils.getCellData(3, 2));
		login.clickOnLoginButton();
		login.verifyLoginWithNonRegisteredEmailAndValidPassword();
		login.clickOnOkButton();
		login.clearField(ExcelUtils.getCellData(3, 1));
		login.clearField(ExcelUtils.getCellData(3, 2));
	}

	// Test to verify login with empty fields
	@Test(priority = 5, description = "Verify login with empty fields")
	@AzureTestCaseID(ID = { "4676" })
	public void testLoginWithEmptyFields() throws Exception {
		login.setUsername(ExcelUtils.getCellData(4, 1));
		login.setPassword(ExcelUtils.getCellData(4, 2));
		login.clickOnLoginButton();
		login.verifyEmptyEmailFieldMessage();
		login.verifyEmptyPasswordFieldMessage();
	}

	// Test to verify login with invalid email format and valid password
	@Test(priority = 6, description = "Verify login with invalid email format and valid password.")
	@AzureTestCaseID(ID = { "4677" })
	public void testLoginWithInvalidEmailFormatAndValidPassword() throws Exception {
		login.setUsername(ExcelUtils.getCellData(5, 1));
		login.setPassword(ExcelUtils.getCellData(5, 2));
		login.clickOnLoginButton();
		login.verifyLoginWithInvalidEmailFormatValidPassword();
		login.clearField(ExcelUtils.getCellData(5, 1));
		login.clearField(ExcelUtils.getCellData(5, 2));
	}

	// Test to verify login with registered email and invalid password
	@Test(priority = 7, description = "Verify login with registered email and invalid password.")
	@AzureTestCaseID(ID = { "4678" })
	public void testLoginWithRegisteredEmailAndInvalidPassword() throws Exception {
		login.setUsername(ExcelUtils.getCellData(6, 1));
		login.setPassword(ExcelUtils.getCellData(6, 2));
		login.clickOnLoginButton();
		login.verifyLoginWithRegisteredEmailAndInvalidPassword();
		login.clickOnOkButton();
		login.clearField(ExcelUtils.getCellData(6, 1));
		login.clearField(ExcelUtils.getCellData(6, 2));
	}

	// Test to verify the logout functionality
	@Test(priority = 8, description = "Verify the logout functionality")
	@AzureTestCaseID(ID = { "4681" })
	public void testLogoutFunctionality() throws Exception {
		login.setUsername(ExcelUtils.getCellData(7, 1));
		login.setPassword(ExcelUtils.getCellData(7, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(7, 3));
		login.verifySuccessfullLoginScreen();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnLogoutButton();
		login.verifySuccessfulLogoutScreen();

	}

	// Test to verify that the user is able to login after logout
	@Test(priority = 9, description = "Verify that the user is able to login after logout")
	@AzureTestCaseID(ID = { "4682" })
	public void testLoginAfterLogout() throws Exception {
		login.setUsername(ExcelUtils.getCellData(8, 1));
		login.setPassword(ExcelUtils.getCellData(8, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(8, 3));
		login.verifySuccessfullLoginScreen();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnLogoutButton();
		login.verifySuccessfulLogoutScreen();
		login.setUsername(ExcelUtils.getCellData(8, 1));
		login.setPassword(ExcelUtils.getCellData(8, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(8, 3));
		login.verifySuccessfullLoginScreen();
	}

}

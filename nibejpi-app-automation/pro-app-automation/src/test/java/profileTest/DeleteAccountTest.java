package profileTest;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.profile.DeleteAccountScreen;
import org.nibejpi.pro.app.auto.pages.profile.ProfileScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.settings.SettingsScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAccountTest extends TestBase
{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private SettingsScreen settings;
	private ProfileScreen profile;
	private DeleteAccountScreen deleteaccount;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		settings = new SettingsScreen(driver);
		profile = new ProfileScreen(driver);
		deleteaccount = new DeleteAccountScreen(driver);
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Profile");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(15, 1));
		login.setPassword(ExcelUtils.getCellData(15, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(15, 3));
	}

	@Test(priority = 1, description = "Verify Delete account screen UI.")
	@AzureTestCaseID(ID = { "5169" })
	public void verifyDeleteAccountScreenUI()
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnDeleteAccount();
		deleteaccount.verifyDeleteAccountScreenUI();
	}

	@Test(priority = 2, description = "Verify Delete account with empty password field.")
	@AzureTestCaseID(ID = { "1430" })
	public void verifyDeleteAccountWithEmptyPasswordField()
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnDeleteAccount();
		deleteaccount.clickDeleteAccountButton();
		deleteaccount.verifyEmptyPassowordFieldValidationMessageIsDisplayed();
	}
	
	@Test(priority = 3, description = "Verify delete account with incorrect password. ")
	@AzureTestCaseID(ID = { "1431" })
	public void verifyDeleteAccountWithIncorrectPassword() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnDeleteAccount();
		deleteaccount.enterPassword(ExcelUtils.getCellData(17, 4));
		deleteaccount.clickDeleteAccountButton();
		deleteaccount.verifyIncorrectPasswordErrorMessageIsDisplayed();
	}
	
	@Test(priority = 4, description = "Verify Delete account cancellation process.")
	@AzureTestCaseID(ID = { "5170" })
	public void verifyDeleteAccountCancellationProcess() throws Exception
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnDeleteAccount();
		deleteaccount.enterPassword(ExcelUtils.getCellData(18, 4));
		deleteaccount.clickDeleteAccountButton();
		//APPS-2573
		deleteaccount.verifyDeleteAccountCancellationProcessByCancelButtonOnAlert();
	}
}


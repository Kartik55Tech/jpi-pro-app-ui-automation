package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.about.AboutScreenAfterLogin;
import org.nibejpi.pro.app.auto.pages.about.AboutScreenBeforeLogin;
import org.nibejpi.pro.app.auto.pages.appearance.AppearanceScreen;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.settings.SettingsScreen;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class AboutSectionTest extends TestBase {

	private LoginScreen login;
	private LoginHelper loginhelper;
	private AboutScreenBeforeLogin aboutBeforeLogin;
	private AboutScreenAfterLogin aboutAfterLogin;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private SettingsScreen settings; 
	private AppearanceScreen appearance;   

	@BeforeClass
	public void Setup() throws Exception {
		login = new LoginScreen(driver);
		loginhelper = new LoginHelper(driver);
		aboutBeforeLogin = new AboutScreenBeforeLogin(driver);
		aboutAfterLogin = new AboutScreenAfterLogin(driver);  
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		settings = new SettingsScreen(driver);
		appearance = new AppearanceScreen(driver);
		loginhelper.performLoginSetup();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "About");
	}
	
	/**
	 * Test Case: Verify UI of About Section at login screen.
	 * 
	 * This test case navigates to the About Section on the login screen and
	 * verifies the user interface of the About Section.
	 * 
	 * Test Steps:
	 * 1. Click on the "About" button on the login screen.
	 * 2. Verify the user interface of the About Section.
	 */
	@Test(priority = 1, description = "Verify UI of About Section at login screen .")
	@AzureTestCaseID(ID = { "5042" })
	public void verifyUIofAboutAtLoginScreen() {
		login.tapOnAboutButton();
		aboutBeforeLogin.verifyUIofAboutSectionAtLoginScreen();
	}
	
	/**
	 * Test Case: Verify UI of About Section at login screen for different languages.
	 * 
	 * This test case navigates to the About Section on the login screen, changes the
	 * language, and verifies the user interface changes for different languages.
	 * 
	 * Test Steps: 
	 * 1. Click on the language dropdown.
	 * 2. Verify the user interface changes after language selection.
	 * 
	 * @throws InterruptedException If the test is interrupted during execution.
	 */
	@Test(priority = 2, description = "Verify UI of About Section at login screen for different languages."
			, dependsOnMethods = {"verifyUIofAboutAtLoginScreen"})
	@AzureTestCaseID(ID = { "5043" })
	public void verifyUIofAboutAtLoginScreenForDifferentLanguages() throws InterruptedException {
		aboutBeforeLogin.clickOnLanguageDrpDown();
		aboutBeforeLogin.verifyAboutScreenUIChangesAfterLanguageSelection();
		aboutBeforeLogin.selectLanguage("English");
	}

	/**
	 * Test Case: Verify navigation and content of Terms of Service (Account) before login.
	 * 
	 * This test case verifies the navigation and content of the "Terms of Service" (Account) section
	 * on the About screen before logging in.
	 * 
	 * Test Steps:
	 * 1. Click on "Terms of Service" (Account).
	 * 2. Verify the navigation and content of the "Terms of Service" (Account) screen.
	 * 
	 * @throws InterruptedException If the test is interrupted during execution.
	 */
	@Test(priority = 3, description = "Verify navigation and content of Terms of Service (Account)."
			, dependsOnMethods = {"verifyUIofAboutAtLoginScreenForDifferentLanguages"})
	@AzureTestCaseID(ID = { "5044" })
	public void verifyNavigationAndContentOfTOSAccountBeforeLogin() throws InterruptedException {
		aboutBeforeLogin.clickOnTOSAccount();
		aboutBeforeLogin.verifyNavigationAndContentOfTOSAccount();
	}

	/**
	 * Test Case: Verify navigation and content of Privacy Policy (Account) before login.
	 * 
	 * This test case verifies the navigation and content of the "Privacy Policy" (Account) section
	 * on the About screen before logging in.
	 * 
	 * Test Steps:
	 * 1. Click on "Privacy Policy" (Account).
	 * 2. Verify the navigation and content of the "Privacy Policy" (Account) screen.
	 * 
	 * @throws InterruptedException If the test is interrupted during execution.
	 */
	@Test(priority = 4, description = "Verify navigation and content of Privacy policy (Account)."
			, dependsOnMethods = {"verifyNavigationAndContentOfTOSAccountBeforeLogin"})
	@AzureTestCaseID(ID = { "5045" })
	public void verifyNavigationAndContentOfPPAccount() throws InterruptedException {
		aboutBeforeLogin.clickOnPrivacyPolicy_Account();
		aboutBeforeLogin.verifyNavigationAndContentOfPPAccount();
	}

	/**
	 * Test Case: Verify navigation and content of Terms of Service (Organization) before login.
	 * 
	 * This test case verifies the navigation and content of the "Terms of Service" (Organization) section
	 * on the About screen before logging in.
	 * 
	 * Test Steps:
	 * 1. Click on "Terms of Service" (Organization).
	 * 2. Verify the navigation and content of the "Terms of Service" (Organization) screen.
	 * 
	 * @throws InterruptedException If the test is interrupted during execution.
	 */
	@Test(priority = 5, description = "Verify navigation and content of Terms of Service (Organization)."
			,dependsOnMethods = {"verifyNavigationAndContentOfPPAccount"},alwaysRun = true)
	@AzureTestCaseID(ID = { "5046" })
	public void verifyNavigationAndContentOfTOSOrg() throws InterruptedException {
		aboutBeforeLogin.clickOnTermsOfServiceOrg();
		aboutBeforeLogin.verifyNavigationAndContentOfTOSOrg();
	}

	/**
	 * Test Case: Verify navigation and content of Open Source Licenses before login.
	 * 
	 * This test case verifies the navigation and content of the "Open Source Licenses" section
	 * on the About screen before logging in.
	 * 
	 * Test Steps:
	 * 1. Click on the "About" button on the login screen.
	 * 2. Click on "Open Source Licenses".
	 * 3. Verify the navigation and content of the "Open Source Licenses" screen.
	 * 
	 * @throws InterruptedException If the test is interrupted during execution.
	 * 
	 */
	@Test(priority = 6, description = "Verify navigation and content of Open Source Licenses."
			, dependsOnMethods = {"verifyNavigationAndContentOfTOSOrg"})
	@AzureTestCaseID(ID = { "5047" })
	public void verifyNavigationAndContentOfOpenSourceLicenses() throws InterruptedException {
		aboutBeforeLogin.clickOnOpenSourceLicenses();
		aboutBeforeLogin.verifyNavigationAndContentOfOpenSrcLicenses();
	}
	
	/**
	 * Test Case: Verify localization support for Terms of Service, Privacy Policy, and Open Source Licenses before login.
	 * 
	 * This test case verifies the localization support for "Terms of Service," "Privacy Policy," and
	 * "Open Source Licenses" on the About screen before logging in.
	 * 
	 * Test Steps:
	 * 1. Click on the "About" button on the login screen.
	 * 2. Verify the localization support for the mentioned sections.
	 * 
	 * @throws InterruptedException If the test is interrupted during execution.
	 */
	@Test(priority = 7, description = "Verify localization support for TOS, Privacy Policy, and Open Source Licenses before login."
			, dependsOnMethods = {"verifyNavigationAndContentOfOpenSourceLicenses"})
	@AzureTestCaseID(ID = { "5049" })
	public void verifyLocalizationSupportForTOS_PP_OpenSrcLicenses() throws InterruptedException {
		aboutBeforeLogin.verifyLocalizationSupportForTOS_PP_OpenSrcLicenses();
		aboutBeforeLogin.navigateBack();
	}
	
	/**
	 * Test Case: Verify UI of About Section after login.
	 * 
	 * This test case verifies the user interface of the "About" section after successfully logging in.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "About" screen via the hamburger menu and settings.
	 * 4. Verify the user interface of the "About" screen.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 8, description = "Verify UI of About Section after login."
			, dependsOnMethods = {"verifyLocalizationSupportForTOS_PP_OpenSrcLicenses"})
	@AzureTestCaseID(ID = { "5050" })
	public void verifyUIofAboutSectionAfterLogin() throws Exception {
		login.setUsername(ExcelUtils.getCellData(9, 1));
		login.setPassword(ExcelUtils.getCellData(9, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(9, 3));
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyUIofAboutScreenAfterLogin();
	}
	
	/**
	 * Test Case: Verify navigation and content of Terms of Service (Account) after login.
	 * 
	 * This test case verifies the navigation and content of the "Terms of Service (Account)" section
	 * on the "About" screen after successfully logging in.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "About" screen via the hamburger menu and settings.
	 * 4. Click on "Terms of Service (Account)".
	 * 5. Verify the navigation and content of the "Terms of Service (Account)" screen.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 9, description = "Verify navigation and content of Terms of Service (Account) after login.")
	@AzureTestCaseID(ID = { "5051" })
	public void verifyNavigationAndContentOfTOSAccountAfterLogin() throws Exception {
		aboutAfterLogin.clickOnTOSAccount();
		aboutAfterLogin.verifyTOSAccountScreenContentAndNavigation();
	}

	/**
	 * Test Case: Verify navigation and content of Privacy Policy (Account) after login.
	 * 
	 * This test case verifies the navigation and content of the "Privacy Policy (Account)" section
	 * on the "About" screen after successfully logging in.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "About" screen via the hamburger menu and settings.
	 * 4. Click on "Privacy Policy (Account)".
	 * 5. Verify the navigation and content of the "Privacy Policy (Account)" screen.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 10, description = "Verify navigation and content of Privacy policy (Account)after login.")
	@AzureTestCaseID(ID = { "5052" })
	public void verifyNavigationAndContentOfPPAccountAfterLogin() throws Exception {
		aboutAfterLogin.clickOnPrivacyPolicy_Account();
		aboutAfterLogin.verifyNavigationAndContentOfPPAccount();
	}
 
	/**
	 * Test Case: Verify navigation and content of Terms of Service (Organization) after login.
	 * 
	 * This test case verifies the navigation and content of the "Terms of Service (Organization)" section
	 * on the "About" screen after successfully logging in.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "About" screen via the hamburger menu and settings.
	 * 4. Click on "Terms of Service (Organization)".
	 * 5. Verify the navigation and content of the "Terms of Service (Organization)" screen.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 11, description = "Verify navigation and content of Terms of Service (Organization)after login.")
	@AzureTestCaseID(ID = { "5053" })
	public void verifyNavigationAndContentOfTOSOrgAfterLogin() throws Exception {
		aboutAfterLogin.clickOnTermsOfServiceOrg();
		aboutAfterLogin.verifyNavigationAndContentOfTOSOrg();
	}

	/**
	 * Test Case: Verify navigation and content of Open Source Licenses after login.
	 * 
	 * This test case verifies the navigation and content of the "Open Source Licenses" section
	 * on the "About" screen after successfully logging in.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "About" screen via the hamburger menu and settings.
	 * 4. Click on "Open Source Licenses".
	 * 5. Verify the navigation and content of the "Open Source Licenses" screen.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 12, description = "Verify navigation and content of Open Source Licenses after Login.")
	@AzureTestCaseID(ID = { "5054" })
	public void verifyNavigationAndContentOfOpenSourceLicensesAfterLogin() throws Exception {
		aboutAfterLogin.clickOnOpenSourceLicenses();
		aboutAfterLogin.verifyNavigationAndContentOfOpenSrcLicenses();
	}

	/**
	 * Test Case: Verify UI of About Section after login for different languages.
	 * 
	 * This test case verifies the user interface of the "About" section after successfully logging in
	 * with different language settings.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "Appearance" screen from settings.
	 * 4. Change the language to the specified language.
	 * 5. Open the "About" screen via the hamburger menu and settings.
	 * 6. Verify the "About" section UI for the specified language.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 13, description = "Verify UI of About Section after login for different language.")
	@AzureTestCaseID(ID = { "5055" })
	public void verifyUIofAboutSectionAfterLoginForDifferentLanguages() throws Exception {
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Norsk");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyAboutSectionForLanguage("Norsk");
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Svenska");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyAboutSectionForLanguage("Svenska");
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Danish");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyAboutSectionForLanguage("Danish");
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Deutsch");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyAboutSectionForLanguage("Deutsch");
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Français");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyAboutSectionForLanguage("Français");
	}

	/**
	 * Test Case: Verify localization support for Terms of Service, Privacy Policy, and Open Source Licenses after login.
	 * 
	 * This test case verifies the localization support for the "Terms of Service," "Privacy Policy," and
	 * "Open Source Licenses" sections on the "About" screen after successfully logging in with different languages.
	 * 
	 * Test Steps:
	 * 1. Login with valid credentials.
	 * 2. Set the service partner name.
	 * 3. Open the "Appearance" screen from settings.
	 * 4. Change the language to the specified language.
	 * 5. Open the "About" screen via the hamburger menu and settings.
	 * 6. Verify localization support for the specified language.
	 * 
	 * @throws Exception If any exception occurs during the test.
	 */
	@Test(priority = 14, description = "Verify localization support for TOS, Privacy Policy, and Open Source Licenses after login.")
	@AzureTestCaseID(ID = { "5056" })
	public void verifyLocalizationSupportForTOS_PP_OpenSrcLicensesAfterLogin() throws Exception {
		aboutAfterLogin.verifyLocalizationSupportForFrançaisLanguage();
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Norsk");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyLocalizationSupportForNorskLanguage();
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Svenska");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyLocalizationSupportForSvenskaLanguage();
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Danish");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyLocalizationSupportForDanishLanguage();
		aboutAfterLogin.navigateBack();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Deutsch");
		appearance.navigateBack();
		settings.clickOnAboutMyUplinkButton();
		aboutAfterLogin.verifyLocalizationSupportForDeutschLanguage();
	}

}

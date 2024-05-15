package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.about.AboutScreenBeforeLogin;
import org.nibejpi.pro.app.auto.pages.appearance.AppearanceScreen;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.help.HelpAfterLogin;
import org.nibejpi.pro.app.auto.pages.help.HelpBeforeLogin;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.settings.SettingsScreen;
import org.testng.annotations.*;

public class HelpSectionTest extends TestBase {
	private LoginScreen login;
	private LoginHelper loginhelper;
	private HelpBeforeLogin helpBeforeLogin;
	private AboutScreenBeforeLogin aboutBeforeLogin;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private SettingsScreen settings;
	private AppearanceScreen appearance;
	private HelpAfterLogin helpAfterLogin;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		loginhelper = new LoginHelper(driver);
		helpBeforeLogin = new HelpBeforeLogin(driver);
		aboutBeforeLogin = new AboutScreenBeforeLogin(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		settings = new SettingsScreen(driver);
		appearance = new AppearanceScreen(driver);
		helpAfterLogin = new HelpAfterLogin(driver);
		loginhelper.performLoginSetup();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Help");
	}
	
	/**
	 * Test Case: Verify UI of Help Section at login screen.
	 * 
	 * This test case verifies the user interface of the Help Section on the login
	 * screen.
	 */
	@Test(priority = 1, description = "Verify UI of Help Section at login screen .")
	@AzureTestCaseID(ID = { "5101" })
	public void verifyUIofHelpSectionAtLoginScreen() throws Exception {
		login.tapOnHelpButton();
		helpBeforeLogin.verifyUIOfHelpScreenForEnglish();
	}

	/**
	 * Test Case: Verify localization support for Help Section at login screen.
	 * 
	 * This test case verifies the localization support for the Help Section on the
	 * login screen.
	 */
	@Test(priority = 2, description = "Verify localization support for Help Section at login screen.")
	@AzureTestCaseID(ID = { "5102" })
	public void verifyLocalizationSupportForHelpSectionAtLoginScreen() throws Exception {
		login.tapOnAboutButton();
		aboutBeforeLogin.clickOnLanguageDrpDown();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(2, 4));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyUIOfHelpScreenForFrançais();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(2, 5));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyUIOfHelpScreenForNorsk();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(2, 6));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyUIOfHelpScreenForSvenska();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(2, 7));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyUIOfHelpScreenForDanish();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(2, 8));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyUIOfHelpScreenForDeutsch();
	}

	/**
	 * Test Case: Verify navigation and localization support for content of FAQ's.
	 * 
	 * This test case verifies the navigation and localization support for the
	 * content of FAQs.
	 * @throws Exception 
	 */
	@Test(priority = 3, description = "Verify navigation and localization support for content of FAQ's ")
	@AzureTestCaseID(ID = { "5103" })
	public void verifyNavigationAndLocalizationSupportForContentOfFaqs() throws Exception {
		login.tapOnHelpButton();
		helpBeforeLogin.verifyContentOfFAQsForEnglish();
		helpBeforeLogin.navigateBack();
		login.tapOnAboutButton();
		aboutBeforeLogin.clickOnLanguageDrpDown();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(3, 4));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyContentOfFAQsForFrançais();
		helpBeforeLogin.navigateBack();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(3, 5));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyContentOfFAQsForNorsk();
		helpBeforeLogin.navigateBack();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(3, 6));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyContentOfFAQsForSvenska();
		helpBeforeLogin.navigateBack();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(3, 7));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyContentOfFAQsForDanish();
		helpBeforeLogin.navigateBack();
		login.tapOnAboutButton();
		aboutBeforeLogin.selectLanguage(ExcelUtils.getCellData(3, 8));
		aboutBeforeLogin.navigateBack();
		login.tapOnHelpButton();
		helpBeforeLogin.verifyContentOfFAQsForDeutsch();
	}

	/**
	 * Test Case: Verify UI of Help Section after login.
	 * 
	 * This test case verifies the user interface of the Help Section after logging
	 * in.
	 */
	@Test(priority = 4, description = "Verify UI of Help Section after login.")
	@AzureTestCaseID(ID = { "5104" })
	public void verifyUIofHelpSectionAfterLogin() throws Exception {
		login.setUsername(ExcelUtils.getCellData(4, 1));
		login.setPassword(ExcelUtils.getCellData(4, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(4, 3));
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyUIofHelpScreenForEnglishAfterLogin();
	}

	/**
	 * Test Case: Verify navigation and localization support for content of FAQ's
	 * after login.
	 * 
	 * This test case verifies the navigation and localization support for the
	 * content of FAQs after logging in.
	 */
	@Test(priority = 5, description = "Verify navigation and localization support for content of FAQ's after login.")
	@AzureTestCaseID(ID = { "5105" })
	public void VerifyNavigationLocalizationSupportForFAQsAfterLogin() throws Exception {
		login.setUsername(ExcelUtils.getCellData(4, 1));
		login.setPassword(ExcelUtils.getCellData(4, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(4, 3));
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentOfFAQsAfterLogin("en-US","Nibe");
		helpAfterLogin.navigateBack();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Français");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentOfFAQsAfterLogin("fr-FR","Nibe");
		helpAfterLogin.navigateBack();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Norsk");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentOfFAQsForNorskAfterLogin();
		helpAfterLogin.navigateBack();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Svenska");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentOfFAQsForSvenskaAfterLogin();
		helpAfterLogin.navigateBack();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Danish");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentOfFAQsForDanishAfterLogin();
		helpAfterLogin.navigateBack();
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Deutsch");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentOfFAQsForDeutschAfterLogin();
	}

	/**
	 * Test Case: Verify customer support.
	 * 
	 * This test case verifies the customer support functionality.
	 */
	@Test(priority = 6, description = "Verify customer support.")
	@AzureTestCaseID(ID = { "5106" })
	public void testCustomerSupport() throws Exception {
		login.setUsername(ExcelUtils.getCellData(4, 1));
		login.setPassword(ExcelUtils.getCellData(4, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(4, 3));
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.clickOnCustomerSupport("Customer support");
		Thread.sleep(2000);
		helpAfterLogin.selectTopic("Online Store");
		helpAfterLogin.selectSystem("namrata-simulator");
		// helpAL.enterEmailCS();
		helpAfterLogin.enterSummary("This is Mobile Automation Test");
		helpAfterLogin.enterDescription("This is Mobile Automation Test");
		helpAfterLogin.clickonSendButtonAtCustomerSupport();

		// Bug fix : APPS - 2530
	}

	/**
	 * Test Case: Verify customer support with empty fields.
	 * 
	 * This test case verifies the customer support functionality with empty fields.
	 */
	@Test(priority = 7, description = "Verify customer support with empty fields.")
	@AzureTestCaseID(ID = { "5107" })
	public void testCustomerSupportWithEmptyFields() throws Exception {
		login.setUsername(ExcelUtils.getCellData(4, 1));
		login.setPassword(ExcelUtils.getCellData(4, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(4, 3));
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.clickOnCustomerSupport("Customer support");
		helpAfterLogin.verifyCustomerSupportWithEmptyfields();
	}

	/**
	 * Test Case: Verify localization support for Customer Support.
	 * 
	 * This test case verifies the localization support for customer support.
	 */
	@Test(priority = 8, description = "Verify localization support for Customer Support.")
	@AzureTestCaseID(ID = { "5108" })
	public void testLocalizationSupportForCustomerSupport() throws Exception {
		login.setUsername(ExcelUtils.getCellData(4, 1));
		login.setPassword(ExcelUtils.getCellData(4, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(4, 3));
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Français");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.clickOnCustomerSupport("Service client");
		helpAfterLogin.verifyLocalizationSupportForCustomerSupportinFrançais();
	}

	/**
	 * Test Case: Verify Help section for other brands.
	 * 
	 * This test case verifies the Help Section for other brands.
	 */
	@Test(priority = 9, description = "Verify Help section for other brands.")
	@AzureTestCaseID(ID = { "5109" })
	public void testHelpSectionForOtherBrands() throws Exception {
		login.setUsername(ExcelUtils.getCellData(9, 1));
		login.setPassword(ExcelUtils.getCellData(9, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(9, 3));
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentForFAQsOfContura();
		// Bug fix : APPS-2539

	}

	/**
	 * Test Case: Verify localization support in Help section for other brands.
	 * 
	 * This test case verifies the localization support in the Help Section for
	 * other brands.
	 */
	@Test(priority = 10, description = "Verify localization support in Help section for other brands.")
	@AzureTestCaseID(ID = { "5110" })
	public void testLocalizationSupportInHelpSectionForOtherBrands() throws Exception {
		login.setUsername(ExcelUtils.getCellData(9, 1));
		login.setPassword(ExcelUtils.getCellData(9, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(9, 3));
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnAppearanceButton();
		appearance.clickOnLanguageDrpDwn();
		appearance.selectLanguage("Svenska");
		appearance.navigateBack();
		settings.navigateBack();
		system.tapHamburgerMenu();
		system.tapHelpButton();
		helpAfterLogin.verifyNavigationAndContentForFAQsOfConturaInSvenska();
	}

}

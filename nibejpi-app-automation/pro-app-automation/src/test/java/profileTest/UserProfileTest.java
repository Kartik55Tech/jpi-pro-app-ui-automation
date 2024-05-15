
package profileTest;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.profile.ProfileScreen;
import org.nibejpi.pro.app.auto.pages.profile.UserProfileScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.settings.SettingsScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserProfileTest extends TestBase

{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private SettingsScreen settings;
	private ProfileScreen profile;
	private UserProfileScreen userprofile;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		settings = new SettingsScreen(driver);
		profile = new ProfileScreen(driver);
		userprofile = new UserProfileScreen(driver);

		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA,"Profile");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(1, 1));
		login.setPassword(ExcelUtils.getCellData(1, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1, 3));
	}

	@Test(priority = 1, description = "Verify user is able to update personal details.")
	@AzureTestCaseID(ID = { "3891" })
	public void testUpdatePersonalDetails() throws Exception {
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnUserProfile();
		userprofile.setFullName(ExcelUtils.getCellData(1, 4));
		userprofile.setAddressLine1(ExcelUtils.getCellData(1, 5));
		userprofile.setAddressLine2(ExcelUtils.getCellData(1, 6));
		userprofile.setZipCode(ExcelUtils.getCellData(1, 7));
		userprofile.setCity(ExcelUtils.getCellData(1, 8));
		userprofile.selectCountry(ExcelUtils.getCellData(1, 10));
		userprofile.selectState(ExcelUtils.getCellData(1, 9));
		userprofile.setEmail(ExcelUtils.getCellData(1, 11));
		userprofile.clickOnSaveButton();
		userprofile.clickOnOkButton();
		profile.navigateBack();
		settings.clickOnProfileButton();
		profile.clickOnUserProfile();
		userprofile.verifyChangesAreUpdatedInUserProfile(ExcelUtils.getCellData(1, 4), ExcelUtils.getCellData(1, 5),
				ExcelUtils.getCellData(1, 6), ExcelUtils.getCellData(1, 7), ExcelUtils.getCellData(1, 8),
				ExcelUtils.getCellData(1, 9), ExcelUtils.getCellData(1, 10), ExcelUtils.getCellData(1, 11));

	}

	@Test(priority = 2, description = "Verify Error Messages for Empty Input Fields")
	@AzureTestCaseID(ID = { "3893" })
	public void verifyErrorMessagesForEmptyFields() throws Exception 
	{
		system.tapHamburgerMenu();
		system.tapSettings();
		settings.clickOnProfileButton();
		profile.clickOnUserProfile();
		userprofile.setFullName(ExcelUtils.getCellData(2, 4));
		userprofile.setAddressLine1(ExcelUtils.getCellData(2, 5));
		userprofile.setAddressLine2(ExcelUtils.getCellData(2, 6));
		userprofile.setZipCode(ExcelUtils.getCellData(2, 7));
		userprofile.setCity(ExcelUtils.getCellData(2, 8));
		userprofile.selectCountry(ExcelUtils.getCellData(2, 10));
		userprofile.selectState(ExcelUtils.getCellData(2, 9));
		userprofile.setEmail(ExcelUtils.getCellData(2, 11));
		userprofile.clickOnSaveButton();
		userprofile.verifyErrorMessageIsDisplayedForEmptyFields();
	}
	
}

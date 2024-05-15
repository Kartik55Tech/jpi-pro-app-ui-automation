package ServicePartnerProfile;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.ProfileScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.ServicePartnerProfileScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class ProfileTest extends TestBase
{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system; 
	private ServicePartnerProfileScreen spprofile;
	private ProfileScreen profile;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		spprofile = new ServicePartnerProfileScreen(driver);
		profile = new ProfileScreen(driver);
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA,"EditSP");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(1, 1));
		login.setPassword(ExcelUtils.getCellData(1, 2));
		login.clickOnLoginButton();
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1, 3));
		system.tapHamburgerMenu();
		system.tapProfile();
	}

	@Test(priority = 1, description = "Verify Service partner profile screen UI.")
	@AzureTestCaseID(ID = { "2426" })
	public void verifyServicePartnerProfileScreenUI()
	{
		spprofile.verifySPProfileScreenUI();
	}
	
	@Test(priority = 2, description = "Verify Service partner name .")
	@AzureTestCaseID(ID = { "3875" })
	public void verifyServicePartnerNameMatchesData() throws Exception
	{
		spprofile.clickOnProfile();
		profile.verifySpName(ExcelUtils.getCellData(3, 3));
	}
	
	@Test(priority = 3, description = "Verify Address details of the service partner.")
	@AzureTestCaseID(ID = { "3876" })
	public void verifyServicePartnerAddressDetails() throws Exception
	{
		spprofile.clickOnProfile();
		profile.verifyAddressLine1(ExcelUtils.getCellData(3, 4));
		profile.verifyPostalCode(ExcelUtils.getCellData(3, 6));
		profile.verifyCity(ExcelUtils.getCellData(3, 7));
		profile.verifyCountry(ExcelUtils.getCellData(3, 9));
	}
	
	@Test(priority = 4, description = "Verify Contact details of service partner")
	@AzureTestCaseID(ID = { "3877" })
	public void verifyServicePartnerContactDetails() throws Exception
	{
		spprofile.clickOnProfile();
		profile.verifyEmail(ExcelUtils.getCellData(4, 10));
		profile.verifyName(ExcelUtils.getCellData(4, 11));
		profile.verifyPhoneNumber(ExcelUtils.getCellData(4, 12));
	}
	
	@Test(priority = 5, description = "Verify Brand details of service partner.")
	@AzureTestCaseID(ID = { "5193" })
	public void verifyServicePartnerBrandDetails() throws Exception
	{
		spprofile.clickOnProfile();
		profile.verifyBrandName(ExcelUtils.getCellData(5, 13));
		profile.verifyVATnumber(ExcelUtils.getCellData(5, 14));
	}
	
	@Test(priority = 6, description = "Verify user is able to update Service partner profile.")
	@AzureTestCaseID(ID = { "3878" })
	public void testUpdateServicePartnerProfile() throws Exception
	{
		spprofile.clickOnProfile();
		profile.setServicePartnerName(ExcelUtils.getCellData(6, 3));
		profile.setAddressLine1(ExcelUtils.getCellData(6, 4));
		profile.setAddressLine2(ExcelUtils.getCellData(6, 5));
		profile.setPostalCode(ExcelUtils.getCellData(6, 6));
		profile.setCity(ExcelUtils.getCellData(6, 7));
		profile.selectCountry(ExcelUtils.getCellData(6, 9));
		profile.selectState(ExcelUtils.getCellData(6, 8));
		profile.setEmail(ExcelUtils.getCellData(6, 10));
		profile.setName(ExcelUtils.getCellData(6, 11));
		profile.setPhoneNumber(ExcelUtils.getCellData(6, 12));
		profile.clickOnSaveButton();
		profile.verifyToastMessageIsVisibleAfterUpdatingServicePartnerSuccessfully();
		profile.navigateBack();
		spprofile.clickOnProfile();
		profile.verifyServicePartnerDetailsAreUpdated();
		profile.setServicePartnerName(ExcelUtils.getCellData(1, 3));
		profile.clickOnSaveButton();
	}
	
	@Test(priority = 7, description = "Verify empty field validations while updating Service partner profile.")
	@AzureTestCaseID(ID = { "3879" })
	public void testServicePartnerProfileEmptyFieldValidations() throws Exception
	{
		spprofile.clickOnProfile();
		profile.setServicePartnerName(ExcelUtils.getCellData(7, 3));
		profile.setAddressLine1(ExcelUtils.getCellData(7, 4));
		profile.clickOnSaveButton();
		profile.verifyEmptyFieldValidationsAreDisplayed();
	}
	
	
}

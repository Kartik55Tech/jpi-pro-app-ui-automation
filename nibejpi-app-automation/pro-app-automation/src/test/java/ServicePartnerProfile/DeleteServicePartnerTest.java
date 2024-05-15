package ServicePartnerProfile;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.DeleteServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.ServicePartnerProfileScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class DeleteServicePartnerTest extends TestBase
{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system; 
	private ServicePartnerProfileScreen spprofile;
	private DeleteServicePartnerScreen deletesp;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		spprofile = new ServicePartnerProfileScreen(driver);
		deletesp = new DeleteServicePartnerScreen(driver);
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "EditSP");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(14, 1));
		login.setPassword(ExcelUtils.getCellData(14, 2));
		login.clickOnLoginButton();
		
	}
	
	@Test(priority = 1, description = "Verify Delete Service Partner Functionality for multiple user")
	@AzureTestCaseID(ID = { "2428" })
	public void testDeleteServicePartnerFunctionalityForMutipleUser() throws Exception
	{
		spscreen.setServicePartnerName(ExcelUtils.getCellData(14, 3));
		system.tapHamburgerMenu();
		system.tapProfile();
		spprofile.clickOnDelete();
		deletesp.verifyDeleteServicePartnerButtonIsNotDisplayedWhenMultipleUsersAreAdded();  
	}
	
	@Test(priority = 2, description = "Verify Delete Service Partner Functionality for Single user")
	@AzureTestCaseID(ID = { "2428" })
	public void testDeleteServicePartnerFunctionalityForSingleUser() throws Exception
	{
		spscreen.setServicePartnerName(ExcelUtils.getCellData(15, 3));
		system.tapHamburgerMenu();
		system.tapProfile();
		spprofile.clickOnDelete();
		deletesp.verifyDeleteServicePartnerButtonIsDisplayedForSingleUsers();
		deletesp.clickOnDeleteButton();
		deletesp.verifyAlertPopupIsDisplayedAfterClickingDeleteButton();
		deletesp.enterPassword(ExcelUtils.getCellData(15, 4));
		deletesp.clickDeleteButtonOnAlert();
		deletesp.verifyInvalidPasswordErrorPopupIsDisplayedForIncorrectPassword();
		deletesp.clickOnOkButtonOnAlert();
		deletesp.clickOnDeleteButton();
		deletesp.clickOnCancelButton();
		
	}
	
}

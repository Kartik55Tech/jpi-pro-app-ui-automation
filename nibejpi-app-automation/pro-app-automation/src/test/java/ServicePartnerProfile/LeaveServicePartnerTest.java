package ServicePartnerProfile;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.LeaveServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.ServicePartnerProfileScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class LeaveServicePartnerTest extends TestBase
{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system; 
	private ServicePartnerProfileScreen spprofile;
	private LeaveServicePartnerScreen leavesp;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		spprofile = new ServicePartnerProfileScreen(driver);
		leavesp = new LeaveServicePartnerScreen(driver);
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA,"EditSP");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(10, 1));
		login.setPassword(ExcelUtils.getCellData(10, 2));
		login.clickOnLoginButton();
		
	}
	
	@Test(priority = 1, description = "Verify Leave Service Partner Functionality for single user")
	@AzureTestCaseID(ID = { "2427" })
	public void testLeaveServicePartnerFunctionalityForSingleUser() throws Exception
	{
		spscreen.setServicePartnerName(ExcelUtils.getCellData(10, 3));
		system.tapHamburgerMenu();
		system.tapProfile();
		spprofile.clickOnLeave();
		leavesp.verifyLeaveServicePartnerButtonIsNotDisplayedForSingleUser();              
	}
	
	@Test(priority = 2, description = "Verify Leave Service Partner Functionality with multiple user.")
	@AzureTestCaseID(ID = { "2427" })
	public void testLeaveServicePartnerFunctionalityForMultipleUser() throws Exception
	{
		spscreen.setServicePartnerName(ExcelUtils.getCellData(11, 3));
		system.tapHamburgerMenu();
		system.tapProfile();
		spprofile.clickOnLeave();
		leavesp.clickOnLeaveButton();
		leavesp.verifyAlertPopupIsDisplayedAfterClickingLeaveButton();
		leavesp.enterPassword(ExcelUtils.getCellData(11, 4));
		leavesp.clickOnLeaveAlertButton();
		leavesp.verifyInvalidPasswordErrorPopupIsDisplayedForIncorrectPassword();
		leavesp.clickOnOkButtonOnAlert();
		leavesp.clickOnLeaveButton();
		leavesp.enterPassword(ExcelUtils.getCellData(11, 4));
		leavesp.clickOnCancelButton();
		
	}
}

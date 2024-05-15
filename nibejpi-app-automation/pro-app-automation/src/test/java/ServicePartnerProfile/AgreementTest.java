package ServicePartnerProfile;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.AgreementScreen;
import org.nibejpi.pro.app.auto.pages.editspprofile.ServicePartnerProfileScreen;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class AgreementTest extends TestBase
{
	private LoginScreen login;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system; 
	private ServicePartnerProfileScreen spprofile;
	private AgreementScreen agreement;

	@BeforeMethod
	public void setUp() throws Exception {
		login = new LoginScreen(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		spprofile = new ServicePartnerProfileScreen(driver);
		agreement = new AgreementScreen(driver);
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "EditSP");
		login.tapOnNextButton();
		login.tapOnMyUplinkLogo();
		login.selectEnvironment();
		login.setUsername(ExcelUtils.getCellData(10, 1));
		login.setPassword(ExcelUtils.getCellData(10, 2));
		login.clickOnLoginButton();
		
	}
	
	@Test(priority = 1, description = "Verify Agreement screen UI.")
	@AzureTestCaseID(ID = { "5194" })
	public void verifyAgreementScreenUI() throws Exception
	{
		spscreen.setServicePartnerName(ExcelUtils.getCellData(10, 3));
		system.tapHamburgerMenu();
		system.tapProfile();
		spprofile.clickOnAgreement();
		agreement.verifyAgreementScreenIsDisplayed();
	}
		
}

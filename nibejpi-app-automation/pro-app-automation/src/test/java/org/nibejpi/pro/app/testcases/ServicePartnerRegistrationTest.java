package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.app.util.RandomUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.hamburgerMenu.HamburgerMenuScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.servicepartnerprofile.ServicePartnerProfileScreen;
import org.nibejpi.pro.app.auto.pages.spregister.SPRegisterScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.*;

@Listeners(AzureTestCaseListener.class)
public class ServicePartnerRegistrationTest extends TestBase
{
	private ServicePartnerScreen spscreen;
	private SPRegisterScreen spregister;
	private SystemsScreen system;
	private HamburgerMenuScreen hamburgerMenu;
	private ServicePartnerProfileScreen spprofile;
	private LoginHelper loginHelper;
	private RandomUtils random=new RandomUtils();
	
	@BeforeClass
	public void Setup() throws Exception
	{
		spscreen = new ServicePartnerScreen(driver);
		spregister = new SPRegisterScreen(driver);
		hamburgerMenu = new HamburgerMenuScreen(driver);
		system = new SystemsScreen(driver);
		spprofile = new ServicePartnerProfileScreen(driver);
		loginHelper = new LoginHelper(driver);
		loginHelper.performLoginSetup();
		loginHelper.login();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Service Partner Registration");
		spscreen.clickOnAddSPButton();
	}
	
	/**
	 * Test case to verify the UI elements of the Service Partner Registration Screen.
	 * It checks if all the expected UI elements are present and properly displayed.
	 */
	@Test(priority = 1, description = "Verify the UI of the Service Partner Registration Screen.")
	@AzureTestCaseID(ID = { "4958" })
	public void verifyServicePartnerRegistrationScreenUIElements() throws InterruptedException
	{
		spregister.verifyServicePartnerRegistrationScreenUI(); 
	}
	
	/**
	 * Test case to verify the registration of a new Service partner for European countries.
	 * It tests the registration process by providing valid data for European countries.
	 */
	@Test(priority = 2, description = "Verify registration of new Service partner for European countries")
	@AzureTestCaseID(ID = { "3881" })
	public void testServicePartnerRegistrationEuropeanCountries() throws Exception
	{
		spregister.setServicePartnerName(random.generateRandomServicePartnerName());
		spregister.setAddressLine1(ExcelUtils.getCellData(1, 4));
		spregister.setAddressLine2(ExcelUtils.getCellData(1, 5));
		spregister.setZipCode(random.generateRandomZipCode());
		spregister.setCity(ExcelUtils.getCellData(1, 7));
		spregister.selectCountry(ExcelUtils.getCellData(1, 9));
		spregister.setEmail(random.generateRandomEmail());
		spregister.setName(random.generateRandomName());
		spregister.setPhoneNumber(random.generateRandomPhoneNumber());
		spregister.selectBrandInitial(ExcelUtils.getCellData(1, 13));
		spregister.selectBrandName(ExcelUtils.getCellData(1, 14));
		spregister.setVATCode();
		spregister.checkTOS();
		spregister.clickOnRegisterButton();
		system.tapHamburgerMenu();
		hamburgerMenu.tapOnSPProfileButton();
		spprofile.clickOnSPProfileButton();
		spprofile.verifyServicePartnerIsCreated();
		spprofile.navigateBack();
		system.tapHamburgerMenu();
		hamburgerMenu.tapOnServicePartnerButton();
		spscreen.clickOnAddSPButton();
	}
	
	/**
	 * Test case to verify the registration of a new Service partner for non-European countries.
	 * It tests the registration process by providing valid data for non-European countries.
	 */
	@Test(priority = 3, description = "Verify registration of new Service partner for Non-European countries")
	@AzureTestCaseID(ID = { "4960" })
	public void testServicePartnerRegistrationNonEuropeanCountries() throws Exception
	{
		//Bug fix APPS-2916
		spregister.setServicePartnerName(random.generateRandomServicePartnerName());
		spregister.setAddressLine1(ExcelUtils.getCellData(2, 4));
		spregister.setAddressLine2(ExcelUtils.getCellData(2, 5));
		spregister.setZipCode(random.generateRandomZipCode());
		spregister.setCity(ExcelUtils.getCellData(2, 7));
		spregister.selectCountry(ExcelUtils.getCellData(2, 9));
		spregister.selectState(ExcelUtils.getCellData(2, 8));
		spregister.setEmail(random.generateRandomEmail());
		spregister.setName(random.generateRandomName());
		spregister.setPhoneNumber(random.generateRandomPhoneNumber());
		spregister.selectBrandInitial(ExcelUtils.getCellData(2, 13));
		spregister.selectBrandName(ExcelUtils.getCellData(2, 14));
		spregister.checkTOS();
		spregister.clickOnRegisterButton();
		system.tapHamburgerMenu();
		hamburgerMenu.tapOnSPProfileButton();
		spprofile.clickOnSPProfileButton();
		spprofile.verifyServicePartnerIsCreated();
		spprofile.navigateBack();
		system.tapHamburgerMenu();
		hamburgerMenu.tapOnServicePartnerButton();
		spscreen.clickOnAddSPButton();
	}
	
	/**
	 * Test case to verify the Service partner registration with empty fields.
	 */
	@Test(priority = 4, description = "Verify Service partner registration with Empty fields.")
	@AzureTestCaseID(ID = { "4968" })
	public void testServicePartnerRegistrationEmptyFields() throws Exception
	{
		//Will be implemented after Bug Fix APPS - 2479
		
	}
	
	/**
	 * Test case to verify the Service partner registration without accepting Terms of Services.
	 * It tests the registration process by not accepting the Terms of Services.
	 */
	@Test(priority = 5, description = "Verify Service partner registration without accepting Terms of Services.")
	@AzureTestCaseID(ID = { "4969" })
	public void testServicePartnerRegistrationWithoutTOS() throws Exception
	{
		spregister.setServicePartnerName(random.generateRandomServicePartnerName());
		spregister.setAddressLine1(ExcelUtils.getCellData(4, 4));
		spregister.setAddressLine2(ExcelUtils.getCellData(4, 5));
		spregister.setZipCode(random.generateRandomZipCode());
		spregister.setCity(ExcelUtils.getCellData(4, 7));
		spregister.selectCountry(ExcelUtils.getCellData(4, 9));
		spregister.selectState(ExcelUtils.getCellData(4, 8));
		spregister.setEmail(random.generateRandomEmail());
		spregister.setName(random.generateRandomName());
		spregister.setPhoneNumber(random.generateRandomPhoneNumber());
		spregister.selectBrandInitial(ExcelUtils.getCellData(4, 13));
		spregister.selectBrandName(ExcelUtils.getCellData(4, 14));
		spregister.clickOnRegisterButton();
		spregister.validateUserIsNotAbleToRegisterSPWithoutTOS();
		
	}
	
	/**
	 * Test case to verify the Service partner registration with an invalid email format.
	 */
	@Test(priority = 6, description = "Verify Service partner registration with Invalid Email Format.")
	@AzureTestCaseID(ID = { "4970" })
	public void testServicePartnerRegistrationWithInvalidEmailFormat() throws Exception
	{
		//Will be implemented after Bug Fix APPS - 2479
		
	}
	
	/**
	 * Test case to verify the navigation to the service partner registration screen.
	 */
	@Test(priority = 7, description = "Verify the navigation of service partner registration screen.")
	@AzureTestCaseID(ID = { "3917" })
	public void testNavigationofServicePartnerRegistration() throws Exception
	{
		//Will be implemented after Bug Fix APPS - 2445
		
	}
	
	/**
	 * Test case to verify the Service partner registration with brands that require approval.
	 */
	@Test(priority = 8, description = "Verify service partner registration with brands that require approval.")
	@AzureTestCaseID(ID = { "3882" })
	public void testSPRegistrationWithBrandsRequiringApproval() throws Exception
	{
		//Will be implemented after Bug Fix APPS - 2482
		
	}
	
	/**
	 * Test case to verify the Service partner registration with an invalid VAT number.
	 */
	@Test(priority = 9, description = "Verify service partner registration with invalid VAT number.")
	@AzureTestCaseID(ID = { "3918" })
	public void testSPRegistrationWithInvalidVAT() throws Exception
	{
		//Will be implemented after Bug Fix APPS - 2445
		
	}
	
	
	
	
	
	
}

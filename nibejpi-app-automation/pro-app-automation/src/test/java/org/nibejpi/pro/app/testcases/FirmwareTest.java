package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.listener.AzureTestCaseListener;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.firmware.FirmwareScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AzureTestCaseListener.class)
public class FirmwareTest extends TestBase
{
	private LoginHelper loginHelper;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system; 
	private FirmwareScreen firmware;
	
	// Initialize necessary objects and perform login setup
	@BeforeMethod
	public void setUp() throws Exception {
		loginHelper = new LoginHelper(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		firmware = new FirmwareScreen(driver);
		loginHelper.performLoginSetup();
		loginHelper.login();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA,"Firmware");
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1, 3));
		system.tapHamburgerMenu();
	}
	
	/**
     * Test Case: Verify functionality of firmware download in hamburger menu.
     *
     * This test case verifies the functionality of firmware download and related actions in the hamburger menu.
     *
     * Test Steps:
     * 1. Navigate to the Firmware section in the hamburger menu.
     * 2. Click on the Download button to initiate firmware download.
     * 3. Verify that the firmware is successfully downloaded.
     * 4. Click on the Delete button to delete the downloaded firmware.
     * 5. Verify that the firmware deletion is successful.
     * 6. Click on the Download button again.
     * 7. Click on the Cancel button to abort the firmware download.
     * 8. Verify that an abort alert is displayed.
     * 9. Click on the Abort button on the alert.
     * 10. Verify that the firmware download is aborted.
     */
	@Test(priority = 1, description = "Verify functionality of firmware download in hamburger menu.")
	@AzureTestCaseID(ID = { "800" })
	public void verifyFirmwareDownloadInHamburgerMenu() throws Exception
	{
		system.tapFirmwareButton();
		firmware.clickOnDownloadButton();
		firmware.verifyThatFirmwareIsSuccessfullyDownloaded();
		firmware.clickOnDeleteButton();
		firmware.verifyFirmwareDeletionSuccess();
		firmware.clickOnDownloadButton();
		firmware.clickOnCancelButton();
		firmware.verifyAbortAlertIsDisplayed(); 
		firmware.clickOnAbortButtonOnAlert();
		firmware.verifyFirmwareDownloadIsAborted();
	}
	
	
}

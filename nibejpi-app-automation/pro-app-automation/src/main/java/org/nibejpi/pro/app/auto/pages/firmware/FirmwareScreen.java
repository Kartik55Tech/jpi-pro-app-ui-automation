package org.nibejpi.pro.app.auto.pages.firmware;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Page Object class for the Firmware screen. It extends ScreenActions for
 * common screen actions and utilities.
 */
public class FirmwareScreen extends ScreenActions implements Firmware {

	/**
	 * Constructor for the FirmwareScreen class.
	 *
	 * @param driver The AppiumDriver instance.
	 */
	public FirmwareScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// WebElements representing elements on the Firmware screen
	@AndroidFindBy(id = TITLETEXT)
	WebElement titleText;

	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;

	@AndroidFindBy(xpath = DOWNLOAD_BTN)
	WebElement downloadBtn;

	@AndroidFindBy(xpath = FIRMWARE_STATUS)
	WebElement firmware_Status;

	@AndroidFindBy(id = PROGRESS_TEXT)
	WebElement progressText;

	@AndroidFindBy(id = TEXT_DESCRIPTION)
	WebElement textDescription;

	@AndroidFindBy(id = PROGRESS_BAR)
	WebElement progressBar;

	@AndroidFindBy(id = CANCEL_BTN)
	WebElement cancelBtn;

	@AndroidFindBy(id = CONTINUE_BTN)
	WebElement continueBtn;

	@AndroidFindBy(id = DELETE_BTN)
	WebElement deleteBtn;

	@AndroidFindBy(id = ALERT_MSG)
	WebElement alertMsg;

	@AndroidFindBy(id = CONTINUE_ALERTBTN)
	WebElement continueAlertBtn;

	@AndroidFindBy(id = ABORT_BTN)
	WebElement abortBtn;

	/**
	 * Click on the Cancel Button.
	 */
	public void clickOnCancelButton() {
		click(cancelBtn);
	}

	/**
	 * Verify that the Abort Alert is displayed with expected elements.
	 */
	public void verifyAbortAlertIsDisplayed() {
		waitForElementToBeVisible(alertMsg);
		Assert.assertEquals(alertMsg.getAttribute("text"), "Abort firmware download?");
		Assert.assertTrue(abortBtn.isDisplayed(), "Abort button is not displayed");
		Assert.assertEquals(abortBtn.getAttribute("text"), "Abort");
		Assert.assertTrue(continueAlertBtn.isDisplayed(), "Continue button is not displayed");
		Assert.assertEquals(continueAlertBtn.getAttribute("text"), "Continue");
	}

	/**
	 * Verify that the Firmware download is aborted.
	 */
	public void verifyFirmwareDownloadIsAborted() {
		waitForElementToBeVisible(firmware_Status);
		if (firmware_Status.getAttribute("text").contains("No firmware available on your device")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Firmware download process is not cancelled.");
		}
	}

	/**
	 * Click on the Download Button if firmware is available.
	 */
	public void clickOnDownloadButton() {
		if (firmware_Status.getAttribute("text").contains("No firmware available on your device")) {
			click(downloadBtn);
		}
	}

	/**
	 * Click on the Delete Button if firmware is downloaded.
	 */
	public void clickOnDeleteButton() {
		if (firmware_Status.getAttribute("text").contains("Downloaded")) {
			click(deleteBtn);

		}
	}

	/**
	 * Click on the Continue Button on the alert.
	 */
	public void clickOnContinueButtonOnAlert() {
		click(continueAlertBtn);
	}

	/**
	 * Click on the Abort Button on the alert.
	 */
	public void clickOnAbortButtonOnAlert() {
		click(abortBtn);
	}

	/**
	 * Verify that the Firmware is deleted successfully.
	 *
	 * @throws InterruptedException Exception for handling thread interruptions.
	 */
	public void verifyFirmwareDeletionSuccess() throws InterruptedException {
		Thread.sleep(4000);
		if (firmware_Status.getAttribute("text").contains("No firmware available on your device")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Firmware is not deleted successfully.");
		}
	}

	/**
	 * Verify that the Firmware is successfully downloaded.
	 */
	public void verifyThatFirmwareIsSuccessfullyDownloaded() {
		SoftAssert softAssert = new SoftAssert();

		waitForElementToBeVisible(cancelBtn);

		if (cancelBtn.isDisplayed()) {
			waitForTextToBePresent(progressText, "Success");
			if (progressText.getAttribute("text").equals("Success")) {
				click(continueBtn);
				waitForElementToBeVisible(firmware_Status);
				softAssert.assertTrue(firmware_Status.getAttribute("text").contains("Downloaded"),
						"Firmware not downloaded successfully.");
			}
		} else if (progressText.getAttribute("text").contains("Failed")) {
			// If "Failed" is present, take corrective actions
			click(continueBtn);
			waitForElementToBeVisible(downloadBtn);
			click(downloadBtn);
			waitForTextToBePresent(progressText, "Success");
			click(continueBtn);
			waitForElementToBeVisible(firmware_Status);
			softAssert.assertTrue(firmware_Status.getAttribute("text").contains("Downloaded"),
					"Firmware not downloaded successfully.");
		}

		softAssert.assertAll();
	}

}

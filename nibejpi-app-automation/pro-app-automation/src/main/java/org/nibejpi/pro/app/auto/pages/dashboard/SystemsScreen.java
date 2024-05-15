package org.nibejpi.pro.app.auto.pages.dashboard;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SystemsScreen extends ScreenActions implements Systems {
	public SystemsScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements on the Systems screen
	@AndroidFindBy(xpath = HAMBURGER_MENU)
	WebElement hamburgerBtn;

	@AndroidFindBy(xpath = SETTINGS)
	WebElement settingsBtn;

	@AndroidFindBy(xpath = HELP)
	WebElement helpBtn;

	@AndroidFindBy(xpath = GROUPING)
	WebElement groupingBtn;

	@AndroidFindBy(accessibility = LIST_VIEW)
	WebElement listView;

	@AndroidFindBy(accessibility = MAP_VIEW)
	WebElement mapView;

	@AndroidFindBy(xpath = FIRMWARE)
	WebElement firmware;

	@AndroidFindBy(xpath = SP_PROFILE)
	WebElement profile;

	@AndroidFindBy(xpath = SERVICE_PARTNER)
	WebElement servicePartner;

	@AndroidFindBy(xpath = SECURITY)
	WebElement security;

	// Click on the Service Partner Profile
	public void tapProfile() {
		click(profile);
	}

	// Click on the Service Partner button
	public void tapServicePartner() {
		click(servicePartner);
	}

	// Click on the Security button
	public void tapSecurity() {
		click(security);
	}

	// Tap on the Map View button
	public void tapMapView() {
		click(mapView);
	}

	// Click on the Hamburger menu button
	public void tapHamburgerMenu() {
		click(hamburgerBtn);
	}

	// Click on the Settings button
	public void tapSettings() {
		click(settingsBtn);
	}

	// Click on the Help button
	public void tapHelpButton() {
		click(helpBtn);
	}

	// Click on the Grouping button
	public void tapGrouping() {
		click(groupingBtn);
	}

	// Click on the Firmware button
	public void tapFirmwareButton() {
		click(firmware);
	}

	// Select a system by scrolling to the specified SystemName
	public void selectSystem(String SystemName) {
		waitForElementToBeVisible(hamburgerBtn);
		int maxAttempts = 3; // Set the maximum number of retry attempts

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Scroll to the System name
				scrollToText(SystemName);

				// Construct the XPath using the complete text
				String xpath = "//android.widget.TextView[@text=\"" + SystemName + "\"]";

				// Find the element and perform the click
				WebElement element = driver.findElement(By.xpath(xpath));
				waitForElementToBeVisible(element);
				waitForElementToBeClickable(element);
				element.click();
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException: Attempt " + attempt);
			}

		}
	}

}

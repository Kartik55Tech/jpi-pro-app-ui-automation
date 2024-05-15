package org.nibejpi.pro.app.auto.pages.register;

import org.nibejpi.app.constant.ErrorMessage;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterScreenTwo extends ScreenActions implements Register {

	/**
	 * Constructor for RegisterScreenTwo.
	 *
	 * @param driver The AppiumDriver instance.
	 */
	public RegisterScreenTwo(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements on the screen identified by AndroidFindBy annotations
	@AndroidFindBy(xpath = TITLE_TXT_SCR_TWO)
	WebElement textHeader;

	@AndroidFindBy(xpath = FULL_NAME)
	WebElement fullName;

	@AndroidFindBy(xpath = ADDRESS_LINE1)
	WebElement addressLine1;

	@AndroidFindBy(xpath = ADDRESS_LINE2)
	WebElement addressLine2;

	@AndroidFindBy(xpath = ZIP)
	WebElement zipCode;

	@AndroidFindBy(xpath = CITY)
	WebElement city;

	@AndroidFindBy(id = STATE_DDL)
	WebElement stateDrpdwn;

	@AndroidFindBy(id = COUNTRY_DDL)
	WebElement countryDrpdwn;

	@AndroidFindBy(id = REGISTER_BTN)
	WebElement finalRegisterBtn;

	@AndroidFindBy(id = BACK_BTN)
	WebElement backBtn;

	@AndroidFindBy(xpath = FULL_NAME_EMPTY_FLD_MSG)
	WebElement full_name_EmptyFld_Msg;

	@AndroidFindBy(xpath = FULL_NAME_EMPTY_FLD_MSG)
	WebElement addressLine1_EmptyFld_Msg;

	@AndroidFindBy(xpath = ZIP_EMPTY_FLD_MSG)
	WebElement zip_EmptyFld_Msg;

	@AndroidFindBy(xpath = CITY_EMPTY_FLD_MSG)
	WebElement city_EmptyFld_Msg;

	@AndroidFindBy(id = STATE_EMPTY_FLD_MSG)
	WebElement state_EmptyFld_Msg;

	@AndroidFindBy(id = COUNTRY_EMPTY_FLD_MSG)
	WebElement country_EmptyFld_Msg;

	/**
	 * Set the Full Name on the screen.
	 *
	 * @param Fullname The Full Name to set.
	 * @return The RegisterScreenTwo instance for method chaining.
	 */
	public RegisterScreenTwo setFullName(String Fullname) {
		scrollToText("Tell us some more about you");
		waitForElementToBeVisible(fullName);
		fullName.sendKeys(Fullname);
		return this;
	}

	/**
	 * Set the Address Line 1 on the screen.
	 *
	 * @param AddressLine1 The Address Line 1 to set.
	 * @return The RegisterScreenTwo instance for method chaining.
	 */
	public RegisterScreenTwo setAddressLine1(String AddressLine1) {
		waitForElementToBeVisible(addressLine1);
		addressLine1.clear();
		addressLine1.sendKeys(AddressLine1);
		return this;
	}

	/**
	 * Set the Address Line 2 on the screen.
	 *
	 * @param AddressLine2 The Address Line 2 to set.
	 * @return The RegisterScreenTwo instance for method chaining.
	 */
	public RegisterScreenTwo setAddressLine2(String AddressLine2) {
		waitForElementToBeVisible(addressLine2);
		addressLine2.sendKeys(AddressLine2);
		return this;
	}

	/**
	 * Set the Zip Code on the screen.
	 *
	 * @param ZipPostalCode The Zip Code to set.
	 * @return The RegisterScreenTwo instance for method chaining.
	 */
	public RegisterScreenTwo setZipCode(String ZipPostalCode) {
		waitForElementToBeVisible(zipCode);
		zipCode.clear();
		zipCode.sendKeys(ZipPostalCode);
		return this;
	}

	/**
	 * Set the City on the screen.
	 *
	 * @param City The City to set.
	 * @return The RegisterScreenTwo instance for method chaining.
	 */
	public RegisterScreenTwo setCity(String City) {
		waitForElementToBeVisible(city);
		city.clear();
		city.sendKeys(City);
		scrollToText("Back");
		return this;
	}

	/**
	 * Navigate back to the previous screen.
	 */
	public void navigateBack() {
		click(backBtn);
	}

	/**
	 * Select a State from the dropdown.
	 *
	 * @param StateName The State to select.
	 */
	public void selectState(String StateName) {
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) stateDrpdwn).getId()));

		// Use the 'StateName' parameter in the UiSelector to scroll and find the
		// element
		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))", StateName);

		// Scroll to the element with the specified text and click
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
	}

	/**
	 * Select a Country from the dropdown.
	 *
	 * @param CountryName The Country to select.
	 */
	public void selectCountry(String CountryName) {
		scrollToText("Back");
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) countryDrpdwn).getId()));

		// Use the 'StateName' parameter in the UiSelector to scroll and find the
		// element
		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))", CountryName);

		// Scroll to the element with the specified text and click
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
	}

	/**
	 * Click the Register button on the screen.
	 */
	public void tapOnRegisterButton() {
		click(finalRegisterBtn);
	}

	/**
	 * Verifies user redirection to the Address Screen after clicking the Next
	 * button by checking the visibility of the title text.
	 */
	public void verifyRedirectionToAddressScreenAfterNextButtonClick() {
		waitForDynamicTextContent(addressLine1);
		waitForElementToBeVisible(textHeader);
		Assert.assertTrue(textHeader.isDisplayed(), "HeaderText is not displayed.");
		System.out.println("User redirected to next screen.");
	}

	/**
	 * Verifies the UI elements on Registration Screen Two. This method checks the
	 * visibility and status of various fields and buttons.
	 *
	 * @throws InterruptedException If there's an interruption during the thread
	 *                              sleep.
	 */
	public void verifyRegistrationScreenTwoUI() throws InterruptedException {
		waitForDynamicTextContent(zipCode);
		// TitleText
		Assert.assertTrue(textHeader.isDisplayed(), "Tell us some more about you message is not displayed.");
		Assert.assertEquals(textHeader.getText(), "Tell us some more about you");
		// Full Name
		verifyInputField(fullName, "Full Name");
		// AddressLine1
		verifyInputField(addressLine1, "Address Line1");
		// AddressLine2
		verifyInputField(addressLine2, "Address Line 2");
		// Zip-PostalCode
		verifyInputField(zipCode, "Zip/Postal-Code");
		// City
		verifyInputField(city, "City");
		scrollToText("Back");
		// Country
		verifySpinner(countryDrpdwn, "Country");
		// Register button
		verifyButton(finalRegisterBtn, "Register");
		// Back button
		verifyButton(backBtn, "Navigate Back");
		System.out.println("All the elements are present on second screen.");
	}

	/**
	 * Verifies appearance of empty field messages on Screen Two with optional
	 * State.
	 *
	 * @throws InterruptedException If there's an interruption during the thread
	 *                              sleep.
	 */
	public void verifyEmptyFieldScreenTwoWithOptionalState() throws InterruptedException {
		waitForDynamicTextContent(zipCode);
		// Clear pre-fetched data
		addressLine1.clear();
		zipCode.clear();
		city.clear();
		scrollToText("Back");
		click(finalRegisterBtn);
		scrollToText("Tell us some more about you");
		verifyWarningMessageDisplayed(full_name_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Full Name empty field message is not displayed.");
		verifyWarningMessageDisplayed(addressLine1_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Address Line 1 empty field message is not displayed.");
		verifyWarningMessageDisplayed(zip_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Zip empty field message is not displayed.");
		verifyWarningMessageDisplayed(city_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"City empty field message is not displayed.");
		verifyWarningMessageDisplayed(country_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Country empty field message is not displayed.");

	}

	/**
	 * Verifies appearance of empty field messages on Screen Two with mandatory
	 * State.
	 */
	public void verifyEmptyFieldScreenTwoWithMandatoryState() {
		selectCountry("Canada");
		click(finalRegisterBtn);
		scrollToText("Tell us some more about you");
		verifyWarningMessageDisplayed(full_name_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Full Name empty field message is not displayed.");
		verifyWarningMessageDisplayed(addressLine1_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Address Line 1 empty field message is not displayed.");
		verifyWarningMessageDisplayed(zip_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Zip empty field message is not displayed.");
		verifyWarningMessageDisplayed(city_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"City empty field message is not displayed.");
		verifyWarningMessageDisplayed(state_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Country empty field message is not displayed.");
		navigateBack();

	}

	/**
	 * Verifies the user is not able to register without Address, Zip, City.
	 *
	 * @throws InterruptedException If there's an interruption during the thread
	 *                              sleep.
	 */
	public void verifyUserNotAbleToRegisterWithoutAddressZipCity() throws InterruptedException {
		waitForDynamicTextContent(zipCode);
		addressLine1.clear();
		zipCode.clear();
		city.clear();
		scrollToText("Register");
		selectCountry("Please Select");
		finalRegisterBtn.click();
		scrollToText("Tell us some more about you");
		verifyWarningMessageDisplayed(addressLine1_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Address Line 1 empty field message is not displayed.");
		verifyWarningMessageDisplayed(zip_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Zip empty field message is not displayed.");
		verifyWarningMessageDisplayed(city_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"City empty field message is not displayed.");
		verifyWarningMessageDisplayed(country_EmptyFld_Msg, ErrorMessage.EMPTY_FIELD,
				"Country empty field message is not displayed.");
		navigateBack();
	}

}

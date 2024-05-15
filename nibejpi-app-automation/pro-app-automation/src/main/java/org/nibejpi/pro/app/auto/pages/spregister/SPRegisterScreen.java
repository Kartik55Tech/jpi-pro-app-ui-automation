package org.nibejpi.pro.app.auto.pages.spregister;

import org.apache.commons.lang3.RandomStringUtils;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SPRegisterScreen extends ScreenActions implements SPRegister {

	public SPRegisterScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements for Service Partner Registration screen
	@AndroidFindBy(xpath = SP_NAME)
	WebElement spName;

	@AndroidFindBy(xpath = ADDRESS_LINE1)
	WebElement addressLine1;

	@AndroidFindBy(xpath = ADDRESS_LINE2)
	WebElement addressLine2;

	@AndroidFindBy(xpath = POSTAL_CODE)
	WebElement postalCode;

	@AndroidFindBy(xpath = CITY)
	WebElement city;

	@AndroidFindBy(id = STATE)
	WebElement state;

	@AndroidFindBy(xpath = STATE_OPT)
	WebElement stateOpt;

	@AndroidFindBy(id = COUNTRY)
	WebElement country;

	@AndroidFindBy(xpath = EMAIL)
	WebElement email;

	@AndroidFindBy(xpath = NAME)
	WebElement name;

	@AndroidFindBy(xpath = PHONENUMBER)
	WebElement phoneNumber;

	@AndroidFindBy(id = BRAND)
	WebElement brand;

	@AndroidFindBy(xpath = BRANDINITIAL)
	WebElement brandinitial;

	@AndroidFindBy(id = NEXT_BTN)
	WebElement nextBtn;

	@AndroidFindBy(id = BRANDNAME1)
	WebElement brandname1;

	@AndroidFindBy(xpath = BRANDNAME2)
	WebElement brandname2;

	@AndroidFindBy(xpath = VAT)
	WebElement vat;

	@AndroidFindBy(id = TOS)
	WebElement TOSCheckbox;

	@AndroidFindBy(id = REGISTER_BTN)
	WebElement register_Btn;

	@AndroidFindBy(id = TEXT_BRAND)
	WebElement text_Brand;

	@AndroidFindBy(xpath = SELECTED_COUNTRY)
	WebElement selectedCountry;

	@AndroidFindBy(id = TOS_WARNING)
	WebElement tos_WarningMsg;

	@AndroidFindBy(id = NUMBERPKR_INPUT)
	WebElement numberPickerInput;

	// Set the Service Partner Name
	public void setServicePartnerName(String spname) {
		scrollToText("Create Service Partner");
		spName.sendKeys(spname);
	}

	// Set Address Line 1
	public void setAddressLine1(String AddressLine1) throws InterruptedException {
		waitForDynamicTextContent(addressLine1);
		addressLine1.clear();
		addressLine1.sendKeys(AddressLine1);
	}

	// Set Address Line 2
	public void setAddressLine2(String AddressLine2) {
		addressLine2.sendKeys(AddressLine2);
	}

	// Set Zip Code
	public void setZipCode(String ZipCode) {
		waitForDynamicTextContent(postalCode);
		postalCode.clear();
		postalCode.sendKeys(ZipCode);
	}

	// Set City
	public void setCity(String City) {
		waitForDynamicTextContent(city);
		city.clear();
		city.sendKeys(City);
	}

	// Select Country
	public void selectCountry(String countryName) {
		scrollToText("Contact Person");
		waitForElementToBeVisible(country);
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) country).getId()));

		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))", countryName);

		// Scroll to the element with the specified text and click
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
	}

	// Select State
	public void selectState(String stateName) {
		scrollToText("Create Service Partner");
		waitForElementToBeVisible(state);
		driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) state).getId()));

		// Use the 'StateName' parameter in the UiSelector to scroll and find the
		// element
		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))", stateName);

		// Scroll to the element with the specified text and click
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
		scrollToText("Brand");
	}

	// Set Email
	public void setEmail(String Email) {
		scrollToText("Register");
		email.sendKeys(Email);
	}

	// Set Name
	public void setName(String Name) {
		name.sendKeys(Name);
	}

	// Set PhoneNumber
	public void setPhoneNumber(String PhoneNumber) {
		phoneNumber.sendKeys(PhoneNumber);
	}

	// Set BrandInitial
	public void selectBrandInitial(String startingletter) {
		brand.click();
		String brandStartingLetter = startingletter;
		String BrandInitial = String.format(BRANDINITIAL, brandStartingLetter);
		WebElement BI = driver.findElement(By.xpath(BrandInitial));
		String actualText = BI.getAttribute("text");
		do {
			BI.click();
			actualText = BI.getAttribute("text");
		} while (!actualText.equals(startingletter));

		BI.click();
		nextBtn.click();
	}

	public void selectBrandName(String BrandName) {
		System.out.println("Number picker attribute: " + numberPickerInput.getAttribute("text"));
		String actualText = brandname1.getAttribute("text");
		System.out.println(actualText);
		if (numberPickerInput.getAttribute("text").equals(BrandName)) {
			nextBtn.click();
		} else {
			System.out.println("Actual text: " + actualText);
			while (!actualText.equals(BrandName)) {
				brandname1.click();
				actualText = brandname1.getAttribute("text");
			}
			nextBtn.click();
		}
	}

	// Set VATCode
	public void setVATCode() {
		String countryIsSelected = selectedCountry.getAttribute("text");

		if (countryIsSelected != null && !countryIsSelected.isEmpty()) {
			switch (countryIsSelected) {
			case "Austria":
				vat.sendKeys("ATU" + RandomStringUtils.randomNumeric(8));
				break;
			case "Belgium":
				vat.sendKeys("BE0" + RandomStringUtils.randomNumeric(9));
				break;
			case "Bulgaria":
				vat.sendKeys("BG" + RandomStringUtils.randomNumeric(9, 10));
				break;
			case "Croatia":
				vat.sendKeys("HR" + RandomStringUtils.randomNumeric(11));
				break;
			case "Cyprus":
				vat.sendKeys("CY" + RandomStringUtils.randomNumeric(8) + RandomStringUtils.randomAlphabetic(1));
				break;
			case "Czech Republic":
				vat.sendKeys("CZ" + RandomStringUtils.randomNumeric(8, 10));
				break;
			case "Denmark":
				vat.sendKeys("DK" + RandomStringUtils.randomNumeric(8));
				break;
			case "Estonia":
				vat.sendKeys("EE" + RandomStringUtils.randomNumeric(9));
				break;
			case "Finland":
				vat.sendKeys("FI" + RandomStringUtils.randomNumeric(8));
				break;
			case "France":
				vat.sendKeys("FR" + RandomStringUtils.randomAlphanumeric(2) + RandomStringUtils.randomNumeric(9));
				break;
			case "Germany":
				vat.sendKeys("DE" + RandomStringUtils.randomNumeric(9));
				break;
			case "Greece":
				vat.sendKeys("EL" + RandomStringUtils.randomNumeric(9));
				break;
			case "Hungary":
				vat.sendKeys("HU" + RandomStringUtils.randomNumeric(8));
				break;
			case "Ireland":
				vat.sendKeys("IE" + RandomStringUtils.randomAlphanumeric(8, 9));
				break;
			case "Italy":
				vat.sendKeys("IT" + RandomStringUtils.randomNumeric(11));
				break;
			case "Isle of Man":
				vat.sendKeys("GB" + RandomStringUtils.randomAlphanumeric(3) + RandomStringUtils.randomNumeric(9, 12));
				break;
			case "Latvia":
				vat.sendKeys("LV" + RandomStringUtils.randomNumeric(11));
				break;
			case "Lithuania":
				vat.sendKeys("LT" + RandomStringUtils.randomNumeric(9, 12));
				break;
			case "Luxembourg":
				vat.sendKeys("LU" + RandomStringUtils.randomNumeric(8));
				break;
			case "Malta":
				vat.sendKeys("MT" + RandomStringUtils.randomNumeric(8));
				break;
			case "Monaco":
				vat.sendKeys("FR" + RandomStringUtils.randomAlphabetic(2) + RandomStringUtils.randomNumeric(9));
				break;
			case "Netherlands":
				vat.sendKeys("NL" + RandomStringUtils.randomNumeric(9) + "B" + RandomStringUtils.randomNumeric(2));
				break;
			case "Poland":
				vat.sendKeys("PL" + RandomStringUtils.randomNumeric(10));
				break;
			case "Portugal":
				vat.sendKeys("PT" + RandomStringUtils.randomNumeric(9));
				break;
			case "Romania":
				vat.sendKeys("RO" + RandomStringUtils.randomAlphanumeric(2, 10));
				break;
			case "Slovakia":
				vat.sendKeys("SK" + RandomStringUtils.randomNumeric(10));
				break;
			case "Slovenia":
				vat.sendKeys("SI" + RandomStringUtils.randomNumeric(8));
				break;
			case "Spain":
				vat.sendKeys("ES" + RandomStringUtils.randomAlphanumeric(1) + RandomStringUtils.randomNumeric(7)
						+ RandomStringUtils.randomAlphanumeric(1));
				break;
			case "Sweden":
				vat.sendKeys("SE" + RandomStringUtils.randomNumeric(12));
				break;
			case "United Kingdom":
				vat.sendKeys("GB" + RandomStringUtils.randomNumeric(9, 12));
				break;
			default:
				System.out.println("No VAT code generated for " + countryIsSelected);
				break;
			}
		} else {
			System.out.println("Non-European country is selected.");
		}
	}

	// Select Terms of Service
	public void checkTOS() {
		waitForElementToBeVisible(TOSCheckbox);
		TOSCheckbox.click();
	}

	// Click on Register button
	public void clickOnRegisterButton() {
		click(register_Btn);
	}

	// Verify Service Partner Registration Screen UI
	public void verifyServicePartnerRegistrationScreenUI() throws InterruptedException {
		
		
		verifyInputField(spName, "Service Partner Name");
		addressLine1.clear();
		verifyInputField(addressLine1, "Address Line 1");
		verifyInputField(addressLine2, "Address Line 2");
		postalCode.clear();
		verifyInputField(postalCode, "Postal Code");
		city.clear();
		verifyInputField(city, "City");
		verifySpinner(stateOpt,"State");
		Assert.assertTrue(stateOpt.isDisplayed(), "State field is not displayed");
		Assert.assertTrue(stateOpt.isEnabled(), "State field is not enabled");
		Assert.assertTrue(country.isDisplayed(), "Country field is not displayed");
		Assert.assertTrue(country.isEnabled(), "Country field is not enabled");
		scrollToText("Brand");
		Assert.assertTrue(email.isDisplayed(), "Email field is not displayed");
		Assert.assertTrue(email.isEnabled(), "Email field is not enabled");
		Assert.assertTrue(name.isDisplayed(), "Name field is not displayed");
		Assert.assertTrue(name.isEnabled(), "Name field is not enabled");
		Assert.assertTrue(phoneNumber.isDisplayed(), "Phone Number field is not displayed");
		Assert.assertTrue(phoneNumber.isEnabled(), "Phone Number field is not enabled");
		scrollToText("Register");
		Assert.assertTrue(brand.isDisplayed(), "Brand field is not displayed");
		Assert.assertTrue(brand.isEnabled(), "Brand field is not enabled");
		selectCountry("Sweden");
		waitForElementToBeVisible(vat);
		Assert.assertTrue(vat.isDisplayed(), "VAT field is not displayed");
		Assert.assertTrue(vat.isEnabled(), "VAT field is not enabled");
		scrollToText("I accept the Terms of Service");
		Assert.assertTrue(TOSCheckbox.isDisplayed(), "TOS checkbox is not displayed");
		Assert.assertTrue(TOSCheckbox.isEnabled(), "TOS checkbox is not enabled");
		scrollToText("Register");
		Assert.assertTrue(register_Btn.isDisplayed(), "Register button is not displayed");
		Assert.assertTrue(register_Btn.isEnabled(), "Register button is not enabled");
	}

	// Validate User Is Not Able to Register SP Without Accepting TOS
	public void validateUserIsNotAbleToRegisterSPWithoutTOS() {
		Assert.assertEquals(tos_WarningMsg.getAttribute("text"), "You need to accept the conditions.");
	}

}

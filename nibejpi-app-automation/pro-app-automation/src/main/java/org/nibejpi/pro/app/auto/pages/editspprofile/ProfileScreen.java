package org.nibejpi.pro.app.auto.pages.editspprofile;
import org.apache.commons.lang3.RandomStringUtils;
import org.nibejpi.app.constant.ErrorMessage;
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

public class ProfileScreen extends ScreenActions implements ServicePartnerProfile,ErrorMessage
{

	public ProfileScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = SPNAME)
	WebElement spName;
	
	@AndroidFindBy(xpath = ADDRESS_LINE1 )
	WebElement addressLine1 ;
	
	@AndroidFindBy(xpath = ADDRESS_LINE2 )
	WebElement addressLine2;
	
	@AndroidFindBy(xpath = POSTALCODE )
	WebElement postalCode;
	
	@AndroidFindBy(xpath = CITY)
	WebElement city;
	
	@AndroidFindBy(id = STATE)
	WebElement state;
	
	@AndroidFindBy(id = COUNTRY)
	WebElement country;
	
	@AndroidFindBy(xpath = EMAIL)
	WebElement email;
	
	@AndroidFindBy(xpath = NAME )
	WebElement name;
	
	@AndroidFindBy(xpath = PHONENUMBER)
	WebElement phoneNumber;
	
	@AndroidFindBy(xpath = BRAND)
	WebElement brand;
	
	@AndroidFindBy(xpath = VAT)
	WebElement vat;
	
	@AndroidFindBy(id = SAVE_BTN )
	WebElement saveBtn;
	
	@AndroidFindBy(xpath = SELECTED_COUNTRY)
	WebElement selectedCountry;
	
	@AndroidFindBy (xpath=TOASTMSG)
	WebElement toastMessage;
	
	@AndroidFindBy (id=TITLETEXT)
	WebElement topNavTitle;
	
	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;
	
	@AndroidFindBy(xpath = FIELD_ERROR_MSG)
	WebElement fieldErrorMessage;
	
	public void verifyToastMessageIsVisibleAfterUpdatingServicePartnerSuccessfully()
	{
		String toastmsg = driver.findElement(By.xpath(TOASTMSG)).getAttribute("name");
		System.out.println("Toast message :"+ toastmsg);
		Assert.assertEquals(toastmsg,"Your information has been saved.");
	}
	
	
	public void verifySpName(String expectedSPName)
	{
		waitForElementToBeVisible(spName);
		Assert.assertTrue(spName.isDisplayed(), "SP Name is not displayed.");
		Assert.assertEquals(spName.getAttribute("text"),expectedSPName);
	}
	
	public void verifyAddressLine1(String expectedAddressLine1)
	{
		waitForElementToBeVisible(addressLine1);
		Assert.assertTrue(addressLine1.isDisplayed(), "Address Line 1 field is not displayed.");
		Assert.assertEquals(addressLine1.getAttribute("text"),expectedAddressLine1);
	}
	
	public void verifyAddressLine2(String expectedAddressLine2)
	{
		waitForElementToBeVisible(addressLine2);
		Assert.assertTrue(addressLine2.isDisplayed(), "Address Line 2 field is not displayed.");
		Assert.assertEquals(addressLine2.getAttribute("text"),expectedAddressLine2);
	}
	
	public void verifyPostalCode(String expectedPostalCode)
	{
		waitForElementToBeVisible(postalCode);
		Assert.assertTrue(postalCode.isDisplayed(), "Postal Code field is not displayed.");
		Assert.assertEquals(postalCode.getAttribute("text"),expectedPostalCode);
	}
	
	public void verifyCity(String expectedCity)
	{
		waitForElementToBeVisible(city);
		Assert.assertTrue(city.isDisplayed(), "City field is not displayed.");
		Assert.assertEquals(city.getAttribute("text"),expectedCity);
	}
	
	public void verifyCountry(String expectedCountry)
	{
		scrollToText("Contact Person");
		waitForElementToBeVisible(selectedCountry);
		Assert.assertTrue(selectedCountry.isDisplayed(), "Country field is not displayed.");
		Assert.assertEquals(selectedCountry.getAttribute("text"),expectedCountry);
	}
	
	public void verifyEmail(String expectedEmail) throws InterruptedException
	{
		scrollToText("Save");
		Thread.sleep(3000);
		waitForElementToBeVisible(email);
		Assert.assertTrue(email.isDisplayed(), "Email field is not displayed.");
		Assert.assertEquals(email.getAttribute("text"),expectedEmail);
	}
	
	public void verifyName(String expectedName)
	{
		waitForElementToBeVisible(name);
		Assert.assertTrue(name.isDisplayed(), "Name field is not displayed.");
		Assert.assertEquals(name.getAttribute("text"),expectedName);
	}
	
	public void verifyPhoneNumber(String expectedPhoneNumber)
	{
		waitForElementToBeVisible(phoneNumber);
		Assert.assertTrue(phoneNumber.isDisplayed(), "Phone number field is not displayed.");
		Assert.assertEquals((phoneNumber.getAttribute("text")),expectedPhoneNumber);
		
	}
	
	public void verifyBrandName(String expectedBrandName)
	{
		scrollToText("Save");
		waitForElementToBeVisible(brand);
		Assert.assertTrue(brand.isDisplayed(), "Brand field is not displayed.");
		Assert.assertFalse(brand.isEnabled(), "Brand field is not disabled");
		Assert.assertEquals(brand.getAttribute("text"),expectedBrandName);
	}
	
	public void verifyVATnumber(String expectedVAT)
	{
		waitForElementToBeVisible(vat);
		Assert.assertTrue(vat.isDisplayed(), "VAT field is not displayed.");
		Assert.assertEquals(vat.getAttribute("text"),expectedVAT);
	}
	
	public void setServicePartnerName(String SPname) throws InterruptedException
	{
		Thread.sleep(5000);
		spName.clear();
		spName.sendKeys(SPname);
	}
	
	public void setAddressLine1(String AddressLine1)
	{
		addressLine1.clear();
		addressLine1.sendKeys(AddressLine1);
	}
	
	public void setAddressLine2(String AddressLine2)
	{
		addressLine2.clear();
		addressLine2.sendKeys(AddressLine2);
	}
	
	public void setPostalCode(String PostalCode)
	{
		postalCode.clear();
		postalCode.sendKeys(PostalCode);
	}
	
	public void setCity(String City)
	{
		city.clear();
		city.sendKeys(City);
		scrollToText("Contact Person");
		
	}
	
	
	public void selectCountry(String countryName)
	{
			driver.executeScript("mobile: clickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) country).getId()));
		
		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
		+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))",countryName );

		// Scroll to the element with the specified text and click
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
	}
	
	public void selectState(String stateName)
	{	
		driver.executeScript("mobile: clickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) state).getId()));
		
		// Use the 'StateName' parameter in the UiSelector to scroll and find the element
		String scrollableText = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
		+ ".scrollIntoView(new UiSelector().textMatches(\"%s\").instance(0))",stateName );

		// Scroll to the element with the specified text and click
		driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollableText)).click();
		
		scrollToText("Save");
	}
	
	public void setEmail(String Email)
	{
		email.clear();
		email.sendKeys(Email);
	}
	
	public void setName(String Name)
	{
		name.clear();
		name.sendKeys(Name);
	}
	
	public void setPhoneNumber(String PhoneNumber)
	{
		phoneNumber.clear();
		phoneNumber.sendKeys(PhoneNumber);
	}
	
	public void setVATCode() 
	{
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
	                vat.sendKeys("ES" + RandomStringUtils.randomAlphanumeric(1) + RandomStringUtils.randomNumeric(7) + RandomStringUtils.randomAlphanumeric(1));
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

	public void clickOnSaveButton()
	{
		scrollToText("Save");
		click(saveBtn);
	}
	
	public void navigateBack()
	{
		click(backButton);
	}
	
	public void verifyServicePartnerDetailsAreUpdated() throws InterruptedException
	{
		Thread.sleep(5000);
		waitForElementToBeVisible(spName);
		Assert.assertEquals(spName.getAttribute("text"),"Mobile Automation SP Test ");
	}
	
	public void verifyEmptyFieldValidationsAreDisplayed()
	{
		scrollToText("Address");
		Assert.assertTrue(fieldErrorMessage.isDisplayed(), "Empty Field validation text is not displayed.");
		Assert.assertEquals(fieldErrorMessage.getAttribute("text"), EMPTY_FIELD);
	}
	

	
	
}

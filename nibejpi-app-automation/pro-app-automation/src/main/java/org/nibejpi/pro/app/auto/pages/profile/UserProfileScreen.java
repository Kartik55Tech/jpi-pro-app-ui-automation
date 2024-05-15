package org.nibejpi.pro.app.auto.pages.profile;

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

public class UserProfileScreen extends ScreenActions implements Profile
{

	public UserProfileScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy (xpath=NAME)
	WebElement name;
	
	@AndroidFindBy (xpath=ADDRESSLINE1)
	WebElement addressLine1;
	
	@AndroidFindBy (xpath=ADDRESSLINE2)
	WebElement addressLine2;
	
	@AndroidFindBy (xpath=ZIPCODE)
	WebElement postalCode;
	
	@AndroidFindBy (xpath=CITY)
	WebElement city;
	
	@AndroidFindBy (id=STATE)
	WebElement state;

	@AndroidFindBy (id=COUNTRY)
	WebElement country;
	
	@AndroidFindBy (xpath=EMAIL)
	WebElement email;

	@AndroidFindBy (id=SAVE)
	WebElement saveBtn;
	
	@AndroidFindBy (id=TITLETEXT)
	WebElement topNavTitle;
	
	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;
	
	@AndroidFindBy(id = ALERTMESSAGE)
	WebElement alertMessage;
	
	@AndroidFindBy(id = ALERTOK)
	WebElement okButton;
	
	@AndroidFindBy(xpath = SELECTEDSTATE)
	WebElement selectedState;
	
	@AndroidFindBy(xpath = SELECTEDCOUNTRY)
	WebElement selectedCountry;
	
	@AndroidFindBy(xpath = FIELD_ERROR_MSG)
	WebElement emptyFieldMessage;

	public void setFullName(String fullName) throws InterruptedException
	{
		Thread.sleep(5000);
		name.sendKeys(fullName);
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
	
	public void setZipCode(String ZipCode)
	{
		postalCode.clear();
		postalCode.sendKeys(ZipCode);
	}
	
	public void setCity(String City)
	{
		city.clear();
		city.sendKeys(City);
		scrollToText("Save");
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
	
	public void clickOnSaveButton()
	{
		Assert.assertTrue(saveBtn.isDisplayed(), "Save button is not displayed.");
		Assert.assertTrue(saveBtn.isEnabled(), "Save button is not clickable.");
		click(saveBtn);
		
	}
	
	public void clickOnOkButton()
	{
		Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed.");
		Assert.assertEquals(alertMessage.getAttribute("text"), "Your profile has been successfully updated.","Alert message after save does not match.");
		click(okButton);
	}
	
	public void verifyChangesAreUpdatedInUserProfile(String ExpectedName , String AddressLine1 , String AddressLine2 , String PostalCode , String City ,String State , String Country , String Email) throws InterruptedException
	{
		Thread.sleep(6000);
		Assert.assertEquals(name.getAttribute("text"), ExpectedName);
		Assert.assertEquals(addressLine1.getAttribute("text"), AddressLine1);
		Assert.assertEquals(addressLine2.getAttribute("text"), AddressLine2);
		Assert.assertEquals(postalCode.getAttribute("text"), PostalCode);
		Assert.assertEquals(city.getAttribute("text"), City);
		scrollToText("Save");
		Assert.assertEquals(selectedState.getAttribute("text"), State);
		Assert.assertEquals(selectedCountry.getAttribute("text"), Country);
		Assert.assertEquals(email.getAttribute("text"),Email);
	}
	
	public void verifyErrorMessageIsDisplayedForEmptyFields()
	{
		Assert.assertTrue(emptyFieldMessage.isDisplayed(), "Empty Field Message is not displayed");
		Assert.assertEquals(emptyFieldMessage.getAttribute("text"),"Field can't be empty.");
	}
	
	
}

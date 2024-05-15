package org.nibejpi.pro.app.auto.pages.editspprofile;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AgreementScreen extends ScreenActions implements ServicePartnerProfile
{

	public AgreementScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = TITLETEXT)
	WebElement titleText;
	
	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;
	
	@AndroidFindBy(xpath = AGREEMENT_CONTENT)
	WebElement agreementContent;
	
	public void verifyAgreementScreenIsDisplayed()
	{
		waitForElementToBeVisible(agreementContent);
		Assert.assertEquals(titleText.getAttribute("text"),"Terms of Service (Organization)");
		Assert.assertTrue(backButton.isDisplayed(), "Password input box on Alert is not displayed.");
		Assert.assertTrue(agreementContent.getAttribute("text").contains("Agreement "),"Content not displayed.");
		scrollToText("Personal data");
		click(backButton);
		waitForElementToBeVisible(titleText);
		Assert.assertEquals(titleText.getAttribute("text"),"Service Partner Profile");
	}
}

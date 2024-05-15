package org.nibejpi.pro.app.auto.pages.editspprofile;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ServicePartnerProfileScreen extends ScreenActions implements ServicePartnerProfile
{

	public ServicePartnerProfileScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = PROFILE)
	WebElement profile;
	
	@AndroidFindBy(xpath = LEAVE)
	WebElement leave;
	
	@AndroidFindBy(xpath = DELETE)
	WebElement delete;
  
	@AndroidFindBy(xpath = AGREEMENT)
	WebElement agreement;
	
	@AndroidFindBy (id=TITLETEXT)
	WebElement topNavTitle;
	
	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;
	
	public void clickOnProfile()
	{
		click(profile);
	}
	
	public void clickOnLeave()
	{
		click(leave);
	}
	
	public void clickOnDelete()
	{
		click(delete);
	}
	
	public void clickOnAgreement()
	{
		click(agreement);
	}
	
	public void verifySPProfileScreenUI()
	{
		verifyUIofElement(profile,"Profile","Profile tab");
		verifyUIofElement(leave,"Leave","Leave tab");
		verifyUIofElement(delete,"Delete","Delete tab");
		verifyUIofElement(agreement,"Agreement","Agreement tab");
		Assert.assertTrue(topNavTitle.isDisplayed(),"TopNavTitle is not displayed");
		Assert.assertTrue(backButton.isDisplayed(),"BackButton is not displayed");
		Assert.assertEquals(topNavTitle.getAttribute("text"), "Service Partner Profile");
	}
}
 
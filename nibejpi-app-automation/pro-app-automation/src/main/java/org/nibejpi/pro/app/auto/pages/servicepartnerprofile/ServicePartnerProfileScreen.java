package org.nibejpi.pro.app.auto.pages.servicepartnerprofile;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ServicePartnerProfileScreen extends ScreenActions implements SPProfile
{

	public ServicePartnerProfileScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);	
	}

	//Elements of Service Partner Profile Screen
	@AndroidFindBy(xpath=SPPROFILE)
	WebElement spProfileBtn;
	
	@AndroidFindBy(xpath=LEAVE)
	WebElement leaveBtn;
	
	@AndroidFindBy(xpath=DELETE)
	WebElement deleteBtn;
	
	@AndroidFindBy(xpath=AGREEMENT)
	WebElement agreementBtn;
	
	@AndroidFindBy(xpath=ADDRESSLINE1)
	WebElement addressLine1;
	
	@AndroidFindBy(id = BACK_BTN)
	WebElement navigateBack;
	
	//Click on the Service Partner Profile button.
	public void clickOnSPProfileButton()
	{
		spProfileBtn.click();
	}
	
	//Verify that a service partner is created by checking the address.
	public void verifyServicePartnerIsCreated() throws InterruptedException
	{
		Thread.sleep(10000);
		Assert.assertEquals(addressLine1.getAttribute("text"), "5151 , Grand Street");
	}
	
	public void navigateBack()
	{
		click(navigateBack);
	}
	
	
	
}

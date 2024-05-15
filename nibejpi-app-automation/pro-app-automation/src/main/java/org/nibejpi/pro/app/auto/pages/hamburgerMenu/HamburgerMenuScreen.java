package org.nibejpi.pro.app.auto.pages.hamburgerMenu;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HamburgerMenuScreen extends ScreenActions implements HamburgerMenu
{

	public HamburgerMenuScreen(AppiumDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = FIRMWARE_BTN )
	WebElement firmwareBtn;
	
	@AndroidFindBy(xpath = SECURITY_BTN )
	WebElement securityBtn;
	
	@AndroidFindBy(xpath = GROUPING_BTN )
	WebElement groupingBtn;
	
	@AndroidFindBy(xpath = PROFILE_BTN )
	WebElement spProfileBtn;
	
	@AndroidFindBy(xpath = SERVICEPARTNER_BTN )
	WebElement servicePartnerBtn;
	
	@AndroidFindBy(xpath = SETTINGS_BTN )
	WebElement settingsBtn;
	
	@AndroidFindBy(xpath = HELP_BTN )
	WebElement helpBtn;
	
	
	public void tapOnSettingsButton()
	{
		settingsBtn.click();
	}
	
	public void tapOnSPProfileButton()
	{
		spProfileBtn.click();
	}
	
	public void tapOnServicePartnerButton()
	{
		click(servicePartnerBtn);
	}
	
}

package org.nibejpi.pro.app.auto.pages.settings;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsScreen extends ScreenActions implements Settings
{

	public SettingsScreen(AppiumDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy (xpath= APPEARANCE_BTN)
	WebElement appearanceBtn ;
	
	@AndroidFindBy (xpath = PROFILE_BTN)
	WebElement profileBtn;
	
	@AndroidFindBy (xpath = ABOUTMYUPLINK_BTN )
	WebElement aboutMyUpLinkBtn;
	
	@AndroidFindBy (xpath = LOGOUT_BTN )
	WebElement logoutBtn;
	
	@AndroidFindBy(xpath = BACK_NAV_BTN )
	WebElement nav_Back_Btn;
	
	
	
	public void navigateBack()
	{
		waitForElementToBeClickable(nav_Back_Btn);
		nav_Back_Btn.click();
	}
	
	public void clickOnAppearanceButton()
	{
		waitForElementToBeClickable(appearanceBtn);
		appearanceBtn.click();
	}
	
	public void clickOnProfileButton()
	{
		click(profileBtn);
	}
	
	public void clickOnLogoutButton()
	{
		waitForElementToBeClickable(logoutBtn);
		logoutBtn.click();
	}
	
	public void clickOnAboutMyUplinkButton()
	{
		waitForElementToBeClickable(aboutMyUpLinkBtn);
		aboutMyUpLinkBtn.click();
	}
}

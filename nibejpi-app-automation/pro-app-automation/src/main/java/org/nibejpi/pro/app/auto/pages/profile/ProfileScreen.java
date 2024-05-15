package org.nibejpi.pro.app.auto.pages.profile;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProfileScreen extends ScreenActions implements Profile
{

	public ProfileScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath =USER_PROFILE )
	WebElement userProfile;
	
	@AndroidFindBy(xpath = NOTIFICATIONS )
	WebElement notifications;
	
	@AndroidFindBy(xpath =PERMISSSIONS )
	WebElement permissions ;
	
	@AndroidFindBy(xpath =CHANGE_PASSWORD)
	WebElement changePassword ;
	
	@AndroidFindBy(xpath = DELETE_ACCOUNT)
	WebElement deleteAccount;
	
	@AndroidFindBy(id = BACKNAVBTN)
	WebElement backButton;
	
	@AndroidFindBy(id = TITLETEXT)
	WebElement titleText;
	
	public void navigateBack()
	{
		click(backButton);
	}
	
	public void clickOnUserProfile()
	{
		click(userProfile);
	}
	
	public void clickOnNotifications()
	{
		click(notifications);
	}
	
	public void clickOnPermissions()
	{
		click(permissions);
	}
	
	public void clickOnChangePassword()
	{
		click(changePassword);
	}
	
	public void clickOnDeleteAccount()
	{
		click(deleteAccount);
	}
	
	
}

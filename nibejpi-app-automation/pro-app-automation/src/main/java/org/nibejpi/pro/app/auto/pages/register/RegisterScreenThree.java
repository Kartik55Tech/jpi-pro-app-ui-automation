package org.nibejpi.pro.app.auto.pages.register;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterScreenThree extends ScreenActions implements Register
{
	/**
     * Constructor for RegisterScreenThree.
     *
     * @param driver The AppiumDriver instance.
     */
	public RegisterScreenThree(AppiumDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}

	// Elements on the screen identified by AndroidFindBy annotations
	@AndroidFindBy(xpath = TITLE_TXT_SCR_THREE )
	WebElement titleText;
	
	@AndroidFindBy(id = RESEND_BTN )
	WebElement resendBtn;
	
	@AndroidFindBy(id = CONTINUE_BTN )
	WebElement continueBtn;
	
	@AndroidFindBy(xpath = TOAST_MSG)
	WebElement toastMsg;
	
	@AndroidFindBy (id = BACK_BTN3)
	WebElement backBtn;
	
	 /**
     * Verifies the email verification toast message.
     * This method retrieves the toast message and asserts its content.
	 * @throws InterruptedException 
     */
	public void verifyEmailVerificationToastMessage() throws InterruptedException
	{
		Thread.sleep(4000);
		String toastmsg = driver.findElement(By.xpath(TOAST_MSG)).getAttribute("name");
		System.out.println("Toast message :"+ toastmsg);
		Assert.assertEquals(toastmsg,"E-mail address has not been verified yet");
	}
	
	 /**
     * Verifies the visibility and status of UI elements on Register Screen Three.
     */
	public void verifyRegistrationScreenThreeUI()
	{
		Assert.assertTrue(titleText.isDisplayed(), "Verify your email screen is not displayed.");
		//Resend button
		Assert.assertTrue(resendBtn.isDisplayed(), "Resend Button is not displayed");
		Assert.assertTrue(resendBtn.isEnabled(), "Resend Button is not clickable");
	
		//Continue button
		Assert.assertTrue(continueBtn.isDisplayed(), "Continue Button is not displayed");
		Assert.assertTrue(continueBtn.isEnabled(), "Continue Button is not clickable");
		
		//Back button
		Assert.assertTrue(backBtn.isDisplayed(), "Back Button is not displayed");
		Assert.assertTrue(backBtn.isEnabled(), "Back Button is not clickable");
		
		click(backBtn);
		
	}
	
	
	
	
}

package org.nibejpi.pro.app.auto.pages.modes;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddModeScreen extends ScreenActions implements Modes
{

	public AddModeScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = ADD_BUTTON)
	WebElement addButton;
	
	@AndroidFindBy(id = MODE_NAME_TEXT)
	WebElement modeNameTextField;
	
	@AndroidFindBy(id = NEXT_BUTTON)
	WebElement nextButton;
	
	@AndroidFindBy(id = CANCEL_BUTTON)
	WebElement cancelButton;
	
	@AndroidFindBy(id = TEXT_INPUT_ERROR )
	WebElement textErrorMsg;
	
	@AndroidFindBy(id = CHECK_VIEW_IMG)
	WebElement checkViewImg;
	
	public void tapOnAddbutton()
	{
		click(addButton);
	}
	
	public void setModeName(String modeName)
	{
		modeNameTextField.sendKeys(modeName);
	}
	
	public void tapOnNextButton()
	{
		click(nextButton);
	}
	
	public void tapOnCancelButton()
	{
		click(cancelButton);
	}
	
	public void tapOnCreateButton()
	{
		click(nextButton);
	}
	
	public void selectModeSettingsName(String modeSetting) {

	    // Use the 'modeSetting' parameter to create a dynamic XPath
	    String modeXpath = String.format("//android.widget.TextView[@text='%s']", modeSetting);

	    // Scroll to the element with the specified text and click
	    driver.findElement(By.xpath(modeXpath)).click();
	    
	    Assert.assertTrue(checkViewImg.isDisplayed(), "Mode setting is not selected.");
	}
	
	public void verifyModeIsAdded(String modeName)
	{
		waitForElementToBeVisible(addButton);
		// Use the 'modeName' parameter to create a dynamic XPath
	    String modeXpath = String.format("//android.widget.TextView[@text='%s']", modeName);
	    WebElement mode = driver.findElement(By.xpath(modeXpath));
	    Assert.assertTrue(mode.isDisplayed(), "Mode is not added successfully");
	}
	
	
	
}

package org.nibejpi.pro.app.auto.pages.vacation;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class VacationScreen extends ScreenActions implements Vacation
{

	public VacationScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = ADD_BUTTON)
	WebElement addButton;
	
	@AndroidFindBy(id = MONTH_NAME )
	WebElement monthName;
	
	@AndroidFindBy(accessibility = PREVIOUS )
	WebElement previous;

	@AndroidFindBy(accessibility = NEXT )
	WebElement next;
	
	public void tapOnAddButton()
	{
		click(addButton);
	}
	
	
}

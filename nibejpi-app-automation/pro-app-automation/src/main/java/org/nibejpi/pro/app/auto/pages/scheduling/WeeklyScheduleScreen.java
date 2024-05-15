package org.nibejpi.pro.app.auto.pages.scheduling;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WeeklyScheduleScreen extends ScreenActions implements Scheduling
{

	public WeeklyScheduleScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = ADD_BTN_SUNDAY)
	WebElement addButton;
	
	@AndroidFindBy(xpath = MORE_BTN_SUNDAY)
	WebElement moreButton;
	
	@AndroidFindBy(xpath = COPY_SCHEDULE )
	WebElement copySchedule;
	
	@AndroidFindBy(xpath = DELETE_SCHEDULE)
	WebElement deleteSchedule;
	
	public void tapOnAddButton()
	{
		click(addButton);
	}
	
	public void tapOnMoreButton()
	{
		click(moreButton);
	}
	
	public void tapOnCopySchedule()
	{
		click(copySchedule);
	}
	
	public void tapOnDeleteSchedule()
	{
		click(deleteSchedule);
	}
	
}

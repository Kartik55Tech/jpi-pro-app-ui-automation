package org.nibejpi.pro.app.auto.pages.scheduling;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ScheduleScreen extends ScreenActions implements Scheduling {

	public ScheduleScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = SCHEDULING_ICON)
	WebElement scheduling_Icon;

	@AndroidFindBy(xpath = STATUS_CARD)
	WebElement statusCard;
	
	@AndroidFindBy(id = OVERRIDE_SWITCH)
	WebElement override_Switch;

	@AndroidFindBy(xpath = SCHEDULE)
	WebElement schedule;

	@AndroidFindBy(xpath = VACATION)
	WebElement vacation;

	@AndroidFindBy(xpath = MODES)
	WebElement modes;

	@AndroidFindBy(xpath = SEND_TO_GRP)
	WebElement sendToGroup;

	@AndroidFindBy(id = BACK_BTN)
	WebElement navigateBack;

	public void tapOnSchedulingIcon() {
		
		if (scheduling_Icon.isDisplayed())
		{
			click(scheduling_Icon);
		}
	}

	public void tapOnOverrideSwitch() {
		click(override_Switch);
	}

	public void tapOnScheduling() {
		click(schedule);
	}

	public void tapOnVacation() {
		click(vacation);
	}

	public void tapOnModes() {
		click(modes);
	}

	public void tapOnSendToGroup() {
		click(sendToGroup);
	}

}

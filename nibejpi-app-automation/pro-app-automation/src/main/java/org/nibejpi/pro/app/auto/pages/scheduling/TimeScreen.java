package org.nibejpi.pro.app.auto.pages.scheduling;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TimeScreen extends ScreenActions implements Scheduling
{

	public TimeScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id = EVENT_TIME_PICKER)
	WebElement eventTimePicker;
	
	@AndroidFindBy(id = END_TIME_PICKER)
	WebElement endTimePicker;
	
	@AndroidFindBy(xpath = MINUTE_SELECTOR)
	WebElement minuteSelector;
	
	@AndroidFindBy(xpath = SELECTED_HOUR)
	WebElement selectedHour;
	
	@AndroidFindBy(xpath = SELECTED_MINUTE)
	WebElement selectedMinute;
	
	@AndroidFindBy(xpath = AM_PM_SELECTOR)
	WebElement am_Pm_Selector;
	
	@AndroidFindBy(id = CANCEL_BUTTON)
	WebElement cancelButton;
	
	@AndroidFindBy(id = OK_BUTTON)
	WebElement okButton;
	
	@AndroidFindBy(id = START_TIME )
	WebElement startTime;
	
	@AndroidFindBy(id = STOP_TIME )
	WebElement stopTime;
	
	@AndroidFindBy(id = SAVE_BUTTON )
	WebElement saveButton;
	
	@AndroidFindBy(id = CANCEL)
	WebElement cancel;
	
	@AndroidFindBy(xpath = EVENT_SELECT )
	WebElement selectedEvent;
	
	@AndroidFindBy(id = TOOL_TIP )
	WebElement toolTip ;
	
	@AndroidFindBy(id = BACK_BTN)
	WebElement navigateBack;
	
	@AndroidFindBy(id = SAVE_ALRT )
	WebElement saveAlertButton;
	
	@AndroidFindBy(id = DISCARD_ALRT )
	WebElement discardAlertButton;
	
	
	public void setStartTime(String selectedHr, String selectedMin, String am_Pm) {
	    eventTimePicker.click();
	    while (!(selectedHour.getAttribute("text").equals(selectedHr) &&
	             selectedMinute.getAttribute("text").equals(selectedMin) &&
	             am_Pm_Selector.getAttribute("text").equals(am_Pm))) {
	        click(minuteSelector);
	    }
	    click(okButton);
	    String expectedStartTime = (selectedHr.length() > 1 ? selectedHr : "0" + selectedHr) + ":" + selectedMin + " " + am_Pm;

	    // Use assertions to check if the start time is set successfully
	    Assert.assertEquals(startTime.getAttribute("text"), expectedStartTime,
	            "Start time is not set as expected.");
	    System.out.println("Start Time: " + startTime.getAttribute("text") + " set successfully.");
	}

	public void setStopTime(String selectedHr, String selectedMin, String am_Pm) {
	    endTimePicker.click();

	    while (!(selectedHour.getAttribute("text").equals(selectedHr) &&
	             selectedMinute.getAttribute("text").equals(selectedMin) &&
	             am_Pm_Selector.getAttribute("text").equals(am_Pm))) {
	        click(minuteSelector);
	    }

	    click(okButton);

	    String expectedStopTime = (selectedHr.length() > 1 ? selectedHr : "0" + selectedHr) + ":" + selectedMin + " " + am_Pm;

	    // Use assertions to check if the stop time is set successfully
	    Assert.assertEquals(stopTime.getAttribute("text"), expectedStopTime,
	            "Stop time is not set as expected.");

	    if (stopTime.getAttribute("text").equals(expectedStopTime)) {
	        System.out.println("Stop Time: " + stopTime.getAttribute("text") + " set successfully.");
	    } else {
	        System.out.println("Stop Time not set!");
	    }
	}

	
	public void tapOnNextButton()
	{
		click(saveButton);
	}
	
	public void tapOnCancelButton()
	{
		click(cancelButton);
	}

	public void tapOnSaveButton()
	{
		click(saveButton);
	}
	
	public void saveChanges()
	{
		Assert.assertTrue(selectedEvent.isDisplayed(),"Added event is not displayed on the selected day.");
		click(navigateBack);
		waitForElementToBeVisible(discardAlertButton);
		Assert.assertTrue(discardAlertButton.isDisplayed(),"Discard button is not displayed");
		click(saveAlertButton);
	}
}

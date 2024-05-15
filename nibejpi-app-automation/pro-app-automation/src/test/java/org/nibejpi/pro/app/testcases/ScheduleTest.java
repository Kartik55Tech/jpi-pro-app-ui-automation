package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.CopyScheduleScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.CreateEventScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.DeleteScheduleScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.ScheduleScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.TimeScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.WeeklyScheduleScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleTest extends TestBase
{
	private LoginHelper loginHelper;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private ScheduleScreen schedule;
	private WeeklyScheduleScreen weeklySchedule;
	private CreateEventScreen createEvent;
	private TimeScreen timeScreen ;
	private CopyScheduleScreen copySchedule;
	private DeleteScheduleScreen deleteSchedule;
	
	@BeforeMethod
	public void setUp() throws Exception {
		loginHelper = new LoginHelper(driver);
		spscreen = new ServicePartnerScreen(driver); 
		system = new SystemsScreen(driver);
		schedule = new ScheduleScreen(driver);
		weeklySchedule = new WeeklyScheduleScreen(driver);
		createEvent = new CreateEventScreen(driver);
		timeScreen = new TimeScreen(driver);
		copySchedule = new CopyScheduleScreen(driver);
		deleteSchedule = new DeleteScheduleScreen(driver);
		loginHelper.performLoginSetup();
		loginHelper.login();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Scheduling");
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1,3 ));
		system.selectSystem(ExcelUtils.getCellData(1,4 ));
		schedule.tapOnSchedulingIcon();
		schedule.tapOnScheduling();
	}
	
	@Test(priority = 1, description = "Verify is user is able to schedule an event. ")
	@AzureTestCaseID(ID = { "" })
	public void testEventSchedulingSuccess() throws Exception 
	{
		
		weeklySchedule.tapOnAddButton();
		createEvent.selectMode(ExcelUtils.getCellData(1,5));
		timeScreen.setStartTime("6","30","PM");
		timeScreen.tapOnNextButton();
		timeScreen.setStopTime("8","00","PM");
		timeScreen.tapOnSaveButton();
		timeScreen.saveChanges();
	}
	
	@Test(priority = 2, description = "Verify is user is able to copy schedule. ")
	@AzureTestCaseID(ID = { "" })
	public void testCopyScheduleFunctionality() throws Exception 
	{
		weeklySchedule.tapOnMoreButton();
		weeklySchedule.tapOnCopySchedule();
		copySchedule.selectDayToCopy("Monday");
		copySchedule.selectDayToCopy("Saturday");
		copySchedule.clickOnCopyButton();
		copySchedule.verifyScheduledIsCopied();
		timeScreen.saveChanges();
	}
	
	@Test(priority = 3, description = "Verify is user is able to delete schedule. ")
	@AzureTestCaseID(ID = { "" })
	public void testDeleteScheduleFunctionality() throws Exception 
	{
		weeklySchedule.tapOnMoreButton();
		weeklySchedule.tapOnDeleteSchedule();
		deleteSchedule.verifyDeleteScheduleAlertIsDisplayed();
		deleteSchedule.tapOnCancelButton();
		weeklySchedule.tapOnDeleteSchedule();
		deleteSchedule.tapOnDeleteButton();
		deleteSchedule.verifyThatScheduledEventIsDeleted();
		timeScreen.saveChanges();
	}	
}

package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.ScheduleScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.vacation.CreateVacationScreen;
import org.nibejpi.pro.app.auto.pages.vacation.VacationScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VacationTest extends TestBase
{
	private LoginHelper loginHelper;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private ScheduleScreen schedule;
	private VacationScreen vacation;
	private CreateVacationScreen createVacation;

	@BeforeMethod
	public void setUp() throws Exception {
		loginHelper = new LoginHelper(driver);
		spscreen = new ServicePartnerScreen(driver); 
		system = new SystemsScreen(driver);
		schedule = new ScheduleScreen(driver);
		vacation = new VacationScreen(driver);
		createVacation = new CreateVacationScreen(driver);
		loginHelper.performLoginSetup();
		loginHelper.login();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Scheduling");
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1,3 ));
		system.selectSystem("NIBE S1255-6 E EM 3X400V");  //ExcelUtils.getCellData(1,4 )
		schedule.tapOnSchedulingIcon();
		schedule.tapOnVacation();
	}
	
	@Test(priority = 1, description = "Verify is user is able to schedule a vacation event ")
	@AzureTestCaseID(ID = { "" })
	public void testVacationEventScheduling() throws Exception 
	{
		vacation.tapOnAddButton();
		createVacation.setStartDate("1", "July 2024");
		createVacation.setEndDate("28", "July 2024");
		createVacation.tapOnNextButton();
		createVacation.selectMode("test");
		createVacation.tapOnCreateButton();
	}
	
	@Test(priority = 2, description = "Verify is user is able to edit a vacation event ")
	@AzureTestCaseID(ID = { "" })
	public void testVacationEventEditing() throws Exception 
	{
		
	}

		
	
	
}

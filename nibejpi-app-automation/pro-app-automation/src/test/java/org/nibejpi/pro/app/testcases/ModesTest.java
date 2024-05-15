package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.modes.AddModeScreen;
import org.nibejpi.pro.app.auto.pages.modes.DeleteModeScreen;
import org.nibejpi.pro.app.auto.pages.modes.EditModesScreen;
import org.nibejpi.pro.app.auto.pages.scheduling.ScheduleScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModesTest extends TestBase
{
	private LoginHelper loginHelper;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private ScheduleScreen schedule;
	private AddModeScreen addMode;
	private EditModesScreen editMode;
	private DeleteModeScreen deleteMode;

	@BeforeMethod
	public void setUp() throws Exception {
		loginHelper = new LoginHelper(driver);
		spscreen = new ServicePartnerScreen(driver); 
		system = new SystemsScreen(driver);
		schedule = new ScheduleScreen(driver);
		addMode = new AddModeScreen(driver);
		editMode = new EditModesScreen(driver);
		deleteMode = new DeleteModeScreen(driver);
		loginHelper.performLoginSetup();
		loginHelper.login();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Scheduling");
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1,3 ));
		//system.selectSystem(ExcelUtils.getCellData(1,4 )); 
		system.selectSystem("QA-Test-Simulator");
		schedule.tapOnSchedulingIcon();
		schedule.tapOnModes();
	}
	
	
	//Pre-requisites : QA-Test-Simulator device should be online and connected to user.
	
	@Test(priority = 1, description = "Verify is user is able to add a Mode. ")
	@AzureTestCaseID(ID = { "" })
	public void testAddModeFunctionality() throws Exception 
	{
		addMode.tapOnAddbutton();
		addMode.setModeName("QA1");
		addMode.tapOnNextButton();
		addMode.selectModeSettingsName("Block compressor");
		addMode.tapOnNextButton();
		addMode.tapOnCreateButton();
		addMode.verifyModeIsAdded("QA1");
	}
	
	@Test(priority = 2, description = "Verify is user is able to edit a Mode. ")
	@AzureTestCaseID(ID = { "" })
	public void testEditModeFunctionality() throws Exception 
	{
		editMode.selectModeNameToEdit("QA1");
		editMode.clearModeName();
		editMode.setModeName("QA2");
		editMode.tapOnNextButton();
		editMode.selectModeSettingsName("Block additional heat");
		editMode.tapOnNextButton();
		editMode.tapOnCreateButton();
		editMode.verifyModeIsEdited("QA2");
	}
	
	@Test(priority = 3, description = "Verify is user is able to delete a Mode. ")
	@AzureTestCaseID(ID = { "" })
	public void testDeleteModeFunctionality() throws Exception 
	{
		deleteMode.swipeToDeleteMode("QA2");
		deleteMode.cancelModeDeletionByClickingNo();
		deleteMode.swipeToDeleteMode("QA2");
		deleteMode.confirmModeDeletionByClickingYes();
		deleteMode.verifyModeIsDeleted("QA2");
	}
	
	
}

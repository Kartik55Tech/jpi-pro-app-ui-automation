package org.nibejpi.pro.app.testcases;

import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.grouping.CreateGroupScreen;
import org.nibejpi.pro.app.auto.pages.grouping.DeleteGroupScreen;
import org.nibejpi.pro.app.auto.pages.grouping.EditGroupScreen;
import org.nibejpi.pro.app.auto.pages.grouping.GroupingScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class GroupingTest extends TestBase

{
	private LoginHelper loginHelper;
	private ServicePartnerScreen spScreen;
	private SystemsScreen system;
	private GroupingScreen grouping;
	private CreateGroupScreen createGroup;
	private EditGroupScreen editGroup;
	private DeleteGroupScreen deleteGroup;
	
	// Initialize necessary objects and perform login setup
	@BeforeClass
	public void setUp() throws Exception 
	{
		loginHelper = new LoginHelper(driver);
		spScreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		grouping = new GroupingScreen(driver);
		createGroup = new CreateGroupScreen(driver) ;
		editGroup = new EditGroupScreen(driver);
		deleteGroup = new DeleteGroupScreen(driver);
		loginHelper.performLoginSetup();
		loginHelper.login();
        ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA,"Grouping");
		spScreen.setServicePartnerName(ExcelUtils.getCellData(1, 3));
	}
	
	/**
     * Test Case: Verify the creation of a group.
     *
     * This test case navigates through the application to create a group and verifies its creation.
     *
     * Test Steps:
     * 1. Navigate to the Grouping section.
     * 2. Create a top-level group.
     * 3. Create three nested groups.
     * 4. Verify the created groups and associated systems.
     */
	@Test(priority = 1, description = "Verify the create group functionality with systems.")
	@AzureTestCaseID(ID = { "1062" })
	public void testCreateGroupFunctionalityWithSystems() throws Exception 
	{
		//APPS- 2924
		system.tapHamburgerMenu();
		system.tapGrouping();
		grouping.tapOnCreateButton();
		createGroup.setGroupName("Test Case 1062");
		createGroup.tapNextButton();
		createGroup.chooseGroupLevel("Make top level group");
		createGroup.tapNextButton();
		createGroup.selectSystem("rishi-clm");
		createGroup.tapOnSaveButton();
		grouping.navigateBack();
		system.tapHamburgerMenu();
		system.tapGrouping();
		grouping.tapOnCreateButton();
		createGroup.setGroupName("Automation Test-Lvl 1");
		createGroup.tapNextButton();
		createGroup.chooseGroupLevel("Test Case 1062");
		createGroup.tapNextButton();
		createGroup.selectSystem("rishi-clmtest");
		createGroup.tapOnSaveButton();
		grouping.tapOnCreateButton();
		createGroup.setGroupName("Automation Test-Lvl 2");
		createGroup.tapNextButton();
		createGroup.chooseGroupLevel("Automation Test-Lvl 1");
		createGroup.tapNextButton();
		createGroup.selectSystem("rishihoiaxtest");
		createGroup.tapOnSaveButton();
		grouping.tapOnCreateButton();
		createGroup.setGroupName("Automation Test-Lvl 3");
		createGroup.tapNextButton();
		createGroup.chooseGroupLevel("Automation Test-Lvl 2");
		createGroup.tapNextButton();
		createGroup.selectSystem("my-test-device");
		createGroup.tapOnSaveButton();
		createGroup.verifyGroupIsCreated("Test Case 1062","Automation Test-Lvl 1" ,"Automation Test-Lvl 2" ,"Automation Test-Lvl 3",
				"rishi-clm" ,"rishi-clmtest" , "rishihoiaxtest", "my-test-device");
	}
	
	@Test(priority = 2, description = "Verify create group functionality without adding system.")
	@AzureTestCaseID(ID = { "5903" })
	public void testCreateGroupFunctionalityWithoutSystem() throws Exception 
	{
		
		
		
	}
	
	
	
	/**
     * Test Case: Verify the validations during the creation of a group.
     *
     * This test case verifies the validation messages when creating a group with invalid inputs.
     *
     * Test Steps:
     * 1. Navigate to the Grouping section.
     * 2. Click on the Create button without providing any details.
     * 3. Verify the empty field validation.
     * 4. Provide a group name and proceed without selecting a system.
     * 5. Verify the validation for not placing the group without system selection.
     */
	@Test(priority = 3, description = "Verify the validations during the creation of a group.")
	@AzureTestCaseID(ID = { "1061" })
	public void verifyValidationsDuringGroupCreation() throws Exception 
	{
		system.tapHamburgerMenu();
		system.tapGrouping();
		grouping.verifyGroupingScreenValidations();
		grouping.tapOnCreateButton();
		createGroup.tapNextButton();
		createGroup.verifyEmptyFieldValidation();
		createGroup.setGroupName("Automation Test 1");
		createGroup.tapNextButton();
		createGroup.verifyGroupIsNotPlacedWithoutSelection();
	}
	
	/**
     * Test Case: Verify the functionality of edit group.
     *
     * This test case verifies the functionality to edit an existing group.
     *
     * Test Steps:
     * 1. Navigate to the Grouping section.
     * 2. Click on the Edit button.
     * 3. Select a group to edit.
     * 4. Rename the group, place it in a different level, and edit associated systems.
     * 5. Save the changes and verify the edited group and associated systems.
     */
	@Test(priority = 4, description = "Verify the functionality of edit group.")
	@AzureTestCaseID(ID = { "1065" })
	public void verifyEditGroupFunctionality() throws Exception 
	{
		system.tapHamburgerMenu();
		system.tapGrouping();
		grouping.tapOnEditButton();
		editGroup.selectGroupToEdit("Automation Test-Lvl 3");
		editGroup.renameGroup("Rename Automation Test-Lvl 3");
		editGroup.placeTheGroup("Automation Test-Lvl 2");
		editGroup.editSystemsInGroup("my-test-device");
		editGroup.tapOnSaveButton();
		editGroup.verifyGroupIsEdited("Mobile Automation - Group","Automation Test-Lvl 1" ,"Automation Test-Lvl 2" ,"Rename Automation Test-Lvl 3",
				"QA-Test-Simulator" ,"rishi-nibe" , "rishihoiaxtest", "my-test-device");
	}
	
	/**
     * Test Case: Verify the functionality of delete group.
     *
     * This test case verifies the functionality to delete groups.
     *
     * Test Steps:
     * 1. Navigate to the Grouping section.
     * 2. Click on the Delete button.
     * 3. Select groups to delete and confirm deletion.
     * 4. Verify that the deleted groups are no longer present.
     */
	@Test(priority = 4, description = "Verify the functionality of delete group.")
	@AzureTestCaseID(ID = { "1071" })
	public void verifyDeleteGroupFunctionality() throws Exception 
	{
		system.tapHamburgerMenu();
		system.tapGrouping();
		grouping.tapOnDeleteButton();
		deleteGroup.selectGroupToDelete("Mobile Automation - Group");
		deleteGroup.tapOnDeleteButton();
		deleteGroup.confirmDelete();
		grouping.tapOnDeleteButton();
		deleteGroup.selectGroupToDelete("Automation Test-Lvl 1");
		deleteGroup.tapOnDeleteButton();
		deleteGroup.confirmDelete();
		grouping.tapOnDeleteButton();
		deleteGroup.selectGroupToDelete("Automation Test-Lvl 2");
		deleteGroup.tapOnDeleteButton();
		deleteGroup.confirmDelete();
		grouping.tapOnDeleteButton();
		deleteGroup.selectGroupToDelete("Rename Automation Test-Lvl 3");
		deleteGroup.tapOnDeleteButton();
		deleteGroup.cancelDelete();
		deleteGroup.tapOnDeleteButton();
		deleteGroup.confirmDelete();
		deleteGroup.verifyGroupIsDeleted();	
	}
	
}

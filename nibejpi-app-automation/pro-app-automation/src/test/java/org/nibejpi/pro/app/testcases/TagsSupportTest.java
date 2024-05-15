package org.nibejpi.pro.app.testcases;
import org.nibejpi.app.annotation.AzureTestCaseID;
import org.nibejpi.app.base.TestBase;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.dashboard.SystemsScreen;
import org.nibejpi.pro.app.auto.pages.servicepartner.ServicePartnerScreen;
import org.nibejpi.pro.app.auto.pages.tagsupport.TagsScreen;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class TagsSupportTest extends TestBase {
	private LoginHelper loginhelper;
	private ServicePartnerScreen spscreen;
	private SystemsScreen system;
	private TagsScreen tag;

	@BeforeClass
	public void setUp() throws Exception {
		loginhelper = new LoginHelper(driver);
		spscreen = new ServicePartnerScreen(driver);
		system = new SystemsScreen(driver);
		tag = new TagsScreen(driver);
		loginhelper.performLoginSetup();
		loginhelper.login();
		ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "Tags");
		spscreen.setServicePartnerName(ExcelUtils.getCellData(1, 3));
	}

	// Test method to verify that user is able to add tags for a system
	@Test(priority = 1, description = "Verify that user is able to add tags for a system.")
	@AzureTestCaseID(ID = { "4835" })
	public void testAddTagsForSystem() throws Exception {
		system.selectSystem(ExcelUtils.getCellData(1, 4));
		tag.tapOnTags();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(1, 5));
		tag.tapOnSaveButton();
		tag.verifyTagIsCreated(ExcelUtils.getCellData(1, 5));
	}

	// Test method to verify that user is not able to create duplicate tags in a system
	@Test(priority = 2, description = "Verify that user is not able to create duplicate tags in a system.")
	@AzureTestCaseID(ID = { "5143" })
	public void testNoDuplicateTagsCreated() throws Exception {
		system.selectSystem(ExcelUtils.getCellData(2, 4));
		tag.tapOnTags();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(2, 5));
		tag.tapOnSaveButton();
		tag.verifyDuplicateTagIsNotCreated();
	}

	 // Test method to verify that user is able to add tag name maximum up to 20 characters
	@Test(priority = 3, description = "Verify that user is able to add tag name maximum upto 20 characters.")
	@AzureTestCaseID(ID = { "4836" })
	public void testTagMaxLengthLimit() throws Exception {
		system.selectSystem(ExcelUtils.getCellData(3, 4));
		tag.tapOnTags();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(3, 5));
		tag.verifyTagNameMaxLengthIsUpto20Characters();
	}

	// Test method to verify that user is able to cancel the process while adding tag
	@Test(priority = 4, description = "Verify that user is able to cancel the process while adding tag.")
	@AzureTestCaseID(ID = { "4842" })
	public void testCancelTagAddingProcess() throws Exception {
		system.selectSystem(ExcelUtils.getCellData(4, 4));
		tag.tapOnTags();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(4, 5));
		tag.tapOnCancelButton();
		tag.verifyTagAddingProcessIsCancelled();
	}

	// Test method to verify that Show More/Show Less button is displayed only when up to 5 tags are added
	@Test(priority = 5, description = "Verify that Show More/Show less button is displayed only when upto 5 tags are added.")
	@AzureTestCaseID(ID = { "4844" })
	public void testShowMoreLessButtonDisplay() throws Exception {
		system.selectSystem(ExcelUtils.getCellData(5, 4));
		tag.tapOnTags();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(5, 5));
		tag.tapOnSaveButton();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(5, 6));
		tag.tapOnSaveButton();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(5, 7));
		tag.tapOnSaveButton();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(5, 8));
		tag.tapOnSaveButton();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(5, 9));
		tag.tapOnSaveButton();
		tag.tapOnAddTagButton();
		tag.setTagName(ExcelUtils.getCellData(5, 10));
		tag.tapOnSaveButton();
		tag.verifyShowMoreShowLessButtonIsDisplayed("QA1", "QA2","QA3","QA4","QA5","QA6");

	}
	
	// Test method to verify that tags should not be visible in map view
	@Test(priority = 6, description = "	Verify that tags should not be visible in map view.")
	@AzureTestCaseID(ID = { "4843" })
	public void testTagsNotVisibleInMapView() throws InterruptedException, Exception
	{
		system.tapMapView();
		tag.verifyTagIsNotVisibleInMapView(ExcelUtils.getCellData(6, 5));
	}
	
	 // Test method to verify that user is able to delete added tags
	@Test(priority = 7, description = "Verify that user is able to delete added tags.")
	@AzureTestCaseID(ID = { "4841" })
	public void testDeleteTagsForSystem() throws Exception 
	{
		system.selectSystem(ExcelUtils.getCellData(7, 4));
		tag.tapOnTags();
		tag.swipeToDeleteTag(ExcelUtils.getCellData(7, 6));
		tag.confirmTagDeletionByClickingYes();
		tag.verifyTagIsDeleted(ExcelUtils.getCellData(7, 6));
		tag.swipeToDeleteTag(ExcelUtils.getCellData(7, 7));
		tag.confirmTagDeletionByClickingYes();
		tag.verifyTagIsDeleted(ExcelUtils.getCellData(7, 7));
		tag.swipeToDeleteTag(ExcelUtils.getCellData(7, 8));
		tag.confirmTagDeletionByClickingYes();
		tag.verifyTagIsDeleted(ExcelUtils.getCellData(7, 8));
		tag.swipeToDeleteTag(ExcelUtils.getCellData(7, 9));
		tag.confirmTagDeletionByClickingYes();
		tag.verifyTagIsDeleted(ExcelUtils.getCellData(7, 9));
		tag.swipeToDeleteTag(ExcelUtils.getCellData(7, 10));
		tag.cancelTagDeletionByClickingNo();
		tag.swipeToDeleteTag(ExcelUtils.getCellData(7, 10));
		tag.confirmTagDeletionByClickingYes();
		tag.verifyTagIsDeleted(ExcelUtils.getCellData(7, 10));
	}

	
}

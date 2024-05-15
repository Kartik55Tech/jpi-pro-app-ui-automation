package org.nibejpi.pro.app.auto.pages.grouping;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CreateGroupScreen extends ScreenActions implements Grouping {

	// Constructor
	public CreateGroupScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Screen elements
	@AndroidFindBy(id = GROUP_NAME)
	WebElement groupName;

	@AndroidFindBy(id = NEXT_BTN)
	WebElement nextBtn;

	@AndroidFindBy(id = MAKE_TOP_LVL_GRP)
	WebElement make_TopLvlGrp;

	@AndroidFindBy(id = CHECK_IMG)
	WebElement checkImg;

	@AndroidFindBy(id = BACK_BTN)
	WebElement backBtn;

	@AndroidFindBy(id = TITLE)
	WebElement title;

	@AndroidFindBy(id = FIELD_EMPTY_MSG)
	WebElement empty_Fld_Msg;

	/**
	 * Sets the name for the new group.
	 *
	 * @param groupNameText The text to set as the group name.
	 */
	public void setGroupName(String GroupName) {
		groupName.sendKeys(GroupName);
	}

	/**
	 * Clicks the "Next" button on the screen.
	 */
	public void tapNextButton() {
		click(nextBtn);
	}

	/**
	 * Navigates back to the previous screen.
	 */
	public void navigateBack() {
		click(backBtn);
	}

	/**
	 * Clicks the "Save" button after configuring the group details.
	 */
	public void tapOnSaveButton() {
		scrollToText("Save");
		Assert.assertEquals(nextBtn.getAttribute("text"), "Save", "Save button text is different.");
		click(nextBtn);
	}

	/**
	 * Marks the group as a top-level group.
	 */
	public void makeTopLevelGroup() {
		click(make_TopLvlGrp);
		Assert.assertTrue(checkImg.isDisplayed(), "Make Top Level Group is not checked after clicking.");
	}

	/**
	 * Chooses the group level from the available options.
	 *
	 * @param groupLevel The level to choose for the group.
	 */
	public void chooseGroupLevel(String GroupLevel) {
		String GpLvLxpath = "//android.widget.TextView[@text=\"" + GroupLevel + "\"]";
		WebElement GrpLevel = driver.findElement(By.xpath(GpLvLxpath));
		click(GrpLevel);
	}

	/**
	 * Selects a system from the list.
	 *
	 * @param systemName The name of the system to select.
	 */
	public void selectSystem(String SystemName) {
		int maxAttempts = 3; // Set the maximum number of retry attempts

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Scroll to the FAQ question
				scrollToText(SystemName);

				// Construct the XPath using the complete text
				String xpath = "//android.widget.TextView[@text=\"" + SystemName + "\"]";

				// Find the element and perform the click
				WebElement element = driver.findElement(By.xpath(xpath));
				waitForElementToBeVisible(element);
				waitForElementToBeClickable(element);
				element.click();
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException: Attempt " + attempt);
			}
		}
	}

	/**
	 * Unselects a previously selected system.
	 *
	 * @param serialNumberOfSelectedSystem The serial number of the system to
	 *                                     unselect.
	 */
	public void unselectSystem(String SerialNumberOfSelectedSystem) {
		int maxAttempts = 3; // Set the maximum number of retry attempts

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Scroll to the FAQ question
				scrollToText(SerialNumberOfSelectedSystem);

				// Construct the XPath using the complete text
				String xpath = "//android.widget.TextView[@text=\"" + SerialNumberOfSelectedSystem + "\"]";

				// Find the element and perform the click
				WebElement element = driver.findElement(By.xpath(xpath));
				waitForElementToBeVisible(element);
				waitForElementToBeClickable(element);
				element.click();
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException: Attempt " + attempt);
			}
		}
	}

	/**
	 * Verifies that a group is created with a single system.
	 *
	 * @param groupName  The name of the created group.
	 * @param systemName The name of the system associated with the group.
	 * @throws InterruptedException Thrown in case of thread interruption during
	 *                              sleep.
	 */
	public void verifyGroupIsCreated(String GroupName, String SystemName) throws InterruptedException {
		longWait(2);
		int maxAttempts = 3;
		// Find and click the Group element
		WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupName + "\"]"));
		waitForElementToBeVisible(element);
		Assert.assertTrue(element.isDisplayed(), "Created Group is not displayed on the Grouping Screen");
		Assert.assertEquals(element.getAttribute("text"), GroupName, "GroupName displayed is incorrect.");
		click(backBtn);
		longWait(2);
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				WebElement element1 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupName + "\"]"));
				waitForElementToBeVisible(element1);
				verifyUIofElement(element1, GroupName, "GroupName");
				element1.click();

				// Find and validate the System element
				WebElement element2 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + SystemName + "\"]"));
				verifyUIofElement(element2, SystemName, "SystemName");
				Assert.assertEquals(title.getAttribute("text"), GroupName, "System displayed is incorrect.");

				break; // Break the loop if everything is successful
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException: Attempt " + attempt);
			}
		}
	}

	/**
	 * Verifies that a group is created with multiple levels and associated systems.
	 *
	 * @param topLvlGrpName    The name of the top-level group.
	 * @param groupLvl1        The name of the first-level group.
	 * @param groupLvl2        The name of the second-level group.
	 * @param groupLvl3        The name of the third-level group.
	 * @param topLvlSystemName The name of the system associated with the top-level
	 *                         group.
	 * @param lvl1SystemName   The name of the system associated with the
	 *                         first-level group.
	 * @param lvl2SystemName   The name of the system associated with the
	 *                         second-level group.
	 * @param lvl3SystemName   The name of the system associated with the
	 *                         third-level group.
	 * @throws InterruptedException Thrown in case of thread interruption during
	 *                              sleep.
	 */
	public void verifyGroupIsCreated(String TopLvlGrpName, String GroupLvl1, String GroupLvl2, String GroupLvl3,
			String TopLvlSystemName, String Lvl1SystemName, String Lvl2SystemName, String Lvl3SystemName)
			throws InterruptedException {
		longWait(1);
		int maxAttempts = 3;
		// Find and click the Group element
		WebElement TopLvl = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + TopLvlGrpName + "\"]"));
		waitForElementToBeVisible(TopLvl);
		Assert.assertTrue(TopLvl.isDisplayed(), "Created Group Lvl1 is not displayed on the Grouping Screen");
		Assert.assertEquals(TopLvl.getAttribute("text"), TopLvlGrpName, "GroupName lvl1 displayed is incorrect.");
		click(TopLvl);

		WebElement Lvl1 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupLvl1 + "\"]"));
		waitForElementToBeVisible(Lvl1);
		Assert.assertTrue(Lvl1.isDisplayed(), "Created Group Lvl1 is not displayed on the Grouping Screen");
		Assert.assertEquals(Lvl1.getAttribute("text"), GroupLvl1, "GroupName lvl1 displayed is incorrect.");
		click(Lvl1);

		WebElement Lvl2 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupLvl2 + "\"]"));
		waitForElementToBeVisible(Lvl2);
		Assert.assertTrue(Lvl2.isDisplayed(), "Created GroupLvl 2 is not displayed on the Grouping Screen");
		Assert.assertEquals(Lvl2.getAttribute("text"), GroupLvl2, "GroupName Lvl2 displayed is incorrect.");
		click(Lvl2);

		WebElement Lvl3 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupLvl3 + "\"]"));
		waitForElementToBeVisible(Lvl3);
		Assert.assertTrue(Lvl3.isDisplayed(), "Created GroupLvl 3 is not displayed on the Grouping Screen");
		Assert.assertEquals(Lvl3.getAttribute("text"), GroupLvl3, "GroupName lvl3 displayed is incorrect.");
		click(Lvl3);

		click(backBtn);
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				WebElement element1 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + TopLvlGrpName + "\"]"));
				waitForElementToBeVisible(element1);
				verifyUIofElement(element1, TopLvlGrpName, "GroupName");
				click(element1);

				// Find and validate the System element
				WebElement element2 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + TopLvlSystemName + "\"]"));
				verifyUIofElement(element2, TopLvlSystemName, "SystemName");
				Assert.assertEquals(title.getAttribute("text"), TopLvlGrpName, "System displayed is incorrect.");

				WebElement lvl1 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupLvl1 + "\"]"));
				waitForElementToBeVisible(lvl1);
				verifyUIofElement(lvl1, GroupLvl1, "GroupName");
				click(lvl1);

				WebElement element3 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + Lvl1SystemName + "\"]"));
				verifyUIofElement(element3, Lvl1SystemName, "SystemName");
				Assert.assertEquals(title.getAttribute("text"), GroupLvl1, "System displayed is incorrect.");

				WebElement lvl2 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupLvl2 + "\"]"));
				waitForElementToBeVisible(lvl2);
				verifyUIofElement(lvl2, GroupLvl2, "GroupName");
				click(lvl2);

				WebElement element4 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + Lvl2SystemName + "\"]"));
				verifyUIofElement(element4, Lvl2SystemName, "SystemName");
				Assert.assertEquals(title.getAttribute("text"), GroupLvl2, "System displayed is incorrect.");

				WebElement lvl3 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupLvl3 + "\"]"));
				waitForElementToBeVisible(lvl3);
				verifyUIofElement(lvl3, GroupLvl3, "GroupName");
				click(lvl3);

				WebElement element5 = driver
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + Lvl3SystemName + "\"]"));
				verifyUIofElement(element5, Lvl3SystemName, "SystemName");
				Assert.assertEquals(title.getAttribute("text"), GroupLvl3, "System displayed is incorrect.");

				break; // Break the loop if everything is successful
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException: Attempt " + attempt);
			}
		}
	}

	/**
	 * Verifies that the empty field validation message is displayed.
	 */
	public void verifyEmptyFieldValidation() {
		Assert.assertTrue(empty_Fld_Msg.isDisplayed(), "Empty Field validation does not displayed.");
	}

	/**
	 * Verifies that a group is not placed without system selection.
	 */
	public void verifyGroupIsNotPlacedWithoutSelection() {
		try {
			// Attempt to find the element
			@SuppressWarnings("unused")
			WebElement checkImg = driver.findElement(AppiumBy.id("com.myuplink.pro:id/imageCheck"));

			// If the element is found, fail the test
			Assert.fail("Group level is selected before selection.");
		} catch (NoSuchElementException e) {

		}
	}

}

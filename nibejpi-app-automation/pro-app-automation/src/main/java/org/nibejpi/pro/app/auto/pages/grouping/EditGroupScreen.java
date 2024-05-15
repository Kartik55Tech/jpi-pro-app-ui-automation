package org.nibejpi.pro.app.auto.pages.grouping;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EditGroupScreen extends ScreenActions implements Grouping {

	 /**
     * Constructor for the EditGroupScreen class.
     *
     * @param driver AppiumDriver instance for interacting with the app.
     */
	public EditGroupScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements annotated using PageFactory
	@AndroidFindBy(id = NEXT_BTN)
	WebElement nextBtn;

	@AndroidFindBy(id = GROUP_NAME)
	WebElement groupName;

	@AndroidFindBy(id = TITLE)
	WebElement title;

	@AndroidFindBy(id = BACK_BTN)
	WebElement backBtn;

	 /**
     * Selects a group to edit based on the given group name.
     *
     * @param groupName Name of the group to be edited.
     */
	public void selectGroupToEdit(String GroupName) {
		WebElement GName = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + GroupName + "\"]"));
		waitForElementToBeVisible(GName);
		click(GName);
		click(nextBtn);

	}

	/**
     * Clicks the Save button on the screen.
     */
	public void tapOnSaveButton() {
		scrollToText("Save");
		Assert.assertEquals(nextBtn.getAttribute("text"), "Save", "Save button text is different.");
		click(nextBtn);
	}

	/**
     * Renames the group with the given new name.
     *
     * @param newGroupName New name for the group.
     */
	public void renameGroup(String GroupName) {
		groupName.clear();
		groupName.sendKeys(GroupName);
		Assert.assertTrue(nextBtn.isEnabled(), "Next button is not clickable");
		click(nextBtn);
	}

	/**
     * Places the group at a specific level based on the given group level.
     *
     * @param groupLevel Level at which the group needs to be placed.
     */
	public void placeTheGroup(String GroupLevel) {
		String groupLevelXpath = "//android.widget.TextView[@text=\"" + GroupLevel + "\"]";
		WebElement levelElement = driver.findElement(By.xpath(groupLevelXpath));
		click(levelElement);
		click(nextBtn);
	}

	/**
     * Edits systems within the group by unselecting a specific system.
     *
     * @param systemName Name of the system to be unselected.
     */
	public void editSystemsInGroup(String SystemName) {
		unselectSystem("namrata-simulator");

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
     * Unselects a system within the group based on the given system name.
     *
     * @param systemName Name of the system to be unselected.
     */
	public void unselectSystem(String SystemName) {
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
     * Verifies that the group has been successfully edited with the specified details.
     *
     * @param topLevelGroupName  Top-level group name.
     * @param groupLevel1        Name of group level 1.
     * @param groupLevel2        Name of group level 2.
     * @param groupLevel3        Name of group level 3.
     * @param topLevelSystemName Name of the top-level system.
     * @param level1SystemName   Name of system at level 1.
     * @param level2SystemName   Name of system at level 2.
     * @param level3SystemName   Name of system at level 3.
     * @throws InterruptedException Thrown if thread is interrupted during sleep.
     */
	public void verifyGroupIsEdited(String TopLvlGrpName, String GroupLvl1, String GroupLvl2, String GroupLvl3,
			String TopLvlSystemName, String Lvl1SystemName, String Lvl2SystemName, String Lvl3SystemName)
			throws InterruptedException {
		Thread.sleep(4000);
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
		Thread.sleep(4000);
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

}

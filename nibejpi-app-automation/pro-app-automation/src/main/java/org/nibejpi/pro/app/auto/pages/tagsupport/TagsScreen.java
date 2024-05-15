package org.nibejpi.pro.app.auto.pages.tagsupport;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;

public class TagsScreen extends ScreenActions implements Tags {

	public TagsScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	 // Android elements using PageFactory's @AndroidFindBy annotation
	@AndroidFindBy(xpath = TAGS_ICON)
	WebElement tagsIcon;

	@AndroidFindBy(id = ADD_TAG_BTN)
	WebElement addTagButton;

	@AndroidFindBy(id = TAG_NAME_TXT)
	WebElement tagNameText;

	@AndroidFindBy(id = SAVE_BTN)
	WebElement saveButton;

	@AndroidFindBy(id = CANCEL_BTN)
	WebElement cancelButton;

	@AndroidFindBy(id = BACK_BTN)
	WebElement backButton;

	@AndroidFindBy(id = YES_BTN)
	WebElement yesButton;

	@AndroidFindBy(id = NO_BTN)
	WebElement noButton;

	@AndroidFindBy(id = ALERT_MSG)
	WebElement alertDialogMessage;

	@AndroidFindBy(id = ALERT_TITLE)
	WebElement alertDialogTitle;

	@AndroidFindBy(id = TEXT_INPUT_ERROR)
	WebElement textInputError;

	@AndroidFindBy(id = TEXT_INPUT_COUNTER)
	WebElement textInputCounter;

	@AndroidFindBy(id = SHOWMORE_BTN)
	WebElement showMoreButton;

	/**
     * Clicks on the "Tags" icon after scrolling to find it.
     */
	public void tapOnTags() {
		scrollToText("Tags");
		click(tagsIcon);
	}

	/**
     * Clicks on the "Add Tag" button.
     *
     * @throws InterruptedException
     */
	public void tapOnAddTagButton() throws InterruptedException {
		Thread.sleep(2000);
		click(addTagButton);
	}

	/**
     * Clicks on the "Save" button.
     */
	public void tapOnSaveButton() {
		click(saveButton);
	}

	 /**
     * Clicks on the "Cancel" button.
     */
	public void tapOnCancelButton() {
		click(cancelButton);
	}

	 /**
     * Sets the tag name in the text field.
     *
     * @param TagName The tag name to be set.
     * @throws InterruptedException
     */
	public void setTagName(String TagName) throws InterruptedException {
		Thread.sleep(2000);
		tagNameText.sendKeys(TagName);
	}

	/**
     * Swipes left to delete a tag.
     *
     * @param TagName The name of the tag to be deleted.
     * @throws InterruptedException
     */
	public void swipeToDeleteTag(String TagName) throws InterruptedException 
	{
		Thread.sleep(2000);
		String xpath = "//android.widget.TextView[@text=\"" + TagName + "\"]";
		// Find the element and perform the click
		WebElement tag = driver.findElement(By.xpath(xpath));
		waitForElementToBeVisible(tag);
		swipeLeft(tag);
	}

	 /**
     * Confirms tag deletion by clicking "Yes" on the alert.
     */
	public void confirmTagDeletionByClickingYes() {
		waitForElementToBeVisible(alertDialogTitle);
		Assert.assertEquals(alertDialogTitle.getAttribute("text"), "Delete tag");
		waitForElementToBeVisible(alertDialogMessage);
		Assert.assertEquals(alertDialogMessage.getAttribute("text"), "Are you sure you want to delete this tag?");
		waitForElementToBeVisible(yesButton);
		Assert.assertEquals(yesButton.getAttribute("text"), "Yes");
		click(yesButton);
	}
	
	 /**
     * Cancels tag deletion by clicking "No" on the alert.
     */
	public void cancelTagDeletionByClickingNo()
	{
		waitForElementToBeVisible(alertDialogTitle);
		Assert.assertEquals(alertDialogTitle.getAttribute("text"), "Delete tag");
		waitForElementToBeVisible(alertDialogMessage);
		Assert.assertEquals(alertDialogMessage.getAttribute("text"), "Are you sure you want to delete this tag?");
		waitForElementToBeVisible(noButton);
		Assert.assertEquals(noButton.getAttribute("text"), "No");
		click(noButton);
		System.out.println("Tag delete cancellation process is successful.");
	}

	/**
     * Verifies that a tag with the specified name is created and displayed.
     *
     * @param TagName The name of the tag to be verified.
     * @throws InterruptedException
     */
	public void verifyTagIsCreated(String TagName) throws InterruptedException {
		longWait(2);
		String xpath = "//android.widget.TextView[@text=\"" + TagName + "\"]";
		// Find the element and perform the click
		WebElement tag = driver.findElement(By.xpath(xpath));
		Assert.assertTrue("Tag is not created", tag.isDisplayed());
		click(backButton);
		click(backButton);

		// Re-find the element after navigating back
		WebElement reTag = driver.findElement(By.xpath(xpath));
		scrollToText(TagName);
		Assert.assertTrue("Tag is not displayed under corresponding system.", reTag.isDisplayed());
	}

	/**
     * Verifies that a tag with the specified name is deleted.
     *
     * @param TagName The name of the tag to be deleted.
     * @throws InterruptedException
     */
	public void verifyTagIsDeleted(String TagName) throws InterruptedException {
		Thread.sleep(2000);
		try {
			// Attempt to find the element
			String xpath = "//android.widget.TextView[@text=\"" + TagName + "\"]";
			// Find the element and perform the click
			@SuppressWarnings("unused")
			WebElement tag = driver.findElement(By.xpath(xpath));

			// If the element is found, fail the test
			Assert.fail("Tag is not deleted.");
		} catch (NoSuchElementException e) {
			System.out.println(TagName + " tag is successfully deleted");
			// The element is not found, which is the desired behavior
			// No action needed, absence of the element is expected
			// Test will pass if the element is not found
		}
	}

	 /**
     * Verifies that duplicate tag creation is prevented and displays the correct validation message.
     */
	public void verifyDuplicateTagIsNotCreated() {
		waitForElementToBeVisible(textInputError);
		Assert.assertTrue("Duplicate tag validation message is not displayed", textInputError.isDisplayed());
		Assert.assertEquals(textInputError.getAttribute("text"), "Tag already exists");
	}

	 /**
     * Verifies that the tag name input field has a maximum length of 20 characters.
     */
	public void verifyTagNameMaxLengthIsUpto20Characters() {
		waitForElementToBeVisible(tagNameText);
		Assert.assertEquals(tagNameText.getAttribute("text"), "MobileAutomationTagN");
		waitForElementToBeVisible(textInputCounter);
		Assert.assertEquals(textInputCounter.getAttribute("text"), "20/20");
	}

	/**
     * Verifies that the process of adding a tag is cancelled successfully.
     */
	public void verifyTagAddingProcessIsCancelled() {
		waitForElementToBeVisible(addTagButton);
		Assert.assertTrue("Add Tag Button is not displayed , Process is not cancelled", addTagButton.isDisplayed());
	}

	 /**
     * Verifies the display of "Show More" and "Show Less" buttons for a list of tags.
     *
     * @param Tag1 The name of the first tag.
     * @param Tag2 The name of the second tag.
     * @param Tag3 The name of the third tag.
     * @param Tag4 The name of the fourth tag.
     * @param Tag5 The name of the fifth tag.
     * @param Tag6 The name of the sixth tag.
     * @throws InterruptedException
     */
	public void verifyShowMoreShowLessButtonIsDisplayed(String Tag1, String Tag2, String Tag3, String Tag4, String Tag5,
			String Tag6) throws InterruptedException {
		Thread.sleep(3000);
		String xpath1 = "//android.widget.TextView[@text=\"" + Tag1 + "\"]";
		// Find the element and perform the click
		WebElement tag1 = driver.findElement(By.xpath(xpath1));
		Assert.assertTrue("Tag1 is not created", tag1.isDisplayed());

		String xpath2 = "//android.widget.TextView[@text=\"" + Tag2 + "\"]";
		// Find the element and perform the click
		WebElement tag2 = driver.findElement(By.xpath(xpath2));
		Assert.assertTrue("Tag2 is not created", tag2.isDisplayed());

		String xpath3 = "//android.widget.TextView[@text=\"" + Tag3 + "\"]";
		WebElement tag3 = driver.findElement(By.xpath(xpath3));
		Assert.assertTrue("Tag3 is not created", tag3.isDisplayed());

		String xpath4 = "//android.widget.TextView[@text=\"" + Tag4 + "\"]";
		WebElement tag4 = driver.findElement(By.xpath(xpath4));
		Assert.assertTrue("Tag4 is not created", tag4.isDisplayed());

		String xpath5 = "//android.widget.TextView[@text=\"" + Tag5 + "\"]";
		WebElement tag5 = driver.findElement(By.xpath(xpath5));
		Assert.assertTrue("Tag5 is not created", tag5.isDisplayed());

		String xpath6 = "//android.widget.TextView[@text=\"" + Tag6 + "\"]";
		WebElement tag6 = driver.findElement(By.xpath(xpath6));
		Assert.assertTrue("Tag5 is not created", tag6.isDisplayed());

		click(backButton);
		click(backButton);
		Thread.sleep(5000);

		WebElement reTag1 = driver.findElement(By.xpath(xpath1));
		scrollToText(Tag1);
		Assert.assertTrue("Tag is not displayed under corresponding system.", reTag1.isDisplayed());

		WebElement reTag2 = driver.findElement(By.xpath(xpath2));
		Assert.assertTrue("Tag2 is not displayed under corresponding system.", reTag2.isDisplayed());

		WebElement reTag3 = driver.findElement(By.xpath(xpath3));
		Assert.assertTrue("Tag3 is not displayed under corresponding system.", reTag3.isDisplayed());

		WebElement reTag4 = driver.findElement(By.xpath(xpath4));
		Assert.assertTrue("Tag4 is not displayed under corresponding system.", reTag4.isDisplayed());

		WebElement reTag5 = driver.findElement(By.xpath(xpath5));
		Assert.assertTrue("Tag5 is not displayed under corresponding system.", reTag5.isDisplayed());

		Assert.assertTrue("Show more button is not displayed.", showMoreButton.isDisplayed());
		Assert.assertEquals(showMoreButton.getAttribute("text"), "Show more");
		Assert.assertTrue("Show more button is not clickable.", showMoreButton.isEnabled());
		click(showMoreButton);
		Thread.sleep(2000);
		WebElement reTag6 = driver.findElement(By.xpath(xpath6));
		Assert.assertTrue("Tag6 is not displayed under corresponding system.", reTag6.isDisplayed());

		Assert.assertTrue("Show less button is not displayed.", showMoreButton.isDisplayed());
		Assert.assertEquals(showMoreButton.getAttribute("text"), "Show less");
		Assert.assertTrue("Show less button is not clickable.", showMoreButton.isEnabled());
		click(showMoreButton);
	}

	 /**
     * Verifies that a tag is not visible in the map view.
     *
     * @param TagName The name of the tag to be verified as not visible in the map view.
     * @throws InterruptedException
     */
	public void verifyTagIsNotVisibleInMapView(String TagName) throws InterruptedException 
	{
		Thread.sleep(2000);
		try {
			// Attempt to find the element
			String xpath = "//android.widget.TextView[@text=\"" + TagName + "\"]";
			// Find the element and perform the click
			@SuppressWarnings("unused")
			WebElement tag = driver.findElement(By.xpath(xpath));

			// If the element is found, fail the test
			Assert.fail("Tag is not deleted.");
		} catch (NoSuchElementException e) {
			// The element is not found, which is the desired behavior
			// No action needed, absence of the element is expected
			// Test will pass if the element is not found
		}

	}

}

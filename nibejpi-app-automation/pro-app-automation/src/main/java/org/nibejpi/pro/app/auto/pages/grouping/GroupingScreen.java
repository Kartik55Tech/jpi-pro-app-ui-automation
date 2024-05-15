package org.nibejpi.pro.app.auto.pages.grouping;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GroupingScreen extends ScreenActions implements Grouping
{

	/**
     * Initializes a new instance of the GroupingScreen class.
     *
     * @param driver The AppiumDriver instance to interact with the app.
     */
	public GroupingScreen(AppiumDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
     * WebElements representing  Grouping screen.
     */
	@AndroidFindBy(id = BACK_BTN)
	WebElement backBtn;
	
	@AndroidFindBy(id = TITLE)
	WebElement title;
	
	@AndroidFindBy(id = CREATE_BTN)
	WebElement createBtn;
	
	@AndroidFindBy(id = EDIT_BTN)
	WebElement editBtn;
	
	@AndroidFindBy(id = DELETE_BTN)
	WebElement deleteBtn;
	
	/**
     * Clicks on the 'Create' button on the Grouping screen.
     *
     * @throws InterruptedException Thrown in case of thread interruption during sleep.
     */
	public void tapOnCreateButton() throws InterruptedException
	{
		Thread.sleep(5000);
		click(createBtn);
	}
	
	/**
     * Clicks on the 'Edit' button on the Grouping screen.
     */
	public void tapOnEditButton()
	{
		click(editBtn);
	}
	
	/**
     * Clicks on the 'Delete' button on the Grouping screen.
     */
	public void tapOnDeleteButton()
	{
		click(deleteBtn);
	}
	
	 /**
     * Navigates back from the Grouping screen.
     */
	public void navigateBack()
	{
		click(backBtn);
	}
	
	 /**
     * Verifies that the 'Edit' and 'Delete' buttons are disabled before creating a group.
     */
	public void verifyGroupingScreenValidations()
	{
		Assert.assertFalse(editBtn.isEnabled(), "Edit button is enabled before creating a group.");
		Assert.assertFalse(deleteBtn.isEnabled(),"Delete button is enabled before creating a group.");
	}
}

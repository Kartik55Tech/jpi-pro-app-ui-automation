package org.nibejpi.pro.app.auto.pages.vacation;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CreateVacationScreen extends ScreenActions implements Vacation

{
	public CreateVacationScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = MONTH_NAME)
	WebElement monthName;

	@AndroidFindBy(accessibility = PREVIOUS)
	WebElement previous;

	@AndroidFindBy(accessibility = NEXT)
	WebElement next;

	@AndroidFindBy(id = VACATION_TITLE)
	WebElement vacationTitle;

	@AndroidFindBy(id = VACATION_TOGGLE_BTN)
	WebElement vacationToggleBtn;

	@AndroidFindBy(id = NEXT_BUTTON)
	WebElement nextButton;

	@AndroidFindBy(id = CANCEL_BUTTON)
	WebElement cancelButton;

	@AndroidFindBy(xpath = CREATE_BUTTON)
	WebElement createButton;

	@AndroidFindBy(id = CANCEL_MODE_BUTTON)
	WebElement cancelModeButton;

	@AndroidFindBy(id = CHECK_VIEW)
	WebElement checkView;

	public void setVacationTitle(String VacationTitle) {
		vacationTitle.sendKeys(VacationTitle);
	}

	public void enableVacation() {
		click(vacationToggleBtn);
	}

	public void tapOnNextButton() {
		click(nextButton);
	}

	public void tapOnCancelButton() {
		click(cancelButton);
	}

	public void tapOnCancelMode() {
		click(cancelModeButton);
	}

	public void tapOnCreateButton() {
		click(createButton);
	}

	public void setStartDate(String date, String monthYear) throws InterruptedException {
		String locator1 = "(//android.widget.CheckedTextView[@content-desc=%s])[1]";
		String dynamicLocator1 = String.format(locator1, "\"" + date + "\"");
		System.out.println(dynamicLocator1);

		String locator2 = "(//android.widget.CheckedTextView[@content-desc=%s])[2]";
		String dynamicLocator2 = String.format(locator2, "\"" + date + "\"");
		System.out.println(dynamicLocator2);

		while (!monthName.getAttribute("text").equals(monthYear)) {
			click(next);
		}
		
		WebElement currentMonthDate = driver.findElement(By.xpath(dynamicLocator1));
		

		if (monthName.getAttribute("text").equals(monthYear)) {
			// Convert date string to integer
			int dayOfMonth = Integer.parseInt(date);

			if (dayOfMonth >= 15) {
				WebElement nextMonthDate = driver.findElement(By.xpath(dynamicLocator2));
				click(nextMonthDate);
			} else if (dayOfMonth < 15) {
				click(currentMonthDate);
			}
		}
	}
	
	public void setEndDate(String date, String monthYear) throws InterruptedException {
	    String locator1 = "(//android.widget.CheckedTextView[@content-desc=%s])[1]";
	    String dynamicLocator1 = String.format(locator1, "\"" + date + "\"");
	    System.out.println("Dynamic Locator 1: " + dynamicLocator1);

	    String locator2 = "(//android.widget.CheckedTextView[@content-desc=%s])[2]";
	    String dynamicLocator2 = String.format(locator2, "\"" + date + "\"");
	    System.out.println("Dynamic Locator 2: " + dynamicLocator2);

	    System.out.println("Expected Month and Year: " + monthYear);

	    if (!monthName.getAttribute("text").equals(monthYear)) {
	        System.out.println("Current Month: " + monthName.getAttribute("text"));
	        // The desired month is not displayed, keep clicking "Next" until it is
	        while (!monthName.getAttribute("text").equals(monthYear)) {
	            click(next);
	            System.out.println("Clicked Next");
	            System.out.println("Current Month: " + monthName.getAttribute("text"));
	        }
	    }

	    WebElement currentMonthDate = driver.findElement(By.xpath(dynamicLocator1));

	    if (monthName.getAttribute("text").equals(monthYear)) {
	        System.out.println("Current Month: " + monthName.getAttribute("text"));
	        // Convert date string to integer
	        int dayOfMonth = Integer.parseInt(date);
	        boolean isNextMonthDatePresent = false;
	        WebElement nextMonthDate = null;

	        try {
	            nextMonthDate = driver.findElement(By.xpath(dynamicLocator2));
	            isNextMonthDatePresent = true;
	        } catch (NoSuchElementException e) {
	            // Handle the case where the next month date element is not found
	            System.out.println("Next month date element not found: " + e.getMessage());
	        }

	        if (currentMonthDate.isDisplayed() && (isNextMonthDatePresent || nextMonthDate.isDisplayed())) {
	            if (dayOfMonth >= 15) {
	                click(nextMonthDate);
	                System.out.println("Clicked Next Month Date");
	            } else {
	                click(currentMonthDate);
	                System.out.println("Clicked Current Month Date");
	            }
	        } else {
	            if (currentMonthDate.isDisplayed() && nextMonthDate.isDisplayed()) {
	                click(currentMonthDate);
	                System.out.println("Clicked Current Month Date");
	            } else {
	                click(nextMonthDate);
	                System.out.println("Clicked Next Month Date");
	            }
	        }
	    }
	}


	public void selectMode(String modeName) {

		// Use the 'modeName' parameter to create a dynamic XPath
		String modeXpath = String.format("//android.widget.TextView[@text='%s']", modeName);

		// Scroll to the element with the specified text and click
		driver.findElement(By.xpath(modeXpath)).click();
		Assert.assertTrue(checkView.isDisplayed(), "Mode is not selected");

	}
}

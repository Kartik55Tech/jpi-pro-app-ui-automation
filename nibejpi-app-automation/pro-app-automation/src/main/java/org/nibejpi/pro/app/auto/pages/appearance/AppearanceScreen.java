package org.nibejpi.pro.app.auto.pages.appearance;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppearanceScreen extends ScreenActions implements Appearance {

	public AppearanceScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = TOP_NAV_TITLE)
	WebElement top_Nav_Title;

	@AndroidFindBy(xpath = LANG_TITILE)
	WebElement lang_Title;

	@AndroidFindBy(xpath = LANG_SELECTION)
	WebElement lang_selection;

	@AndroidFindBy(xpath = LANG_SELECTED)
	WebElement lang_Selected;

	@AndroidFindBy(xpath = BACK_NAV_BTN)
	WebElement nav_Back_Btn;

	public void navigateBack() {
		waitForElementToBeClickable(nav_Back_Btn);
		nav_Back_Btn.click();
	}

	public void clickOnLanguageDrpDwn() {
		waitForElementToBeClickable(lang_Title);
		lang_Title.click();
	}

	public void selectLanguage(String LanguageName) throws InterruptedException {

		String Lang = LanguageName;
		String SelectLanguage = String.format(LANG_SELECTION, Lang);

		do {
			try {
				// Locate the element within the loop to prevent staleness
				WebElement LI = driver.findElement(By.xpath(SelectLanguage));
				// Wait for the element to be clickable
				waitForElementToBeClickable(LI);
				// Perform the click
				LI.click();
				waitForElementToBeClickable(lang_Title);
				// Click on language dropdown
				lang_Title.click();
				// Wait for language selection element
				waitForElementToBeClickable(lang_Selected);
				// Click on the selected language
				lang_Selected.click();
			} catch (StaleElementReferenceException e) {
				// Handle staleness
				e.printStackTrace();
			}
		} while (!lang_Selected.getAttribute("text").equals(LanguageName));
	}

}

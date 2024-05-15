package org.nibejpi.pro.app.auto.pages.about;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AboutScreenBeforeLogin extends ScreenActions implements About

{
	public AboutScreenBeforeLogin(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements for About Screen before login.
	@AndroidFindBy(id = LANGUAGE_DRPDWN)
	WebElement languageDrpDwn;

	@AndroidFindBy(xpath = TOS_ACCOUNT)
	WebElement tos_Account;

	@AndroidFindBy(xpath = PRIVACYPLCY_ACCNT)
	WebElement prvcyPlcy_Account;

	@AndroidFindBy(xpath = TOS_ORG)
	WebElement tos_Org;

	@AndroidFindBy(xpath = OPNSRCLICENSES)
	WebElement opnsrclicenses;

	@AndroidFindBy(xpath = VERSION_NUMBER)
	WebElement ver_number;

	@AndroidFindBy(xpath = NAV_BACKBTN)
	WebElement back_NavBtn;

	@AndroidFindBy(xpath = LANG_SELECTION)
	WebElement lang_selection;

	@AndroidFindBy(id = LANG_PICKER)
	WebElement langPicker;

	@AndroidFindBy(id = LANG_SELECTED)
	WebElement langSelected;

	@AndroidFindBy(xpath = TOP_NAV_TITLE)
	WebElement topNavTitle;

	@AndroidFindBy(id = REFRESH_BTN)
	WebElement refresh_Btn;

	@AndroidFindBy(xpath = TOS_ACCNT_CONTENT)
	WebElement tos_Acnt_Content;

	@AndroidFindBy(xpath = PP_ACCNT_CONTENT)
	WebElement pp_Acnt_Content;

	@AndroidFindBy(xpath = TOS_ORG_CONTENT)
	WebElement tos_Org_Content;

	@AndroidFindBy(xpath = OPN_SRC_CONTENT)
	WebElement opn_Src_Content;

	// Click on the language drop-down
	public void clickOnLanguageDrpDown() {
		waitForElementToBeClickable(languageDrpDwn);
		click(languageDrpDwn);
	}

	// Select a language from the drop-down
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

				waitForElementToBeClickable(languageDrpDwn);

				// Click on language drop-down
				languageDrpDwn.click();

				// Wait for language selection element
				waitForElementToBeClickable(langSelected);

				// Click on the selected language
				langSelected.click();
			} catch (StaleElementReferenceException e) {
				// Handle staleness
				e.printStackTrace();
			}

		} while (!langSelected.getAttribute("text").equals(LanguageName));
	}

	// Click on the Terms of Service (Account)
	public void clickOnTOSAccount() {
		click(tos_Account);
	}

	// Click on the Privacy Policy (Account)
	public void clickOnPrivacyPolicy_Account() {
		click(prvcyPlcy_Account);
	}

	// Click on the Terms of Service (Organization)
	public void clickOnTermsOfServiceOrg() {
		click(tos_Org);
	}

	// Click on the Open Source Licenses
	public void clickOnOpenSourceLicenses() {
		click(opnsrclicenses);
	}

	// Click on the Back Button
	public void navigateBack() {
		click(back_NavBtn);
	}

	// Verify the UI elements on the About screen before login
	public void verifyUIofAboutSectionAtLoginScreen() {

		Assert.assertTrue(languageDrpDwn.isDisplayed(), "Language dropdown is not displayed. ");
		Assert.assertTrue(languageDrpDwn.isEnabled(), "Language dropdown is not clickable. ");

		Assert.assertTrue(tos_Account.isDisplayed(), "Terms of Service(Account) is not displayed");
		Assert.assertTrue(tos_Account.isEnabled(), "Terms of Service(Account) is not clickable. ");

		Assert.assertTrue(prvcyPlcy_Account.isDisplayed(), "Privacy policy(Account) is not displayed");
		Assert.assertTrue(prvcyPlcy_Account.isEnabled(), "Privacy policy(Account) is not clickable. ");

		Assert.assertTrue(tos_Org.isDisplayed(), "Terms of Service(Organisation) is not displayed");
		Assert.assertTrue(tos_Org.isEnabled(), "Terms of Service(Organisation) is not clickable. ");

		Assert.assertTrue(opnsrclicenses.isDisplayed(), "Open Source Licenses is not displayed");
		Assert.assertTrue(opnsrclicenses.isEnabled(), "Open Source Licenses is not clickable. ");

		Assert.assertTrue(ver_number.isDisplayed(), "Version Number is not displayed");

		Assert.assertTrue(back_NavBtn.isDisplayed(), "Back Button is not displayed");
		Assert.assertTrue(back_NavBtn.isEnabled(), "Back Button is not clickable. ");

	}

	// Verify UI changes after language selection
	public void verifyAboutScreenUIChangesAfterLanguageSelection() throws InterruptedException {
		List<String> languages = Arrays.asList("Norsk", "Svenska", "Danish", "Deutsch", "Français");

		for (String language : languages) {
			selectLanguage(language);
			Thread.sleep(3000); // Sleep for 3 seconds (consider using explicit waits).

			// Define expected UI elements for the current language.
			Map<String, String> expectedUIElements = new HashMap<>();

			switch (language) {
			case "Norsk":
				expectedUIElements.put("languageDrpDwn", "Språk");
				expectedUIElements.put("tos_Account", "Vilkår for tjenesten (konto)");
				expectedUIElements.put("prvcyPlcy_Account", "Personvernerklæring (konto)");
				expectedUIElements.put("tos_Org", "Vilkår for tjenesten (bedrift)");
				expectedUIElements.put("opnsrclicenses", "Åpne kildelisenser");
				expectedUIElements.put("ver_number", "Versjonnummer:");
				break;

			case "Svenska":
				expectedUIElements.put("languageDrpDwn", "Språk");
				expectedUIElements.put("tos_Account", "Användarvillkor (Konto)");
				expectedUIElements.put("prvcyPlcy_Account", "Integritetspolicy (Konto)");
				expectedUIElements.put("tos_Org", "Användarvillkor (Företag)");
				expectedUIElements.put("opnsrclicenses", "Öppen källkodslicenser");
				expectedUIElements.put("ver_number", "Versionsnummer:");
				break;

			case "Danish":
				expectedUIElements.put("languageDrpDwn", "Sprog");
				expectedUIElements.put("tos_Account", "Brugervilkår (Konto)");
				expectedUIElements.put("prvcyPlcy_Account", "Fortrolighedspolitik (Konto)");
				expectedUIElements.put("tos_Org", "Brugervilkår (Organisation)");
				expectedUIElements.put("opnsrclicenses", "Open Source-licenser");
				expectedUIElements.put("ver_number", "Versionsnummer");
				break;

			case "Deutsch":
				expectedUIElements.put("languageDrpDwn", "Sprache");
				expectedUIElements.put("tos_Account", "AGB (Konto)");
				expectedUIElements.put("prvcyPlcy_Account", "Datenschutzerklärung (Konto)");
				expectedUIElements.put("tos_Org", "AGB (Unternehmen)");
				expectedUIElements.put("opnsrclicenses", "Open Source Lizenz");
				expectedUIElements.put("ver_number", "Versionsnummer:");
				break;

			case "Français":
				expectedUIElements.put("languageDrpDwn", "Langue");
				expectedUIElements.put("tos_Account", "Conditions d'utilisation (Compte)");
				expectedUIElements.put("prvcyPlcy_Account", "Politique de confidentialité (Compte)");
				expectedUIElements.put("tos_Org", "Conditions d'utilisation (Organisation)");
				expectedUIElements.put("opnsrclicenses", "Licences Open Source");
				expectedUIElements.put("ver_number", "Numéro de version :");
				break;
			}

			// Perform assertions based on the expected UI elements.
			Assert.assertEquals(languageDrpDwn.getAttribute("text"), expectedUIElements.get("languageDrpDwn"));
			Assert.assertEquals(tos_Account.getAttribute("text"), expectedUIElements.get("tos_Account"));
			Assert.assertEquals(prvcyPlcy_Account.getAttribute("text"), expectedUIElements.get("prvcyPlcy_Account"));
			Assert.assertEquals(tos_Org.getAttribute("text"), expectedUIElements.get("tos_Org"));
			Assert.assertEquals(opnsrclicenses.getAttribute("text"), expectedUIElements.get("opnsrclicenses"));
			Assert.assertTrue(ver_number.getAttribute("text").contains(expectedUIElements.get("ver_number")));

			// Print statement to indicate language validation
			System.out.println("Validated UI for language: " + language);
		}
	}

	public void verifyNavigationAndContentOfTOSAccount() {
		try {
			verify(() -> {
				waitForElementToBeVisible(topNavTitle);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verifyTextEquals(topNavTitle, "Terms of Service", "Top Nav Title");
				waitForElementToBeVisible(tos_Acnt_Content);
				verifyTextContains(tos_Acnt_Content, "The company myUpTech AB, reg. no. 556633-8140", "Content");
				scrollToText("http://ec.europa.eu/consumers/odr/");
				navigateBack();

				verifyTextEquals(topNavTitle, "About", "Top Nav Title after navigating back");
			});
		} catch (Exception e) {
			// Throw an exception if the verification fails
			throw new AssertionError("Verification of TOS Account navigation and content failed.", e);
		}
	}

	public void verifyNavigationAndContentOfPPAccount() {
		verify(() -> {
			waitForElementToBeVisible(topNavTitle);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verifyTextEquals(topNavTitle, "Privacy Policy", "Top Nav Title");
			waitForElementToBeVisible(pp_Acnt_Content);
			verifyTextContains(pp_Acnt_Content, "What do we do with your personal data?", "Content");
			scrollToText("Your rights");
			navigateBack();
			waitForElementToBeVisible(topNavTitle);
			verifyTextEquals(topNavTitle, "About", "Top Nav Title after navigating back");
		});
	}

	public void verifyNavigationAndContentOfTOSOrg() {
	    try {
	        verify(() -> {
	            try {
	                Thread.sleep(5000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            scrollToText("Term and termination");
	            navigateBack();
	            verifyTextEquals(topNavTitle, "About", "Top Nav Title after navigating back");
	        });
	    } catch (Exception e) {
	        // Throw an exception if the verification fails
	        throw new AssertionError("Verification of TOS Organization navigation and content failed.", e);
	    }
	}

	public void verifyNavigationAndContentOfOpenSrcLicenses() {
	    try {
	        verify(() -> {
	            try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            verifyTextEquals(topNavTitle, "Open Source Licenses", "Top Nav Title");
	            waitForElementToBeVisible(opn_Src_Content);
	            verifyTextContains(opn_Src_Content, "Detekt", "Content");
	            scrollToText("Material Range Bar");
	            navigateBack();
	            verifyTextEquals(topNavTitle, "About", "Top Nav Title after navigating back");
	        });
	    } catch (Exception e) {
	        // Throw an exception if the verification fails
	        throw new AssertionError("Verification of Open Source Licenses navigation and content failed.", e);
	    }
	}

	public void verifyLocalizationSupportForTOS_PP_OpenSrcLicenses() throws InterruptedException {
		Thread.sleep(3000);
		String[] languages = { "Français", "Norsk", "Svenska", "Deutsch", "Danish" };
		String[] sections = { "Terms of Service (Account)", "Privacy Policy (Account)",
				"Terms of Service (Organization)", "Open Source Licenses" };
		clickOnLanguageDrpDown();
		for (String language : languages) {
			verify(() -> {
				clickOnLanguageDrpDown();
				try {
					selectLanguage(language);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000); // Adding a delay to ensure language selection takes effect

					for (String section : sections) {
						selectSectionAndVerify(section, language);
						navigateBack();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clickOnLanguageDrpDown(); // Reset language selection for the next iteration
			});
		}

		// Select English as the final language
		clickOnLanguageDrpDown();
		selectLanguage("English");
	}

	public void selectSectionAndVerify(String section, String language) {
		switch (section) {
		case "Terms of Service (Account)":
			waitForElementToBeClickable(tos_Account).click();
			verifyTopNavBarTitle(getExpectedTitleForSection(section, language), section);
			break;
		case "Privacy Policy (Account)":
			waitForElementToBeClickable(prvcyPlcy_Account).click();
			verifyTopNavBarTitle(getExpectedTitleForSection(section, language), section);
			break;
		case "Terms of Service (Organization)":
			waitForElementToBeClickable(tos_Org).click();
			verifyTopNavBarTitle(getExpectedTitleForSection(section, language), section);
			break;
		case "Open Source Licenses":
			waitForElementToBeClickable(opnsrclicenses).click();
			verifyTopNavBarTitle(getExpectedTitleForSection(section, language), section);
			break;
		default:
			break;
		}
	}

	public String getExpectedTitleForSection(String section, String language) {
		switch (language) {
		case "Français":
			switch (section) {
			case "Terms of Service (Account)":
				return "Conditions d'utilisation";
			case "Privacy Policy (Account)":
				return "Politique de confidentialité";
			case "Terms of Service (Organization)":
				return "Conditions d'utilisation (Organisation)";
			case "Open Source Licenses":
				return "Licences Open Source";
			default:
				return "";
			}
		case "Norsk":
			switch (section) {
			case "Terms of Service (Account)":
				return "Vilkår for tjenesten";
			case "Privacy Policy (Account)":
				return "Personvernerklæring";
			case "Terms of Service (Organization)":
				return "Vilkår for tjenesten (bedrift)";
			case "Open Source Licenses":
				return "Åpne kildelisenser";
			default:
				return "";
			}
		case "Svenska":
			switch (section) {
			case "Terms of Service (Account)":
				return "Användarvillkor";
			case "Privacy Policy (Account)":
				return "Integritetspolicy";
			case "Terms of Service (Organization)":
				return "Användarvillkor (Företag)";
			case "Open Source Licenses":
				return "Öppen källkodslicenser";
			default:
				return "";
			}
		case "Deutsch":
			switch (section) {
			case "Terms of Service (Account)":
				return "AGB";
			case "Privacy Policy (Account)":
				return "Datenschutzerklärung";
			case "Terms of Service (Organization)":
				return "AGB (Unternehmen)";
			case "Open Source Licenses":
				return "Open Source Lizenz";
			default:
				return "";
			}
		case "Danish":
			switch (section) {
			case "Terms of Service (Account)":
				return "Brugervilkår";
			case "Privacy Policy (Account)":
				return "Fortrolighedspolitik";
			case "Terms of Service (Organization)":
				return "Brugervilkår (Organisation)";
			case "Open Source Licenses":
				return "Open Source-licenser";
			default:
				return "";
			}
		default:
			return "";
		}
	}

	public void verifyTopNavBarTitle(String expectedTitle, String section) {
		SoftAssert softAssert = new SoftAssert();
		waitForElementToBeVisible(topNavTitle);
		softAssert.assertEquals(topNavTitle.getAttribute("text"), expectedTitle,
				section + " top Nav Bar is not updating in " + expectedTitle + " language.");
		softAssert.assertAll();
	}


}

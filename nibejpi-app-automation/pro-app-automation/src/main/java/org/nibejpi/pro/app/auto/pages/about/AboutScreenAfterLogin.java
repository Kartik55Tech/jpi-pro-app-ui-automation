package org.nibejpi.pro.app.auto.pages.about;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AboutScreenAfterLogin extends ScreenActions implements About {
	// Constructor for AboutScreenAfterLogin class
	public AboutScreenAfterLogin(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements on the About Screen
	@AndroidFindBy(xpath = TOS_ACCOUNT_LGN)
	WebElement tos_Account_Lgn;

	@AndroidFindBy(xpath = PP_ACCOUNT_LGN)
	WebElement pp_Acnt_Lgn;

	@AndroidFindBy(xpath = TOS_ORG_LGN)
	WebElement tos_Org_Lgn;

	@AndroidFindBy(xpath = OPEN_SRC_LCNSE_LGN)
	WebElement opnsrclicenses_Lgn;

	@AndroidFindBy(xpath = VERSION_NUMBER_LGN)
	WebElement ver_Number_Lgn;

	@AndroidFindBy(xpath = NAV_BACKBTN)
	WebElement back_NavBtn;

	@AndroidFindBy(xpath = TOP_NAV_TITLE_LGN)
	WebElement topNavTitle_Lgn;

	@AndroidFindBy(xpath = TOS_ACCNT_CONTENT)
	WebElement tos_Acnt_Content;

	@AndroidFindBy(xpath = PP_ACCNT_CONTENT)
	WebElement pp_Acnt_Content;

	@AndroidFindBy(xpath = TOS_ORG_CONTENT)
	WebElement tos_Org_Content;

	@AndroidFindBy(xpath = OPN_SRC_CONTENT)
	WebElement opn_Src_Content;

	// Methods to click on various elements
	public void clickOnTOSAccount() {
		waitForElementToBeClickable(tos_Account_Lgn);
		tos_Account_Lgn.click();
	}

	public void clickOnPrivacyPolicy_Account() {
		waitForElementToBeClickable(pp_Acnt_Lgn);
		pp_Acnt_Lgn.click();
	}

	public void clickOnTermsOfServiceOrg() {
		waitForElementToBeClickable(tos_Org_Lgn);
		tos_Org_Lgn.click();
	}

	public void clickOnOpenSourceLicenses() {
		waitForElementToBeClickable(opnsrclicenses_Lgn);
		opnsrclicenses_Lgn.click();
	}

	public void navigateBack() {
		waitForElementToBeVisible(back_NavBtn);
		waitForElementToBeClickable(back_NavBtn);
		back_NavBtn.click();
	}
	
	public void verifyTOSAccountScreenContentAndNavigation() {
	    verify(() -> {
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        verifyTextEquals(topNavTitle_Lgn, "Terms of Service", "Top Nav Title");
	        waitForElementToBeVisible(tos_Acnt_Content);
	        verifyTextContains(tos_Acnt_Content, "The company myUpTech AB, reg. no. 556633-8140", "Content");
	        scrollToText("http://ec.europa.eu/consumers/odr/");
	        navigateBack();
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        verifyTextEquals(topNavTitle_Lgn, "About myUplink PRO", "Top Nav Title after navigating back");
	    });
	}

	public void verifyNavigationAndContentOfPPAccount() {
	    verify(() -> {
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        verifyTextEquals(topNavTitle_Lgn, "Privacy Policy", "Top Nav Title");
	        waitForElementToBeVisible(pp_Acnt_Content);
	        verifyTextContains(pp_Acnt_Content, "What do we do with your personal data?", "Content");
	        scrollToText("Your rights");
	        navigateBack();
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        verifyTextEquals(topNavTitle_Lgn, "About myUplink PRO", "Top Nav Title after navigating back");
	    });
	}

	public void verifyNavigationAndContentOfTOSOrg() {
	    verify(() -> {
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        verifyTextEquals(topNavTitle_Lgn, "Terms of Service (Organization)", "Top Nav Title");
	        waitForElementToBeVisible(tos_Org_Content);
	        verifyTextContains(tos_Org_Content, "User Instructions – instructions", "Content");
	        scrollToText("Term and termination");
	        navigateBack();
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        verifyTextEquals(topNavTitle_Lgn, "About myUplink PRO", "Top Nav Title after navigating back");
	    });
	}

	public void verifyNavigationAndContentOfOpenSrcLicenses() {
	    verify(() -> {
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        verifyTextEquals(topNavTitle_Lgn, "Open Source Licenses", "Top Nav Title");
	        waitForElementToBeVisible(opn_Src_Content);
	        verifyTextContains(opn_Src_Content, "Detekt", "Content");
	        scrollToText("Material Range Bar");
	        navigateBack();
	        waitForElementToBeVisible(topNavTitle_Lgn);
	        verifyTextEquals(topNavTitle_Lgn, "About myUplink PRO", "Top Nav Title after navigating back");
	    });
	}

	// Method to verify the UI of the About Screen
	public void verifyUIofAboutScreenAfterLogin() {

		waitForElementToBeVisible(tos_Account_Lgn);
		Assert.assertTrue(tos_Account_Lgn.isDisplayed(), "Terms of Service(Account) is not displayed");
		Assert.assertTrue(tos_Account_Lgn.isEnabled(), "Terms of Service(Account) is not clickable. ");

		Assert.assertTrue(pp_Acnt_Lgn.isDisplayed(), "Privacy policy(Account) is not displayed");
		Assert.assertTrue(pp_Acnt_Lgn.isEnabled(), "Privacy policy(Account) is not clickable. ");

		Assert.assertTrue(tos_Org_Lgn.isDisplayed(), "Terms of Service(Organisation) is not displayed");
		Assert.assertTrue(tos_Org_Lgn.isEnabled(), "Terms of Service(Organisation) is not clickable. ");

		Assert.assertTrue(opnsrclicenses_Lgn.isDisplayed(), "Open Source Licenses is not displayed");
		Assert.assertTrue(opnsrclicenses_Lgn.isEnabled(), "Open Source Licenses is not clickable. ");

		Assert.assertTrue(ver_Number_Lgn.isDisplayed(), "Version Number is not displayed");

		Assert.assertTrue(back_NavBtn.isDisplayed(), "Back Button is not displayed");
		Assert.assertTrue(back_NavBtn.isEnabled(), "Back Button is not clickable. ");
	}

	// Methods to verify text in different languages
	
	/**
	 * Verify the About section for the given language.
	 *
	 * @param language The language for which the About section is being verified.
	 */
	public void verifyAboutSectionForLanguage(String language) {
	    switch (language) {
	        case "Français":
	            verifyAboutSection("À propos de myUplink PRO", "Conditions d'utilisation (Compte)", "Politique de confidentialité (Compte)",
	                "Conditions d'utilisation (Organisation)", "Licences Open Source", "Numéro de version :");
	            break;
	        case "Norsk":
	            verifyAboutSection("Om myUplink PRO", "Vilkår for tjenesten (konto)", "Personvernerklæring (konto)",
	                "Vilkår for tjenesten (bedrift)", "Åpne kildelisenser", "Versjonnummer:");
	            break;
	        case "Svenska":
	            verifyAboutSection("Om myUplink PRO", "Användarvillkor (Konto)", "Integritetspolicy (Konto)",
	                "Användarvillkor (Företag)", "Öppen källkodslicenser", "Versionsnummer:");
	            break;
	        case "Deutsch":
	            verifyAboutSection("Über myUplink PRO", "AGB (Konto)", "Datenschutzerklärung (Konto)",
	                "AGB (Unternehmen)", "Open Source Lizenz", "Versionsnummer:");
	            break;
	        case "Danish":
	            verifyAboutSection("Om myUplink PRO", "Brugervilkår (Konto)", "Fortrolighedspolitik (Konto)",
	                "Brugervilkår (Organisation)", "Open Source-licenser", "Versionsnummer");
	            break;
	        default:
	            System.out.println("Unsupported language: " + language);
	    }
	}

	private void verifyAboutSection(String topNavTitleText, String tosAccountText, String ppAccountText, 
	        String tosOrgText, String opnsrclicensesText, String versionText) {
	    waitForElementToBeVisible(topNavTitle_Lgn);
	    Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), topNavTitleText);
	    Assert.assertEquals(tos_Account_Lgn.getAttribute("text"), tosAccountText);
	    Assert.assertEquals(pp_Acnt_Lgn.getAttribute("text"), ppAccountText);
	    Assert.assertEquals(tos_Org_Lgn.getAttribute("text"), tosOrgText);
	    Assert.assertEquals(opnsrclicenses_Lgn.getAttribute("text"), opnsrclicensesText);
	    Assert.assertTrue(ver_Number_Lgn.getAttribute("text").contains(versionText));
	}

	public void verifyLocalizationSupportForNorskLanguage() {
		clickOnTOSAccount();
		waitForElementToBeVisible(topNavTitle_Lgn);
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Vilkår for tjenesten",
				"TOS (Account) top Nav Bar is not updating in Norsk language.");
		navigateBack();
		clickOnPrivacyPolicy_Account();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Personvernerklæring",
				"Privacy Policy(Account) top Nav Bar is not updating in Norsk language.");
		navigateBack();
		clickOnTermsOfServiceOrg();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Vilkår for tjenesten (bedrift)",
				"TOS(Organisation) top Nav Bar is not updating in Norsk language.");
		navigateBack();
		clickOnOpenSourceLicenses();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Åpne kildelisenser",
				"TOS(Organisation) top Nav Bar is not updating in Norsk language.");
		navigateBack();
	}

	public void verifyLocalizationSupportForSvenskaLanguage() {
		clickOnTOSAccount();
		waitForElementToBeVisible(topNavTitle_Lgn);
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Användarvillkor",
				"TOS (Account) top Nav Bar is not updating in Svenska language.");
		navigateBack();
		clickOnPrivacyPolicy_Account();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Integritetspolicy",
				"Privacy Policy(Account) top Nav Bar is not updating in Svenska language.");
		navigateBack();
		clickOnTermsOfServiceOrg();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Användarvillkor (Företag)",
				"TOS(Organisation) top Nav Bar is not updating in Svenska language.");
		navigateBack();
		clickOnOpenSourceLicenses();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Öppen källkodslicenser",
				"TOS(Organisation) top Nav Bar is not updating in Svenska language.");
		navigateBack();
	}

	public void verifyLocalizationSupportForDeutschLanguage() {
		clickOnTOSAccount();
		waitForElementToBeVisible(topNavTitle_Lgn);
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "AGB",
				"TOS (Account) top Nav Bar is not updating in Deutsch language.");
		navigateBack();
		clickOnPrivacyPolicy_Account();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Datenschutzerklärung",
				"Privacy Policy(Account) top Nav Bar is not updating in Deutsch language.");
		navigateBack();
		clickOnTermsOfServiceOrg();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "AGB (Unternehmen)",
				"TOS(Organisation) top Nav Bar is not updating in Deutsch language.");
		navigateBack();
		clickOnOpenSourceLicenses();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Open Source Lizenz",
				"TOS(Organisation) top Nav Bar is not updating in Deutsch language.");
		navigateBack();
	}

	public void verifyLocalizationSupportForDanishLanguage() {
		clickOnTOSAccount();
		waitForElementToBeVisible(topNavTitle_Lgn);
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Brugervilkår",
				"TOS (Account) top Nav Bar is not updating in Danish language.");
		navigateBack();
		clickOnPrivacyPolicy_Account();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Fortrolighedspolitik",
				"Privacy Policy(Account) top Nav Bar is not updating in Danish language.");
		navigateBack();
		clickOnTermsOfServiceOrg();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Brugervilkår (Organisation)",
				"TOS(Organisation) top Nav Bar is not updating in Danish language.");
		navigateBack();
		clickOnOpenSourceLicenses();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Open Source-licenser",
				"TOS(Organisation) top Nav Bar is not updating in Danish language.");
		navigateBack();
	}

	public void verifyLocalizationSupportForFrançaisLanguage() {
		clickOnTOSAccount();
		waitForElementToBeVisible(topNavTitle_Lgn);
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Conditions d'utilisation",
				"TOS (Account) top Nav Bar is not updating in Français language.");
		navigateBack();
		clickOnPrivacyPolicy_Account();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Politique de confidentialité",
				"Privacy Policy(Account) top Nav Bar is not updating in Français language.");
		navigateBack();
		clickOnTermsOfServiceOrg();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Conditions d\'utilisation (Organisation)",
				"TOS(Organisation) top Nav Bar is not updating in Français language.");
		navigateBack();
		clickOnOpenSourceLicenses();
		Assert.assertEquals(topNavTitle_Lgn.getAttribute("text"), "Licences Open Source",
				"TOS(Organisation) top Nav Bar is not updating in Français language.");
		navigateBack();
	}

}

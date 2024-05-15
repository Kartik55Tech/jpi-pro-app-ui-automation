package org.nibejpi.pro.app.auto.pages.help;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import model.FAQitem;

public class HelpBeforeLogin extends ScreenActions implements Help {

	public HelpBeforeLogin(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements of Help screen
	@AndroidFindBy(xpath = TOP_NAV_TITLE)
	WebElement top_Nav_Title;

	@AndroidFindBy(xpath = GET_STARTED_FAQ1)
	WebElement faq1;

	@AndroidFindBy(xpath = CON_STRING_FAQ2)
	WebElement faq2;

	@AndroidFindBy(xpath = INT_CON_FAQ3)
	WebElement faq3;

	@AndroidFindBy(xpath = SYS_SERVER_FAQ4)
	WebElement faq4;

	@AndroidFindBy(xpath = CON_BRKN_FAQ5)
	WebElement faq5;

	@AndroidFindBy(xpath = CANT_LGN_FAQ6)
	WebElement faq6;

	@AndroidFindBy(xpath = MYSYSTEM_FAQ7)
	WebElement faq7;

	@AndroidFindBy(xpath = EVNT_ALARM_FAQ8)
	WebElement faq8;

	@AndroidFindBy(xpath = OWNERS_FAQ9)
	WebElement faq9;

	@AndroidFindBy(xpath = HOW_MANY_SYSTEMS_FAQ10)
	WebElement faq10;

	@AndroidFindBy(xpath = MONITOR_SYS_FAQ11)
	WebElement faq11;

	@AndroidFindBy(xpath = DATA_BREACH_FAQ12)
	WebElement faq12;

	@AndroidFindBy(xpath = WEB_BROWSER_FAQ13)
	WebElement faq13;

	@AndroidFindBy(xpath = NAV_BACK_BTN)
	WebElement back_Btn;

	@AndroidFindBy(xpath = FAQ_QST_CONTENT)
	WebElement faq_ques_content;

	@AndroidFindBy(id = FAQ_ANSWER_TEXT)
	WebElement faq_Answer_Text;

	// Method to click on FAQ element
	public void clickFAQ(WebElement faqelement) {
		int maxAttempts = 3;
		int attempts = 0;

		try {
			while (attempts < maxAttempts) {
				try {
					waitForElementToBeVisible(faqelement);
					waitForElementToBeClickable(faqelement);
					faqelement.click();
					return; // Exit the method if successful
				} catch (StaleElementReferenceException e) {
					System.err.println("StaleElementReferenceException. Retrying...");
					attempts++;
				} catch (Exception e) {
					System.err.println("Failed to click FAQ element: " + e.getMessage());
					e.printStackTrace();
					throw new RuntimeException("Failed to click FAQ element: " + e.getMessage());
				}
			}
		} finally {
			if (attempts == maxAttempts) {
				System.err.println("Exceeded maximum attempts to click the FAQ element.");
				throw new RuntimeException("Exceeded maximum attempts to click the FAQ element.");
			}
		}
	}

	// Method to click on back button
	public void navigateBack() {
		click(back_Btn);
	}
	
	public void verifyUIOfHelpScreenForLanguage(String language,String expectedBrandName, String expectedNavigationTitle) {
	    // Generate access token
	    utils.AuthManager.generateAccessToken();

	    // Get FAQ items
	    FAQitem[] faqItems = services.FAQ.getFaq(language,expectedBrandName);

	    // Assert top navigation title
	    Assert.assertEquals(top_Nav_Title.getAttribute("text"), expectedNavigationTitle);

	    // Verify FAQ elements up to faq12
	    String[] faqLabels = {"faq1", "faq2", "faq3", "faq4", "faq5", "faq6",
	                          "faq7", "faq8", "faq9", "faq10", "faq11", "faq12"};
	    for (int i = 0; i < faqLabels.length && i < faqItems.length; i++) {
	        verifyUIofElement(getFaqElementByLabel(faqLabels[i]), faqItems[i].getText(), faqLabels[i]);
	        // Scroll after verifying faq12
	        if (i == 11 && faqItems.length > 12) {
	            scrollToText(faqItems[12].getText());
	            break; // Exit the loop after scrolling
	        }
	    }
	    // Verify faq13 if there are more FAQs
	    if (faqItems.length > 12) {
	        verifyUIofElement(getFaqElementByLabel("faq13"), faqItems[12].getText(), "faq13");
	    }
	    // Navigate back
	    navigateBack();
	}
	
	public void verifyUIOfHelpScreenForEnglish()
	{
		verifyUIOfHelpScreenForLanguage("en-US","Nibe","Help");
	}

	public void verifyUIOfHelpScreenForFrançais() {
	    verifyUIOfHelpScreenForLanguage("fr-FR","Nibe","Assistance");
	}

	public void verifyUIOfHelpScreenForNorsk() {
	    verifyUIOfHelpScreenForLanguage("nb-NO","Nibe", "Hjelp");
	}

	public void verifyUIOfHelpScreenForSvenska() {
	    verifyUIOfHelpScreenForLanguage("sv-SE","Nibe", "Hjälp");
	}

	public void verifyUIOfHelpScreenForDanish() {
	    verifyUIOfHelpScreenForLanguage("da-DK","Nibe","Hjælp");
	}

	public void verifyUIOfHelpScreenForDeutsch() {
	    verifyUIOfHelpScreenForLanguage("de-DE","Nibe","Hilfe");
	}

	private WebElement getFaqElementByLabel(String label) {
	    switch (label) {
	        case "faq1":
	            return faq1;
	        case "faq2":
	            return faq2;
	        case "faq3":
	            return faq3;
	        case "faq4":
	            return faq4;
	        case "faq5":
	            return faq5;
	        case "faq6":
	            return faq6;
	        case "faq7":
	            return faq7;
	        case "faq8":
	            return faq8;
	        case "faq9":
	            return faq9;
	        case "faq10":
	            return faq10;
	        case "faq11":
	            return faq11;
	        case "faq12":
	            return faq12;
	        case "faq13":
	            return faq13;
	        default:
	            throw new IllegalArgumentException("Invalid FAQ label: " + label);
	    }
	}
	
	public void verifyTextofFAQs(WebElement faqElement, WebElement faqQuestion, String FaqQuestionText,
			String FaqQelementName, WebElement faqAnswer, String FaqAnswerTextPart, String FaqAelementName) {
		clickFAQ(faqElement);
		waitForElementToBeVisible(faqQuestion);
		Assert.assertTrue(faqQuestion.isDisplayed(), FaqQelementName + " is not displayed.");
		Assert.assertEquals(cleanText(faqQuestion.getAttribute("text")), cleanText(FaqQuestionText));
		waitForElementToBeVisible(faqAnswer);
		Assert.assertTrue(faqAnswer.isDisplayed(), FaqAelementName + " is not displayed.");
		String actualText = cleanText(faqAnswer.getAttribute("text"));
		String expectedTextPart = cleanText(FaqAnswerTextPart);
		Assert.assertTrue(actualText.contains(expectedTextPart),
				FaqAelementName + " does not contain the expected text: " + FaqAnswerTextPart);
		navigateBack();
	}

	private String cleanText(String text) {
		// Remove formatting, spacing, and HTML tags from the text
		return text.replaceAll("\\s+", "").replaceAll("<[^>]*>", "");
	}

	public void verifyContentOfFAQs(String languageCode, String brandName) {
	    // Generate access token
	    utils.AuthManager.generateAccessToken();

	    // Get FAQ items
	    FAQitem[] faqItems = services.FAQ.getFaq(languageCode, brandName);

	    // Iterate through each FAQ item and verify its content
	    for (int i = 0; i < faqItems.length; i++) {
	        FAQitem faqItem = faqItems[i];
	        WebElement faqElement = getFaqElementByLabel("faq" + (i + 1));
	        String faqQuestionText = faqItem.getText();
	        String faqAnswerTextPart = faqItem.getSections().get(0).getContent();
	        String faqAnswerText = faqQuestionText.contains("?") ? faqAnswerTextPart : faqQuestionText + " " + faqAnswerTextPart;
	        
	        verifyTextofFAQs(faqElement, faq_ques_content, faqQuestionText, faqQuestionText, 
	                         faq_Answer_Text, faqAnswerText, "FAQ" + (i + 1) + "answer");
	        if (i == 11 && faqItems.length > 12) {
	            scrollToText(faqItems[12].getText());
	            break; // Exit the loop after scrolling
	        }
	    }
	    // Verify faq13 if there are more FAQs
	    if (faqItems.length > 12) {
	    	verifyTextofFAQs(faq13, faq_ques_content, faqItems[12].getText(), faqItems[12].getText(), 
		    		faq_Answer_Text, faqItems[12].getSections().get(0).getContent(), "FAQ13answer");
	    }
	    System.out.println("Verification for Content of FAQs for " + languageCode + " language is successful.");
	}


	//Methods to verify content and navigation of FAQs
	public void verifyContentOfFAQsForEnglish() {
		verifyContentOfFAQs("en-US", "Nibe");
	}

	public void verifyContentOfFAQsForFrançais() {
		verifyContentOfFAQs("fr-FR", "Nibe");
	}

	public void verifyContentOfFAQsForNorsk() {
		verifyContentOfFAQs("nb-NO", "Nibe");
	}

	public void verifyContentOfFAQsForSvenska() {
		verifyContentOfFAQs("sv-SE", "Nibe");
	}

	public void verifyContentOfFAQsForDanish() {
		verifyContentOfFAQs("da-DK", "Nibe");
	}

	public void verifyContentOfFAQsForDeutsch() {
		verifyContentOfFAQs("de-DE", "Nibe");
		
	}

}

package org.nibejpi.pro.app.auto.pages.help;

import java.util.ArrayList;
import java.util.List;

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
import model.FAQitem;

public class HelpAfterLogin extends ScreenActions implements Help {

	public HelpAfterLogin(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements for Help screen after login
	@AndroidFindBy(xpath = TOP_NAV_TITLE)
	WebElement top_Nav_Title;

	@AndroidFindBy(xpath = FAQ1_LGN)
	WebElement faq1;

	@AndroidFindBy(xpath = FAQ2_LGN)
	WebElement faq2;

	@AndroidFindBy(xpath = FAQ3_LGN)
	WebElement faq3;

	@AndroidFindBy(xpath = FAQ4_LGN)
	WebElement faq4;

	@AndroidFindBy(xpath = FAQ5_LGN)
	WebElement faq5;

	@AndroidFindBy(xpath = FAQ6_LGN)
	WebElement faq6;

	@AndroidFindBy(xpath = FAQ7_LGN)
	WebElement faq7;

	@AndroidFindBy(xpath = FAQ8_LGN)
	WebElement faq8;

	@AndroidFindBy(xpath = FAQ9_LGN)
	WebElement faq9;

	@AndroidFindBy(xpath = FAQ10_LGN)
	WebElement faq10;

	@AndroidFindBy(xpath = FAQ11_LGN)
	WebElement faq11;

	@AndroidFindBy(xpath = FAQ12_LGN)
	WebElement faq12;

	@AndroidFindBy(xpath = FAQ13_LGN)
	WebElement faq13;

	@AndroidFindBy(xpath = CUSTOMER_SUPORRT)
	WebElement customerSupport;

	@AndroidFindBy(xpath = NAV_BACK_BTN)
	WebElement back_Btn;

	@AndroidFindBy(xpath = FAQ_QST_CONTENT_LGN)
	WebElement faq_ques_content;

	@AndroidFindBy(id = FAQ_ANSWER_TEXT)
	WebElement faq_Answer_Text;

	@AndroidFindBy(id = SELECT_TOPIC)
	WebElement select_topic;

	@AndroidFindBy(id = SELECT_SYSTEM)
	WebElement select_system;

	@AndroidFindBy(xpath = PICKER)
	WebElement pickerCS;

	@AndroidFindBy(xpath = PICKER_BUTTON)
	WebElement pickerBtn;

	@AndroidFindBy(id = CS_EMAIL)
	WebElement emailCS;

	@AndroidFindBy(id = CS_SUMMARY)
	WebElement summaryCS;

	@AndroidFindBy(id = CS_DESCRIPTION)
	WebElement descriptionCS;

	@AndroidFindBy(id = CS_SEND_BTN)
	WebElement sendBtn;

	@AndroidFindBy(xpath = FAQ1_CTRA_ANSWER)
	WebElement faq1Ans_Ctra;

	/**
	 * Click on the "Customer Support" element with the specified text.
	 *
	 * @param customerSupportText The text to locate and click the "Customer
	 *                            Support" element.
	 */
	public void clickOnCustomerSupport(String customerSupportText) {
		scrollToText(customerSupportText);
		click(customerSupport);
	}

	/**
	 * Select a system by name from the dropdown.
	 *
	 * @param SystemName The name of the system to select.
	 */
	public void selectTopic(String TopicName) {
		click(select_topic);

		String currentTopic = select_topic.getAttribute("text");
		if (currentTopic.equals(TopicName)) {
			return;
		} else {
			click(pickerCS);
			currentTopic = select_topic.getAttribute("text");
			if (currentTopic.equals(TopicName)) {
				return;
			}
		}

		do {
			click(pickerBtn);
			currentTopic = select_topic.getAttribute("text");
		} while (!currentTopic.equals(TopicName));
	}

	/**
	 * Enter an email address in the Customer Support form.
	 */
	public void selectSystem(String SystemName) {
		click(select_system);
		String currentSystem = select_system.getAttribute("text");
		if (currentSystem.equals(SystemName)) {
			return;
		} else {
			click(pickerCS);
			currentSystem = select_system.getAttribute("text");
			if (currentSystem.equals(SystemName)) {
				return;
			}
		}

		do {
			click(pickerBtn);
			currentSystem = select_system.getAttribute("text");
		} while (!currentSystem.equals(SystemName));

	}

	/**
	 * Enter a summary text in the Customer Support form.
	 *
	 * @param SummaryText The summary text to enter.
	 */
	public void enterEmailCS() {
		emailCS.sendKeys("RishiTest@gmail.com");
	}

	/**
	 * Enter a description in the Customer Support form.
	 *
	 * @param Description The description to enter.
	 */
	public void enterSummary(String SummaryText) {
		summaryCS.sendKeys(SummaryText);
	}

	/**
	 * Click on a Frequently Asked Question (FAQ) element with retry mechanism.
	 *
	 * @param faqelement The FAQ element to click.
	 */
	public void enterDescription(String Description) {
		descriptionCS.sendKeys(Description);
	}

	/**
	 * Navigate back in the application.
	 */
	public void clickFAQ(WebElement faqelement) {
		int maxAttempts = 3;
		int attempts = 0;

		try {
			while (attempts < maxAttempts) {
				try {
					waitForElementToBeVisible(faqelement);
					waitForElementToBeClickable(faqelement);
					faqelement.click();
					System.out.println("Clicked on the FAQ element.");
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

	/**
	 * Navigate back in the application.
	 */
	public void navigateBack() {
		click(back_Btn);
	}

	/**
	 * Click on the "Send" button in the Customer Support form.
	 */
	public void clickonSendButtonAtCustomerSupport() {
		waitForElementToBeClickable(sendBtn);
		scrollToText("Send");
		sendBtn.click();
	}

	public void verifyUIOfHelpScreenForLanguage(
			String language, 
			String expectedBrandName,
			boolean verifyFAQcontent) 
	{
		// Generate access token
		utils.AuthManager.generateAccessToken();

		// Get FAQ items
		List<FAQitem> faqItemList = new ArrayList<>(List.of(services.FAQ.getFaq(language, expectedBrandName)));
		FAQitem customerSupport = new FAQitem();
		customerSupport.setText("Customer support");
		faqItemList.add(customerSupport);


		for (FAQitem faqItem : faqItemList) {
			try {
				WebElement faq = driver.findElement(By.xpath(this.getFAQxpath(faqItem.getText())));
				verifyUIofElement(faq, faqItem.getText(), String.format("Element %s", faqItem.getText()));
				if(verifyFAQcontent)
				{
					verifyTextofFAQs(faq,faqItem );
				}
			
			} catch (Exception e) {
				scrollToText(faqItem.getText());
				WebElement faq = driver.findElement(By.xpath(this.getFaqQuestionXpath(faqItem.getText())));
				verifyUIofElement(faq, faqItem.getText(), String.format("Element %s", faqItem.getText()));
			}
		}
		// Navigate back
		navigateBack();
	}

	public void verifyUIOfHelpScreenForEnglish() {
		verifyUIOfHelpScreenForLanguage("en-US", "Nibe" ,false);
	}

	public void verifyContentOfHelpScreen(
			String language,
			String expectedBrandName,
			String expectedNavigationTitle
			)
	{
		// Generate access token
		utils.AuthManager.generateAccessToken();

		// Get FAQ items
		List<FAQitem> faqItemList = new ArrayList<>(List.of(services.FAQ.getFaq(language, expectedBrandName)));
		FAQitem customerSupport = new FAQitem();
		customerSupport.setText("Customer support");
		faqItemList.add(customerSupport);

		// Assert top navigation title
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), expectedNavigationTitle);

		for (FAQitem faqItem : faqItemList) {
			try {
				WebElement faq = driver.findElement(By.xpath(this.getFaqQuestionXpath(faqItem.getText())));
				verifyUIofElement(faq, faqItem.getText(), String.format("Element %s", faqItem.getText()));
			} catch (Exception e) {
				scrollToText(faqItem.getText());
				WebElement faq = driver.findElement(By.xpath(this.getFaqQuestionXpath(faqItem.getText())));
				verifyUIofElement(faq, faqItem.getText(), String.format("Element %s", faqItem.getText()));
			}
		}
		// Navigate back
		navigateBack();
	}

	
	private String cleanText(String text) {
		// Remove formatting, spacing, and HTML tags from the text
		return text.replaceAll("\\s+", "").replaceAll("<[^>]*>", "");
	}

	public void verifyTextofFAQs(WebElement faqElement, 
			 FAQitem faqItem) {
		clickFAQ(faqElement);
		WebElement faqQuestion = driver.findElement(By.xpath(this.getFaqQuestionXpath(faqItem.getText())));
		
		
		waitForElementToBeVisible(faqQuestion);

		SoftAssert softAssert = new SoftAssert();
		try {
			softAssert.assertTrue(faqQuestion.isDisplayed(), faqItem.getFaqId() + " is not displayed.");
			softAssert.assertEquals(cleanText(faqQuestion.getAttribute("text")), cleanText(faqItem.getText()));

			waitForElementToBeVisible(faq_Answer_Text);
			softAssert.assertTrue(faq_Answer_Text.isDisplayed(), faqItem.getFaqId() + " answer is not displayed.");

			String actualText = cleanText(faq_Answer_Text.getAttribute("text"));
			String expectedTextPart = cleanText(faqItem.getSections().get(0).getContent());
			softAssert.assertTrue(actualText.contains(expectedTextPart),
					faqItem.getFaqId() + " does not contain the expected text.");
		} catch (Throwable t) {
			// Log or handle the exception if needed
		} finally {
			softAssert.assertAll(); // This will assert all the soft asserts
			navigateBack();
		}
	}

	public void verifyUIofHelpScreenForEnglishAfterLogin() {
		verifyUIOfHelpScreenForLanguage("en-US", "Contura",false);
	}

	public void verifyNavigationAndContentOfFAQsAfterLogin(String languageCode , String brandName) throws InterruptedException {

		verifyUIOfHelpScreenForLanguage(languageCode, brandName ,true);

	}
	
	public void verifyNavigationAndContentOfFAQsForFrançaisAfterLogin() throws InterruptedException {
		verifyUIOfHelpScreenForLanguage("en-FR", "Nibe",false);
		/*
		verifyTextofFAQs(faq1, faq_ques_content, "Comment démarrer avec myUplink?", "FAQ1 question", faq_Answer_Text,
				"Pour connecter votre système,", "FAQ1 answer");
		verifyTextofFAQs(faq2, faq_ques_content, "Qu'est-ce qu'une chaîne de connexion ?", "FAQ2 question",
				faq_Answer_Text, "La chaîne de connexion est générée", "FAQ2 answer");
		verifyTextofFAQs(faq3, faq_ques_content, "Quelle est la vitesse de connexion requise ?", "FAQ3 question",
				faq_Answer_Text, "La vitesse de la connexion n'a pas d'impact", "FAQ3 answer");
		verifyTextofFAQs(faq5, faq_ques_content, "Que se passe-t-il si la connexion est interrompue ?", "FAQ4 question",
				faq_Answer_Text, "Si le modem est éteint ou si la connexion Internet", "FAQ4 answer");
		verifyTextofFAQs(faq6, faq_ques_content, "Je n'arrive pas à me connecter, que puis-je faire ?", "FAQ5 question",
				faq_Answer_Text, "Tout d'abord, assurez-vous que votre connexion", "FAQ5 answer");
		verifyTextofFAQs(faq7, faq_ques_content, "Mon système ne peut pas se connecter à Internet, que puis-je faire ?",
				"FAQ6 question", faq_Answer_Text, "le Wi-Fi du système est activé", "FAQ6 answer");
		verifyTextofFAQs(faq8, faq_ques_content, "Que se passe-t-il en cas d'alarme ?", "FAQ7 question",
				faq_Answer_Text, "une notification est envoyée par push", "FAQ7 answer");
		verifyTextofFAQs(faq9, faq_ques_content, "Que dois-je faire si le système change de propriétaire ?",
				"FAQ8 question", faq_Answer_Text, "Lorsqu'un système change de propriétaire,", "FAQ8 answer");
		verifyTextofFAQs(faq10, faq_ques_content, "À combien de systèmes un utilisateur peut-il se connecter ?",
				"FAQ9 question", faq_Answer_Text, "Il n'y a pas de plafond", "FAQ9 answer");
		scrollToText("Quels sont les navigateurs Web recommandés pour myUplink ?");
		verifyTextofFAQs(faq11, faq_ques_content, "Pouvez-vous surveiller et gérer mon système ?", "FAQ10 question",
				faq_Answer_Text, "Nous n'offrons actuellement pas", "FAQ10 answer");
		verifyTextofFAQs(faq12, faq_ques_content,
				"Que recommandez-vous pour empêcher le piratage de mon produit myUplink et de mon compte ?",
				"FAQ11 question", faq_Answer_Text, " Il est donc important que vous", "FAQ11 answer");
		verifyTextofFAQs(faq13, faq_ques_content, "Quels sont les navigateurs Web recommandés pour myUplink ?",
				"FAQ12 question", faq_Answer_Text, "Nous vous recommandons de toujours", "FAQ12 answer");
		verifyUIofElement(customerSupport, "Service client", "Customer support");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Veuillez sélectionner", "select topic dropdown");
		verifyUIofElement(select_system, "Veuillez sélectionner", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Résumé", "Placeholder Summary is not displayed.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "La description",
				"Placeholder Description is not displayed.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Service client", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "Envoyer", "Send Button in French");
		navigateBack();
		*/
	}

	public void verifyNavigationAndContentOfFAQsForNorskAfterLogin() throws InterruptedException {
		
		
		/*
		verifyTextofFAQs(faq1, faq_ques_content, "Hvordan kommer jeg i gang med myUplink?", "FAQ1 question",
				faq_Answer_Text, "For å koble til systemet ditt, er ", "FAQ1 answer");
		verifyTextofFAQs(faq2, faq_ques_content, "Hva er en forbindelsesstreng?", "FAQ2 question", faq_Answer_Text,
				"En forbindelsesstreng er en kode", "FAQ2 answer");
		verifyTextofFAQs(faq3, faq_ques_content, "Hvor rask forbindelse kreves?", "FAQ3 question", faq_Answer_Text,
				"Hastigheten på forbindelsen har", "FAQ3 answer");
		verifyTextofFAQs(faq5, faq_ques_content, "Hva skjer hvis forbindelsen brytes?", "FAQ4 question",
				faq_Answer_Text, "Hvis modemet slås av eller internettforbindelsen ", "FAQ4 answer");
		verifyTextofFAQs(faq6, faq_ques_content, "Jeg klarer ikke å logge inn, hva kan jeg gjøre?", "FAQ5 question",
				faq_Answer_Text, "forsikre deg om at internettforbindelsen", "FAQ5 answer");
		verifyTextofFAQs(faq7, faq_ques_content, "Systemet mitt kan ikke kobles til internett, hva kan jeg gjøre?",
				"FAQ6 question", faq_Answer_Text, "Sørg for at det trådløse ", "FAQ6 answer");
		verifyTextofFAQs(faq8, faq_ques_content, "Hva skjer i tilfelle alarmen går?", "FAQ7 question", faq_Answer_Text,
				"Varselet viser hva alarmen gjelder", "FAQ7 answer");
		verifyTextofFAQs(faq9, faq_ques_content, "Hva skal jeg gjøre hvis systemet skifter eiere?", "FAQ8 question",
				faq_Answer_Text, "system skifter eier", "FAQ8 answer");
		verifyTextofFAQs(faq10, faq_ques_content, "Hvor mange systemer kan en bruker koble seg til?", "FAQ9 question",
				faq_Answer_Text, "grense for hvor mange", "FAQ9 answer");
		scrollToText("Hvilken nettleser er anbefalt for myUplink?");
		verifyTextofFAQs(faq11, faq_ques_content, "Kan dere overvåke og administrere mitt system?", "FAQ10 question",
				faq_Answer_Text, "ikke denne tjenesten", "FAQ10 answer");
		verifyTextofFAQs(faq12, faq_ques_content,
				"Hva anbefaler du for å forhindre at myUplink-produktet mitt og kontoen min blir hacket?",
				"FAQ11 question", faq_Answer_Text, "i produktene som er koblet til", "FAQ11 answer");
		verifyTextofFAQs(faq13, faq_ques_content, "Hvilken nettleser er anbefalt for myUplink?", "FAQ12 question",
				faq_Answer_Text, "nyeste versjonen av en av de store", "FAQ12 answer");
		verifyUIofElement(customerSupport, "Kundeservice", "Customer support");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Vennligst velg", "select topic dropdown");
		verifyUIofElement(select_system, "Vennligst velg", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box Norsk is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Sammendrag",
				"Placeholder Summary is not displayed in Norsk.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "Beskrivelse",
				"Placeholder Description is not displayed.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Kundeservice", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "Send", "Send Button in Norsk");
		navigateBack();
		*/
	}

	public void verifyNavigationAndContentOfFAQsForSvenskaAfterLogin() throws InterruptedException {
		
		/*
		verifyTextofFAQs(faq1, faq_ques_content, "Hur kommer jag igång med myUplink?", "FAQ1 question", faq_Answer_Text,
				"För att ansluta din anläggning", "FAQ1 answer");
		verifyTextofFAQs(faq2, faq_ques_content, "Vad är en anslutningssträng?", "FAQ2 question", faq_Answer_Text,
				"En anslutningssträng är en", "FAQ2 answer");
		verifyTextofFAQs(faq3, faq_ques_content, "Vilka är kraven på min internetanslutning?", "FAQ3 question",
				faq_Answer_Text, "Anslutningens hastighet har", "FAQ3 answer");
		verifyTextofFAQs(faq5, faq_ques_content, "Vad händer om anslutningen bryts?", "FAQ4 question", faq_Answer_Text,
				"Om modemet stängs av eller", "FAQ4 answer");
		verifyTextofFAQs(faq6, faq_ques_content, "Vad ska jag göra om det inte går att logga in?", "FAQ5 question",
				faq_Answer_Text, "att din internetanslutning fungerar", "FAQ5 answer");
		verifyTextofFAQs(faq7, faq_ques_content, "Vad ska jag göra om min anläggning inte kan ansluta till internet?",
				"FAQ6 question", faq_Answer_Text, "wifi är aktiverat i anläggningen.", "FAQ6 answer");
		verifyTextofFAQs(faq8, faq_ques_content, "Vad händer vid larm?", "FAQ7 question", faq_Answer_Text,
				"såväl push-meddelande som", "FAQ7 answer");
		verifyTextofFAQs(faq9, faq_ques_content, "Vad ska jag göra om anläggningen byter ägare?", "FAQ8 question",
				faq_Answer_Text, "anläggning byter ägare", "FAQ8 answer");
		verifyTextofFAQs(faq10, faq_ques_content, "Hur många anläggningar kan en användare vara ansluten till?",
				"FAQ9 question", faq_Answer_Text, "ingen övre gräns för", "FAQ9 answer");
		scrollToText("Vilken webbläsare rekommenderas till myUplink?");
		verifyTextofFAQs(faq11, faq_ques_content, "Kan ni övervaka och administrera min anläggning?", "FAQ10 question",
				faq_Answer_Text, "erbjuder vi inte den", "FAQ10 answer");
		verifyTextofFAQs(faq12, faq_ques_content,
				"Vad rekommenderar ni för att skydda min myUplink-produkt och mitt konto från att bli hackad?",
				"FAQ11 question", faq_Answer_Text, "i dina produkter anslutna", "FAQ11 answer");
		verifyTextofFAQs(faq13, faq_ques_content, "Vilken webbläsare rekommenderas till myUplink?", "FAQ12 question",
				faq_Answer_Text, "i din webbläsare.", "FAQ12 answer");
		verifyUIofElement(customerSupport, "Kundsupport", "Customer support in Svenska");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Välj", "select topic dropdown");
		verifyUIofElement(select_system, "Välj", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box Svenska is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Sammanfattning",
				"Placeholder Summary is not displayed in Norsk.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "Beskrivning",
				"Placeholder Description is not displayed.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Kundsupport", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "Skicka", "Send Button in Svenska");
		navigateBack();
		*/

	}

	public void verifyNavigationAndContentOfFAQsForDanishAfterLogin() throws InterruptedException {
		
		
		/*
		verifyTextofFAQs(faq1, faq_ques_content, "Hvordan kommer jeg i gang med myUplink?", "FAQ1 question",
				faq_Answer_Text, "dit system skal", "FAQ1 answer");
		verifyTextofFAQs(faq2, faq_ques_content, "Hvad er en tilslutningsstreng?", "FAQ2 question", faq_Answer_Text,
				"som sikrer, at kun autoriserede", "FAQ2 answer");
		verifyTextofFAQs(faq3, faq_ques_content, "Hvor hurtig forbindelse kræves der?", "FAQ3 question",
				faq_Answer_Text, "ikke betydelig", "FAQ3 answer");
		verifyTextofFAQs(faq5, faq_ques_content, "Hvad sker der, hvis forbindelsen bliver afbrudt?", "FAQ4 question",
				faq_Answer_Text, "internetforbindelsen afbrydes, brydes", "FAQ4 answer");
		verifyTextofFAQs(faq6, faq_ques_content, "Jeg kan ikke logge ind, hvad kan jeg gøre?", "FAQ5 question",
				faq_Answer_Text, "at din internetforbindelse virker", "FAQ5 answer");
		verifyTextofFAQs(faq7, faq_ques_content, "Mit system kan ikke forbinde til internettet, hvad kan jeg gøre?",
				"FAQ6 question", faq_Answer_Text, "at dit kabel er forbundet", "FAQ6 answer");
		verifyTextofFAQs(faq8, faq_ques_content, "Hvad sker der i tilfælde af en alarm?", "FAQ7 question",
				faq_Answer_Text, "notifikation både via push og e-mail til", "FAQ7 answer");
		verifyTextofFAQs(faq9, faq_ques_content, "Hvad skal jeg gøre, hvis systemet skifter ejer?", "FAQ8 question",
				faq_Answer_Text, "Når et system skifter ejer", "FAQ8 answer");
		verifyTextofFAQs(faq10, faq_ques_content, "Hvor mange systemer kan en bruger være forbundet til?",
				"FAQ9 question", faq_Answer_Text, "hvor mange systemer en bruger", "FAQ9 answer");
		scrollToText("Hvilken webbrowser anbefales til myUplink?");
		verifyTextofFAQs(faq11, faq_ques_content, "Kan I overvåge og administrere mit system?", "FAQ10 question",
				faq_Answer_Text, "ikke denne service", "FAQ10 answer");
		verifyTextofFAQs(faq12, faq_ques_content,
				"Hvad anbefaler du for at forhindre, at mit myUplink-produkt og min konto bliver hacket?",
				"FAQ11 question", faq_Answer_Text, "de produkter og software", "FAQ11 answer");
		verifyTextofFAQs(faq13, faq_ques_content, "Hvilken webbrowser anbefales til myUplink?", "FAQ12 question",
				faq_Answer_Text, "version af en af de store", "FAQ12 answer");
		verifyUIofElement(customerSupport, "Kunde support", "Customer support in Danish");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Vælg venligst", "select topic dropdown");
		verifyUIofElement(select_system, "Vælg venligst", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box Danish is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Resumé",
				"Placeholder Summary is not displayed in Danish.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "Beskrivelse",
				"Placeholder Description is not displayed in Danish.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Kunde support", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "	 Send", "Send Button in Danish");
		navigateBack();
		*/
	}

	public void verifyNavigationAndContentOfFAQsForDeutschAfterLogin() throws InterruptedException {
		
		/*
		verifyTextofFAQs(faq1, faq_ques_content, "Wie kann ich myUplink verwenden?", "FAQ1 question", faq_Answer_Text,
				"ist eine Internetverbindung", "FAQ1 answer");
		verifyTextofFAQs(faq2, faq_ques_content, "Was ist eine Verbindungszeichenfolge?", "FAQ2 question",
				faq_Answer_Text, "der sicherstellt, dass nur", "FAQ2 answer");
		verifyTextofFAQs(faq3, faq_ques_content, "Wie schnell muss die Verbindung sein?", "FAQ3 question",
				faq_Answer_Text, "Geschwindigkeit der Verbindung", "FAQ3 answer");
		verifyTextofFAQs(faq5, faq_ques_content, "Was passiert bei einem Verbindungsausfall?", "FAQ4 question",
				faq_Answer_Text, "Internetverbindung gestört ist", "FAQ4 answer");
		verifyTextofFAQs(faq6, faq_ques_content, "Ich kann mich nicht anmelden. Was soll ich tun?", "FAQ5 question",
				faq_Answer_Text, "Internetverbindung funktioniert", "FAQ5 answer");
		verifyTextofFAQs(faq7, faq_ques_content,
				"Meine Anlage kann keine Verbindung zum Internet herstellen. Was soll ich tun?", "FAQ6 question",
				faq_Answer_Text, "der Anlage aktiviert ist", "FAQ6 answer");
		verifyTextofFAQs(faq8, faq_ques_content, "Was passiert bei einem Alarm?", "FAQ7 question", faq_Answer_Text,
				"Benachrichtigung via Push und Mail", "FAQ7 answer");
		verifyTextofFAQs(faq9, faq_ques_content, "Was soll ich tun, wenn die Anlage den Besitzer wechselt?",
				"FAQ8 question", faq_Answer_Text, "sollte der ehemalige Besitzer sein", "FAQ8 answer");
		verifyTextofFAQs(faq10, faq_ques_content, "Mit wie vielen Anlagen kann ein Benutzer verbunden sein?",
				"FAQ9 question", faq_Answer_Text, "für die Anzahl der Anlagen", "FAQ9 answer");
		scrollToText("Welcher Browser wird für myUplink empfohlen?");
		verifyTextofFAQs(faq11, faq_ques_content, "Können Sie meine Anlage überwachen und verwalten?", "FAQ10 question",
				faq_Answer_Text, "aber erkundigen Sie sich bitte", "FAQ10 answer");
		verifyTextofFAQs(faq12, faq_ques_content,
				"Was empfehlen Sie, um zu verhindern, dass mein myUplink-Produkt und mein Konto gehackt werden?",
				"FAQ11 question", faq_Answer_Text, "der von uns entwickelten Produkte", "FAQ11 answer");
		verifyTextofFAQs(faq13, faq_ques_content, "Welcher Browser wird für myUplink empfohlen?", "FAQ12 question",
				faq_Answer_Text, "Version eines der großen Browserhersteller", "FAQ12 answer");
		verifyUIofElement(customerSupport, "Kundendienst", "Customer support in Deutsch");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Bitte auswählen", "select topic dropdown");
		verifyUIofElement(select_system, "Bitte auswählen", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box Deutsch is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Zusammenfassung",
				"Placeholder Summary is not displayed in Deutsch.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "Beschreibung",
				"Placeholder Description is not displayed in Deutsch.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Kundendienst", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "Senden", "Send Button in Deutsch");
		navigateBack();
		*/
	}
	
	
	
	
	public void verifyCustomerSupportWithEmptyfields() throws InterruptedException {
		Thread.sleep(7000);
		verifyUIofDisabledElement(sendBtn, "Send", "Send Button with no data");
		selectTopic("Technical Support");
		scrollToText("Send");
		verifyUIofDisabledElement(sendBtn, "Send", "Send Button with selected topic");
		scrollToText("System");
		selectSystem("namrata-simulator");
		scrollToText("Send");
		verifyUIofDisabledElement(sendBtn, "Send", "Send Button with selected topic,system");
		enterSummary("This is Mobile Automation Test");
		scrollToText("Send");
		verifyUIofDisabledElement(sendBtn, "Send", "Send Button with selected topic,system,summary");
		enterDescription("This is Mobile Automation Test");
		scrollToText("Send");
		verifyUIofElement(sendBtn, "Send", "Send Button with selected topic,system,summary");

	}

	public void verifyLocalizationSupportForCustomerSupportinFrançais() throws InterruptedException {
		Thread.sleep(7000);
		verifyUIofDisabledElement(sendBtn, "Envoyer", "Send Button with no data");
		selectTopic("Retour d'information");
		scrollToText("Envoyer");
		verifyUIofDisabledElement(sendBtn, "Envoyer", "Send Button with selected topic");
		scrollToText("Envoyer");
		selectSystem("namrata-simulator");
		scrollToText("Envoyer");
		verifyUIofDisabledElement(sendBtn, "Envoyer", "Send Button with selected topic,system");
		enterSummary("This is Mobile Automation Test");
		scrollToText("Envoyer");
		verifyUIofDisabledElement(sendBtn, "Envoyer", "Send Button with selected topic,system,summary");
		enterDescription("This is Mobile Automation Test");
		scrollToText("Envoyer");
		verifyUIofElement(sendBtn, "Envoyer", "Send Button with selected topic,system,summary");
	}

	// Methods to click on FAQs
	public void clickFAQOtherBrands(String FAQQuestion) {
		int maxAttempts = 3; // Set the maximum number of retry attempts

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Scroll to the FAQ question
				scrollToText(FAQQuestion);

				// Construct the XPath using the complete text
				String xpath = "//android.widget.TextView[@text=\"" + FAQQuestion + "\"]";

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

	public void verifyTextofOtherBrandFAQs(WebElement faqQuestion, String FaqQuestionText, String FaqQelementName,
			WebElement faqAnswer, String FaqAnswerTextPart, String FaqAelementName) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		clickFAQOtherBrands(FaqQuestionText);
		waitForElementToBeVisible(faqQuestion);
		softAssert.assertTrue(faqQuestion.isDisplayed(), FaqQelementName + " is not displayed.");
		softAssert.assertEquals(faqQuestion.getAttribute("text"), FaqQuestionText);
		waitForElementToBeVisible(faqAnswer);
		softAssert.assertTrue(faqAnswer.isDisplayed(), FaqAelementName + " is not displayed.");
		softAssert.assertTrue(faqAnswer.getAttribute("text").contains(FaqAnswerTextPart));
		softAssert.assertAll();
		navigateBack();
		Thread.sleep(2000);
	}

	public void verifyNavigationAndContentForFAQsOfContura() throws InterruptedException {
		verifyTextofOtherBrandFAQs(faq_ques_content, "What should I consider before lighting for the first time?",
				"FAQ1 question", faq1Ans_Ctra, "Your Contura fireplace is ", "FAQ1 answer");
		// Bug fix : APPS-2539
//		verifyTextofOtherBrandFAQs(faq_ques_content, "How to set up the hatch in open position?", "FAQ2 question", 
//				faq_Answer_Text, "The door can be locked in the open", "FAQ2 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "How do I light the fire?", "FAQ3 question", faq_Answer_Text,
				"the correct operating temperature in the firebox", "FAQ3 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "How do I add logs to prevent indentation?", "FAQ4 question",
				faq_Answer_Text, "Open the door a few centimeters", "FAQ4 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What type of wood  should I use?", "FAQ5 question",
				faq_Answer_Text, "All types of wood, such as birch", "FAQ5 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How damp firewood can I use?", "FAQ6 question", faq_Answer_Text,
				"About 50% of the weight of", "FAQ6 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What am I not allowed to burn?", "FAQ7 question", faq_Answer_Text,
				"Under no circumstances may fossil fuels", "FAQ7 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How does power regulation work?", "FAQ8 question",
				faq_Answer_Text, "Keep in mind that the output power", "FAQ8 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How to clean the glass?", "FAQ9 question", faq_Answer_Text,
				"Soot may form on the glass", "FAQ9 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How do I empty the ash-pan?", "FAQ10 question", faq_Answer_Text,
				"When emptying the ash-pan", "FAQ10 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How do I get started with myUplink?", "FAQ11 question",
				faq_Answer_Text, "To connect your system, it i", "FAQ11 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How to clean the stove?", "FAQ12 question", faq_Answer_Text,
				"Use a wire brush to clean", "FAQ12 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Where do I turn if I need new parts/spare parts?",
				"FAQ13 question", faq_Answer_Text, "Parts located near the actual", "FAQ13 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content,
				"Why is difficult to light the fire and the fire dies after a short time?", "FAQ14 question",
				faq_Answer_Text, "Check that the wood is sufficiently dry", "FAQ14 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Why do I experience excessive amounts on the glass?",
				"FAQ15 question", faq_Answer_Text, "There is always some soot on the glass", "FAQ15 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Why do I get occasional smell of smoke around the fireplace?",
				"FAQ16 question", faq_Answer_Text, "This can occur when wind blows down the chimney", "FAQ16 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Why is the stove making some noise?", "FAQ17 question",
				faq_Answer_Text, "A buzzing may be heard when the", "FAQ17 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What happens in the event of a power cut?", "FAQ18 question",
				faq_Answer_Text, "In the event of a power cut, the damper", "FAQ18 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What status messages can I expect from the app:",
				"FAQ19 question", faq_Answer_Text, "Temp to high in stove.", "FAQ19 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What do the different colors of LED mean in the button?",
				"FAQ20 question", faq_Answer_Text, "LED off:", "FAQ20 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "What is a connection string?", "FAQ21 question", faq_Answer_Text,
				"A connection string is a code", "FAQ21 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Wifi pairing problem?", "FAQ21 question", faq_Answer_Text,
				"The wifi-modul only support", "FAQ22 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What are the requirements on my internet connection?",
				"FAQ22 question", faq_Answer_Text, "The speed of the connection has no", "FAQ23 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "What happens if connection is broken?", "FAQ23 question",
				faq_Answer_Text, "If the modem is switched off or the", "FAQ24 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "I can't log in, what can I do?", "FAQ24 question",
				faq_Answer_Text, "First, make sure your internet", "FAQ25 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "My system cannot connect to the internet, what can I do?",
				"FAQ25 question", faq_Answer_Text, "Make sure wifi is enabled on the system", "FAQ26 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What happens in the event of an alarm?", "FAQ26 question",
				faq_Answer_Text, "In the event of an alarm, a notification", "FAQ27 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "What should I do if the system changes owners?", "FAQ27 question",
				faq_Answer_Text, "When a system changes owner", "FAQ28 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How many systems can a user be connected to?", "FAQ28 question",
				faq_Answer_Text, "There is no upper limit to how many systems", "FAQ29 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Can you monitor and administer my system?", "FAQ29 question",
				faq_Answer_Text, "At present, we do not offer", "FAQ30 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "How secure is my system against data breaches?", "FAQ30 question",
				faq_Answer_Text, "We have several security measures", "FAQ31 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Which web browser is recommended for myUplink?", "FAQ31 question",
				faq_Answer_Text, "All web browsers", "FAQ32 answer");

		verifyUIofElement(customerSupport, "Customer support", "Customer support");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Please Select", "select topic dropdown");
		verifyUIofElement(select_system, "Please Select", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Summary", "Placeholder Summary is not displayed.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "Description",
				"Placeholder Description is not displayed.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Customer support", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "Send", "Send Button");
		navigateBack();
	}

	public void verifyNavigationAndContentForFAQsOfConturaInSvenska() throws InterruptedException {
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad bör jag tänka på innan jag tänder för första gången?",
				"FAQ1 question", faq1Ans_Ctra, "Din Contura eldstad är avsedd att användas som en sekundär värmekälla",
				"FAQ1 answer");
		// Bug fix : APPS-2539
//		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur ställer man luckan i städläge?", "FAQ2 question", 
//				faq_Answer_Text, "Dörren kan låsas", "FAQ2 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur gör jag en upptändning?", "FAQ3 question", faq_Answer_Text,
				"Var uppmärksam på att om", "FAQ3 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur lägger jag in ved utan inrykning?", "FAQ4 question",
				faq_Answer_Text, "Vänta till du har bara", "FAQ4 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Vilken typ av ved ska jag använda?", "FAQ5 question",
				faq_Answer_Text, "Alla träslag, som björk", "FAQ5 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur fuktig ved kan jag använda?", "FAQ6 question",
				faq_Answer_Text, "Cirka 50 % av vikten av", "FAQ6 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad får jag inte elda?", "FAQ7 question", faq_Answer_Text,
				"Man får absolut inte", "FAQ7 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur fungerar effektregleringen?", "FAQ8 question",
				faq_Answer_Text, "Tänk på att uteffekten", "FAQ8 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur rengör man glaset?", "FAQ9 question", faq_Answer_Text,
				"Vid eldning kan glasen bli sotiga", "FAQ9 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur tömmer jag asklådan?", "FAQ10 question", faq_Answer_Text,
				"När du tömmer asklådan, se till", "FAQ10 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur kommer jag igång med myUplink?", "FAQ11 question",
				faq_Answer_Text, "För att ansluta din anläggning", "FAQ11 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur rengör man kaminen?", "FAQ12 question", faq_Answer_Text,
				"Använd en stålborste för", "FAQ12 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Vart vänder jag mig om jag behöver reservdelar?",
				"FAQ13 question", faq_Answer_Text, "Detaljer som sitter nära själva", "FAQ13 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content,
				"Varför är det svårt att tända brasan och elden dör ut efter en kortare tid?", "FAQ14 question",
				faq_Answer_Text, "Kontrollera att veden är tillräckligt torr", "FAQ14 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Varför upplever jag för onormalt mycket sot på glasen?",
				"FAQ15 question", faq_Answer_Text, "Det blir alltid en viss sotbeläggning", "FAQ15 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Varför känner jag röklukt vid kaminen ibland?", "FAQ16 question",
				faq_Answer_Text, "Detta kan förekomma vid vindnedslag i", "FAQ16 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Varför låter kaminen ibland?", "FAQ17 question", faq_Answer_Text,
				"När luckan öppnas efter att", "FAQ17 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad händer vid ett strömavbrott?", "FAQ18 question",
				faq_Answer_Text, "Vid strömavbrott kommer", "FAQ18 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Vilka statusmeddelanden kan jag förvänta mig från appen?",
				"FAQ19 question", faq_Answer_Text, "Temperatur för hög i kaminen", "FAQ19 answer");

		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad betyder färgerna på LED-knappen?", "FAQ20 question",
				faq_Answer_Text, "När kaminen inte är", "FAQ20 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad är en anslutningssträng?", "FAQ21 question", faq_Answer_Text,
				"En anslutningssträng", "FAQ21 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Wifi anslutningsproblem?", "FAQ21 question", faq_Answer_Text,
				"Wifi modulen stödjer", "FAQ22 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vilka är kraven på min internetanslutning?", "FAQ22 question",
				faq_Answer_Text, "Anslutningens hastighet har ingen", "FAQ23 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad händer om anslutningen bryts?", "FAQ23 question",
				faq_Answer_Text, "Om modemet stängs", "FAQ24 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad ska jag göra om det inte går att logga in?", "FAQ24 question",
				faq_Answer_Text, "Kontrollera först att", "FAQ25 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content,
				"Vad ska jag göra om min anläggning inte kan ansluta till internet?", "FAQ25 question", faq_Answer_Text,
				"Kontrollera att wifi är aktiverat", "FAQ26 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad händer vid larm?", "FAQ26 question", faq_Answer_Text,
				"Vid larm skickas såväl", "FAQ27 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vad ska jag göra om anläggningen byter ägare?", "FAQ27 question",
				faq_Answer_Text, "När en anläggning byter ägare", "FAQ28 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Hur många anläggningar kan en användare vara ansluten till?",
				"FAQ28 question", faq_Answer_Text, "Det finns ingen övre gräns", "FAQ29 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Kan ni övervaka och administrera min anläggning?",
				"FAQ29 question", faq_Answer_Text, "I dagsläget erbjuder vi inte", "FAQ30 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content,
				"Vad rekommenderar ni för att skydda min myUplink-produkt och mitt konto från att bli hackad?",
				"FAQ30 question", faq_Answer_Text, "Vi arbetar kontinuerligt med", "FAQ31 answer");
		verifyTextofOtherBrandFAQs(faq_ques_content, "Vilken webbläsare rekommenderas till myUplink?", "FAQ31 question",
				faq_Answer_Text, "Vi rekommenderar dig att", "FAQ32 answer");
		verifyUIofElement(customerSupport, "Kundsupport", "Customer support in Svenska");
		clickFAQ(customerSupport);
		verifyUIofElement(select_topic, "Välj", "select topic dropdown");
		verifyUIofElement(select_system, "Välj", "select sytem dropdown");
		Assert.assertTrue(summaryCS.isDisplayed(), "Summary Input box Svenska is not displayed.");
		Assert.assertEquals(summaryCS.getAttribute("text"), "Sammanfattning",
				"Placeholder Summary is not displayed in Norsk.");
		Assert.assertTrue(descriptionCS.isDisplayed(), "Description Input box is not displayed.");
		Assert.assertEquals(descriptionCS.getAttribute("text"), "Beskrivning",
				"Placeholder Description is not displayed.");
		Assert.assertEquals(top_Nav_Title.getAttribute("text"), "Kundsupport", " TopNavBar title is not present.");
		verifyUIofDisabledElement(sendBtn, "Skicka", "Send Button in Svenska");
		navigateBack();
	}

}

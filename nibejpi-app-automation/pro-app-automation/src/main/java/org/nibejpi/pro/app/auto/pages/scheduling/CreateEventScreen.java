package org.nibejpi.pro.app.auto.pages.scheduling;

import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CreateEventScreen extends ScreenActions implements Scheduling {

	public CreateEventScreen(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void selectMode(String modeName) {

	    // Use the 'modeName' parameter to create a dynamic XPath
	    String modeXpath = String.format("//android.widget.TextView[@text='%s']", modeName);

	    // Scroll to the element with the specified text and click
	    driver.findElement(By.xpath(modeXpath)).click();
	}


}

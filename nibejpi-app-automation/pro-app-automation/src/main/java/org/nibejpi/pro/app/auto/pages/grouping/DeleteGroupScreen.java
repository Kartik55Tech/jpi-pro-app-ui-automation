package org.nibejpi.pro.app.auto.pages.grouping;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.nibejpi.app.pageobject.screenaction.ScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DeleteGroupScreen extends ScreenActions implements Grouping {

    // Locators using AndroidFindBy annotation
    @AndroidFindBy(id = NEXT_BTN)
    WebElement nextBtn;

    @AndroidFindBy(id = GROUP_NAME)
    WebElement groupName;

    @AndroidFindBy(id = TITLE)
    WebElement title;

    @AndroidFindBy(id = BACK_BTN)
    WebElement backBtn;

    @AndroidFindBy(id = DELETE_GRP_ALRT)
    WebElement delete_Alert_Msg;

    @AndroidFindBy(id = CANCEL_ALRT_BTN)
    WebElement cancel_Alrt_Btn;

    @AndroidFindBy(id = DELETE_ALRT_BTN)
    WebElement delete_Alert_Btn;

    @AndroidFindBy(xpath = DEFAULT_GRP)
    WebElement no_Group;

    // Constructor
    public DeleteGroupScreen(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Method to select a group for deletion
    public void selectGroupToDelete(String groupName) throws InterruptedException {
        Thread.sleep(3000);
        WebElement groupElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + groupName + "\"]"));
        waitForElementToBeVisible(groupElement);
        click(groupElement);
    }

    // Method to click the delete button
    public void tapOnDeleteButton() {
        Assert.assertTrue(nextBtn.isDisplayed(), "Delete Button is not displayed.");
        Assert.assertEquals(nextBtn.getAttribute("text"), "Delete", "Delete button is not present");
        click(nextBtn);
    }

    // Method to confirm group deletion
    public void confirmDelete() {
        waitForElementToBeVisible(delete_Alert_Msg);
        Assert.assertEquals(delete_Alert_Msg.getAttribute("text"), "Are you sure you want to delete this group?");
        click(delete_Alert_Btn);
    }

    // Method to cancel group deletion
    public void cancelDelete() {
        click(cancel_Alrt_Btn);
    }

    // Method to verify that the group is deleted
    public void verifyGroupIsDeleted() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertTrue(no_Group.isDisplayed(), "No Group field is not displayed");
        Assert.assertEquals(no_Group.getAttribute("text"), "No groups created");
    }
}

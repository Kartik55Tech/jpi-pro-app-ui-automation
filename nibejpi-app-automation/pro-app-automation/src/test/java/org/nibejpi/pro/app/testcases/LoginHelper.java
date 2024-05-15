package org.nibejpi.pro.app.testcases;

import io.appium.java_client.AppiumDriver;
import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.util.ExcelUtils;
import org.nibejpi.pro.app.auto.pages.login.LoginScreen;

public class LoginHelper {

    private AppiumDriver driver;

    public LoginHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    // Method to perform the setup for the login process
    public void performLoginSetup() throws Exception {
        LoginScreen login = new LoginScreen(driver);
        
        // Perform the sequence of actions
        login.tapOnNextButton();
        login.tapOnMyUplinkLogo();
        login.selectEnvironment();
    }

    // Method to perform the login using data from an Excel sheet
    public void login() throws Exception {
        LoginScreen login = new LoginScreen(driver);

        // Set the Excel file
        ExcelUtils.setExcelFile(Constants.PRO_TEST_DATA, "ProLogin");

        // Set the username and password from the Excel sheet
        login.setUsername(ExcelUtils.getCellData(1, 1));
        login.setPassword(ExcelUtils.getCellData(1, 2));

        // Click the login button
        login.clickOnLoginButton();
    }
}

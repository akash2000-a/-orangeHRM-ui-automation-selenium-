package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.JsonReader;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver, waitSeconds);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");

        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard page is not displayed after valid login");
    }

    @DataProvider(name = "invalidLoginJsonData")
    public Object[][] getInvalidLoginData() {
        return JsonReader.getJsonData("testdata/loginData.json");
    }

    @Test(priority = 2, dataProvider = "invalidLoginJsonData", description = "Verify error message with invalid credentials from JSON")
    public void testInvalidLogin(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver, waitSeconds);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");

        loginPage.login(username, password);
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message text mismatch");
    }
}




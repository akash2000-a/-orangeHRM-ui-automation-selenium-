package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;

public class AddEmployeeTest extends BaseTest {
    @Test(priority = 1, description = "Verify adding a new employee")
    public void testAddEmployee() {
        LoginPage loginPage = new LoginPage(driver, waitSeconds);
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard page not displayed");

        AddEmployeePage addEmployeePage = dashboardPage.clickAddEmployeeLink();

        addEmployeePage.addEmployee("Akash", "Mondal", "12345");

        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed(), "Success message is not displayed");
        String successMessage= addEmployeePage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Success"), "Success message text did not contain 'Success'. Actual: " + successMessage);
    }
    
}

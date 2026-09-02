package com.orangehrm.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.base.DriverFactory;

import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            saveScreenshotToAllure(result.getName() + " _Failure", screenshot);
        }
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveScreenshotToAllure(String testName, byte[] screenshot) {
        return screenshot;
    }
}

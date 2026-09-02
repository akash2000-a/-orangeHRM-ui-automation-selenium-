package com.orangehrm.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.orangehrm.utils.ConfigReader;

public abstract class BaseTest {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected int waitSeconds;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        String appUrl = ConfigReader.getAppUrl();
        waitSeconds = ConfigReader.getExplicitWait();

        logger.info("Setting up test execution. Browser: {}, URL: {}", browser, appUrl);
        driver = DriverFactory.initDriver(browser);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        driver.get(appUrl);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down test execution.");
        DriverFactory.quitDriver();
    }
}

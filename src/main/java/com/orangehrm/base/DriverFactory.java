package com.orangehrm.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.orangehrm.utils.ConfigReader;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Objects;

/**
 * DriverFactory manages WebDriver instances using ThreadLocal for thread safety.
 * This ensures clean isolation when running tests in parallel.
 */
public final class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();

    private DriverFactory() {
        // Prevent instantiation of utility class
    }

    public static WebDriver initDriver(String browser) {
        WebDriver driver;
        logger.info("Initializing driver for browser: {}", browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-infobars");
                if(ConfigReader.isHeadless()) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                } else {
                chromeOptions.addArguments("--start-maximized");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                logger.warn("Unknown browser '{}', defaulting to Chrome", browser);
                driver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized"));
                break;
        }

        driverTL.set(driver);
        return getDriver();
    }

    public static WebDriver getDriver() {
        return Objects.requireNonNull(driverTL.get(), "Driver is null! Call initDriver() first.");
    }

    public static void quitDriver() {
        if (driverTL.get() != null) {
            driverTL.get().quit();
            driverTL.remove();
            logger.info("WebDriver instance quit and removed from ThreadLocal storage.");
        }
    }
}


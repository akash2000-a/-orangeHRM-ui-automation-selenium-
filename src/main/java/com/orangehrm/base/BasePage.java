package com.orangehrm.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orangehrm.utils.WaitUtils;

public abstract class BasePage {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final int waitSeconds;

    protected BasePage(WebDriver driver, int waitSeconds) {
        this.driver = driver;
        this.waitSeconds = waitSeconds;
    }

    protected void click(WebElement element) {
        WaitUtils.waitForClickable(driver, element, waitSeconds).click();
        logger.debug("Clicked element: {}", element);
    }

    protected void type(WebElement element, String text) {
        WebElement e = WaitUtils.waitForVisible(driver, element, waitSeconds);
        e.clear();
        e.sendKeys(text);
        logger.debug("Typed '{}' into element", text);
    }

    protected String getText(WebElement element) {
        String text = WaitUtils.waitForVisible(driver, element, waitSeconds).getText();
        logger.debug("Retrieved text from element: {}", text);
        return text;
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return WaitUtils.waitForVisible(driver, element, waitSeconds).isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed or visible: {}", e.getMessage());
            return false;
        }
    }
}
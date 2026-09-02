package com.orangehrm.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtils {
    private WaitUtils() {}

    public static WebElement waitForVisible(WebDriver driver, WebElement element, int timeoutSeconds){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForClickable(WebDriver driver, WebElement element, int timeoutSeconds){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static boolean waitForText(WebDriver driver, WebElement element, String text, int timeoutSeconds){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}

package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.base.BasePage;

public class DashboardPage extends BasePage {

    @FindBy(css = ".oxd-topbar-header-title")
    private WebElement headerTitle;

    @FindBy(css = ".oxd-userdropdown")
    private WebElement userDropdown;

    @FindBy(xpath="//span[text()='PIM']")
    private WebElement pimLink;


    @FindBy(xpath="//a[text()='Add Employee']")
    private WebElement addEmployeeLink;
    public AddEmployeePage clickAddEmployeeLink() {
        click(pimLink);
        click(addEmployeeLink);
        return new AddEmployeePage(driver, waitSeconds);
    }

    
    public DashboardPage(WebDriver driver, int waitSeconds) {
        super(driver, waitSeconds);
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        return isDisplayed(headerTitle);
    }

    public String getHeaderTitleText() {
        return getText(headerTitle);
    }

    public boolean isUserDropdownDisplayed() {
        return isDisplayed(userDropdown);
    }
}

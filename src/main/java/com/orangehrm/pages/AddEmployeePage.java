package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.base.BasePage;

public class AddEmployeePage extends BasePage {

    public AddEmployeePage(WebDriver driver, int waitSeconds) {
        super(driver, waitSeconds);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private WebElement firstNameInput;
    @FindBy(name = "lastName")
    private WebElement lastNameInput;
    @FindBy(xpath="//label[text()='Employee Id']/parent::div/following-sibling::div//input")
    private WebElement employeeIdInput;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;
    @FindBy(css=".oxd-toast-content-text")
    private WebElement successMessage;
    public AddEmployeePage enterFirstName(String firstName) {
        type(firstNameInput, firstName);
        return this;
    }

    public AddEmployeePage enterLastName(String lastName) {
        type(lastNameInput, lastName);
        return this;
    }

    public AddEmployeePage enterEmployeeId(String employeeId) {
        type(employeeIdInput, employeeId);
        return this;
    }

    public void clickSaveButton() {
        click(saveButton);
    }
    public void addEmployee(String firstName, String lastName, String employeeId) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmployeeId(employeeId);
        clickSaveButton();
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}

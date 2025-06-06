package com.claimmanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators: Declared private to encapsulate page details.
    // This hides the implementation (how elements are found) from outside classes,
    // so only this page object can interact with them.
    // This promotes better maintainability and prevents misuse from other classes.
    private By usernameField = By.xpath("//input[@id='txt_username']");
    private By passwordField = By.xpath("//input[@id='txt_password']");
    private By signInButton = By.xpath("//input[@id='btn_login']");
    private By errorMessage = By.xpath("//span[@id='lblErrMessage']");
    private By validationMessage = By.xpath("//span[@id=\"Label2\"]");
    private By usernameValidationMessage  = By.xpath("//span[@id='RequiredFieldValidator1']");
    private By passwordValidationMessage  = By.xpath("//span[@id='RequiredFieldValidator2']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;


    }

    // Method to enter username; hides locator details from test code
    public void enterUserName(String userName) {
        driver.findElement(usernameField).click();
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(userName);
    }


    // Method to enter password; encapsulates how password field is accessed
    public void enterPassword(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    // Clicks the sign in button, encapsulating locator details
    public void clickSignIn(){
        driver.findElement(signInButton).click();

        //Wait unitl either success or error is Visible
        wait.until(driver -> driver.getCurrentUrl().contains("/mainscreen.aspx") || !driver.findElements(errorMessage).isEmpty());

    }

    // Returns error message text, waiting for it if necessary
    public String getLoginErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText().trim();
    }

    public String getValidationMessage(){
        return driver.findElement(validationMessage).getText().trim();
    }

    public String isLandingPageDisplayed(){
       //   return driver.getTitle();
           return driver.getCurrentUrl();
    }

    public String getUsernameValidationMessage() {
        return driver.findElement(usernameValidationMessage).getText().trim();
    }

    public String getPasswordValidationMessage() {
        return driver.findElement(passwordValidationMessage).getText().trim();
    }

}

package com.claimmanagement.steps;

import com.claimmanagement.pages.LoginPage;
import com.claimmanagement.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    DriverFactory driverFactory = new DriverFactory();  // Create DriverFactory object

    @Before
    public void setUp() {
        driver = driverFactory.initDriver();  // Initialize WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Implicit wait to avoid timing issues
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Explicit wait for element synchronization
        loginPage = new LoginPage(driver, wait);  // Initialize LoginPage with both driver and wait
    }

    @After
    public void tearDown() {
        driverFactory.quitDriver();  // Close browser after each scenario
    }

    @Given("The user is on the Tata Memorial Hospitals Claim Management login page")
    public void the_user_is_on_the_tata_memorial_hospitals_claim_management_login_page() {
        driver.get("https://intranet.tmc.gov.in/msw1/Login.aspx");  // Navigate to the login page URL
    }

    @When("The user enters {string} as username")
    public void the_user_enters_as_username(String username) {
        loginPage.enterUserName(username);  // Enter username into username field
    }

    @When("The user enters {string} as password")
    public void the_user_enters_as_password(String password) {
        loginPage.enterPassword(password);  // Enter password into password field
    }

    @When("The user clicks the Sign In button")
    public void the_user_clicks_the_sign_in_button() throws InterruptedException {
        loginPage.clickSignIn();  // Click the Sign In button
        Thread.sleep(5000);  // Wait for 5 seconds to handle synchronization (could be avoided with proper waits)
    }

    @Then("The user should see the Claim Management landing page")
    public void the_user_should_see_the_claim_management_landing_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/mainscreen.aspx"));  // Verify successful login
    }

    @Then("The user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getLoginErrorMessage();  // Get the actual error message
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);  // Assert that the error message matches
    }

    @When("The user leaves the username and password fields empty")
    public void the_user_leaves_the_username_and_password_fields_empty() {
        loginPage.enterUserName("");  // Leave username field empty
        loginPage.enterPassword("");  // Leave password field empty
    }

    @Then("The user should see a validation message {string} and {string}")
    public void the_user_should_see_a_validation_message(String expectedUsernameMsg, String expectedPasswordMsg) {
        String actualUsernameMsg = loginPage.getUsernameValidationMessage();  // Get the actual username validation message
        String actualPasswordMsg = loginPage.getPasswordValidationMessage();  // Get the actual password validation message

        Assert.assertEquals(expectedUsernameMsg, actualUsernameMsg);  // Assert that the username validation message matches
        Assert.assertEquals(expectedPasswordMsg, actualPasswordMsg);  // Assert that the password validation message matches
    }
}

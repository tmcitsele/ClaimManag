package com.claimmanagement.steps;

import com.claimmanagement.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    @Before
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Explicit wait set for 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //sets wait

        // Initialize LoginPage with driver and wait
        loginPage = new LoginPage(driver,wait);

    }

    @After
    public void tearDown() {
        // Close browser after each scenario
        driver.quit();
    }


    @Given("The user is on the Tata Memorial Hospitals Claim Management login page")
    public void the_user_is_on_the_tata_memorial_hospitals_claim_management_login_page() {
        // Navigate to the login page URL
        driver.get("https://intranet.tmc.gov.in/msw1/Login.aspx");
    }

    @When("The user enters {string} as username")
    public void the_user_enters_as_username(String username) {
        // Enter username into username field
        loginPage.enterUserName(username);
    }

    @When("The user enters {string} as password")
    public void the_user_enters_as_password(String password) {
        // Enter password into password field
        loginPage.enterPassword(password);
    }

    @When("The user clicks the Sign In button")
    public void the_user_clicks_the_sign_in_button() throws InterruptedException {
        // Click the Sign In button and wait for either landing page or error message
        loginPage.clickSignIn();
        // Now, wait logic is inside loginPage.clickSignIn() to handle synchronization

        Thread.sleep(5000);
    }

    @Then("The user should see the Claim Management landing page")
    public void the_user_should_see_the_claim_management_landing_page() {
        // Verify URL contains expected path indicating successful login
       Assert.assertTrue( loginPage.isLandingPageDisplayed().contains("/mainscreen.aspx"));

    }

    @Then("The user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedErrorMessage) {
        // Verify displayed error message matches expected
        String actualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @When("The user leaves the username and password fields empty")
    public void the_user_leaves_the_username_and_password_fields_empty() {
        // Leave both username and password fields empty
        loginPage.enterUserName("");
        loginPage.enterPassword("");
    }

    @Then("The user should see a validation message {string} and {string}")
    public void the_user_should_see_a_validation_message(String expectedUsernameMsg, String expectedPasswordMsg) {
        // Verify validation messages for username and password fields
        String actualUsernameMsg = loginPage.getUsernameValidationMessage();
        String actualPasswordMsg = loginPage.getPasswordValidationMessage();

        Assert.assertEquals(expectedUsernameMsg, actualUsernameMsg);
        Assert.assertEquals(expectedPasswordMsg, actualPasswordMsg);
    }

}

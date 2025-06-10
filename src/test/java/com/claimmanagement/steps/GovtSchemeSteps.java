package com.claimmanagement.steps;

import com.claimmanagement.pages.GovtSchemePage;
import com.claimmanagement.pages.LoginPage;
import com.claimmanagement.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GovtSchemeSteps {

    private static final Logger log = LoggerFactory.getLogger(GovtSchemeSteps.class);

    private WebDriver driver;
    private WebDriverWait wait;
    private GovtSchemePage govtSchemePage;
    private LoginPage loginPage;


    @Before
    public void setUp() {
        //System.out.println("=== Setting up driver and wait ===");
        log.info("=== Setting up driver and wait ===");
        driver = DriverFactory.initDriver();  // Use static method from singleton DriverFactory
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        govtSchemePage = new GovtSchemePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
    }

    @After
    public void tearDown() {
        //System.out.println("=== Quitting driver ===");
        log.info("=== Quitting driver ===");
        DriverFactory.quitDriver();
    }

    @Given("The user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        //System.out.println("Navigating to login page...");
        log.info("Navigating to login page...");
        driver.get("https://intranet.tmc.gov.in/msw1/Login.aspx");

        // You can still externalize credentials if you want
        String username = System.getProperty("username", System.getenv("TEST_USERNAME"));
        String password = System.getProperty("password", System.getenv("TEST_PASSWORD"));

        if (username == null || password == null) {
            // fallback to hardcoded for now or throw error
            username = "126267";
            password = "491809";
            //System.out.println("Using fallback hardcoded credentials.");
            log.info("Using fallback hardcoded credentials.");
            // Or throw new RuntimeException("Username or password not set");
        }

        //System.out.println("Logging in with username: " + username);
        log.info("Logging in with username: {}", username);
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
    }

    @When("The user hovers over the {string} menu")
    public void the_user_hovers_over_the_menu(String menuName) {
        //System.out.println("Hovering over menu: " + menuName);
        log.info("Hovering over menu: {}", menuName);
        if ("Govt. Scheme".equalsIgnoreCase(menuName.trim())) {
            govtSchemePage.hoverOverGovtSchemeMenu();
        } else {
            throw new RuntimeException("Unsupported menu: " + menuName);
        }
    }

    /*@Then("The user should see the following options:")
    public void the_user_should_see_the_following_options(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedOptions = dataTable.asList();
        System.out.println("Expected dropdown options: " + expectedOptions);

        List<String> actualOptions = govtSchemePage.getDropdownOptions();
        System.out.println("Actual dropdown options: " + actualOptions);

        Assert.assertEquals("Dropdown options do not match!", expectedOptions, actualOptions);
    }*/

    @Then("The user should see the following options:")
    public void the_user_should_see_the_following_options(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedOptions = dataTable.asList();
        List<String> actualOptions = govtSchemePage.getDropdownOptions();
        //System.out.println("Expected dropdown options: " + expectedOptions);

        log.info("Expected dropdown options: {}", expectedOptions);
        log.info("Actual dropdown options: {}", actualOptions);

        // Ignore case differences
        // Convert both lists to lowercase to ignore case issues
        List<String> expectedLower = expectedOptions.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        List<String> actualLower = actualOptions.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Assert.assertEquals("Dropdown options do not match!", expectedLower, actualLower);
    }

    @And("The user selects {string}")
    public void the_user_selects(String optionName) {
        //System.out.println("Selecting dropdown option: " + optionName);
        log.info("Selecting dropdown option: {}", optionName);
        govtSchemePage.selectOption(optionName);
    }

    @Then("The user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String expectedPageName) {
        //System.out.println("Waiting to verify redirection to: " + expectedPageName);
        log.info("Waiting to verify redirection to: {}", expectedPageName);
        boolean isOnExpectedPage = false;

        for (int i = 0; i < 5; i++) {
            if (govtSchemePage.isOnPage(expectedPageName)) {
                isOnExpectedPage = true;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }

        Assert.assertTrue("Expected to be on page: " + expectedPageName, isOnExpectedPage);
        //System.out.println("Successfully redirected to: " + expectedPageName);
        log.info("Successfully redirected to: {}", expectedPageName);
    }










   /* WebDriver driver;
    WebDriverWait wait;
    GovtSchemePage govtSchemePage;
    LoginPage loginPage;
    DriverFactory driverFactory = new DriverFactory();

    @Before
    public void setUp() {
        driver = driverFactory.initDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        govtSchemePage = new GovtSchemePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
    }

    @After
    public void tearDown() {
        driverFactory.quitDriver();
    }


    @Given("The user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        driver.get("https://intranet.tmc.gov.in/msw1/Login.aspx");
        loginPage.enterUserName("126267");  // Replace with valid test credentials
        loginPage.enterPassword("491809");
        loginPage.clickSignIn();
    }

    @When("The user hovers over the {string} menu")
    public void the_user_hovers_over_the_menu(String menuName) {
        if (menuName.equalsIgnoreCase("Govt, Scheme")) {
            govtSchemePage.hoverOverGovtSchemeMenu();
        }
        else {
            throw new RuntimeException("Unsupported menu: " + menuName);
        }
    }

    @Then("The user should see the following options:")
    public void the_user_should_see_the_following_options(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedOptions = dataTable.asList();
        List<String> actualOptions = govtSchemePage.getDropdownOptions();
        Assert.assertEquals("Dropdown option do not match", expectedOptions, actualOptions);
    }

    @And("The user selects {string}")
    public void the_user_selects(String optionName) {
        govtSchemePage.selectOption(optionName);
    }


    @Then("The user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String expectedPageName) {
        Assert.assertTrue("Expected to be on page: " + expectedPageName,
                govtSchemePage.isOnPage(expectedPageName));
    }*/


}



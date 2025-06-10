package com.claimmanagement.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class GovtSchemePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By govtSchemeMenu = By.xpath("//button[@class='dropbtn']");
    private By dropdownOptions = By.xpath("//div[@class='dropdown-content']//a");


    private static final Map<String, String> pageUrlFragments;
    static {
        Map<String, String>map = new HashMap<>();
        map.put("enquiry", "frmGovSchreq.aspx");
        map.put("under treatment case tracker", "frmGovSchEnq.aspx");
        map.put("discharged case tracker", "frmGovPreAuth.aspx");
        map.put("pre-auth closed report", "frmGovRpt.aspx");
        map.put("request report", "frmGovAllrpt.aspx");
        pageUrlFragments = Collections.unmodifiableMap(map);
    }

    public GovtSchemePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
    }

    public void hoverOverGovtSchemeMenu() {
        System.out.println("Trying to hover over 'Govt. Scheme' menu...");
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(govtSchemeMenu));
        actions.moveToElement(menu).perform();
        System.out.println("Hovered on menu. Waiting for dropdown options...");


        try {
            Thread.sleep(500); // Slight delay to allow dropdown to load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));
        System.out.println("Dropdown options should now be visible.");
    }


    // without ----- seperator
    /*public List<String> getDropdownOptions() {
        System.out.println("Fetching all dropdown option texts...");
        List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));
        List<String> optionTexts = new ArrayList<>();

        for (WebElement option : options) {
            String text = option.getText().trim();
            optionTexts.add(text);
            System.out.println("Found option: " + text);
        }

        return optionTexts;
    }*/

    public List<String> getDropdownOptions() {
        System.out.println("Fetching all dropdown option texts...");
        List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));
        List<String> filteredOptions = new ArrayList<>();

        for (WebElement option : options) {
            String text = option.getText().trim();

            // Skip separators or empty strings
            if (text.isEmpty() || text.equals("-----------------------")) {
                continue;
            }

            filteredOptions.add(text);
            System.out.println("Found option: " + text);
        }

        return filteredOptions;
    }

    public void selectOption(String optionName) {
        System.out.println("Trying to select option: " + optionName);
        List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));

        for (WebElement option : options) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(optionName.trim())) {
                System.out.println("Clicking on option: " + text);
                actions.moveToElement(option).perform();
                option.click();
                return;
            }
        }


        System.out.println("Option not found in dropdown: " + optionName);
    }

    // Assertion is wrong it is checking url but I am providing name of the page
    /*public boolean isOnPage(String expectedPageName) {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String normalizedExpected = expectedPageName.toLowerCase().replace(" ", "");
        System.out.println("Current URL: " + currentUrl);
        System.out.println("Expecting page containing: " + normalizedExpected);
        return currentUrl.contains(normalizedExpected);
    }*/

    public boolean isOnPage(String expectedPageName){
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String key = expectedPageName.toLowerCase().trim();

        System.out.println("Current url: " + currentUrl);
        System.out.println("Checking if URL contains expected fragment for page: " + key);

        if (pageUrlFragments.containsKey(key)){
            String expectedFragment = pageUrlFragments.get(key).toLowerCase();
            boolean result = currentUrl.contains(expectedFragment);
            System.out.println("Expected URL fragment: " + expectedFragment + " Found? " + result);
            return result;
        }
        else {
            // Fallback: naive check (space removed)
            boolean fallbackResult = currentUrl.contains(key.replace(" ", ""));
            System.out.println("No mapping found. Using fallback check: " + fallbackResult);
            return fallbackResult;
        }
    }
}

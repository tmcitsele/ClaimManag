package com.claimmanagement.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DriverFactory {

    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    private static WebDriver driver;

   /* static {
        // Ensure 'logs' folder exists before any logging occurs
        File logDir = new File("logs");
        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (created) {
                log.info("✅ 'logs/' directory created.");
            } else {
                log.warn("⚠️ Failed to create 'logs/' directory.");
            }
        }
    }*/

    // Initialize driver only if not already initialized
    public static WebDriver initDriver() {
        if (driver == null) {
            log.info("Initializing ChromeDriver...");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            log.info("ChromeDriver initialized and window maximized.");
        }
        else {
            log.info("Using existing WebDriver instance.");
        }
        return driver;
    }

    /*// Get the existing driver instance
    public static WebDriver getDriver() {
        return driver;
    }*/

    // Quit and nullify the driver to clean up properly
    public static void quitDriver() {
        if (driver != null) {
            log.info("Quitting WebDriver...");
            driver.quit();
            driver = null;
            log.info("WebDriver quit and nullified.");
        } else {
            log.warn("quitDriver called but driver was already null.");
        }
    }
}

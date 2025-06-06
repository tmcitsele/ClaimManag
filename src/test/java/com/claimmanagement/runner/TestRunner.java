package com.claimmanagement.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",   // ✅ Path to .feature files
        glue = {"com.claimmanagement.steps"},       // ✅ Path to step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        tags = "@Smoke"  // Optional: run only specific tagged scenarios
)
public class TestRunner {
}

package com.claimmanagement.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)  // Must be Cucumber.class for Cucumber runner
@CucumberOptions(
        features = "src/test/resources/Features/GovtSchemesfeature.feature",
        glue = {"com.claimmanagement.steps"},
        plugin = {"pretty", "html:target/govtscheme-reports.html"},
        monochrome = true
)
public class GovtSchemeTestRunner {
}

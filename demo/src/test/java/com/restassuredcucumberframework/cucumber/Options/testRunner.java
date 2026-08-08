package com.restassuredcucumberframework.cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/com/restassuredcucumberframework/FeatureFiles",
    glue = {"com.restassuredcucumberframework.StepDefinitions"},
    tags = "@AddPlaceAPI or @DeletePlaceAPI",
    plugin = "json:target/jsonReports/cucumber-report.json"    //compile -test-verify
)
public class testRunner extends AbstractTestNGCucumberTests {
}
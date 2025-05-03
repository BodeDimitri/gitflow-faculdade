package com.example.faculdade;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "json:target/cucumber-report.json"
    },
    features = "src/test/resources/features",
    glue = "com.exemplo.steps"
)
public class RunCucumberTest {
}

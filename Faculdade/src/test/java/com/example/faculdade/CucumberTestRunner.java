package com.example.faculdade;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  
    glue = "com.example.faculdade",            
    plugin = {"pretty", "summary"},            
    publish = true                             
)
public class CucumberTestRunner {
}

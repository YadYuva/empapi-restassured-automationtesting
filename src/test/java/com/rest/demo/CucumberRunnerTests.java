package com.rest.demo;

/*import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;*/

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


/**
 * Cucumber runner class with features,steps definition files glued and extent report plugin
 * @author Santhalakshmi
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.rest.demo.steps"},
        features = {"src/test/java/com/rest/demo/features"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/reports/report.html"})

public class CucumberRunnerTests extends SpringIntegrationTest{
}

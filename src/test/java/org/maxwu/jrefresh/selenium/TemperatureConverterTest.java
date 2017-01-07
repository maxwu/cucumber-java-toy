package org.maxwu.jrefresh.selenium;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;


/**
 * Created by maxwu on 1/2/17.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber-report.json"
        },
        features = {
                /* FIXME: Test Cucumber feature sequence. */
                "src/test/resources/org.maxwu.jrefresh.selenium"
        },
        dryRun = false,
        glue = "org.maxwu.jrefresh.selenium.stepdefs"
)
public class TemperatureConverterTest {
}

package org.maxwu.jrefresh.selenium;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.maxwu.jrefresh.ColorPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    static Logger logger = LoggerFactory.getLogger(TemperatureConverterTest.class.getName());

    @AfterClass
    public static void tearDownSuite(){
        logger.info(ColorPrint.blue("...Suite tears down @AfterClass..."));
    }
}

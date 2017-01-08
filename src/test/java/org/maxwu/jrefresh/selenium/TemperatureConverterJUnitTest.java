package org.maxwu.jrefresh.selenium;

import cucumber.api.java.After;
import org.junit.Before;
import org.junit.Test;
import org.maxwu.jrefresh.ColorPrint;

/**
 * Created by maxwu on 1/5/17.
 */
public class TemperatureConverterJUnitTest {
    @Before
    public void setUp(){
        ColorPrint.println_blue("Starting JUnit 2nd phase test");
    }

    @Test
    public void verify_true(){
        ColorPrint.println_blue("Sample Case-1");
        // This is the sample case driven by JUnit.
        // The real tests were done with Cucumber JVM and Selenium Page Object.
    }

    @After
    public void tearDown(){
        ColorPrint.println_blue("The tear down step");
    }
}

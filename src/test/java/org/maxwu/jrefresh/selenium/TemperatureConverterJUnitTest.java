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
        ColorPrint.println_blue("case-1");

    }
    @After
    public void tearDown(){
        ColorPrint.println_blue("The tear down step");
    }
}

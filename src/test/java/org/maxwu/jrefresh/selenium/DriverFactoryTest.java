package org.maxwu.jrefresh.selenium;

import org.junit.*;
import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.WebDriver;

/**
 * Created by maxwu on 1/5/17.
 * This class is designed to demo pure JUnit way of test.
 * (Instead of Cucumber-JVM).
 */
public class TemperatureConverterJUnitTest {
    private WebDriver driver = null;

    @Before
    public void setUp(){
        driver = DriverFactory.getDriver();
    }

    @Test
    public void get_one_page(){
        // This is the sample case driven by JUnit.
        // The real tests were done with Cucumber JVM and Selenium Page Object.
        driver.get("http://www.facebook.com");
    }

    @Test
    public void get_one_page_after1(){
        driver.get("http://www.google.com");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

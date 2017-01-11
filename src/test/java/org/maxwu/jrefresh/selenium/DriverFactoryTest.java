package org.maxwu.jrefresh.selenium;

import org.junit.*;
import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by maxwu on 1/5/17.
 * This class is designed to demo pure JUnit test on Classes.
 * (Instead of Cucumber-JVM).
 */
public class DriverFactoryTest {

    @Before
    public void setUp(){
        // Intend to keep empty.
        // In the future, @BeforeEach could be considered to simplify cases.
    }

    @Test
    public void getDriverTest(){
        WebDriver driver = DriverFactory.getDriver();
        Assert.assertNotNull(driver);
        Assert.assertTrue(driver instanceof FirefoxDriver);
        Assert.assertTrue(driver instanceof RemoteWebDriver);
        DriverFactory.quitDriver(driver);
    }

    @Test
    public void navigateWeb(){
        WebDriver driver = DriverFactory.getDriver();
        driver.get("http://www.facebook.com");
        driver.get("http://www.google.com");
        DriverFactory.quitDriver(driver);
    }

    @After
    public void tearDown(){
        // Intend to keep empty.
    }
}

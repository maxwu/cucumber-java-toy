package org.maxwu.jrefresh.selenium;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.maxwu.jrefresh.selenium.pageObjects.PageBase;
import org.maxwu.jrefresh.selenium.pageObjects.WrongPageException;
import org.openqa.selenium.WebDriver;
import org.maxwu.jrefresh.selenium.WrongPageExceptionTest;

import static org.hamcrest.CoreMatchers.startsWith;

/**
 * Created by maxwu on 2/25/17.
 */
public class WrongPageExceptionTest {
    WebDriver driver = null;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        if (driver != null) {
            DriverFactory.quitDriver(driver);
        }
        driver = DriverFactory.getDriver();
    }

    /*
    Use inner class to override the test method.
     */
    @Test
    public void testWrongTitle() {
        expectedEx.expect(WrongPageException.class);
        expectedEx.expectMessage(startsWith("Wrong Title"));
        new PageBase(driver) {
            @Override
            public boolean checkTitle() {
                driver.get("http://maxwu.me");
                return checkTitle("MaxWu");
            }
        }.checkTitle();
    }

    @Test
    public void testSunnyTitle(){
        Assert.assertTrue(
                new PageBase(driver, ".*", ".*\\+U.*").get("http://maxwu.me").checkTitle()
        );
        Assert.assertTrue(
                new PageBase(driver, ".*", ".*Max.*").get("http://maxwu.me").checkTitle()
        );
    }

    @Test
    public void testWrongUrl() {
        expectedEx.expect(WrongPageException.class);
        expectedEx.expectMessage(startsWith("Wrong URL"));
        new PageBase(driver, ".*M.*", ".*").get("http://maxwu.me").checkUrl();
    }

    @Test
    public void testSunnyUrl(){
        Assert.assertTrue(
            new PageBase(driver, ".*maxwu\\.me.*", ".*").get("http://maxwu.me").checkUrl()
        );
        Assert.assertTrue(
            new PageBase(driver, "^http://.*", ".*").get("http://maxwu.me").checkUrl()
        );
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver(driver);
        driver = null;
    }
}

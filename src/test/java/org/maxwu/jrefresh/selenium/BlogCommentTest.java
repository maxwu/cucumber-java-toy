package org.maxwu.jrefresh.selenium;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runners.MethodSorters;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.pageObjects.BlogCommentPage;
import org.maxwu.jrefresh.selenium.pageObjects.PageBase;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by maxwu on 2/13/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlogCommentTest {
    private WebDriver driver = null;
    private BlogCommentPage blogCommentPage = null;

    @Rule
    public TestRule watch = new ScreenshotWatcher();
    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp(){
        String testName = name.getMethodName();

        if (driver != null){
            //quit the last session
            DriverFactory.quitDriver(driver);
        }
        driver = DriverFactory.getDriver();

        // For method name in list, ignore PageObject initialization.
        if (testName.matches(".*[N|n]oPageObject.*")){
            ColorPrint.println_blue("Case '" + testName +"' intends not to create PageObject.");
        }else {
            blogCommentPage = new BlogCommentPage(driver);
            ScreenshotWatcher.setPageObject(blogCommentPage);
        }
    }

    @Test(timeout = 60000)
    public void a01checkComments(){
        //TODO: comments checks.
       blogCommentPage.getComments().stream().limit(10)
                .forEach(n -> ColorPrint.println_green("Extracted Comment: " + n));
    }

    @Test(timeout = 60000)
    public void a02TestSequenceNoPageObject(){
        // Demo case to show the runner order.
        Assert.assertTrue("http://maxwu.me/2016/10/02/dropme/".matches(".*dropme.*"));
        driver.get("http://yahoo.com");
        ColorPrint.println_blue("the title is \"" + driver.getTitle() + "\"");
        PageBase.saveScreenShot(driver, "TestSequence2");
    }

    @Test(timeout = 60000)
    public void a03TestSequence(){
        // print title right after blogCommentPage creation.
        ColorPrint.println_blue("the title is \"" + driver.getTitle() + "\"");
        PageBase.saveScreenShot(driver, "TestSequence3");
    }

    @After
    public void tearDown(){
        String testName = name.getMethodName();
        PageBase.saveScreenShot(driver, testName);

        DriverFactory.quitDriver(driver);
        driver = null;
    }

}


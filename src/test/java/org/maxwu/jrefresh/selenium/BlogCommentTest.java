package org.maxwu.jrefresh.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.maxwu.jrefresh.selenium.pageObjects.BlogCommentPage;
import org.maxwu.jrefresh.selenium.pageObjects.PageBase;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by maxwu on 2/13/17.
 */
public class BlogCommetTest{
    private WebDriver driver = null;
    private BlogCommentPage blogCommentPage = null;

    @Rule
    public TestRule watch = new ScreenshotWatcher();

    @Before
    public void setUp(){
        if (driver != null){
            DriverFactory.quitDriver(driver);
        }

        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        blogCommentPage = new BlogCommentPage(driver);
        ScreenshotWatcher.setPageObject(blogCommentPage);
    }

    @Test
    public void loginDisqus(){

    }

    @After
    public void tearDown(){
        if (driver != null){
            DriverFactory.quitDriver(driver);
        }
    }

}

class ScreenshotWatcher extends TestWatcher {

    private static PageBase page;

    public static void setPageObject(PageBase page){
        ScreenshotWatcher.page = page;
    }

    @Override
    public void failed(Throwable e, Description description) {
        page.saveScreenShot();
    }

}

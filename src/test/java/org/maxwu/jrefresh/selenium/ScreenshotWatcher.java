package org.maxwu.jrefresh.selenium;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.maxwu.jrefresh.selenium.pageObjects.PageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by maxwu on 2/16/17.
 * A demo of how to use TestWatcher interface to set JUnit rule.
 */
class ScreenshotWatcher extends TestWatcher {
    static Logger logger = LoggerFactory.getLogger(ScreenshotWatcher.class.getName());
    private static PageBase page;

    public static void setPageObject(PageBase page){
        ScreenshotWatcher.page = page;
    }

    @Override
    public void failed(Throwable e, Description description) {
        logger.info("Case " + description.getMethodName() + " fails.");
    }

    @Override
    public void succeeded(Description description) {
        logger.info("Case " + description.getMethodName() + " succeeds.");
    }

}


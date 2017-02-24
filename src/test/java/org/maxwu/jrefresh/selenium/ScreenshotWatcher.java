package org.maxwu.jrefresh.selenium;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.pageObjects.PageBase;

/**
 * Created by maxwu on 2/16/17.
 * A demo of how to use TestWatcher interface to set JUnit rule.
 */
class ScreenshotWatcher extends TestWatcher {

    private static PageBase page;

    public static void setPageObject(PageBase page){
        ScreenshotWatcher.page = page;
    }

    @Override
    public void failed(Throwable e, Description description) {
        ColorPrint.println_blue("Case " + description.getMethodName() + " fails.");
    }

    @Override
    public void succeeded(Description description) {
        ColorPrint.println_blue("Case " + description.getMethodName() + " succeeds.");
    }

}


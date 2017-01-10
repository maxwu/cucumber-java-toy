package org.maxwu.jrefresh.selenium;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

/**
 * Created by maxwu on 1/2/17.
 */
public class DriverFactory {

    // TODO: Add config.json items and loop on multiple browsers.
    public static WebDriver getDriver(){
        ColorPrint.println_blue("****Request on Web Driver received****");

        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver();
    }

    public static boolean hasQuit(WebDriver driver) {
        return ((RemoteWebDriver)driver).getSessionId() == null;
    }
}

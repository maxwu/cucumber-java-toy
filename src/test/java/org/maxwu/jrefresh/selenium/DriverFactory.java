package org.maxwu.jrefresh.selenium;

import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by maxwu on 1/2/17.
 */
public class DriverFactory {
    public enum Browsers {FIREFOX, FIREFOX_NIGHTLY};

    //TODO: Change to config.json
    public static void setPropertiesFirefox(Browsers bin){
        System.setProperty("webdriver.gecko.driver", "/Users/maxwu/devel/webdrivers/geckodriver");
        if (Browsers.FIREFOX_NIGHTLY == bin){
            System.setProperty("webdriver.firefox.bin", "/Applications/FirefoxNightly.app/Contents/MacOS/firefox-bin");
        }else if (Browsers.FIREFOX == bin){
            System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
        }else {
            ColorPrint.println_red(System.err, "Wrong browser type defined.");
            throw new RuntimeException("Non-Firefox browser shall not set FF profile.");
        }
    }

    public static WebDriver getFirefoxNightlyDriver(){
        setPropertiesFirefox(Browsers.FIREFOX_NIGHTLY);
//        String profileDir = "/Users/maxwu/Library/Application Support/Firefox/Profiles/kgyv8qd5.default";
//        FirefoxProfile profile = new FirefoxProfile(new File(profileDir));
//        return new FirefoxDriver(profile);
        return new FirefoxDriver();
    }

    public static WebDriver getDriver(){
        return getFirefoxNightlyDriver();
    }
}

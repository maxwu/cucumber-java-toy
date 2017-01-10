package org.maxwu.jrefresh.selenium;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.pageObjects.WrongPageException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by maxwu on 1/2/17.
 */
public class DriverFactory {

    // TODO: Add config.json items and loop on multiple browsers.
    // Since it is designed to destroy (quit) driver at every test (scenario) end,
    // it requires a new driver at every test scenario beginning.
    public static synchronized WebDriver getDriver(){
        ColorPrint.println_blue("**** Request on Web Driver received ****");

        FirefoxDriverManager.getInstance().setup();
        WebDriver driver = new FirefoxDriver();
        if (hasQuit(driver)){
            ColorPrint.println_red("**** Driver has quit already ****");
            throw new WrongPageException("New Driver has quit == true!");
        }
        return driver;
    }

    public static boolean hasQuit(WebDriver driver) {
        return ((RemoteWebDriver)driver).getSessionId() == null;
    }

    public static synchronized void quitDriver(WebDriver driver){
        if (driver != null) {
            ColorPrint.println_red("Request received to quit the driver!");
            ((JavascriptExecutor) driver).executeScript("window.stop;");
            driver.quit();
        }else{
            ColorPrint.println_red("Driver is null while requesting to quit!");
        }
    }

    // To simulate the real world, JS still needs a short interval to run and fetch the result.
    public static synchronized void waitInterval(){
        try{
            Thread.sleep(200);
        }catch (Exception e){
            ColorPrint.println_red("Exception during sleep: " + e.getStackTrace());
        }
    }
}

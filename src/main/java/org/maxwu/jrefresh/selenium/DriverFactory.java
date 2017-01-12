package org.maxwu.jrefresh.selenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.pageObjects.WrongPageException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by maxwu on 1/2/17.
 */
public class DriverFactory {

    // TODO: Add config.json items and loop on multiple browsers.
    // Since it is designed to destroy (quit) driver at every test (scenario) end,
    // it requires a new driver at every test scenario beginning.
    public static synchronized WebDriver getDriver(){
        //FirefoxDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().setup();

        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
        if (hasQuit(driver)){
            ColorPrint.println_red("**** New Driver has quit already ****");
            throw new WrongPageException("New Driver has quit == true!");
        }
        ColorPrint.println_blue("**** Created Web Driver #" + driver.hashCode() +"****");
        return driver;
    }

    public static boolean hasQuit(WebDriver driver) {
        return ((RemoteWebDriver)driver).getSessionId() == null;
    }

    public static synchronized void quitDriver(WebDriver driver){
        if ((driver != null) &&(!hasQuit(driver))){
            ColorPrint.println_blue("**** Destroying Web Driver #" + driver.hashCode() +"****");
            ((JavascriptExecutor) driver).executeScript("window.stop;");
            driver.quit();
        }else{
            // Do nothing for null driver on quiting state transition.
            // However, further code of logs will be nice.
        }
    }

    // To simulate the real world, JS still needs a short interval to run and fetch the result.
    public static void waitInterval(){
        ColorPrint.println_red("CAUTION: waiting is a temporary solution for debug only!");
        try{
            Thread.sleep(200);
        }catch (Exception e){
            ColorPrint.println_red("Exception during sleep: " + e.getStackTrace());
        }
    }
}

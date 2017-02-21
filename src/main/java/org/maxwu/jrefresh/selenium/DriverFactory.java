package org.maxwu.jrefresh.selenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.pageObjects.WrongPageException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by maxwu on 1/2/17.
 */
public class DriverFactory {

    // TODO: Add config items and loop on multiple browsers.
    // Since it is designed to destroy (quit) driver at every test (scenario) end,
    // it requires a new driver at every test scenario beginning.
    static ChromeOptions options = null;

    static void setWdmProperties(){
        // FIXME: Only keeping forceCache to false can specify browser driver version.
        System.setProperty("wdm.override", "true");
        System.setProperty("timeout", "30");
        String arch = System.getProperty("sun.arch.data.model");
        ColorPrint.println_blue("Arch=" + arch);
        if (arch.contains("32")){
            String driverVer32bit = "2.25";
            ColorPrint.println_red("Force chrome version to old " + driverVer32bit +" since 32bit is obsolete from Feb 2016");
            // Tested with JUnit Argument-line "-Dwdm.chromeDriverVersion=2.20"
            //   From 2.22 on, the chrome driver requests chrome-browser version 51+
            //   But latest chrome browser for 32bit Linux is version 48 from Feb 2016.
            System.setProperty("wdm.chromeDriverVersion", driverVer32bit);
            System.setProperty("wdm.forceCache", "false");
            // Use Chromium-browser instead of google-chrome
            // apt-get install chromium-browser with
            // root@maxwu:~# chromium-browser --version
            // /bin/bash: warning: setlocale: LC_ALL: cannot change locale (en_US.UTF-8)
            // Chromium 53.0.2785.143 Built on Ubuntu , running on Ubuntu 14.04
            options.setBinary(new File("/usr/bin/chromium-browser"));


        }else{
            // For 64bit system, using cache for latest version.
            System.setProperty("wdm.forceCache", "true");
        }
    }

    public static WebDriver getDriver(){
        options = new ChromeOptions();
        options.addArguments("--always-authorize-plugins");
        options.addArguments("start-maximized");
        setWdmProperties();

        ChromeDriverManager.getInstance().setup();

        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = (null==options)? new ChromeDriver(): new ChromeDriver(options);


        if (hasQuit(driver)){
            ColorPrint.println_red("**** New Driver has quit already ****");
            throw new WrongPageException("New Driver has quit == true!");
        }
        ColorPrint.println_blue("**** Created Web Driver #" + driver.hashCode() +"****");

        waitInterval();

        return driver;
    }

    public static boolean hasQuit(WebDriver driver) {
        // return driver.toString().contains("null");
        return ((RemoteWebDriver)driver).getSessionId() == null;
    }

    public static void quitDriver(WebDriver driver){
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
    public static void waitInterval(int interval){
        ColorPrint.println_red("CAUTION: waiting is a temporary solution for debug only, use WebDriverWait in regular wait-event cases!");
        try{
            Thread.sleep(interval);
        }catch (Exception e){
            ColorPrint.println_red("Exception during sleep: " + e.getStackTrace());
        }
    }

    public static void waitInterval(){
        waitInterval(500);
    }
}

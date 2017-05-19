package org.maxwu.jrefresh.selenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;

/**
 * Created by maxwu on 1/2/17.
 */
public class DriverFactory {

    // TODO: Add config items and loop on multiple browsers.
    // Since it is designed to destroy (quit) driver at every test (scenario) end,
    // it requires a new driver at every test scenario beginning.
    static ChromeOptions options = null;

    static Logger logger = LoggerFactory.getLogger(DriverFactory.class.getName());

    static String ENV_BROWSER = "browser";
    static String DEF_BROWSER = "chrome";
    public static enum Flavor{
        None, FF, CHROME, PHANTOMJS;
    }
    static Flavor browserType = Flavor.None;

    static void setWdmChromeProperties(){
        // FIXME: Only keeping forceCache to false can specify browser driver version.
        System.setProperty("wdm.override", "true");
        System.setProperty("timeout", "30");
        String arch = System.getProperty("sun.arch.data.model");
        logger.info(ColorPrint.blue("Arch=" + arch));

        if (arch.contains("32")){
            /*
            String driverVer32bit = "2.25";
            ColorPrint.println_red("Force chrome version to old " + driverVer32bit +" since 32bit is obsolete from Feb 2016");
               Tested with JUnit Argument-line "-Dwdm.chromeDriverVersion=2.20"
               From 2.22 on, the chrome driver requests chrome-browser version 51+
               But latest chrome browser for 32bit Linux is version 48 from Feb 2016.
            System.setProperty("wdm.chromeDriverVersion", driverVer32bit);
            System.setProperty("wdm.forceCache", "false");
            */
            // Use Chromium-browser instead of google-chrome
            // apt-get install chromium-browser with
            // root@maxwu:~# chromium-browser --version
            // /bin/bash: warning: setlocale: LC_ALL: cannot change locale (en_US.UTF-8)
            // Chromium 53.0.2785.143 Built on Ubuntu , running on Ubuntu 14.04
            // Updated to 55.0.2883.11-0ubuntu1 with private channel.
            System.setProperty("wdm.forceCache", "true");
            options.setBinary(new File("/usr/bin/chromium-browser"));
        }else{
            // For 64bit system, using cache for latest version.
            System.setProperty("wdm.forceCache", "true");
        }
    }

    static WebDriver  getChromeDriver(){
        options = new ChromeOptions();
        // List of options, http://peter.sh/experiments/chromium-command-line-switches/
        // To fix browser crash issue
        options.addArguments("start-maximized");
        // To fix browser hanging-up issue
        options.addArguments("dns-prefetch-disable");
        options.addArguments("test-type");
        options.addArguments("disable-plugins");
        options.addArguments("disable-extensions");
        options.setExperimentalOption("forceDevToolsScreenshot", true);
        options.addArguments("--loglevel 0");
        options.addArguments("ignore-urlfetcher-cert-requests");
        setWdmChromeProperties();

        ChromeDriverManager.getInstance().setup();

        return (null==options)? new ChromeDriver(): new ChromeDriver(options);
    }

    public static WebDriver getDriver(){
        WebDriver driver = null;
        String browser = Optional.ofNullable(System.getenv(ENV_BROWSER)).orElse(DEF_BROWSER);
        logger.debug("Browser Option is " + browser);
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = getChromeDriver();
            browserType = Flavor.CHROME;
            logger.info("**** Created Chrome Web Driver #" + driver.hashCode() + "****");
            return driver;
        }
        if (browser.equalsIgnoreCase("FF") || browser.equalsIgnoreCase("Firefox")) {
            driver = getFirefoxDriver();
            browserType = Flavor.FF;
            logger.info("**** Created Firefox Web Driver #" + driver.hashCode() + "****");
            return driver;
        }
        // Exception shall trigger here to expose issue ASAP.
        return null;
    }

    public static WebDriver getFirefoxDriver(){
        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver();
    }

    // Return true if driver is null or has quit already.
    public static boolean hasQuit(WebDriver driver) {
        if (driver != null) {
            return ((RemoteWebDriver) driver).getSessionId() == null;
        }else{
            return true;
        }
    }

    public static void quitDriver(WebDriver driver){
        if ((driver != null) &&(!hasQuit(driver))){
            logger.debug("**** Destroy Web Driver #" + driver.hashCode() +"****");
            ((JavascriptExecutor) driver).executeScript("window.stop;");
            driver.quit();
        }else{
            logger.error("Destroy a null or quit driver #" + (driver!=null? driver.hashCode(): "None"));
        }
    }

    // To simulate the real world, JS still needs a short interval to run and fetch the result.
    public static void waitInterval(int interval){
        logger.warn("CAUTION: waiting is a temporary solution for debug only, use WebDriverWait in wait-event cases!");
        try{
            Thread.sleep(interval);
        }catch (Exception e){
            logger.error("Exception during sleep: {}", e.getStackTrace());
        }
    }

    public static void waitInterval(){
        waitInterval(500);
    }
}

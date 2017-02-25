package org.maxwu.jrefresh.selenium.pageObjects;

import org.apache.commons.io.FileUtils;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by maxwu on 2/13/17.
 */
public class PageBase {
    static Logger logger = LoggerFactory.getLogger(PageBase.class.getName());

    protected WebDriver driver = null;
    private String urlRegEx = "https?:////";
    private String titleRegEx = ".*";

    public static void saveScreenShot(WebDriver dr, String caseName) {
        if ((dr == null) || (DriverFactory.hasQuit(dr))) {
            logger.error("Driver is null or quit already in saveScreenShot()");
            return;
        }
        String fname = ColorPrint.getTs();
        File scrFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("target/scr_" + fname + "_" + caseName + ".png"));
        }catch (Exception e){
            logger.error("Error in copying screenshot: " + e);
        }
    }

    public void saveScreenShot(String caseName) {
        PageBase.saveScreenShot(driver, caseName);
    }

    public void checkUrl(String urlPattern){
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.matches(urlPattern)){
            throw new WrongPageException("Wrong URL with Pattern \"" + urlPattern + "\"",
                    driver);
        }
    }

    public void checkTitle(String titlePattern){
        String currentTitle = driver.getTitle();
        if (!currentTitle.matches(titlePattern)){
            throw new WrongPageException("Wrong Title with Pattern \"" + titlePattern + "\"",
                    driver);
        }
    }

    public void checkUrl(){
        checkUrl(urlRegEx);
    }

    public void checkTitle(){
        checkTitle(titleRegEx);
    }

    PageBase(WebDriver dr, String urlPattern, String titlePattern){
        driver = dr;
        urlRegEx = urlPattern;
        titleRegEx = titlePattern;
    }
    PageBase(WebDriver dr){
        driver = dr;
    }
}

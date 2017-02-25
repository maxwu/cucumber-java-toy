package org.maxwu.jrefresh.selenium.pageObjects;

import javafx.print.PageOrientation;
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

    public boolean checkUrl(String urlPattern){
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.matches(urlPattern)){
            throw new WrongPageException("Wrong URL with Pattern \"" + urlPattern + "\"",
                    driver);
        }else{
            return true;
        }

    }

    public boolean checkTitle(String titlePattern){
        String currentTitle = driver.getTitle();
        if (!currentTitle.matches(titlePattern)){
            throw new WrongPageException("Wrong Title with Pattern \"" + titlePattern + "\"",
                    driver);
        }else{
            return true;
        }
    }

    public boolean checkUrl(){
        return checkUrl(urlRegEx);
    }

    public boolean checkTitle(){
        return checkTitle(titleRegEx);
    }

    public PageBase(WebDriver dr, String urlPattern, String titlePattern, String baseUrl){
        this(dr, urlPattern, titlePattern);
        this.get(baseUrl);
    }

    public PageBase get(String url){
        driver.get(url);
        return this;
    }

    public PageBase(WebDriver dr, String urlPattern, String titlePattern){
        this(dr);
        urlRegEx = urlPattern;
        titleRegEx = titlePattern;
    }

    public PageBase(WebDriver dr){
        driver = dr;
    }

    public PageBase(){
        this(DriverFactory.getDriver());
    }

    @Override
    protected void finalize(){
        DriverFactory.quitDriver(driver);
    }
}

package org.maxwu.phantomjs.pages;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.apache.commons.io.FileUtils;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.phantomjs.WebDrivable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by maxwu on 7/10/17.
 */
public class PageBase implements WebDrivable {
    private WebDriver driver;
    public final String name = "BasePage";

    public PageBase(){
        PhantomJsDriverManager.getInstance().setup();
        driver = new PhantomJSDriver();
    }

    public PageBase(WebDriver dr){
        this.driver = dr;
    }

    @Override
    public void finalize(){
        quit();
    }

    public void quit(){
        driver.quit();
    }

    public PageBase openUrl(String url){
        driver.get(url);
        return this;
    }

    public boolean checkUrl(String regExUrl){
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.matches(regExUrl);
    }

    public boolean checkTitle(String regExTitle){
        String currentTitle = driver.getTitle();
        return currentTitle.matches(regExTitle);
    }

    public void takeSnap(String folder) throws IOException {
        String ts = ColorPrint.getTs();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(folder + ts +".png"));
    }
}

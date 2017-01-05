package org.maxwu.jrefresh.selenium.pageObjects;

import org.openqa.selenium.WebDriver;

/**
 * Created by maxwu on 1/2/17.
 */
public class TemperatureConverter {
    public static String titleSuffix = " - Google Search";
    private WebDriver dr = null;

    public TemperatureConverter(WebDriver driver){
        if (driver != null) {
            dr = driver;
        }else{
            throw new WrongPageException("Driver is null!");
        }

        //Title check
        String title = driver.getTitle();
        if (!title.endsWith(TemperatureConverter.titleSuffix)){
            throw new WrongPageException("Got wrong title " + title);
        }
    }
}

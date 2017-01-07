package org.maxwu.jrefresh.selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void setLeftInput(String val){
        WebElement leftInput = dr.findElement(By.cssSelector("div#_Aif > input._eif"));
        // BugFix: Be sure to clear the existing strings on this Input Element.
        leftInput.clear();
        leftInput.sendKeys(val);

        // Notes: Return here is optional
        leftInput.sendKeys(Keys.RETURN);
    }

    public String getRightInput(){
        WebElement rightInput = dr.findElement(By.cssSelector("div#_Cif > input._eif"));
        return rightInput.getAttribute("value").trim();
    }

    // To return the top select of converter table.
    public WebElement getConverterSelect(){
        WebElement ele = dr.findElement(By.cssSelector("div#rso div._frf > select"));
        return ele;
    }
}

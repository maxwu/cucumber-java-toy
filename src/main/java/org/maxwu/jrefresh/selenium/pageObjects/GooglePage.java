package org.maxwu.jrefresh.selenium.pageObjects;

import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by maxwu on 1/2/17.
 */
public class GooglePage {
    private WebDriver dr = null;
    private String baseUrl = "https://www.google.co.nz";
    private String baseTitle = "Google";

    public GooglePage(WebDriver driver) throws RuntimeException{
        dr = driver;
        //dr.manage().window().maximize();
        dr.get(baseUrl + "/");
        String title = dr.getTitle();
        if (!title.equals(baseTitle)){
            throw new WrongPageException("Got wrong title:" + title);
        }
    }

    public TemperatureConverter getTempConverter(String keyWords){
        if ((keyWords == null) || (keyWords.isEmpty())){
            keyWords = "Temperature Converter";
        }

        // Find element
        WebElement ele = dr.findElement(By.cssSelector("input#lst-ib"));
        ColorPrint.println_green(System.out, "Got Element class:" + ele.getClass() );

        // Enter ""
        ele.sendKeys(keyWords);
        ele.sendKeys(Keys.RETURN);

        //Wait until stats is visible
        WebDriverWait wait = new WebDriverWait(dr, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
        ele = dr.findElement(By.id("resultStats"));
        ColorPrint.println_blue(System.out,"Result Statistics:" + ele.getText());

        return new TemperatureConverter(dr);
    }
}

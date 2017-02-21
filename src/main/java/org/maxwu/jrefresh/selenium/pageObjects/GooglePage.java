package org.maxwu.jrefresh.selenium.pageObjects;

import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.How;


/**
 * Created by maxwu on 1/2/17.
 */
public class GooglePage {
    private WebDriver dr = null;
    // Special note:
    // Google.co.nz would simply turn to Celsius->Fahrenheit table.
    // Instead, google.com would lead to Fahrenheit->Celsius table as US style.
    private String baseUrl = "https://www.google.co.nz";
    private String baseTitle = "Google";

    @FindBy(how = How.CSS, using = "input#lst-ib")

    WebElement inputSearch;

    public GooglePage(WebDriver driver) throws RuntimeException{
        dr = driver;
        //removed to avoid tab crash on 32bit Chromium
        // dr.manage().window().maximize();

        try {
            dr.get(baseUrl + "/");
        }catch (Exception e){
            dr.navigate().refresh();
        }
        String title = dr.getTitle();
        if (!title.equals(baseTitle)){
            throw new WrongPageException("Got wrong title:" + title);
        }

        PageFactory.initElements(driver, this);
    }

    public TemperatureConverter getTempConverter(String keyWords){
        if ((keyWords == null) || (keyWords.isEmpty())){
            keyWords = "Temperature Converter";
            ColorPrint.println_red("using default search keyword:" + keyWords);
        }

        inputSearch.sendKeys(keyWords);
        inputSearch.sendKeys(Keys.RETURN);
        DriverFactory.waitInterval();

        By byDiv = By.cssSelector("div#resultStats");

        //Wait until stats is visible
        WebDriverWait wait = new WebDriverWait(dr, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byDiv));

        WebElement ele = dr.findElement(byDiv);
        ColorPrint.println_blue(System.out,"Google Search Result Statistics:" + ele.getText());

        return new TemperatureConverter(dr);
    }
}

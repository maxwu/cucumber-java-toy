package org.maxwu.jrefresh.selenium.pageObjects;

import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
        //dr.manage().window().maximize();
        dr.get(baseUrl + "/");
        String title = dr.getTitle();
        if (!title.equals(baseTitle)){
            throw new WrongPageException("Got wrong title:" + title);
        }

        PageFactory.initElements(driver, this);
    }

    public TemperatureConverter getTempConverter(String keyWords){
        if ((keyWords == null) || (keyWords.isEmpty())){
            keyWords = "Temperature Converter";
        }

        inputSearch.sendKeys(keyWords);
        inputSearch.sendKeys(Keys.RETURN);

        //Wait until stats is visible
        WebDriverWait wait = new WebDriverWait(dr, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
        WebElement ele = dr.findElement(By.id("resultStats"));
        ColorPrint.println_blue(System.out,"Google Search Result Statistics:" + ele.getText());

        return new TemperatureConverter(dr);
    }
}

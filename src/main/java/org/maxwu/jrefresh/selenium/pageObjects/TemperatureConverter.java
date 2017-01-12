package org.maxwu.jrefresh.selenium.pageObjects;


import org.maxwu.jrefresh.ColorPrint;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by maxwu on 1/2/17.
 */
public class TemperatureConverter {
    public static String titleSuffix = " - Google Search";
    private WebDriver dr = null;

    @FindBy(how = How.CSS, using = "div#rso div._frf > select._nif")
    private WebElement selectDim;

    @FindBy(how = How.CSS, using = "div#_Aif > input._eif")
    private WebElement inputLeft;

    @FindBy(how = How.CSS, using = "div#_Cif > input._eif")
    private WebElement inputRight;

    @FindBy(how = How.CSS, using = "div#_Aif > select._dif")
    private WebElement selectLeft;

    @FindBy(how = How.CSS, using = "div#_Cif > select._dif")
    private WebElement selectRight;


    public void setSelectDim(String selected){
        Select selectDimOpt = new Select(selectDim);
        selectDimOpt.selectByValue(selected);

        for (WebElement webEle : selectDimOpt.getOptions()){
            // Added for cloud CI platform debug only.
            String optText = webEle.getText();
            ColorPrint.println_green("Option D: " + optText);
        }
    }

    public String getSelectDim(){
        return new Select(selectDim).getFirstSelectedOption().getText().trim();
    }

    public TemperatureConverter(WebDriver driver){

        PageFactory.initElements(driver, this);

        String title = driver.getTitle();
        if (!title.endsWith(TemperatureConverter.titleSuffix)){
            throw new WrongPageException("Got wrong title " + title);
        }
    }

    public void setInputLeft(String val){
        inputLeft.clear();
        inputLeft.sendKeys(val);

        // Notes: Return here is optional, below step could be randomly activated or bypass.
        inputLeft.sendKeys(Keys.RETURN);
    }

    public String getInputLeft(){
        return inputLeft.getAttribute("value").trim();
    }


    public String getInputRight(){
        return inputRight.getAttribute("value").trim();
    }

    // To return the top select of converter table.
    public WebElement getConverterSelect(){
        return selectDim;
    }

    public void setSelectLeft(String opt){
        new Select(selectLeft).selectByVisibleText(opt);
    }

    public void setSelectRight(String opt){
        new Select(selectRight).selectByVisibleText(opt);
    }

    public String getSelectLeft(){
        return new Select(selectLeft).getFirstSelectedOption().getText().trim();
    }

    public String getSelectRight(){
        return new Select(selectRight).getFirstSelectedOption().getText().trim();
    }
}

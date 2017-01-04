package org.maxwu.jrefresh.selenium.stepdefs;

//Before/After
import cucumber.api.java.*;
//Cucumber Annotation Keys
import cucumber.api.java.en.*;

import org.junit.Assert;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by maxwu on 1/2/17.
 */

public class TemperatureConverterTestStepDef {
    private WebDriver driver = null;
    private GooglePage googlePage = null;
    private TemperatureConverter tempConvt = null;

    @Before
    public void setUp(){
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

    @Given("^Google Entrance Page$")
    public void google_Entrance_page() throws Throwable {
        googlePage = new GooglePage(driver);
    }

    @When("^Search \"(.*)\"$")
    public void search_Temperature_Converter(String keyWord) throws Throwable {
        ColorPrint.println_blue(System.out, "Got Keyword:" + keyWord);
        tempConvt = googlePage.getTempConverter(keyWord);
    }

    @Then("^The page title is \"(.*)\"$")
    public void page_Title_is_Temperature_Converter(String expectedTitle) throws Throwable {
        expectedTitle += TemperatureConverter.titleSuffix;

        ColorPrint.println_blue(System.out, "Got expected title:" + expectedTitle);
        if (tempConvt == null){
            throw new WrongPageException("TemperatureConverter is null!");
        }
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @And("^There is a \"(.*)\" option selected$")
    public void verify_option_selected(String val) throws Throwable{
        WebElement ele = driver.findElement(By.xpath("/html/body/div/div[6]/div[4]/div[8]/div[1]/div[2]/div/div[2]/div[2]/div/div/div/div[1]/div/div/div/div[1]/div/div[1]/select"));
        ColorPrint.println_red("Got Select Element:" + ele.getText());

        Assert.assertThat(ele.getText(), containsString("Temperature"));
        ColorPrint.printDriverReport(driver);

        Select select = new Select(ele);
        WebElement opt = select.getFirstSelectedOption();
        ColorPrint.println_red("Got Selected Opt:" + opt.getText());
        Assert.assertEquals(opt.getText(), val.trim());
    }
}

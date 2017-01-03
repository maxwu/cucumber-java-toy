package org.maxwu.jrefresh.selenium.stepdefs;

//Before/After
import cucumber.api.java.*;
//Cucumber Annotation Keys
import cucumber.api.java.en.*;

import org.junit.Assert;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

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
}

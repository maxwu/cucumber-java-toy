package org.maxwu.jrefresh.selenium.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.GooglePage;
import org.maxwu.jrefresh.selenium.pageObjects.TemperatureConverter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by maxwu on 1/8/17.
 */
public class LengthConverterCalStepDef  {
    private WebDriver driver = null;
    private GooglePage googlePage = null;
    private TemperatureConverter tempConvt = null;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        tempConvt = new GooglePage(driver).getTempConverter(null);
    }

    // "After" runs by each scenario ending.
    @After
    public void tearDownHook(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                ColorPrint.println_red("****>>>> Failure in scenario: " + scenario.getName());
                ColorPrint.printDriverReport(driver);
                saveScreenShot();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @After
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }else{
            ColorPrint.println_red("Driver is null in @tearDown()");
        }
    }
    public void saveScreenShot() throws Exception {
        if (driver == null){
            ColorPrint.println_red("Driver is null in @saveScreenShot()");
            return;
        }

        String fname = new java.text.SimpleDateFormat(
                "yyyy-MM-dd-HH_mm_ss").format(new java.util.Date()
        );
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/screenshot" + fname + ".png"));
    }

    /* Step definitions */

    @Given("^Select \"(.*)\" dimension")
    public void selectGivenDimension(String dim) throws Throwable {
        tempConvt.setSelectDim(dim);
    }

    @And("^Select \"(.*)\" unit in left$")
    public void setLeftSelectUnit(String unit) throws Throwable{
        tempConvt.setSelectLeft(unit);
    }

    @And("^Select \"(.*)\" unit in right")
    public void setRightSelectUnit(String unit) throws Throwable{
        tempConvt.setSelectRight(unit);
    }

    @When("^Enter \"(.*)\" in left input$")
    public void setLeftInputNumber(String num) throws Throwable{
        tempConvt.setInputLeft(num);
    }

    @Then("^Get \"(.*)\" on right input$")
    public void verifyRightInputNumebr(String expectedNum) throws Throwable{
        Assert.assertEquals(expectedNum, tempConvt.getInputRight());
    }
}

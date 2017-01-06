package org.maxwu.jrefresh.selenium.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.GooglePage;
import org.maxwu.jrefresh.selenium.pageObjects.TemperatureConverter;
import org.maxwu.jrefresh.selenium.pageObjects.WrongPageException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;

import java.util.Map;

/**
 * Created by maxwu on 1/4/17.
 */
public class TemperatureConverterCalStepdef {
    private WebDriver driver = null;
    private GooglePage googlePage = null;
    private TemperatureConverter tempConvt = null;
    private Map<String, String> degreeList = null;

    // TODO: parameterize the 4 CssSelector for this test scenario.

    @Before
    public void setUp() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        tempConvt = new GooglePage(driver).getTempConverter("temperature converter");
    }

    // "After" runs by each scenario ending.
    @After
    public void tearDownHook(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                ColorPrint.println_red("****>>>> Failure in scenario: " + scenario.getName());
                ColorPrint.printDriverReport(driver);
                String fname = new java.text.SimpleDateFormat(
                        "yyyy-MM-dd-HH_mm_ss").format(new java.util.Date()
                );
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("target/screenshot" + fname + ".png"));

            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    @After
    public void tearDown(){
        //driver.close();
        driver.quit();
    }

    // Get the 1st selected option from filtered select element.
    private String getSelectedText(By byFilter){
        return new Select(driver.findElement(byFilter))
                .getFirstSelectedOption()
                .getText();
    }

    private void verifySelectOptionText(String expected, String got) throws Throwable{
        if (!expected.equals(got)){
            throw new WrongPageException("Excepted "
                    + expected
                    + " but got wrong selected option: "
                    + got
            );
        }
    }

    @Given("^\"(F.*)\" select is present$")
    public void verify_fahrenheit_present(String fahText) throws Throwable {
        String selected = getSelectedText(By.cssSelector("div#_Cif > select")).trim();

        ColorPrint.println_red("Fahrenheit text:" + selected);
        verifySelectOptionText("Fahrenheit", selected);
    }

    @And("^\"(C.*)\" select is present$")
    public void verify_celsius_present(String celText) throws Throwable {
        //select#_Bif
        String selected = getSelectedText(By.cssSelector("div#_Aif > select")).trim();

        ColorPrint.println_red("Celsius text:" + selected);
        verifySelectOptionText("Celsius", selected);
    }

    @When("^Input data from the table:$")
    public void test_input_data(Map<String, String> degreeList) throws Throwable {
        ColorPrint.println_blue(
                "This solution holds data as object member between methods, which is better to divided or turned to Scenario Outline."
        );

        this.degreeList = degreeList;
    }


    private String convertCelsiusToFahrenheit(String celsiusDegree) {
        WebElement inputCelsius = driver.findElement(By.cssSelector("div#_Aif > input._eif"));
        // BugFix: Be sure to clear the existing strings on this Input Element.
        inputCelsius.clear();
        inputCelsius.sendKeys(celsiusDegree);
        inputCelsius.sendKeys(Keys.RETURN);

        WebElement inputFahrenheit = driver.findElement(By.cssSelector("div#_Cif > input._eif"));
        return inputFahrenheit.getAttribute("value").trim();
    }

    @Then("^Results are correct as on table$")
    public void verify_converted_data() throws Throwable{
        ColorPrint.println_blue("Got data table in turned to Map structure");
        //Traditional way to loop Map.
        for (Map.Entry<String, String> entry : degreeList.entrySet()) {
            ColorPrint.println_blue("Celsius: " + entry.getKey() + "\t Fahrenheit: " + entry.getValue());
        }
        // Loop map with Lambda.
        degreeList.forEach(
                (String k, String v) -> {
                    String converted = convertCelsiusToFahrenheit(k);
                    ColorPrint.println_red("Expected " + v + "F,\t got " + converted + "F for " + k + "C");
                    Assert.assertEquals(converted, v);
                }
        );
    }
}

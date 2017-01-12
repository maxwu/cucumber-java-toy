package org.maxwu.jrefresh.selenium.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
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

    @Before
    public void setUp(Scenario scenario) {
        ColorPrint.printScenarioState(this, scenario, "starts, "  + scenario.getStatus());
        // test precondition
        if ((driver == null)||(DriverFactory.hasQuit(driver))){
            driver = DriverFactory.getDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // precondition
    }

    @After
    public void tearDown(Scenario scenario) {

        ColorPrint.printScenarioState(this, scenario, "ends, " + scenario.getStatus());
        if (scenario.isFailed()) {
            try {
                ColorPrint.println_red("****>>>> Failure in scenario: " + scenario.getName());
                ColorPrint.printDriverReport(driver);
                if ((driver != null)&&(!DriverFactory.hasQuit(driver))){
                    String fname = ColorPrint.getTs();
                    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile, new File("target/screenshot" + fname + ".png"));
                }else{
                    ColorPrint.println_red("Exception: driver is not working #" + driver);
                }
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        DriverFactory.quitDriver(driver);
        tempConvt = null;
        googlePage = null;
        driver = null;
    }

    // Get the 1st selected option from filtered select element.
    private String getSelectedText(By byFilter){
        return new Select(driver.findElement(byFilter))
                .getFirstSelectedOption()
                .getText();
    }

    private void verifySelectOptionText(String expected, String got) throws Throwable{
        if (!expected.equals(got)){
            throw new WrongPageException("In Select Web Element, expected "
                    + expected
                    + " but got wrong selected option: "
                    + got
            );
        }
    }

    @Given("^\"(F.*)\" select is present$")
    public void verify_fahrenheit_present(String fahText) throws Throwable {
        tempConvt = new GooglePage(driver).getTempConverter("Temperature Converter");

        tempConvt.setSelectRight(fahText);
        DriverFactory.waitInterval();

        String selected = tempConvt.getSelectRight();
        ColorPrint.println_blue("Fahrenheit text:" + selected);
        verifySelectOptionText(fahText, selected);
    }

    @And("^\"(C.*)\" select is present$")
    public void verify_celsius_present(String celText) throws Throwable {
        tempConvt.setSelectLeft(celText);

        String selected = tempConvt.getSelectLeft();
        ColorPrint.println_blue("Celsius text:" + selected);
        verifySelectOptionText(celText, selected);
    }

    @When("^Input data from the table:$")
    public void test_input_data(Map<String, String> degreeList) throws Throwable {
        ColorPrint.println_green(
                "This solution holds data as object member between methods, which is better to divided or turned to Scenario Outline."
        );
        this.degreeList = degreeList;
    }

    @Given("^Temperature background check$")
    public void verifyWebDriver() throws Throwable{
        ColorPrint.println_blue("Verifying driver: " + driver +" #" + driver.hashCode() +", temperature converter background");
        Assert.assertNotNull(driver);
        Assert.assertFalse(DriverFactory.hasQuit(driver));
    }

    private void enterCelsiusInput(String celsiusDegree){
        tempConvt.setInputLeft(celsiusDegree);
        Assert.assertEquals(celsiusDegree, tempConvt.getInputLeft());
        // The data fetching needs a communication delay.
        DriverFactory.waitInterval();
    }

    private String getFahrenheitDegree(){
        return tempConvt.getInputRight();
    }

    private String convertCelsiusToFahrenheit(String celsiusDegree) {
        enterCelsiusInput(celsiusDegree);
        return getFahrenheitDegree();
    }

    @Then("^Results are correct as on table$")
    public void verify_converted_data() throws Throwable{

        //Traditional way to loop Map.
        for (Map.Entry<String, String> entry : degreeList.entrySet()) {
            ColorPrint.println_blue("Celsius: " + entry.getKey() + "\t Fahrenheit: " + entry.getValue());
        }
        // Loop map with Lambda.
        degreeList.forEach(
                (String k, String v) -> {
                    String converted = convertCelsiusToFahrenheit(k);
                    ColorPrint.println_green("Expected " + v + "F,\t got " + converted + "F for " + k + "C");
                    Assert.assertEquals(converted, v);
                }
        );
    }

    @Given("^Google search page with predefined keywords$")
    public  void verify_page_title() throws Throwable {
        tempConvt = new GooglePage(driver).getTempConverter("Temperature Converter");
        Assert.assertTrue(driver.getTitle().startsWith("Temperature Converter"));
    }

    @When("Enter Celsius degree as \"([^\"]*)\"$")
    public void enter_celsius_degree(String degree) throws Throwable{
        enterCelsiusInput(degree);
    }

    @Then("Check the value against \"([^\"]*)\"$")
    public void check_fahrenheit_degree(String degree) throws Throwable{
        Assert.assertEquals(degree, getFahrenheitDegree());
    }
}

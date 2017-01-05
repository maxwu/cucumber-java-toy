package org.maxwu.jrefresh.selenium.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.GooglePage;
import org.maxwu.jrefresh.selenium.pageObjects.TemperatureConverter;
import org.maxwu.jrefresh.selenium.pageObjects.WrongPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Created by maxwu on 1/4/17.
 */
public class TemperatureConverterCalStepdef {

    private WebDriver driver = null;
    private GooglePage googlePage = null;
    private TemperatureConverter tempConvt = null;

    @Before
    public void setUp() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        tempConvt = new GooglePage(driver).getTempConverter("temperature converter");
    }

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

    @Given("^\"(F.*)\" select is present$")
    public void verify_fahrenheit_present(String fahText) throws Throwable {
        String selected = getSelectedText(By.cssSelector("div#_Cif > select"));
        ColorPrint.println_red("Fahrenheit text:" + selected);
        if (!selected.equals(fahText)){
            throw new WrongPageException("Excepted "
                    + fahText
                    + " but got wrong selected option: "
                    + selected
            );
        }
    }

    @And("^\"(C.*)\" select is present$")
    public void verify_celsius_present(String celText) throws Throwable {
        //select#_Bif
    }

    @When("^Input data from the table:$")
    public void test_input_data(DataTable dt) throws Throwable{

    }

    @When("^Get results as the table:$")
    public void verify_converted_data(DataTable dt) throws Throwable{

    }
}

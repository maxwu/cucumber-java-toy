package org.maxwu.jrefresh.selenium.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.hamcrest.CoreMatchers.containsString;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.*;

/**
 * Created by maxwu on 1/2/17.
 */

public class TemperatureConverterPageStepDef {
    private WebDriver driver = null;
    private GooglePage googlePage = null;
    private TemperatureConverter tempConvt = null;


    @Before
    public void setUp(Scenario scenario) {
        ColorPrint.printScenarioState(this, scenario, "----> starts");
        driver = DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario sc) {
        ColorPrint.printScenarioState(this, sc, "ends "  + sc.getStatus());
        DriverFactory.quitDriver(driver);
        tempConvt = null;
        googlePage = null;
        driver = null;
    }

    @Given("^Web browser initialized$")
    public void verifyWebDriver() throws Throwable{
        ColorPrint.println_blue("Verifying driver: " + driver +" #" + driver.hashCode() +", temperature page background");
        Assert.assertNotNull(driver);
        Assert.assertFalse(DriverFactory.hasQuit(driver));
    }

    @Given("^Google Entrance Page with:$")
    public void google_Entrance_page(String mulText) throws Throwable {
        googlePage = new GooglePage(driver);
        ColorPrint.println_green("Got multiple lines parameter:\n" + mulText);
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
        if (tempConvt == null) {
            throw new WrongPageException("TemperatureConverter is null!");
        }
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @And("^There is a \"(.*)\" option selected$")
    public void verify_option_selected(String val) throws Throwable {
        WebElement ele = tempConvt.getConverterSelect();
        Assert.assertThat(ele.getText(), containsString("Temperature"));

        Select select = new Select(ele);
        WebElement opt = select.getFirstSelectedOption();
        ColorPrint.println_red("Got Selected Opt:" + opt.getText());
        Assert.assertEquals(opt.getText(), val.trim());
    }

}

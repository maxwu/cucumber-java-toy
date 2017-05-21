package org.maxwu.jrefresh.selenium.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.maxwu.jrefresh.ColorPrint;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.GithubPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by maxwu on 5/19/17.
 */
public class GithubSeekTestStepDef {
    static Logger logger = LoggerFactory.getLogger(GithubPage.class.getName());
    private WebDriver driver = null;
    private GithubPage githubPage = null;
    List<String > followingLinks;
    List<String > followingUsers;

    @Before
    public void setUp(Scenario scenario) {
        ColorPrint.printScenarioState(this, scenario, "starts ");
        if ((driver == null)||(DriverFactory.hasQuit(driver))) {
            driver = DriverFactory.getDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario){
        ColorPrint.printScenarioState(this, scenario, "ends, " + scenario.getStatus());
        DriverFactory.quitDriver(driver);
        githubPage = null;
        driver = null;
    }

    @Given("^Web browser initialized for Github$")
    public void verifyWebDriver() throws Throwable{
        ColorPrint.println_blue("Verifying driver: " + driver +" #" + driver.hashCode() +" for Github Test Background");
        Assert.assertNotNull(driver);
        Assert.assertFalse(DriverFactory.hasQuit(driver));
    }

    @Given("^Github User Page on following tab with \"(\\S+)\"")
    public void openUserPageFollowingTab(String user){
        logger.debug("github page on following tab for " + user);
        githubPage = new GithubPage(driver, user);
    }

    @When("^Find all followed users")
    public void checkAllFollowingUsers(){
        followingLinks = githubPage.getFollowingList();
        Assert.assertTrue(followingLinks.size() > 0);
        followingLinks.stream().forEach(logger::info);
    }

    @Then("\"(\\S+)\" is one of them")
    public void checkUserExistence(String target){
        logger.info("checking against " + target);
        followingUsers = githubPage.getFollowingUsers();
        followingUsers.stream().forEach(logger::info);
        Assert.assertTrue(followingUsers.contains(target));
    }
}

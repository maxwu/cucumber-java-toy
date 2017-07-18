package org.maxwu.CapturedWebDriver;

import org.apache.log4j.Level;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.GithubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import static java.util.logging.Level.INFO;

/**
 * Created by maxwu on 7/18/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CapturedWebDriverTest {
    static WebDriver driver;
    private GithubPage githubPage;


    @Before
    public void setUp(){
        driver = DriverFactory.getDriver();
    }

    @Test
    public void webDriver1(){
        githubPage = new GithubPage(driver, "maxwu");
        List<String> followings = githubPage.getFollowingList();
        followings.stream().forEach(System.out::println);
        System.out.println("URL=" + ((HttpCommandExecutor)(((RemoteWebDriver) driver).getCommandExecutor())).getAddressOfRemoteServer());
        List<String> followers = githubPage.getFollowingUsers();
        followers.stream().forEach(System.out::println);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}

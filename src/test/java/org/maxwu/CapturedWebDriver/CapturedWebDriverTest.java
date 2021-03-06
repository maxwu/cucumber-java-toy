package org.maxwu.CapturedWebDriver;

import org.apache.log4j.Level;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.maxwu.jrefresh.selenium.CapturedWebDriver;
import org.maxwu.jrefresh.selenium.DriverFactory;
import org.maxwu.jrefresh.selenium.pageObjects.GithubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static java.util.logging.Level.INFO;

/**
 * Created by maxwu on 7/18/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CapturedWebDriverTest {
    static WebDriver driver;
    private GithubPage githubPage;

    static void pause(){
        System.out.println("Pause until an input with Enter.");
        Scanner scanner = new Scanner(System.in);
        String whatEver = scanner.next();
        scanner.close();
        System.out.println("Resuming.");
    }

    @Before
    public void setUp(){
        driver = DriverFactory.getDriver();
    }

    @Test @Ignore
    public void webDriver1(){
        githubPage = new GithubPage(driver, "maxwu");
        List<String> followings = githubPage.getFollowingList();
        followings.stream().forEach(System.out::println);

        URL driverUrl = ((HttpCommandExecutor)(((RemoteWebDriver) driver).getCommandExecutor())).getAddressOfRemoteServer();
        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        System.out.println("URL=" + driverUrl + " ID=" + sessionId);

        System.out.println("Old Title=" + driver.getTitle());

        pause();

        System.out.println("Building Captured Web Driver...");

        CapturedWebDriver capDriver = new CapturedWebDriver(driverUrl, sessionId);
        System.out.println("New Title=" + capDriver.getTitle());

        List<String> followers = githubPage.getFollowingUsers();
        followers.stream().forEach(System.out::println);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}

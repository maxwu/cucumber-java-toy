package org.maxwu.phantomjs;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.maxwu.phantomjs.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static junit.framework.TestCase.fail;

/**
 * Created by maxwu on 7/10/17.
 */
public class SmokeTest {
    static DesiredCapabilities sCaps;
    static WebDriver dr;
    static PageBase pb;

    @Before
    public void setUp(){
        PhantomJsDriverManager.getInstance().setup();
        sCaps = new DesiredCapabilities();
        sCaps.setJavascriptEnabled(true);
        sCaps.setCapability("takesScreenshot", true);
        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
            "--web-security=false",
            "--ssl-protocol=any",
            "--ignore-ssl-errors=true",
            "--webdriver-loglevel=INFO"
        });
        dr = new PhantomJSDriver(sCaps);
        dr.manage().window().maximize();
        pb = new PageBase(dr);
    }

    @After
    public void tearDown(){
        pb.quit();
    }

    @Test
    public void testScreen(){
       pb.openUrl("http://www.github.com");
       System.out.println("title=" + dr.getTitle());
       System.out.println("URL=" + ((HttpCommandExecutor)(((RemoteWebDriver) dr).getCommandExecutor())).getAddressOfRemoteServer());
       try {
           pb.takeSnap("target/");
       }catch (IOException e){
           fail("Snap taking err " + e.getMessage());
       }
    }
}

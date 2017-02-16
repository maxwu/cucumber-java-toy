package org.maxwu.jrefresh.selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by maxwu on 2/13/17.
 */
public class BlogCommentPage extends PageBase{
    static String baseUrl = "http://maxwu.me";
    static String blogUrl = "http://maxwu.me/2016/10/02/dropme/";
    static String urlRegEx = ".*"; //FIXME: ignore URL check on Hexo site
    static String titleRegEx = ".*"; //FIXME: ignore title check on Hexo site

    @FindBy(how = How.CSS, using = "span.dropdown-toggle-wrapper span[text='Login']")
    WebElement spanLogin;

    @FindBy(how = How.CSS, using = "span.dropdown-toggle-wrapper span.username")
    WebElement spanUsername;

    @FindBy(how = How.CSS, using = "ul.dropdown-menu > li > a[text='Disqus']")
    WebElement aDisqusLogin;

    public BlogCommentPage(WebDriver driver){
        super(driver, urlRegEx, titleRegEx);

        driver.get(blogUrl);
        checkUrl();
        checkTitle();

        PageFactory.initElements(driver, this);
    }

    public void loginDisqus(){

        spanLogin.click();
        aDisqusLogin.click();
    }
}

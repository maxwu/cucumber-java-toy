package org.maxwu.jrefresh.selenium.pageObjects;

import org.maxwu.jrefresh.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maxwu on 5/21/17.
 */
public class GithubPage extends PageBase {
    static Logger logger = LoggerFactory.getLogger(GithubPage.class.getName());
    static private String baseUrl = "https://github.com";
    static private String urlRegEx = ".*github\\..*";
    static private String titleRegEx = ".*(.*).*";

    @FindBy(how=How.CSS, using="div.d-table.col-12.width-full>div.d-table-cell.col-1>a")
    List<WebElement> aUserList;

    public List<String> getFollowingList(){
        return aUserList.stream().map(s-> s.getAttribute("href")).collect(Collectors.toList());
    }

    public List<String> getFollowingUsers(){
        return aUserList.stream().map(s-> s.getAttribute("href"))
            .map(s -> s.split("/"))
            .map(s -> s[s.length-1])
            .collect(Collectors.toList());
    }

    public GithubPage(WebDriver driver, String name) throws RuntimeException{
        super(driver, urlRegEx, titleRegEx);
        if (name == null || name.isEmpty()){
            throw new WrongPageException("profile name cannot be null or empty", driver);
        }
        get(baseUrl + "/" + name + "?tab=following");
        checkUrl();
        checkTitle();
        PageFactory.initElements(driver, this);
    }
}

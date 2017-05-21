package org.maxwu.jrefresh.selenium.pageObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by maxwu on 5/21/17.
 */
public class GithubPage extends PageBase {
    static Logger logger = LoggerFactory.getLogger(GithubPage.class.getName());
    static private String baseUrl = "https://github.com";
    static private String urlRegEx = ".*\\.google\\..*";
    static private String titleRegEx = ".*Google.*";

}

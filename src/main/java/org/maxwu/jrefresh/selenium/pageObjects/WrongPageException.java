package org.maxwu.jrefresh.selenium.pageObjects;

import org.openqa.selenium.WebDriver;

/**
 * Created by maxwu on 1/2/17.
 */
public class WrongPageException extends RuntimeException{
    //Add title and URL to track which state the FSM  throws exception
    private String title = null;
    private String url = null;

    public WrongPageException(String msg){
        super(msg);
    }

    public WrongPageException(String msg, String title, String url){
        super(msg + ", title=" + title + ", url=" + url);
        this.title = title;
        this.url = url;
    }

    public WrongPageException(String msg, WebDriver dr){
        this(msg, dr.getTitle(), dr.getCurrentUrl());
    }
}

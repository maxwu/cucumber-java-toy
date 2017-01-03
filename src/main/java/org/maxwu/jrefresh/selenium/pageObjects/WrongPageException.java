package org.maxwu.jrefresh.selenium.pageObjects;

/**
 * Created by maxwu on 1/2/17.
 */
public class WrongPageException extends RuntimeException{
    public WrongPageException(String msg){
        super(msg);
    }
}

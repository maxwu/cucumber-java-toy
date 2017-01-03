package org.maxwu.jrefresh;

import org.openqa.selenium.WebDriver;

import java.io.PrintStream;

/**
 * Created by maxwu on 1/2/17.
 */
public class ColorPrint {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //TODO: Change to Log4J.
    //TODO: Change to JSON format return.
    public static void report(WebDriver driver){
        String caller = new Exception().getStackTrace()[1].getClassName();
        System.out.println("-->>Caller:" + caller+
                "\n>>>> URL:" + driver.getCurrentUrl() +
                "\n>>>> Title:" + driver.getTitle() +
                "\n>>>> Driver:" + driver.toString()
        );

    }

    public static void println_red(PrintStream ps, String st){
        ps.println(ANSI_RED + st + ANSI_RESET);
    }
    public static void println_green(PrintStream ps, String st){
        ps.println(ANSI_GREEN + st + ANSI_RESET);
    }
    public static void println_blue(PrintStream ps, String st){
        ps.println(ANSI_BLUE + st + ANSI_RESET);
    }
}

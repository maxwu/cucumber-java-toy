package org.maxwu.jrefresh;

import org.openqa.selenium.WebDriver;

import java.io.PrintStream;
import org.json.JSONObject;
import org.json.JSONArray;

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
    private static JSONObject jsonDriverReport(WebDriver driver){
        // Here we take the 3rd object on the calling chain
        // because this method is designed private to supply another public method.
        String caller = new Exception().getStackTrace()[2].getClassName();
        /*System.out.println("-->>Caller:" + caller+
                "\n>>>> URL:" + driver.getCurrentUrl() +
                "\n>>>> Title:" + driver.getTitle() +
                "\n>>>> Driver:" + driver.toString()
        );*/
        JSONObject json = new JSONObject();
        json.append("caller", caller);
        json.append("url", driver.getCurrentUrl());
        json.append("title", driver.getTitle());

        // getClass().getName() will return full name with package path.
        json.append("driver", driver.getClass().getSimpleName());

        return json;
    }

    public static void printDriverReport(WebDriver driver){
        ColorPrint.println(jsonDriverReport(driver).toString(2), ANSI_PURPLE);
    }


    public static void println(String str, String col){
        System.out.println(col + str + ANSI_RESET);
    }

    public static void println_red(PrintStream ps, String st){
        ps.println(ANSI_RED + st + ANSI_RESET);
    }
    public static void println_red(String st){
        println_red(System.out, st);
    }
    public static void println_green(PrintStream ps, String st){
        ps.println(ANSI_GREEN + st + ANSI_RESET);
    }
    public static void println_green(String st){
        println_green(System.out, st);
    }
    public static void println_blue(PrintStream ps, String st){
        ps.println(ANSI_BLUE + st + ANSI_RESET);
    }
    public static void println_blue(String st){
        println_blue(System.out, st);
    }
}

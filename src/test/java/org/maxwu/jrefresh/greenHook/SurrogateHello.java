package org.maxwu.jrefresh.greenHook;

import org.maxwu.jrefresh.ColorPrint;

/**
 * Created by maxwu on 1/16/17.
 */
public class SurrogateHello {
    @GreenHook
    public void setUpTest(){
        ColorPrint.println_blue(">> SurrogateHello setUpTest()!");
    }

    public void tearDown(){
        ColorPrint.println_blue(">> Not a green hook in tearDown.");
    }
}

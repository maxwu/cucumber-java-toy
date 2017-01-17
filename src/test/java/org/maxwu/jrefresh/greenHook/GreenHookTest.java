package org.maxwu.jrefresh.greenHook;

import org.junit.Test;
import org.maxwu.jrefresh.ColorPrint;

import java.lang.reflect.Method;

/**
 * Created by maxwu on 1/17/17.
 */
public class GreenHookTest {

    @Test
    public void verifyGreenHookReflect(){
        ColorPrint.println_blue("GreenHook Reflect starts.");

        try {
            Class<?> ref = SurrogateHello.class;
            Method method = ref.getDeclaredMethod("setUpTest");
            Object obj = ref.newInstance();

            method.invoke(obj);

        }catch (Exception ex){
            ColorPrint.println_red(ex.toString());
        }
        System.out.println();
    }

    @Test
    public void verifyGreenHookProxy(){
        ColorPrint.println_blue("GreenHook Proxy starts.");

        SurrogateHello surrogate = new SurrogateHello();
        GreenHookProxy proxy = new GreenHookProxy();
        SurrogateHello surrogateProxy = (SurrogateHello) proxy.bind(surrogate);
        surrogateProxy.setUpTest();
        surrogateProxy.tearDown();
        System.out.println();
    }
}

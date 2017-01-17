package org.maxwu.jrefresh.greenHook;

import java.lang.annotation.*;

/**
 * Created by maxwu on 1/17/17.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GreenHook {
    public String value() default "";

}




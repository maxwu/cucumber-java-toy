package org.maxwu.jrefresh.greenHook;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.maxwu.jrefresh.ColorPrint;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by maxwu on 1/16/17.
 */
public class GreenHookProxy{
    private Object target;

    public Object bind(final Object target)
    {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor()
        {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy proxy) throws Throwable
            {
                //Check if the method has annotation @GreenHook
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation ann: annotations){
                    ColorPrint.println_green("Checking Annotation: " + ann.toString());
                    // This is how to compare annotations.
                    if (ann.annotationType().equals(GreenHook.class)){
                        ColorPrint.println_red("Interceptor found Annotation, add method to GreenHook map list: " + method.toString());
                    }
                }

                Object result = method.invoke(target, args);
                ColorPrint.println_blue("--End--of--Invoke--Proxy--");
                return result;
            }
        });
        return enhancer.create();
    }

}

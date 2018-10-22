package com.sky.data.proxy;

import com.sky.data.function.UDF;
import com.sky.data.function.CheckTimeFunction;
import com.sky.data.handler.UDFInvocationHandler;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class UDFInvocationHandlerTest {
    @Test
    public void testStandard(){
        CheckTimeFunction checkTimeFunction = new CheckTimeFunction();
        InvocationHandler handler = new UDFInvocationHandler(checkTimeFunction);
        UDF function = (UDF) Proxy.newProxyInstance(checkTimeFunction.getClass().getClassLoader(),checkTimeFunction.getClass().getInterfaces(),handler);
        System.out.println(function.evaluate("201807160000000"));
    }
}

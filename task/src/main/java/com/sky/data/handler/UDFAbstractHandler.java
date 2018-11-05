package com.sky.data.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: UDF代理类基类
 * @date : 2018/8/2 9:18
 **/
public abstract class UDFAbstractHandler implements InvocationHandler {
    /**
     * 函数实例
     */
    private Object object;

    public UDFAbstractHandler(Object o) {
        object = o;
    }

    public void startUp() {
    }

    public void endUp() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        startUp();
        Object result = method.invoke(object, args);
        endUp();
        return result;
    }
}

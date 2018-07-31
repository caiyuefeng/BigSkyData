package com.sky.data.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: 动态代理类
 * @date : 2018/7/26 15:10
 **/
public class UDFInvocationHandler implements InvocationHandler{

    /**
     * 函数实例
     */
    private Object object ;

    public UDFInvocationHandler(Object o){
        object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始调用");
        Object result =  method.invoke(object,args);
        System.out.println("结束调用");
        return result;
    }
}

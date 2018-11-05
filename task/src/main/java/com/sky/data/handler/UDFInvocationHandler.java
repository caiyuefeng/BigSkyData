package com.sky.data.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: 动态代理类
 * @date : 2018/7/26 15:10
 **/
public class UDFInvocationHandler extends UDFAbstractHandler{

    @Override
    public void startUp() {
        System.out.println("开始调用");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Map<String,String> map = (HashMap)Class.forName("java.util.HashMap").newInstance();
        System.out.println(map.put("1","1"));
        String a = "aa";
        String b=  "a"+new String("a");
        System.out.print(a==b);
        System.out.print("\t"+a.equals(b));
    }

    @Override
    public void endUp() {
        super.endUp();
    }

    public UDFInvocationHandler(Object o) {
        super(o);
    }
}

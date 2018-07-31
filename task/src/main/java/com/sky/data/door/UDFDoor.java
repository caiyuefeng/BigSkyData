package com.sky.data.door;

import com.sky.data.function.UDF;
import com.sky.data.function.CheckTimeFunction;
import com.sky.data.handler.UDFInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description:
 * @date : 2018/7/26 16:08
 **/
public class UDFDoor {

    private UDF udf ;

    public UDFDoor(UDF udf){
        this.udf = udf;
    }

    private String execute(String... parameter){
        InvocationHandler handler = new UDFInvocationHandler(udf);
        UDF baseFunction1 = (UDF) Proxy.newProxyInstance(udf.getClass().getClassLoader(),udf.getClass().getInterfaces(),handler);
        return baseFunction1.evaluate(parameter);
    }

    public static void main(String[] args) {
        UDFDoor door = new UDFDoor(new CheckTimeFunction());
        System.out.println(door.execute("20180712000000"));
    }
}

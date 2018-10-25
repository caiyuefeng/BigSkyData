package com.sky.data.door;

import com.sky.data.function.UDF;
import com.sky.data.function.CheckTimeFunction;
import com.sky.data.handler.UDFInvocationHandler;

import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: UDF门面类
 * @date : 2018/7/26 16:08
 **/
public class UDFDoor {

    private UDF udf ;

    public  UDFDoor() {

    }

    public UDFDoor(UDF udf){
        this.udf = udf;
    }

    private String execute(String... parameter){
        InvocationHandler handler = new UDFInvocationHandler(udf);
        return ((UDF) Proxy.newProxyInstance(udf.getClass().getClassLoader(),udf.getClass().getInterfaces(),handler)).evaluate(parameter);
    }

    public static class ClassA{
        public static Integer num = Integer.valueOf("0x04");
        public static Integer getNUM(){
            return ClassA.num;
        }
    }

    public static void main(String[] args) {
      int  x =-3;
      int y=10;
        System.out.println(y%x);
    }

    public static void init(Long num) {
        if(num==null){
            num = new Long(0);
        }
        num++;

    }

    public int add(int a,int b){
        try {

        }catch (Exception e){
            System.out.println(111);
        }finally {
            System.out.println(222);
        }
        return 0;
    }
}

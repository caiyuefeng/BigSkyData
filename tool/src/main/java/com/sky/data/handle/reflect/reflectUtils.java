package com.sky.data.handle.reflect;

import com.sky.bean.parse.InputBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: 蔡月峰
 * @Description: 反射工具类
 * @Date : Create in 23:02 2018/6/8
 * @Modified By:
 */
@SuppressWarnings({"unchecked", "ConfusingArgumentToVarargsMethod"})
public class reflectUtils {


    private static Object getClassMethod(Class classInstance, String methodName, String... parameter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] classes = new Class[parameter.length];
        for (int i = 0; i < parameter.length; i++) {
            classes[i] = String.class;
        }
        Method method = classInstance.getDeclaredMethod(methodName, classes);
        return method.invoke(classInstance, parameter);
    }

    public static Object getClassMethod(String className, String methodName, String... parameter) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class reflectClass = Class.forName(className);
        return getClassMethod(reflectClass, methodName, parameter);
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        InputBean fieldBean = new InputBean();
        setter(fieldBean, "name", "cyf");
        System.out.println(getter(fieldBean, "name"));
    }


    public static Object getter(Object beanObject, String field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String method = "get" + init(field);
        Method getterMethod = beanObject.getClass().getDeclaredMethod(method, null);
        return getterMethod.invoke(beanObject);
    }

    public static Object setter(Object beanObject, String field, String parameter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String method = "set" + init(field);
        Method getterMethod = beanObject.getClass().getDeclaredMethod(method, String.class);
        return getterMethod.invoke(beanObject, parameter);
    }

    private static String init(String field) {
        if (null == field || "".equals(field)) {
            return null;
        }
        int index = 0;
        if (95 == field.charAt(0)) {
            index = 1;
        }
        return field.substring(index, index + 1).toUpperCase() + field.substring(index + 1);
    }

}

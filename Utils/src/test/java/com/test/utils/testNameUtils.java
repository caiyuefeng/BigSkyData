package com.test.utils;

import com.sky.utils.NameUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 13:36 2018/3/3
 * @Modified By:
 */
public class testNameUtils {

    Class nameUtils ;
    Method loadSurnameMethod;
    @Before
    public void init() throws IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        nameUtils = Class.forName("com.sky.utils.NameUtils");
        loadSurnameMethod = nameUtils.getDeclaredMethod("loadSurname");
    }

    @Test
    public void testLoadSurname() throws InvocationTargetException, IllegalAccessException {
        loadSurnameMethod.invoke(null);
    }

}

package com.sky.data.classload;

import com.sky.data.utils.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 23:44 2018/6/7
 * @Modified By:
 */
public class ClassLoaderUtilsTest {

    /**
     * 类加载器工具类实例
     */
    private ClassLoaderUtils classLoaderUtils = ClassLoaderUtils.getInstance();

    @Test
    public void getInstance() throws Exception {
        assertNotNull(ClassLoaderUtils.getInstance());
    }

    @Test
    public void findClass() throws Exception {
        classLoaderUtils.setLibPath(ClassLoaderUtilsTest.class.getResource("/input").getPath());
        Class c = classLoaderUtils.loadClass("com.sky.data.utils.StringUtils");
        assertEquals(StringUtils.getInstance().getClass(), c);
    }

    @Test
    public void getLibPath() throws Exception {
        assertEquals("", classLoaderUtils.getLibPath());
    }

    @Test
    public void setLibPath() throws Exception {
        classLoaderUtils.setLibPath("D:\\test");
        assertEquals("D:\\test", classLoaderUtils.getLibPath());
    }
}
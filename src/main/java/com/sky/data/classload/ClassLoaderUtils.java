package com.sky.data.classload;

import org.apache.commons.lang.StringUtils;

import java.io.*;


/**
 * @Author: 蔡月峰
 * @Description: 类加载器工具类
 * @Date : Create in 23:04 2018/6/7
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public class ClassLoaderUtils extends ClassLoader {

    /**
     * 依赖包路径
     */
    private String libPath = "";

    /**
     * 静态内部类
     */
    private static class LazyUtils {
        private static final ClassLoaderUtils CLASS_LOADER_UTILS = new ClassLoaderUtils();
    }

    /**
     * 私有构造函数屏蔽新建对象
     */
    private ClassLoaderUtils() {
    }

    public static ClassLoaderUtils getInstance() {
        return LazyUtils.CLASS_LOADER_UTILS;
    }

    /**
     * 获取文件字节码
     *
     * @param file 文件
     * @return 字节数组
     * @throws IOException
     */
    private byte[] getClassByteArray(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        while ((len = fileInputStream.read()) != -1) {
            byteArrayOutputStream.write(len);
        }
        byteArrayOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        fileInputStream.close();
        return bytes;
    }

    /**
     * 获取类名
     *
     * @param classFullName
     * @return
     */
    private static String getClassName(String classFullName) {
        return StringUtils.substringBeforeLast(classFullName, ".");
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classByteArray;
        try {
            classByteArray = getClassByteArray(new File(libPath, name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return defineClass(getClassName(name), classByteArray, 0, classByteArray.length);
    }

    public String getLibPath() {
        return libPath;
    }

    public void setLibPath(String libPath) {
        this.libPath = libPath;
    }
}

package com.sky.data.utils;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 21:01 2018/2/7
 * @Modified By:
 */
public class StringUtils {

    public static StringUtils getInstance() {
        return new StringUtils();
    }

    /**
     * 私有构造函数, 防止该类被实例化
     */
    private StringUtils() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param value 待判断字符串
     * @return 判断结果标志 TRUE 为空  FALSE 不为空
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}

package com.sky.data.function;


/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: UDF 基类
 * @date : 2018/7/24 18:28
 **/
public interface UDF {
    /**
     * UDF 不定参数调用接口
     * @return 函数返回值
     */
    String evaluate(String... parameter);

}

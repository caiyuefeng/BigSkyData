package com.sky.data.function;


/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: UDF 基类
 * @date : 2018/7/24 18:28
 **/
public abstract class BaseUDFFunction {

    /**
     * UDF 单参数调用接口
     * @return 函数返回值
     */
    String evaluate(String parameter){ return "";}

    /**
     * UDF 双参数调用接口
     * @return 函数返回值
     */
    String evaluate(String para1,String para2){ return "";}

    /**
     * UDF 三参数调用接口
     * @return 函数返回值
     */
    String evaluate(String para1,String para2,String para3){ return "";}

    /**
     * UDF 四参数调用接口
     * @return 函数返回值
     */
    String evaluate(String para1,String para2,String para3,String para4){ return "";}

    /**
     * UDF 不定参数调用接口
     * @return 函数返回值
     */
    String evaluate(String... parameter){ return "";}

}

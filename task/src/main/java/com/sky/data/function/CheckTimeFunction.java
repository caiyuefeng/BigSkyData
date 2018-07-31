package com.sky.data.function;


import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: 检查时间格式是否正确
 * 1、检查格式是否为yyyyMMddHHmmss
 * 2、检查时间是否在1970010108000之后
 * @date : 2018/7/24 18:28
 **/
public class CheckTimeFunction implements UDF {
    /**
     *  格式化实例
     */
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public String evaluate(String... time) {

        if(time==null || time.length!=1|| StringUtils.isEmpty(time[0]) || time[0].length()!=14){
            return "0";
        }

        try {
            format.setLenient(false);
            Date date = format.parse(time[0]);
            if(date.getTime()/1000>=0){
                return "1";
            }
        } catch (ParseException e) {
            return "0";
        }
        return "0";
    }
}

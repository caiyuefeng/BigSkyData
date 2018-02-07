package com.sky.data.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date: Create in 11:03 2017/12/30
 */
public class ExcelUtils {

    /**
     * 日志实例
     */
    private final static Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 解析数据时，该数据的Excel表格配置信息
     * 该路径为当调用构建函数(getInstance)时未传入参数时的默认路径
     * 若传入配置文件路径则以传入为准
     */
    private static final String EXCEL_XML_PATH = "./conf/Excel.xml";

    /**
     * 私有构造函数，保证该类不会被新建对象
     */
    private ExcelUtils(){}

    /**
     * 获取实例函数,不带有配置文件路径
     */
    private static void getInstance(){

        File xmlFile = new File(EXCEL_XML_PATH);

        if(xmlFile.exists() && xmlFile.isFile()){

        }
    }

    /**
     * 获取实例函数,带有配置文件路径
     * @param xmlPath 配置文件路径
     */
    private static void getInstance(String xmlPath){

    }



    public static void parse(String jsonData){



    }





}

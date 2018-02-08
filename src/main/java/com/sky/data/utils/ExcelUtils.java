package com.sky.data.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

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
     * 工作簿实例
     * 一次解析只会存在一个工作簿实例
     */
    private static final HSSFWorkbook WORKBOOK = new HSSFWorkbook();


    /**
     * 私有构造函数，保证该类不会被新建对象
     */
    private ExcelUtils() {
    }

    /**
     * 获取实例函数,不带有配置文件路径
     */
    private static void getInstance() {

        File xmlFile = new File(EXCEL_XML_PATH);

        if (xmlFile.exists() && xmlFile.isFile()) {

        }
    }

    /**
     * 获取实例函数,带有配置文件路径
     *
     * @param xmlPath 配置文件路径
     */
    private static void getInstance(String xmlPath) {

    }

    /**
     * 获取工作表实例
     * 该函数不带参数默认已 sheet为前缀 当前表序号为后缀最为默认表名
     *
     * @return HSSFSheet 工作表实例
     */
    public HSSFSheet getSheet() {
        return WORKBOOK.getSheet("sheet1");
    }

    /**
     * 获取工作表实例
     * 传入一个表名参数
     *
     * @param sheetName 表名
     * @return HSSFSheet 工作表实例
     */
    public HSSFSheet getSheet(String sheetName) {
        return WORKBOOK.getSheet(sheetName);
    }

    public static void createWorkBook() {


    }



    public static void parse(String jsonData) {
        Map<String,String> jsonMap = GsonUtils.parseMap(jsonData);

        for(Map.Entry<String,String> entry : jsonMap.entrySet()){


        }

    }


}

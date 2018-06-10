package com.sky.data.handle.common;

import com.sky.data.handle.bean.excel.ExcelConfBean;
import com.sky.data.handle.bean.excel.FieldBean;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
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
     * Excel配置信息
     */
    private static final ExcelConfBean EXCEL_CONF_BEAN = new ExcelConfBean();

    /**
     * 工作簿实例
     * 一次解析只会存在一个工作簿实例
     */
    private static final HSSFWorkbook WORKBOOK = new HSSFWorkbook();

    /**
     * 表头所在行索引
     */
    private static final int HEAD_ROW_INDEX = 1;

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
    private static HSSFSheet getSheet() {
        return WORKBOOK.getSheet("sheet1");
    }

    /**
     * 获取工作表实例
     * 传入一个表名参数
     *
     * @param sheetName 表名
     * @return HSSFSheet 工作表实例
     */
    private static HSSFSheet getSheet(String sheetName) {
        return WORKBOOK.getSheet(sheetName);
    }

    public static void parse(String jsonData) {

        HSSFSheet sheet = getSheet();

        Map<String, String> jsonMap = GsonUtils.parseMap(jsonData);

        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

            // 输出单元格
            if (EXCEL_CONF_BEAN.getFieldBeanMap().containsKey(entry.getKey())) {
                createCell(sheet,
                        EXCEL_CONF_BEAN.getFieldBeanMap().get(entry.getKey()),
                        entry.getValue());
            }
        }
    }

    private static void createCell(HSSFSheet sheet, FieldBean fieldBean, String value) {

        int currentColIndex = 1;
        int headColIndex = 1;

        // 生成 KEY Value 所在列名
        createHead(sheet, fieldBean);

        // 生成 KEY 单元格
        if (StringUtils.isEmpty(fieldBean.getKeyOutputName())) {
            createKeyCell();
        }

        // 生成VALUE 单元格
        createValueCell();
    }

    private static void createValueCell() {
    }

    private static void createKeyCell() {
    }

    private static void createHead(HSSFSheet sheet, FieldBean fieldBean) {

        int headColIndex = 1;
        // 生成 KEY 所在列名
        if (!StringUtils.isEmpty(fieldBean.getKeyColName())) {
            int occupyCol = headColIndex;
            if (!StringUtils.isEmpty(fieldBean.getCol())) {
                occupyCol = Integer.valueOf(fieldBean.getCol());
            }

            // 生成表头
            createHead(sheet, fieldBean.getKeyColName(), occupyCol);
            headColIndex = occupyCol + 1;
            // 合并单元格
            if (!StringUtils.isEmpty(fieldBean.getKeyCols())) {
                CellRangeAddress cra = new CellRangeAddress(1, 1,
                        occupyCol, occupyCol + Integer.valueOf(fieldBean.getKeyCols()));
                sheet.addMergedRegion(cra);
                headColIndex--;
                headColIndex += Integer.valueOf(fieldBean.getKeyCols());
            }
        }

        // 生成 VALUE 所在列名
        if (!StringUtils.isEmpty(fieldBean.getValueColName())) {

            int occupyCol = headColIndex;

            if (!StringUtils.isEmpty(fieldBean.getCol())) {
                occupyCol = Integer.valueOf(fieldBean.getCol());
            }

            // 生成表头
            createHead(sheet, fieldBean.getKeyColName(), occupyCol);

            // 合并单元格
            if (!StringUtils.isEmpty(fieldBean.getKeyCols())) {
                mergeCell(sheet, 1, 1,
                        occupyCol, occupyCol + Integer.valueOf(fieldBean.getKeyCols()));
            }
        }
    }

    /**
     * 生成表头
     *
     * @param sheet    表
     * @param headName 表头名
     * @param colIndex 表头所在列索引
     */
    private static void createHead(HSSFSheet sheet, String headName, int colIndex) {
        Row row = sheet.getRow(HEAD_ROW_INDEX);
        if (row.getCell(colIndex) == null) {
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(headName);
        }
    }

    private static void mergeCell(HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol) {
        CellRangeAddress cra = new CellRangeAddress(startRow, endRow, startCol, endCol);
        sheet.addMergedRegion(cra);
    }
}

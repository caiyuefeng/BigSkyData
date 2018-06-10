package com.sky.data.bean.excel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: 蔡月峰
 * @Description: Excel 字段配置信息
 * @Date : Create in 22:06 2018/2/8
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "field")
public class FieldBean {

    /**
     * 原始数据值的 KEY
     */
    @XmlElement(name = "keyName")
    private String keyName;

    /**
     * 原始数据值的 字段KEY 所在列名
     */
    @XmlElement(name = "keyColName")
    private String keyColName;

    /**
     * 原始数据值的 字段在Excel中展示的名称
     */
    @XmlElement(name = "keyOutputName")
    private String keyOutputName;

    /**
     * 原始数据值的 字段 KEY 所占列数
     */
    @XmlElement(name = "keyCols")
    private String keyCols;

    /**
     * 原始数据值的 字段 KEY 所占行数
     */
    @XmlElement(name = "keyRows")
    private String keyRows;


    /**
     * 原始数据值的 字段VALUE 所在列名
     */
    @XmlElement(name = "valueColName")
    private String valueColName;


    /**
     * 原始数据值的 字段VALUE 所占列数
     */
    @XmlElement(name = "valueCols")
    private String valueCols;

    /**
     * 原始数据值的 字段VALUE 所占行数
     */
    @XmlElement(name = "valueRows")
    private String valueRows;


    /**
     * 固定起始列
     */
    @XmlElement(name = "col")
    private String col;

    /**
     * 固定起始行
     */
    @XmlElement(name = "row")
    private String row;

    /**
     * 该字段是否重启一行
     */
    @XmlElement(name = "isNewRow")
    private String isNewRow;

    public String getKeyName() {
        return keyName;
    }

    public String getKeyColName() {
        return keyColName;
    }

    public String getKeyOutputName() {
        return keyOutputName;
    }

    public String getKeyCols() {
        return keyCols;
    }

    public String getKeyRows() {
        return keyRows;
    }

    public String getValueColName() {
        return valueColName;
    }

    public String getValueCols() {
        return valueCols;
    }

    public String getValueRows() {
        return valueRows;
    }

    public String getCol() {
        return col;
    }

    public String getRow() {
        return row;
    }

    public String getIsNewRow() {
        return isNewRow;
    }
}

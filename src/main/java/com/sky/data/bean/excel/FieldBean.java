package com.sky.data.bean.excel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:06 2018/2/8
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "field")
public class FieldBean {

    /**
     * 原始数据值的 KEY
     */
    @XmlElement(name = "name")
    private String name;
}

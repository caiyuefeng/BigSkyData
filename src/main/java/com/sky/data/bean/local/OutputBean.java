package com.sky.data.bean.local;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 21:53 2018/2/5
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "output")
public class OutputBean {

    /**
     * 字段输出键名
     */
    @SerializedName(value = "name")
    @Expose
    @XmlElement(name = "name")
    private String name;

    /**
     * 字段值输出存储类型
     */
    @SerializedName(value = "storeType")
    @Expose
    @XmlElement(name = "storeType")
    private String storeType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
}

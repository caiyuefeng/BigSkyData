package com.sky.data.bean.parse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:19 2018/2/5
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "inputList")
public class InputListBean {

    /**
     * 字段输入信息配置实例
     */
    @SerializedName(value = "input")
    @Expose
    @XmlElement(name="input")
    private List<InputBean> inputBean;

    public List<InputBean> getInputBean() {
        return inputBean;
    }

    public void setInputBean(List<InputBean> inputBean) {
        this.inputBean = inputBean;
    }
}

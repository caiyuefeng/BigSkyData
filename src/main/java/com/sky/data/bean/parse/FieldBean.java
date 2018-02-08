package com.sky.data.bean.parse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 21:52 2018/2/5
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "field")
public class FieldBean {

    /**
     * 字段输出配置信息实例
     */
    @SerializedName(value = "output")
    @Expose
    @XmlElement(name="output")
    private OutputBean outputBean;

    /**
     * 字段输入信息配置组实例
     */
    @SerializedName(value = "inputList")
    @Expose
    @XmlElement(name="inputList")
    private InputListBean inputListBean;

    public OutputBean getOutputBean() {
        return outputBean;
    }

    public void setOutputBean(OutputBean outputBean) {
        this.outputBean = outputBean;
    }

    public InputListBean getInputListBean() {
        return inputListBean;
    }

    public void setInputListBean(InputListBean inputListBean) {
        this.inputListBean = inputListBean;
    }
}

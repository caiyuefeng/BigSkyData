package com.sky.excel.bean.local;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sky.excel.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 21:52 2018/2/5
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Json")
public class JsonConfBean {
    /**
     * 日志实例
     */
    private static final Logger LOG = LoggerFactory.getLogger(JsonConfBean.class);

    /**
     * 字段信息配置实例
     */
    @SerializedName(value = "field")
    @Expose
    @XmlElement(name = "field")
    private FieldBean fieldBean;

    /**
     * 从配置文件中获取JsonConfBean实例
     *
     * @param xmlPath 配置文件路径
     * @return JsonConfBean实例
     */
    public static JsonConfBean valueOf(String xmlPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(JsonConfBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (JsonConfBean) unmarshaller.unmarshal(new File(xmlPath));
        } catch (JAXBException e) {
            LOG.error("xml 文件解析失败 !", e);
            return null;
        }
    }

    public FieldBean getFieldBean() {
        return fieldBean;
    }

    public void setFieldBean(FieldBean fieldBean) {
        this.fieldBean = fieldBean;
    }

    @Override
    public String toString(){
        return GsonUtils.toString(this);
    }
}

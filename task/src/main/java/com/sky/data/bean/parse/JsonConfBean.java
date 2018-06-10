package com.sky.data.bean.parse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import data.utils.GsonUtils;
import data.utils.StringUtils;
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
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<FieldBean> fieldBean;

    /**
     * 输入值存储字典表
     */
    @Expose(deserialize = false,serialize = false)
    public Map<String, String> valueStoreMap = new HashMap<>();

    /**
     * 输入键输出键映射表
     */
    @Expose(deserialize = false,serialize = false)
    public Map<String, String> keyToOutputKeyMap = new HashMap<>();

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
            JsonConfBean jsonConfBean = (JsonConfBean) unmarshaller.unmarshal(new File(xmlPath));
            jsonConfBean.init();
            return jsonConfBean;
        } catch (JAXBException e) {
            LOG.error("xml 文件解析失败 !", e);
            return new JsonConfBean();
        }
    }

    /**
     * 从输入流中获取JsonConfBean实例
     *
     * @param in 配置文件输入流
     * @return JsonConfBean实例
     */
    public static JsonConfBean valueOf(InputStream in) {
        try {
            JAXBContext context = JAXBContext.newInstance(JsonConfBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JsonConfBean jsonConfBean = (JsonConfBean) unmarshaller.unmarshal(in);
            jsonConfBean.init();
            return jsonConfBean;
        } catch (JAXBException e) {
            LOG.error("xml 文件解析失败 !", e);
            return new JsonConfBean();
        }
    }

    /**
     * 初始化JsonConfBean
     */
    private void init() {

        initValueStoreMap();

        initKeyToOutputKeyMap();
    }

    /**
     * 初始化值存储字典表
     */
    private void initValueStoreMap() {
        for (FieldBean fieldBean : getFieldBean()) {
            if (!StringUtils.isEmpty(fieldBean.getOutputBean().getName())) {
                valueStoreMap.put(fieldBean.getOutputBean().getName()
                        , fieldBean.getOutputBean().getStoreType());
            }
        }
    }

    /**
     * 初始化输入键输出键映射表
     */
    private void initKeyToOutputKeyMap() {
        for (FieldBean fieldBean : getFieldBean()) {

            if (StringUtils.isEmpty(fieldBean.getOutputBean().getName())) {
                continue;
            }

            for (InputBean inputBean : fieldBean.getInputListBean().getInputBean()) {
                if (StringUtils.isEmpty(inputBean.getName())) {
                    continue;
                }

                keyToOutputKeyMap.put(inputBean.getName(), fieldBean.getOutputBean().getName());
            }
        }
    }

    public List<FieldBean> getFieldBean() {
        return fieldBean;
    }

    public void setFieldBean(List<FieldBean> fieldBean) {
        this.fieldBean = fieldBean;
    }

    @Override
    public String toString() {
        return GsonUtils.toString(this);
    }
}

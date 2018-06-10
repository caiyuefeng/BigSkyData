package com.sky.data.handle.bean.excel;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:04 2018/2/8
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "excel")
public class ExcelConfBean {
    /**
     * 日志实例
     */
    private static final Logger LOG = LoggerFactory.getLogger(ExcelConfBean.class);

    /**
     * 字段配置信息
     */
    @XmlElement(name = "field")
    private List<FieldBean> field = new ArrayList<>();

    /**
     * 字段KEY - 字段配置信息Map
     */
    private Map<String, FieldBean> fieldBeanMap = new HashMap<>();

    /**
     * 解析ExcelXml 配置文件
     *
     * @param xmlPath 配置文件路径
     * @return ExcelConfBean 实例
     */
    public static ExcelConfBean valueOf(String xmlPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ExcelConfBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ExcelConfBean excelConfBean = (ExcelConfBean) unmarshaller.unmarshal(new File(xmlPath));
            for (FieldBean fieldBean : excelConfBean.getField()) {
                excelConfBean.fieldBeanMap.put(fieldBean.getKeyName(), fieldBean);
            }
            return excelConfBean;
        } catch (JAXBException e) {
            LOG.error(xmlPath + "解析错误", e);
            return new ExcelConfBean();
        }
    }

    public List<FieldBean> getField() {
        return field;
    }

    public Map<String, FieldBean> getFieldBeanMap() {
        return fieldBeanMap;
    }
}

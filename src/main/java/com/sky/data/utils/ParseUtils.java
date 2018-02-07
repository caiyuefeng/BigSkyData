package com.sky.data.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sky.data.bean.local.JsonConfBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 21:41 2018/2/5
 * @Modified By:
 */
public class ParseUtils {

    /**
     * JSON源数据配置文件
     */
    private static final JsonConfBean JSON_CONF_BEAN = JsonConfBean.valueOf(ParseUtils.class.getClassLoader().getResourceAsStream("JsonConfiguration.xml"));

    /**
     * 目标JSON数据缓存
     */
    private static final Map<String, Object> OUTPUT_MAP = new HashMap<>();

    /**
     * 转换Json数据格式
     *
     * @param jsonData 待解析Json数据
     * @return 转换后数据
     */
    private static String parse(String jsonData) {

        JsonObject jsonObject = GsonUtils.parseJsonObject(jsonData);

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if(entry.getValue().isJsonArray()){

            }else if(entry.getValue().isJsonObject()){

            }else if(entry.getValue().isJsonNull()){

            }else{

            }
        }

        return "";
    }
}

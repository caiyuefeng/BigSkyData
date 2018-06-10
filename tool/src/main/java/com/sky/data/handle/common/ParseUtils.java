package com.sky.data.handle.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sky.bean.parse.JsonConfBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 蔡月峰
 * @Description: JSON数据格式转换工具类
 * 使用方法:
 * <p>
 * 复杂格式转换 : ParseUtils.parse( ${jsonData} , 1 );
 * 如 JSON 数据  {"car":{"code":"奥迪","color":"黑色"},"phone":{"code":"华为","color":"白色"}}
 * <p>
 * 简单格式转换 : ParseUtils.parse( ${jsonData} ) 或 ParseUtils.parse( ${jsonData} , 0 );;
 * 如 JSON 数据 {"name":"蔡月峰","age":"23"}
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
     * 复杂JSON标志
     */
    private static final String COMPLEX_ANALYSIS_FLAG = "1";

    /**
     * 输入的JSON分析标志
     */
    private static String INPUT_ANALYSIS_FLAG = "0";

    public static void main(String[] args) {
        System.out.println(ParseUtils.parse("{\"car\":{\"code\":\"奥迪\",\"color\":\"黑色\"},\"phone\":{\"code\":\"华为\",\"color\":\"白色\"}}", "1"));
    }

    /**
     * 私有构造函数, 防止该类被实例化
     */
    private ParseUtils() {
    }

    /**
     * 转换Json数据格式
     *
     * @param jsonData 待解析Json数据
     * @return 转换后数据
     */
    public static String parse(String jsonData, String... items) {

        for (String item : items) {
            INPUT_ANALYSIS_FLAG = item;
            break;
        }

        JsonObject jsonObject = GsonUtils.parseJsonObject(jsonData);

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {

            if (entry.getValue().isJsonNull()) {
                continue;
            }

            if (entry.getValue().isJsonArray()) {
                analysisArray(entry.getKey(), entry.getValue().getAsJsonArray());
            } else if (entry.getValue().isJsonObject()) {
                if (INPUT_ANALYSIS_FLAG.equals(COMPLEX_ANALYSIS_FLAG)) {
                    analysisObject(entry.getKey(), entry.getValue().getAsJsonObject());
                } else {
                    analysisObject(entry.getValue().getAsJsonObject());
                }
            } else {
                putValueToMap(entry.getKey(), entry.getValue().getAsString());
            }
        }

        return GsonUtils.toString(OUTPUT_MAP);
    }

    /**
     * 将键值塞入输出缓存
     *
     * @param key   原始数据KEY
     * @param value 原始数据VALUE
     */
    private static void putValueToMap(String key, String value) {

        String outputKey = JSON_CONF_BEAN.keyToOutputKeyMap.get(key);

        if (!StringUtils.isEmpty(outputKey) &&
                JSON_CONF_BEAN.valueStoreMap.containsKey(outputKey)) {

            switch (JSON_CONF_BEAN.valueStoreMap.get(outputKey).toUpperCase()) {
                case "STRING":
                    OUTPUT_MAP.put(outputKey, value);
                    break;
                case "ARRAY":
                    List<String> valueList = new ArrayList<>();
                    if (OUTPUT_MAP.containsKey(outputKey)) {
                        valueList.addAll((ArrayList<String>) OUTPUT_MAP.get(outputKey));
                    }
                    valueList.add(value);
                    OUTPUT_MAP.put(outputKey, valueList);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 解析数组
     *
     * @param key       来源数据KEY
     * @param jsonArray JSON数组
     */
    private static void analysisArray(String key, JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {

            if (jsonElement.isJsonNull()) {
                continue;
            }

            if (jsonElement.isJsonObject()) {
                if (INPUT_ANALYSIS_FLAG.equals(COMPLEX_ANALYSIS_FLAG)) {
                    analysisObject(key, jsonElement.getAsJsonObject());
                } else {
                    analysisObject(jsonElement.getAsJsonObject());
                }
            } else if (jsonElement.isJsonArray()) {
                analysisArray(key, jsonElement.getAsJsonArray());
            } else {
                putValueToMap(key, jsonElement.getAsString());
            }
        }
    }

    /**
     * 解析复杂JSON对象
     *
     * @param key        原始数据KEY前缀
     * @param jsonObject 待解析JSON对象
     */
    private static void analysisObject(String key, JsonObject jsonObject) {

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue().isJsonNull()) {
                continue;
            }

            if (entry.getValue().isJsonArray()) {
                analysisArray(createKey(key, entry.getKey()),
                        entry.getValue().getAsJsonArray());
                continue;
            }

            if (entry.getValue().isJsonObject()) {
                analysisObject(createKey(key, entry.getKey()),
                        entry.getValue().getAsJsonObject());
                continue;
            }

            putValueToMap(createKey(key, entry.getKey()),
                    entry.getValue().getAsString());

        }
    }

    /**
     * 分析简单JSON对象
     *
     * @param jsonObject JSON对象
     */
    private static void analysisObject(JsonObject jsonObject) {

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue().isJsonNull()) {
                continue;
            }

            if (entry.getValue().isJsonArray()) {
                analysisArray(entry.getKey(), entry.getValue().getAsJsonArray());
                continue;
            }

            if (entry.getValue().isJsonObject()) {
                analysisObject(entry.getValue().getAsJsonObject());
                continue;
            }

            putValueToMap(entry.getKey(), entry.getValue().getAsString());
        }
    }

    /**
     * 生成复杂JSON数据的输入KEY
     *
     * @param prefix KEY前缀
     * @param key    当前KEY
     * @return 复杂JSON数据KEY
     */
    private static String createKey(String prefix, String key) {
        return prefix + "." + key;
    }
}

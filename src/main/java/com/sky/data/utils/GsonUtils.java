package com.sky.data.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date: Create in 21:22 2018/2/5
 */
public class GsonUtils {
    /**
     * Gson对象实例
     */
    private final static Gson GSON = new GsonBuilder().enableComplexMapKeySerialization().create();

    /**
     * 将Json数据解析成Map
     *
     * @param value 带解析数据
     * @return 解析后的Map
     */
    public static Map<String, String> parseMap(String value) {
        return GSON.fromJson(value, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    /**
     * 将Json数据反序列化为JsonObject
     *
     * @param value 待反序列化的Json数据
     * @return JsonObject实例
     */
    public static JsonObject parseJsonObject(String value) {
        return GSON.fromJson(value, JsonObject.class);
    }

    /**
     * 解析Json数据反序列化为 Java Bean
     *
     * @param value 带解析Json
     * @param type  Java Bean 类型
     * @param <T>   Java Bean 类型
     * @return Java Bean 实例
     */
    public static <T> T parse(String value, Type type) {
        return GSON.fromJson(value, type);
    }

    /**
     * 将对Java Bean 序列化为字符串
     *
     * @param object 待序列号对象
     * @return 序列化后的字符串
     */
    public static String toString(Object object) {
        return GSON.toJson(object);
    }
}

package com.resource.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtil {

    // 单例模式：使用线程安全的方式创建 Gson 实例
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting() // 格式化输出 JSON
            .serializeNulls()    // 序列化 null 值
            .create();

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 要转换的对象
     * @return JSON 字符串
     */
    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json JSON 字符串
     * @param clazz 目标类类型
     * @param <T>   泛型类型
     * @return 转换后的对象
     * @throws JsonSyntaxException 如果 JSON 字符串格式不正确
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonSyntaxException {
        return GSON.fromJson(json, clazz);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象（带泛型）
     *
     * @param json JSON 字符串
     * @param type TypeToken 对象，用于处理泛型
     * @param <T>  泛型类型
     * @return 转换后的对象
     * @throws JsonSyntaxException 如果 JSON 字符串格式不正确
     */
    public static <T> T fromJson(String json, Type type) throws JsonSyntaxException {
        return GSON.fromJson(json, type);
    }

    /**
     * 将对象转换为 Map
     *
     * @param obj 要转换的对象
     * @return 转换后的 Map
     */
    public static Map<String, String> toMap(Object obj) {
        return toMap(obj, String.class, String.class);
    }

    // 泛型方法，但不直接使用 TypeToken
    public static <K, V> Map<K, V> toMap(Object obj, Class<K> keyClass, Class<V> valueClass) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);

        // 使用具体类型构建 TypeToken
        Type type = TypeToken.getParameterized(Map.class, keyClass, valueClass).getType();
        return gson.fromJson(jsonString, type);
    }

    /**
     * 将 JSON 字符串转换为 Map
     *
     * @param json JSON 字符串
     * @param <K>  Map 的键类型
     * @param <V>  Map 的值类型
     * @return 转换后的 Map
     * @throws JsonSyntaxException 如果 JSON 字符串格式不正确
     */
    public static <K, V> Map<K, V> jsonToMap(String json) throws JsonSyntaxException {
        Type type = new TypeToken<Map<K, V>>() {}.getType();
        return GSON.fromJson(json, type);
    }

    /**
     * 将 JSON 字符串转换为 JsonElement 对象
     *
     * @param json JSON 字符串
     * @return JsonElement 对象
     * @throws JsonSyntaxException 如果 JSON 字符串格式不正确
     */
    public static JsonElement parseJson(String json) throws JsonSyntaxException {
        return GSON.fromJson(json, JsonElement.class);
    }

    /**
     * 检查 JSON 字符串是否有效
     *
     * @param json JSON 字符串
     * @return 是否有效
     */
    public static boolean isValidJson(String json) {
        try {
            GSON.fromJson(json, Object.class);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
}

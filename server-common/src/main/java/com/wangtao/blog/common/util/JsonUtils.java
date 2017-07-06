package com.wangtao.blog.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:JsonUtils
 * @author: KarlWang
 * @Description: TODO(JSON工具类) 
 * @date:2017年7月5日 下午4:41:37
 * @see com.wangtao.blog.common.util.JsonUtils
 */
public class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static{
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtils.DateTimeFormat.LONG_DATE_PATTERN_LINE.name())); // 日期格式化yyyy-MM-dd HH:mm:ss
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // JSON属性与JavaBean不匹配的时候报错

    }

    /**
     * 获取实例
     * @return
     */
    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * Java对象转Xml字符串（序列化）
     * @param object
     * @return
     */
    public static String beanToJson(Object object) {
        String json;
        try {
            json = objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return json;
    }

    /**
     * Json字符串转Java对象（反序列化）
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Json字符串转Map（反序列化）
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * Json字符串转List（反序列化）
     * @param jsonArrayStr
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz) {
        List<Map<String, Object>> list;
        try {
            list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        List<T> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            result.add(mapToBean(map, clazz));
        }
        return result;
    }

    /**
     * Json字符串转List<bean>（反序列化）
     * @param jsonArrayStr
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToListBean(String jsonArrayStr, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,clazz);
        try {
            return objectMapper.readValue(jsonArrayStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * Map转JavaBean
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<?, ?> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 判断字符串是否是JSON
     * @param json
     * @return
     */
    public static boolean isGoodJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try{
            objectMapper.readValue(json, Object.class);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
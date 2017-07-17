package com.thdjson;

import java.util.List;
import java.util.Map;

/**
 * Created by ThdLee on 2017/7/17.
 */
public class JSON {

    private JSON() {}

   /* JSON Deserializer */

    public static <T> T parseObject(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToObject(json, clazz);
    }

    public static <T> T parseObject(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToObject(json, clazz);
    }

    public static <T> Map<String, T> parseObjectToMap(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToMap(json, clazz);
    }

    public static <T> Map<String, T> parseObjectToMap(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToMap(json, clazz);
    }

    public static Map<String, Object> parseObjectToMap(String json, Class<?>[] clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToMap(json, clazz);
    }

    public static Map<String, Object> parseObjectToMap(String json, Class<?>[] clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToMap(json, clazz);
    }

    public static <T> T parseArray(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToArray(json, clazz);
    }

    public static <T> T parseArray(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToArray(json, clazz);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToList(json, clazz);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToList(json, clazz);
    }

    /* JSON Serializer */

    public static String toJSONString(Object object) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeObjectToString(object);
    }

    public static String toJSONString(Object object, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeObjectToString(object);
    }

    public static String toJSONString(Map map) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeMapToString(map);
    }

    public static String toJSONString(Map map, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeMapToString(map);
    }

    public static String toJSONString(Object[] array) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeArrayToString(array);
    }

    public static String toJSONString(Object[] array, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeArrayToString(array);
    }

    public static String toJSONString(List list) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeListToString(list);
    }

    public static String toJSONString(List list, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeListToString(list);
    }

}

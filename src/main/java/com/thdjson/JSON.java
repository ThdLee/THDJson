package com.thdjson;

import com.thdjson.entity.JSONArray;
import com.thdjson.entity.JSONObject;

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

    public static JSONObject parseObject(String json) {
        JSONParser parser = new JSONParser();
        return parser.parseObject(json);
    }

    public static JSONObject parseObject(String json, boolean caseInsensitive) {
        JSONParser parser = new JSONParser(caseInsensitive);
        return parser.parseObject(json);
    }

    public static JSONArray parseArray(String json) {
        JSONParser parser = new JSONParser();
        return parser.parseArray(json);
    }

    public static JSONArray parseArray(String json, boolean caseInsensitive) {
        JSONParser parser = new JSONParser(caseInsensitive);
        return parser.parseArray(json);
    }

    /* JSON Serializer */

    public static String toJSONString(Object object) {
        JSONSerializer serializer = new JSONSerializer();
        if (object instanceof Map) {
            return serializer.serializeMapToString((Map) object);
        } else if (object instanceof List) {
            return serializer.serializeListToString((List) object);
        } else if (object.getClass().isArray()) {
            return serializer.serializeArrayToString(object);
        }
        return serializer.serializeObjectToString(object);
    }

    public static String toJSONString(Object object, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        if (object instanceof Map) {
            return serializer.serializeMapToString((Map) object);
        } else if (object instanceof List) {
            return serializer.serializeListToString((List) object);
        } else if (object.getClass().isArray()) {
            return serializer.serializeArrayToString(object);
        }
        return serializer.serializeObjectToString(object);
    }

    public static JSONObject toJSONObject(Object object) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeObject(object);
    }

    public static JSONObject toJSONObject(Object object, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeObject(object);
    }

    public static JSONObject toJSONObject(Map map) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeMap(map);
    }

    public static JSONObject toJSONObject(Map map, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeMap(map);
    }

    public static JSONArray toJSONArray(Object[] objects) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeArray(objects);
    }

    public static JSONArray toJSONArray(Object[] objects, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeArray(objects);
    }

    public static JSONArray toJSONArray(List list) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeList(list);
    }

    public static JSONArray toJSONArray(List list, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeList(list);
    }

}

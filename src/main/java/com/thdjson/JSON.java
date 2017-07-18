package com.thdjson;

import com.thdjson.entity.JSONArray;
import com.thdjson.entity.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * This is the main class for using THDJson. You can call {@link #parseArray(String, Class)} to
 * deserialize json string, and call {@link #toJSONString(Object)} to serialize object.
 *
 * @author ThdLee
 */
public class JSON {

    private JSON() {}

    /* JSON Deserializer */

    /**
     * This method deserializes specified Json string into an Object with specified type
     * and default features. You can specify features of {@link JSONDeserializerFeature}
     * in {@link #parseObject(String, Class, JSONDeserializerFeature...)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired object
     * @return an object of T from the string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToObject(json, clazz);
    }

    /**
     * This method deserializes specified Json string into an Object with specified type
     * and specified features. You can use default features in {@link #parseObject(String, Class)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired object
     * @param features features of {@link JSONDeserializerFeature} in deserializer
     * @return an object of T from the string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T parseObject(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToObject(json, clazz);
    }

    /**
     * This method deserializes specified Json string into a Map with specified value type
     * and default features.  You can specify features of {@link JSONDeserializerFeature} in
     * {@link #parseObjectToMap(String, Class, JSONDeserializerFeature...)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired object
     * @return a map of specified value type T. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> Map<String, T> parseObjectToMap(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToMap(json, clazz);
    }

    /**
     * This method deserializes specified Json string into a Map with specified value type
     * and specified features. You can use default features in {@link #parseObjectToMap(String, Class)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired object
     * @param features features of {@link JSONDeserializerFeature} in deserializer
     * @return a map of specified value type T. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> Map<String, T> parseObjectToMap(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToMap(json, clazz);
    }

    /**
     * This method deserializes specified Json string into a HashMap with specified value types
     * array and default features. You can specify features of {@link JSONDeserializerFeature} in
     * {@link #parseObjectToMap(String, Class[], JSONDeserializerFeature...)}
     * @param json the string with json format
     * @param clazz the types array of value in map
     * @return a map of specified value types array. Returns {@code null} if {@code json} is {@code null}.
     */
    public static Map<String, Object> parseObjectToMap(String json, Class<?>[] clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToMap(json, clazz);
    }

    /**
     * This method deserializes specified Json string into a Map with specified value types array
     * and specified features. You can use default features in {@link #parseObjectToMap(String, Class[])}
     * @param json the string with json format
     * @param clazz the types array of value in map
     * @param features features of {@link JSONDeserializerFeature} in deserializer
     * @return a map of specified value types array. Returns {@code null} if {@code json} is {@code null}.
     */
    public static Map<String, Object> parseObjectToMap(String json, Class<?>[] clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToMap(json, clazz);
    }

    /**
     * This method deserializes specified Json string into an Array with specified array type
     * and default features. You can specify features of {@link JSONDeserializerFeature}
     * in {@link #parseArray(String, Class, JSONDeserializerFeature...)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired array
     * @return an array of T type from the string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T parseArray(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToArray(json, clazz);
    }

    /**
     * This method deserializes specified Json string into an Array with specified array type
     * and specified features. You can use default features in {@link #parseArray(String, Class)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired array
     * @param features features of {@link JSONDeserializerFeature} in deserializer
     * @return an array of T type from the string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T parseArray(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToArray(json, clazz);
    }

    /**
     * This method deserializes specified Json string into a List with specified type
     * and default features. You can specify features of {@link JSONDeserializerFeature}
     * in {@link #parseList(String, Class, JSONDeserializerFeature...)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired element
     * @return a list which element is T type from the string.
     * Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        JSONDeserializer deserializer = new JSONDeserializer();
        return deserializer.deserializeToList(json, clazz);
    }

    /**
     * This method deserializes specified Json string into a List with specified type
     * and specified features. You can use default features in {@link #parseList(String, Class)}
     * @param json the string with json format
     * @param clazz the type of T
     * @param <T> the type of the desired element
     * @param features features of {@link JSONDeserializerFeature} in deserializer
     * @return a list which element is T type from the string.
     * Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> List<T> parseList(String json, Class<T> clazz, JSONDeserializerFeature... features) {
        JSONDeserializer deserializer = new JSONDeserializer(features);
        return deserializer.deserializeToList(json, clazz);
    }

    /**
     * This method deserializes specified Json string into {@link JSONObject}. You can get
     * value from JSONObject by keys. Keys are case sensitive, you can specify this option in
     * {@link #parseObject(String, boolean)}.
     * @param json the string with json format
     * @return a JSONObject from string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static JSONObject parseObject(String json) {
        JSONParser parser = new JSONParser();
        return parser.parseObject(json);
    }

    /**
     * This method deserializes specified Json string into {@link JSONObject}. You can get
     * value from JSONObject by keys. You can specify whether keys in JSONObject are case
     * insensitive or not.
     * @param json the string with json format
     * @param caseInsensitive if {@code true}, keys in JSONObject are case insensitive
     *                        if (@code false}, keys in JSONObject are case sensitive
     * @return a JSONObject from string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static JSONObject parseObject(String json, boolean caseInsensitive) {
        JSONParser parser = new JSONParser(caseInsensitive);
        return parser.parseObject(json);
    }

    /**
     * This method deserializes specified Json string into {@link JSONArray}. You can get
     * value from JSONArray by index. If there's at least one JSONObject in JSONArray,
     * Keys in JSONObject are case sensitive, you can specify this option in
     * {@link #parseArray(String, boolean)}.
     * @param json the string with json format
     * @return a JSONArray from string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static JSONArray parseArray(String json) {
        JSONParser parser = new JSONParser();
        return parser.parseArray(json);
    }

    /**
     * This method deserializes specified Json string into {@link JSONObject}. You can get
     * value from JSONObject by index. If there's at least one JSONObject in JSONArray,
     * you can specify whether keys in JSONObject are case insensitive or not.
     * @param json the string with json format
     * @param caseInsensitive if {@code true}, keys in JSONObject are case insensitive
     *                        if (@code false}, keys in JSONObject are case sensitive
     * @return a JSONArray from string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static JSONArray parseArray(String json, boolean caseInsensitive) {
        JSONParser parser = new JSONParser(caseInsensitive);
        return parser.parseArray(json);
    }

    /* JSON Serializer */

    /**
     * This method serializes specified Object into json string with default features. You can specify
     * features of {@link JSONSerializerFeature} in {@link #toJSONString(Object, JSONSerializerFeature...)}
     * @param object the object is to be serialized
     * @return string with json format. Returns {@code null} if {@code object} is {@code null}.
     */
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

    /**
     * This method serializes specified Object into json string with specified features. You can use
     * default features in {@link #toJSONObject(Object)}
     * @param object the object is to be serialized
     * @param features features of {@link JSONSerializerFeature} in serializer
     * @return string with json format. Returns {@code null} if {@code object} is {@code null}.
     */
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

    /**
     * This method serializes specified Object into {@link JSONObject} with default features. You can specify
     * features of {@link JSONSerializerFeature} in {@link #toJSONObject(Object, JSONSerializerFeature...)}
     * @param object the object is to be serialized
     * @return a JSONObject from object. Returns {@code null} if {@code object} is {@code null}.
     */
    public static JSONObject toJSONObject(Object object) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeObject(object);
    }

    /**
     * This method serializes specified Object into {@link JSONObject} with specified features. You can use
     * default features in {@link #toJSONObject(Object)}
     * @param object the object is to be serialized
     * @param features features of {@link JSONSerializerFeature} in serializer
     * @return a JSONObject from object. Returns {@code null} if {@code object} is {@code null}.
     */
    public static JSONObject toJSONObject(Object object, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeObject(object);
    }

    /**
     * This method serializes specified Map into {@link JSONObject} with default features. You can specify
     * features of {@link JSONSerializerFeature} in {@link #toJSONObject(Map, JSONSerializerFeature...)}
     * @param map the map is to be serialized
     * @return a JSONObject from map. Returns {@code null} if {@code map} is {@code null}.
     */
    public static JSONObject toJSONObject(Map map) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeMap(map);
    }

    /**
     * This method serializes specified Map into {@link JSONObject} with specified features. You can use
     * default features in {@link #toJSONObject(Map)}
     * @param map the map is to be serialized
     * @param features features of {@link JSONSerializerFeature} in serializer
     * @return a JSONObject from map. Returns {@code null} if {@code map} is {@code null}.
     */
    public static JSONObject toJSONObject(Map map, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeMap(map);
    }

    /**
     * This method serializes specified Array into {@link JSONArray} with default features. You can specify
     * features of {@link JSONSerializerFeature} in {@link #toJSONArray(Object[], JSONSerializerFeature...)}
     * @param objects the object array is to be serialized
     * @return a JSONArray from array. Returns {@code null} if {@code objects} is {@code null}.
     */
    public static JSONArray toJSONArray(Object[] objects) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeArray(objects);
    }

    /**
     * This method serializes specified Array into {@link JSONArray} with specified features. You can use
     * default features in {@link #toJSONArray(Object[])}
     * @param objects the map is to be serialized
     * @param features features of {@link JSONSerializerFeature} in serializer
     * @return a JSONArray from array. Returns {@code null} if {@code objects} is {@code null}.
     */
    public static JSONArray toJSONArray(Object[] objects, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeArray(objects);
    }

    /**
     * This method serializes specified List into {@link JSONArray} with default features. You can specify
     * features of {@link JSONSerializerFeature} in {@link #toJSONArray(List, JSONSerializerFeature...)}
     * @param list all the elements in the list are to be serialized
     * @return a JSONArray from list. Returns {@code null} if {@code list} is {@code null}.
     */
    public static JSONArray toJSONArray(List list) {
        JSONSerializer serializer = new JSONSerializer();
        return serializer.serializeList(list);
    }

    /**
     * This method serializes specified List into {@link JSONArray} with specified features. You can use
     * default features in {@link #toJSONArray(List)}
     * @param list all the elements in the list are to be serialized
     * @param features features of {@link JSONSerializerFeature} in serializer
     * @return a JSONArray from list. Returns {@code null} if {@code list} is {@code null}.
     */
    public static JSONArray toJSONArray(List list, JSONSerializerFeature... features) {
        JSONSerializer serializer = new JSONSerializer(features);
        return serializer.serializeList(list);
    }

}

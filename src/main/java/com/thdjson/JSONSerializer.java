package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONSerializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Created by Theodore on 2017/7/13.
 */
public class JSONSerializer {
    
    private int features;
    /**
     * Initializes an Json Serializer.
     * Default {@code features} is 0;
     */
    public JSONSerializer() {
        features = 0;
    }

    /**
     * Initializes an Json Serializer with the specified features.
     */
    public JSONSerializer(JSONSerializerFeature... features) {
        for (JSONSerializerFeature feature : features) {
            this.features |= feature.getMask();
        }
    }

    /**
     * Convert object instance to string with json format.
     * @param obj this object will be converted to string
     * @return string with json format
     */
    public String serializeObjectToString(Object obj) {
        return serializeObject(obj).toString();
    }

    /**
     * Convert object instance to JSONObject.
     * @param obj this object will be converted to JsonFormat
     * @return JSONObject instance
     */
    public JSONObject serializeObject(Object obj) {
        JSONObject jsonObject = new JSONObject();
        JSONValue value = null;
        Field[] fields = null;
        try {
            for (Class<?> cla = obj.getClass(); cla != Object.class; cla = cla.getSuperclass()) {
                fields = cla.getDeclaredFields();
                for (Field field : fields) {
                    if (inFlags(JSONSerializerFeature.ONLY_PUBLIC) &&
                            (field.getModifiers() & Modifier.PUBLIC) == 0)
                        continue;

                    field.setAccessible(true);
                    String name = inFlags(JSONSerializerFeature.CASE_INSENSITIVE) ?
                            field.getName().toLowerCase() :
                            field.getName();

                    value = serializeValue(field.get(obj), field.getType());
                    jsonObject.addKeyAndValue(name, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new JSONSerializerException(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * Convert object instance to string with json format.
     * @param map this map will be converted to string
     * @return string with json format
     */
    public String serializeMapToString(Map map) {
        return serializeMap(map).toString();
    }

    /**
     * Convert object instance to JSONObject.
     * @param map this map will be converted to JsonFormat
     * @return JSONObject instance
     */
    public JSONObject serializeMap(Map map) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (Object key : map.keySet()) {
                Object val = map.get(key);
                JSONValue jsonValue = serializeValue(val, val.getClass());
                String k = inFlags(JSONSerializerFeature.CASE_INSENSITIVE) ?
                        key.toString().toLowerCase() :
                        key.toString();
                jsonObject.addKeyAndValue(k, jsonValue);
            }
        } catch (IllegalAccessException e) {
            throw new JSONSerializerException(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * Convert array to string with json format.
     * @param array this array will be converted to string
     * @return string with json format
     */
    public String serializeArrayToString(Object array) {
        return serializeArray(array).toString();
    }

    /**
     * Convert array to JSONArray.
     * @param array this array will be converted to JsonFormat
     * @return JSONArray instance
     */
    public JSONArray serializeArray(Object array) {
        if (!array.getClass().isArray()) throw new JSONSerializerException("wrong type: " + array.getClass());
        JSONArray jsonArray = new JSONArray();
        int len = Array.getLength(array);
        try {
            for (int i = 0; i < len; i++) {
                Object o = Array.get(array, i);
                jsonArray.addValue(serializeValue(o, array.getClass().getComponentType()));
            }
        } catch (IllegalAccessException e) {
            throw new JSONSerializerException(e.getMessage());
        }
        return jsonArray;
    }

    /**
     * Convert elements of list to string with json format.
     * @param list elements in this list will be converted to string
     * @return string with json format
     */
    public String serializeListToString(List list) {
        return serializeList(list).toString();
    }

    /**
     * Convert elements of list to JSONArray.
     * @param list elements in this list will be converted to JSONArray
     * @return JSONArray instance
     */
    public JSONArray serializeList(List list) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Object obj : list) {
                jsonArray.addValue(serializeValue(obj, obj.getClass()));
            }
        } catch (IllegalAccessException e) {
            throw new JSONSerializerException(e.getMessage());
        }
        return jsonArray;
    }

    private <T> JSONValue serializeValue(T obj, Class<?> clazz) throws IllegalAccessException {
        JSONValue JSONValue = null;
        if (obj != null) {

            if (clazz.isPrimitive() || Number.class.isAssignableFrom(clazz)) {

                if (clazz == float.class || clazz == Float.class ||
                        clazz == double.class || clazz == Double.class) {
                    JSONValue = new JSONElement(obj.toString(), JSONValueType.FLOAT);
                } else {
                    JSONValue = new JSONElement(obj.toString(), JSONValueType.INT);
                }

            } else if (clazz == boolean.class || clazz == Boolean.class) {

                JSONValue = new JSONElement(obj.toString(), JSONValueType.BOOL);

            } else if (clazz == char.class || clazz == Character.class || clazz == String.class) {

                JSONValue = new JSONElement(obj.toString(), JSONValueType.STRING);

            } else if (clazz.isArray()) {

                JSONValue = serializeArray(obj);

            } else {

                JSONValue = serializeObject(obj);

            }
        } else {

            JSONValue = new JSONElement(null, JSONValueType.NULL);
            
        }

        return JSONValue;
    }

    private boolean inFlags(JSONSerializerFeature feature) {
        return (features & feature.getMask()) == feature.getMask();
    }
}

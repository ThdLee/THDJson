package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONSerializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Theodore on 2017/7/13.
 */
public class JSONSerializer {

    /* Serialize fields with case insensitive */
    private boolean isCaseInsensitive;

    /* Only Serialize public fields of object */
    private boolean isOnlyPublic;

    /**
     * Initializes an Json Serializer.
     */
    public JSONSerializer() {
        isCaseInsensitive = true;
        isOnlyPublic = true;
    }

    /**
     * Initializes an Json Serializer with the specified flags.
     * @param caseInsensitive {@code true} serialize fields with case insensitive
     *                        {@code false} otherwise
     * @param onlyPublic {@code true} only serialize public fields of object
     *                   {@code false} serialize all kinds of fields of object
     */
    public JSONSerializer(boolean caseInsensitive, boolean onlyPublic) {
        isCaseInsensitive = caseInsensitive;
        isOnlyPublic = onlyPublic;
    }

    /**
     * Convert object instance to json string.
     * @param obj this object will be converted to json string
     * @param clazz the class of obj
     * @return string with json format
     */
    public <T> String serializeToString(T obj, Class<T> clazz) {
        return serialize(obj, clazz).toString();
    }

    /**
     * Convert object instance to JsonFormat.
     * @param obj this object will be converted to JsonFormat
     * @param clazz the class of obj
     * @return jsonFormat instance
     */
    public <T> JSONFormat serialize(T obj, Class<T> clazz) {
        JSONFormat JSONFormat = null;
        try {
            if (clazz.isArray()) {
                JSONFormat = serializeArray(obj, clazz);
            } else {
                JSONFormat = serializeObject(obj, clazz);
            }
        } catch (IllegalAccessException e) {
            throw new JSONSerializerException(e.getMessage());
        }
        return JSONFormat;
    }
    
    private <T> JSONArray serializeArray(T obj, Class<?> clazz) throws IllegalAccessException {
        JSONArray JSONArray = new JSONArray();
        int len = Array.getLength(obj);
        for (int i = 0; i < len; i++) {
            Object o = Array.get(obj, i);
            JSONArray.addValue(serializeValue(o, clazz.getComponentType()));
        }
        return JSONArray;
    }

    private <T> JSONObject serializeObject(T obj, Class<?> clazz) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        JSONObject jsonObject = new JSONObject();
        JSONValue value = null;
        for (Field field : fields) {
            if (isOnlyPublic && (field.getModifiers() & Modifier.PUBLIC) == 0) continue;
            field.setAccessible(true);
            String name = isCaseInsensitive ? field.getName().toLowerCase() : field.getName();
            value = serializeValue(field.get(obj), field.getType());
            jsonObject.addKeyAndValue(name, value);
        }
        return jsonObject;
    }

    private <T> JSONValue serializeValue(T obj, Class<?> clazz) throws IllegalAccessException {
        JSONValue JSONValue = null;
        if (obj != null) {
            if (clazz.isPrimitive() || clazz.isAssignableFrom(Number.class)) {
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
                JSONValue = serializeArray(obj, clazz);
            } else {
                JSONValue = serializeObject(obj, clazz);
            }
        } else {
            JSONValue = new JSONElement(null, JSONValueType.NULL);
        }

        return JSONValue;
    }

    public boolean isCaseInsensitive() {
        return isCaseInsensitive;
    }

    public void setCaseInsensitive(boolean caseInsensitive) {
        isCaseInsensitive = caseInsensitive;
    }

    public boolean isOnlyPublic() {
        return isOnlyPublic;
    }

    public void setOnlyPublic(boolean onlyPublic) {
        isOnlyPublic = onlyPublic;
    }
}

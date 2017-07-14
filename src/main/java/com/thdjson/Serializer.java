package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.SerializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Theodore on 2017/7/13.
 */
public class Serializer {

    /* Serialize fields with case insensitive when true */
    private boolean isCaseInsensitive;

    /* Only Serialize public fields in object when true */
    private boolean isOnlyPublic;

    public Serializer() {
        isCaseInsensitive = true;
        isOnlyPublic = true;
    }

    public Serializer(boolean caseInsensitive, boolean onlyPublic) {
        isCaseInsensitive = caseInsensitive;
        isOnlyPublic = onlyPublic;
    }

    /**
     * Convert object instance to json string
     * @param obj this object will be converted to json string
     * @param clazz the class of obj
     * @return string with json format
     */
    public <T> String serializeToString(T obj, Class<T> clazz) {
        return serialize(obj, clazz).toString();
    }

    /**
     * Convert object instance to JsonFormat
     * @param obj this object will be converted to JsonFormat
     * @param clazz the class of obj
     * @return jsonFormat instance
     */
    public <T> JsonFormat serialize(T obj, Class<T> clazz) {
        JsonFormat jsonFormat = null;
        try {
            if (clazz.isArray()) {
                jsonFormat = serializeArray(obj, clazz);
            } else {
                jsonFormat = serializeObject(obj, clazz);
            }
        } catch (IllegalAccessException e) {
            throw new SerializerException(e.getMessage());
        }
        return jsonFormat;
    }
    
    private <T> JsonArray serializeArray(T obj, Class<?> clazz) throws IllegalAccessException {
        JsonArray jsonArray = new JsonArray();
        int len = Array.getLength(obj);
        for (int i = 0; i < len; i++) {
            Object o = Array.get(obj, i);
            jsonArray.addValue(serializeValue(o, clazz.getComponentType()));
        }
        return jsonArray;
    }

    private <T> JsonObject serializeObject(T obj, Class<?> clazz) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        JsonObject jsonObject = new JsonObject();
        JsonValue value = null;
        for (Field field : fields) {
            if (isOnlyPublic && (field.getModifiers() & Modifier.PUBLIC) == 0) continue;
            field.setAccessible(true);
            String name = isCaseInsensitive ? field.getName().toLowerCase() : field.getName();
            value = serializeValue(field.get(obj), field.getType());
            jsonObject.addKeyAndValue(name, value);
        }
        return jsonObject;
    }

    private <T> JsonValue serializeValue(T obj, Class<?> clazz) throws IllegalAccessException {
        JsonValue jsonValue = null;
        if (obj != null) {
            if (clazz.isPrimitive() || clazz.isAssignableFrom(Number.class)) {
                if (clazz == float.class || clazz == Float.class ||
                        clazz == double.class || clazz == Double.class) {
                    jsonValue = new JsonElement(obj.toString(), JsonValueType.FLOAT);
                } else {
                    jsonValue = new JsonElement(obj.toString(), JsonValueType.INT);
                }
            } else if (clazz == boolean.class || clazz == Boolean.class) {
                jsonValue = new JsonElement(obj.toString(), JsonValueType.BOOL);
            } else if (clazz == char.class || clazz == Character.class || clazz == String.class) {
                jsonValue = new JsonElement(obj.toString(), JsonValueType.STRING);
            } else if (clazz.isArray()) {
                jsonValue = serializeArray(obj, clazz);
            } else {
                jsonValue = serializeObject(obj, clazz);
            }
        } else {
            jsonValue = new JsonElement(null, JsonValueType.NULL);
        }

        return jsonValue;
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

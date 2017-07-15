package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONSerializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

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
     * Convert object instance to JSONObject.
     * @param obj this object will be converted to JsonFormat
     * @return JSONObject instance
     */
    public <T> JSONObject serializeObject(T obj) {
        JSONObject jsonObject = new JSONObject();
        JSONValue value = null;
        Field[] fields = null;
        try {
            for (Class<?> cla = obj.getClass(); cla != Object.class; cla = cla.getSuperclass()) {
                fields = cla.getDeclaredFields();
                for (Field field : fields) {
                    if (isOnlyPublic && (field.getModifiers() & Modifier.PUBLIC) == 0) continue;
                    field.setAccessible(true);
                    String name = isCaseInsensitive ? field.getName().toLowerCase() : field.getName();
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
     * Convert object instance to JSONObject.
     * @param map this map will be converted to JsonFormat
     * @return JSONObject instance
     */
    public <T> JSONObject serializeMap(Map map) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (Object key : map.keySet()) {
                Object val = map.get(key);
                JSONValue jsonValue = serializeValue(val, val.getClass());
                jsonObject.addKeyAndValue(key.toString(), jsonValue);
            }
        } catch (IllegalAccessException e) {
            throw new JSONSerializerException(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * Convert object instance to JSONArray.
     * @param array this array will be converted to JsonFormat
     * @return JSONArray instance
     */
    public <T> JSONArray serializeArray(T array) {
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
     * Convert object instance to JSONArray.
     * @param list this list will be converted to JSONArray
     * @return JSONArray instance
     */
    public <T> JSONArray serializeList(List list) {
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

package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONDeserializerException;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by Theodore on 2017/7/12.
 */
public class JSONDeserializer {

    /* Deserialize fields with case insensitive */
    public static int CASE_INSENSITIVE = 1 << 0;
    /* Only deserialize public fields of object */
    public static int ONLY_PUBLIC = 1 << 1 ;

    private int flags;
    /**
     * Initializes an Json Deserializer.
     * Default {@code flags} is 0;
     */
    public JSONDeserializer() {
        flags = 0;
    }

    /**
     * Initializes an Json Deserializer with the specified flags.
     * @param flags {@code CASE_INSENSITIVE} deserialize fields with case insensitive
     *              {@code ONLY_PUBLIC} only deserialize public fields of object
     */
    public JSONDeserializer(int flags) {
        this.flags = flags;
    }

    /**
     * Deserialize string with json format to class instance.
     * @param json string with json format
     * @param clazz class type to return
     * @return Generic T instance
     * @throws JSONDeserializerException if class wrong
     */
    public <T> T deserializeToObject(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser();
        return deserializeToObject(parser.parseObject(json), clazz);
    }

    /**
     * Deserialize json format to class instance.
     * @param jsonObject instance of JSONObject
     * @param clazz class type to return
     * @return Generic T instance
     * @throws JSONDeserializerException if class wrong
     */
    public <T> T deserializeToObject(JSONObject jsonObject, Class<T> clazz) {
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()) || clazz.isArray())
            throw new JSONDeserializerException("wrong type: " + clazz);
        T result = null;
        try {
            result = deserializeJsonObject((JSONObject) jsonObject, clazz);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return result;
    }

    /**
     * Deserialize string with json format to map.
     * @param json string with json format
     * @param clazz value type in map
     * @return instance of HashMap
     * @throws JSONDeserializerException if class wrong
     */
    public <T> Map<String, T> deserializeToMap(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser();
        return deserializeToMap(parser.parseObject(json), clazz);
    }

    /**
     * Deserialize json format to map.
     * @param jsonObject instance of JSONObject
     * @param clazz value type in map
     * @return instance of HashMap
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> deserializeToMap(JSONObject jsonObject, Class<T> clazz) {
        Map<String, T> map = new HashMap<>();
        try {
            for (String key : jsonObject.keys()) {
                JSONValue value = null;
                if (inFlags(CASE_INSENSITIVE)) {
                    value = jsonObject.getValueWithCaseInsensitive(key);
                } else {
                    value = jsonObject.getValue(key);
                }
                map.put(key, (T) deserializeJsonValue(value, clazz));
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return map;
    }

    /**
     * Deserialize string with json format to array.
     * @param json string with json format
     * @param clazz class type to return
     * @throws JSONDeserializerException if class wrong
     */
    public <T> T deserializeToArray(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser();
        return deserializeToArray(parser.parseArray(json), clazz);
    }

    /**
     * Deserialize json format to array.
     * @param jsonArray instance of JSONArray
     * @param clazz class type to return
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public <T> T deserializeToArray(JSONArray jsonArray, Class<T> clazz) {
        if (!clazz.isArray())
            throw new JSONDeserializerException("wrong type: " + clazz);
        T result = null;
        try {
            result = deserializeJsonArray((JSONArray) jsonArray, clazz);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return result;
    }

    /**
     * Deserialize string with json format to List.
     * @param json instance of JSONArray
     * @param clazz element type in List
     * @return instance of ArrayList
     * @throws JSONDeserializerException if class wrong
     */
    public <T> List<T> deserializeToList(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser();
        return deserializeToList(parser.parseArray(json), clazz);
    }

    /**
     * Deserialize json format to List.
     * @param jsonArray instance of JSONArray
     * @param clazz element type in List
     * @return instance of ArrayList
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> deserializeToList(JSONArray jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            for (Object jsonValue : jsonArray.getArray()) {
                list.add((T)deserializeJsonValue((JSONValue) jsonValue, clazz));
            }
        }  catch (IllegalAccessException | InstantiationException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private <T> T deserializeJsonArray(JSONArray jsonArray, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Object array = Array.newInstance(clazz.getComponentType(), jsonArray.size());

        int i = 0;
        for (Object jsonValue : jsonArray.getArray()) {
            Array.set(array, i, deserializeJsonValue((JSONValue) jsonValue, clazz.getComponentType()));
            i++;
        }
        return (T) array;
    }

    @SuppressWarnings("unchecked")
    private <T> T deserializeJsonObject(JSONObject jsonObject, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        Field[] fields = null;
        for (Class<?> cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            fields = cla.getDeclaredFields();
            for (Field field : fields) {
                if (inFlags(ONLY_PUBLIC) && (field.getModifiers() & Modifier.PUBLIC) == 0) continue;

                String name = field.getName();

                JSONValue JSONValue = inFlags(CASE_INSENSITIVE) ?
                        jsonObject.getValueWithCaseInsensitive(name) :
                        jsonObject.getValue(name);

                if (JSONValue == null) continue;

                Object val = deserializeJsonValue(JSONValue, field.getType());
                field.setAccessible(true);
                field.set(obj, val);
            }
        }
        return obj;
    }

    private Object deserializeJsonValue(JSONValue jsonValue, Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if (jsonValue == null) return null;

        if (clazz == String.class) {
            if (jsonValue instanceof JSONElement)
                return ((JSONElement) jsonValue).getValue();
            return jsonValue.toString();
        }

        JSONValueType type = jsonValue.getType();

        if (jsonValue instanceof JSONElement) {

            String value = ((JSONElement) jsonValue).getValue();

            if (type == JSONValueType.INT) {
                long val = Long.parseLong(value);

                if (clazz == short.class || clazz == Short.class) {

                    if (val < Short.MIN_VALUE || val > Short.MAX_VALUE) {
                        throw new JSONDeserializerException("short overflow: " + value);
                    }

                    return (short) val;
                } else if (clazz == int.class || clazz == Integer.class) {

                    if (val < Integer.MIN_VALUE || val > Integer.MAX_VALUE) {
                        throw new JSONDeserializerException("int overflow: " + value);
                    }

                    return (int) val;
                } else if (clazz == byte.class || clazz == byte.class) {

                    if (val < Byte.MIN_VALUE || val > Byte.MAX_VALUE) {
                        throw new JSONDeserializerException("byte overflow: " + value);
                    }

                    return (byte) val;
                } else if (clazz == long.class || clazz == Long.class) {

                    return val;

                } else if (clazz == Object.class) {

                    if (val >= Integer.MIN_VALUE && val <= Integer.MAX_VALUE)
                        return (int) val;

                    return val;
                }

                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.FLOAT) {
                double val = Double.parseDouble(value);

                if (clazz == float.class || clazz == Float.class) {

                    if (val < Float.MIN_VALUE || val > Float.MAX_VALUE) {
                        throw new JSONDeserializerException("float overflow: " + value);
                    }

                    return (float) val;
                } else if (clazz == double.class || clazz == Double.class || clazz == Object.class) {
                    return val;
                } else if (clazz == String.class) {
                    return value;
                }

                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.BOOL) {

                if (clazz == boolean.class || clazz == Boolean.class || clazz == Object.class) {
                    return Boolean.parseBoolean(value);
                }

                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.STRING) {

                if ((clazz == char.class || clazz == Character.class) && value.length() == 1) {
                    return value.charAt(0);
                } else if (clazz == String.class || clazz == Object.class) {
                    return value;
                }

                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.NULL && clazz.getSuperclass() == Object.class) {
                return null;
            }

            throw new JSONDeserializerException("unknown type: " + clazz);
        } else if (jsonValue instanceof JSONArray) {

            return deserializeJsonArray((JSONArray) jsonValue, clazz);

        } else if (jsonValue instanceof JSONObject) {

            return deserializeJsonObject((JSONObject) jsonValue, clazz);

        }

        throw new JSONDeserializerException("unknown type: " + clazz);
    }

    private boolean inFlags(int flag) {
        return (flags & flag) == flag;
    }

}

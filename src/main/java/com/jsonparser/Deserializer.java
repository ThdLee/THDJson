package com.jsonparser;

import com.jsonparser.entity.*;
import com.jsonparser.exception.DeserializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * Created by Theodore on 2017/7/12.
 */
public class Deserializer {

    @SuppressWarnings("unchecked")
    public <T> T deserializable(JsonFormat json, Class<T> clazz) throws IllegalAccessException, DeserializerException, InstantiationException {

        if (clazz.isArray() && json.getClass() == JsonArray.class) {
            return jsonArrayDeserializable((JsonArray) json, clazz);
        } else if (json.getClass() == JsonObject.class) {
            return jsonObjectDeserializable((JsonObject) json, clazz);
        } else {
            throw new DeserializerException("Wrong Type");
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T jsonArrayDeserializable(JsonArray jsonArray, Class<T> clazz) throws IllegalAccessException, InstantiationException, DeserializerException {
        if (!clazz.isArray()) throw new DeserializerException("not array");
        Object array = Array.newInstance(clazz.getComponentType(), jsonArray.size());
        int i = 0;
        for (Object jsonValue : jsonArray.getArray()) {
            Array.set(array, i, parseJsonValue((JsonValue) jsonValue, clazz.getComponentType()));
        }
        return (T) array;
    }

    @SuppressWarnings("unchecked")
    private <T> T jsonObjectDeserializable(JsonObject jsonObject, Class<T> clazz) throws IllegalAccessException, InstantiationException, DeserializerException {
        T obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            JsonValue jsonValue = jsonObject.getValue(name);
            if (jsonValue == null) continue;
            Object val = parseJsonValue(jsonValue, field.getType());
            field.setAccessible(true);
            field.set(obj, val);
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    private Object parseJsonValue(JsonValue jsonValue, Class<?> clazz) throws IllegalAccessException, InstantiationException, DeserializerException {
        JsonValueType type = jsonValue.getType();

        if (jsonValue.getClass() == JsonElement.class) {

            String value = ((JsonElement) jsonValue).getValue();

            if (type == JsonValueType.INT) {
                long val = Long.parseLong(value);

                if (clazz == short.class || clazz == Short.class) {
                    if (val < Short.MIN_VALUE || val > Short.MAX_VALUE) {
                        throw new DeserializerException("short overflow: " + value);
                    }
                    return (short) val;
                } else if (clazz == int.class || clazz == Integer.class) {
                    if (val < Integer.MIN_VALUE || val > Integer.MAX_VALUE) {
                        throw new DeserializerException("int overflow: " + value);
                    }
                    return (int) val;
                } else if (clazz == byte.class || clazz == byte.class) {
                    if (val < Byte.MIN_VALUE || val > Byte.MAX_VALUE) {
                        throw new DeserializerException("byte overflow: " + value);
                    }
                    return (byte) val;
                } else if (clazz == long.class || clazz == Long.class) {
                    return val;
                }
                throw new DeserializerException("unknown type: " + clazz);
            } else if (type == JsonValueType.FLOAT) {
                double val = Double.parseDouble(value);

                if (clazz == float.class || clazz == Float.class) {
                    if (val < Float.MIN_VALUE || val > Float.MAX_VALUE) {
                        throw new DeserializerException("float overflow: " + value);
                    }
                    return (float) val;
                } else if (clazz == double.class || clazz == Double.class) {
                    return val;
                }
                throw new DeserializerException("unknown type: " + clazz);
            } else if (type == JsonValueType.BOOL) {

                if (clazz == boolean.class || clazz == Boolean.class) {
                    return Boolean.parseBoolean(value);
                }

                throw new DeserializerException("unknown type: " + clazz);
            } else if (type == JsonValueType.STRING) {

                if ((clazz == char.class || clazz == Character.class) && value.length() == 1) {
                    return value.charAt(0);
                } else if (clazz == String.class) {
                    return value;
                }
                throw new DeserializerException("unknown type: " + clazz);
            } else if (type == JsonValueType.NULL && clazz.getSuperclass() == Object.class) {
                return null;
            }
            throw new DeserializerException("unknown type: " + clazz);
        } else if (jsonValue.getClass() == JsonArray.class) {
            return jsonArrayDeserializable((JsonArray) jsonValue, clazz);
        } else if (jsonValue.getClass() == JsonObject.class) {
            return jsonObjectDeserializable((JsonObject) jsonValue, clazz);
        }

        throw new DeserializerException("unknown type: " + clazz);
    }
}

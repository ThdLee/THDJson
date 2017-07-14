package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONDeserializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Theodore on 2017/7/12.
 */
public class JSONDeserializer {

    /* Deserialize fields with case insensitive when true */
    private boolean isCaseInsensitive;

    /* Only deserialize public fields in object when true */
    private boolean isOnlyPublic;

    private JSONParser JSONParser;

    public JSONDeserializer() {
        JSONParser = new JSONParser();
        isCaseInsensitive = true;
        isOnlyPublic = true;
    }

    public JSONDeserializer(boolean caseInsensitive, boolean onlyPublic) {
        JSONParser = new JSONParser();
        isCaseInsensitive = caseInsensitive;
        isOnlyPublic = onlyPublic;
    }

    /**
     * Deserialize json string to class instance
     * @param clazz class type to return
     */
    public <T> T deserialize(String json, Class<T> clazz) {
        JSONFormat JSONFormat = JSONParser.parseJson(json);
        return deserialize(JSONFormat, clazz);
    }

    /**
     * Deserialize json format with case insensitive option to class instance
     * @param clazz class type to return
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(JSONFormat json, Class<T> clazz) {
        T result = null;
        try {
            if (clazz.isArray() && json.getClass() == JSONArray.class) {
                result = DeserializeJsonArray((JSONArray) json, clazz);
            } else if (json.getClass() == JSONObject.class) {
                result = DeserializeJsonObject((JSONObject) json, clazz);
            } else {
                throw new JSONDeserializerException("Wrong Type");
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private <T> T DeserializeJsonArray(JSONArray JSONArray, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if (!clazz.isArray()) throw new JSONDeserializerException("not array");
        Object array = Array.newInstance(clazz.getComponentType(), JSONArray.size());
        int i = 0;
        for (Object jsonValue : JSONArray.getArray()) {
            Array.set(array, i, DeserializeJsonValue((JSONValue) jsonValue, clazz.getComponentType()));
            i++;
        }
        return (T) array;
    }

    private <T> T DeserializeJsonObject(JSONObject jsonObject, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (isOnlyPublic && (field.getModifiers() & Modifier.PUBLIC) == 0) continue;
            String name = field.getName();
            JSONValue JSONValue = isCaseInsensitive ? jsonObject.getValueWithCaseInsensitive(name) : jsonObject.getValue(name);
            if (JSONValue == null) continue;
            Object val = DeserializeJsonValue(JSONValue, field.getType());
            field.setAccessible(true);
            field.set(obj, val);
        }
        return obj;
    }

    private Object DeserializeJsonValue(JSONValue JSONValue, Class<?> clazz) throws IllegalAccessException, InstantiationException {
        JSONValueType type = JSONValue.getType();

        if (JSONValue.getClass() == JSONElement.class) {

            String value = ((JSONElement) JSONValue).getValue();

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
                }
                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.FLOAT) {
                double val = Double.parseDouble(value);

                if (clazz == float.class || clazz == Float.class) {
                    if (val < Float.MIN_VALUE || val > Float.MAX_VALUE) {
                        throw new JSONDeserializerException("float overflow: " + value);
                    }
                    return (float) val;
                } else if (clazz == double.class || clazz == Double.class) {
                    return val;
                }
                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.BOOL) {

                if (clazz == boolean.class || clazz == Boolean.class) {
                    return Boolean.parseBoolean(value);
                }

                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.STRING) {

                if ((clazz == char.class || clazz == Character.class) && value.length() == 1) {
                    return value.charAt(0);
                } else if (clazz == String.class) {
                    return value;
                }
                throw new JSONDeserializerException("unknown type: " + clazz);
            } else if (type == JSONValueType.NULL && clazz.getSuperclass() == Object.class) {
                return null;
            }
            throw new JSONDeserializerException("unknown type: " + clazz);
        } else if (JSONValue.getClass() == JSONArray.class) {
            return DeserializeJsonArray((JSONArray) JSONValue, clazz);
        } else if (JSONValue.getClass() == JSONObject.class) {
            return DeserializeJsonObject((JSONObject) JSONValue, clazz);
        }

        throw new JSONDeserializerException("unknown type: " + clazz);
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

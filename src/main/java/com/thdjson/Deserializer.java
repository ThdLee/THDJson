package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.DeserializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Theodore on 2017/7/12.
 */
public class Deserializer {

    /* Deserialize fields with case insensitive when true */
    private boolean isCaseInsensitive;

    /* Only deserialize public fields in object when true */
    private boolean isOnlyPublic;

    private Parser parser;

    public Deserializer() {
        parser = new Parser();
        isCaseInsensitive = true;
        isOnlyPublic = true;
    }

    public Deserializer(boolean caseInsensitive, boolean onlyPublic) {
        parser = new Parser();
        isCaseInsensitive = caseInsensitive;
        isOnlyPublic = onlyPublic;
    }

    /**
     * Deserialize json string to class instance
     * @param clazz class type to return
     */
    public <T> T deserialize(String json, Class<T> clazz) {
        JsonFormat jsonFormat = parser.parseJson(json);
        return deserialize(jsonFormat, clazz);
    }

    /**
     * Deserialize json format with case insensitive option to class instance
     * @param clazz class type to return
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(JsonFormat json, Class<T> clazz) {
        T result = null;
        try {
            if (clazz.isArray() && json.getClass() == JsonArray.class) {
                result = DeserializeJsonArray((JsonArray) json, clazz);
            } else if (json.getClass() == JsonObject.class) {
                result = DeserializeJsonObject((JsonObject) json, clazz);
            } else {
                throw new DeserializerException("Wrong Type");
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new DeserializerException(e.getMessage());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private <T> T DeserializeJsonArray(JsonArray jsonArray, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if (!clazz.isArray()) throw new DeserializerException("not array");
        Object array = Array.newInstance(clazz.getComponentType(), jsonArray.size());
        int i = 0;
        for (Object jsonValue : jsonArray.getArray()) {
            Array.set(array, i, DeserializeJsonValue((JsonValue) jsonValue, clazz.getComponentType()));
            i++;
        }
        return (T) array;
    }

    private <T> T DeserializeJsonObject(JsonObject jsonObject, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (isOnlyPublic && (field.getModifiers() & Modifier.PUBLIC) == 0) continue;
            String name = field.getName();
            JsonValue jsonValue = isCaseInsensitive ? jsonObject.getValueWithCaseInsensitive(name) : jsonObject.getValue(name);
            if (jsonValue == null) continue;
            Object val = DeserializeJsonValue(jsonValue, field.getType());
            field.setAccessible(true);
            field.set(obj, val);
        }
        return obj;
    }

    private Object DeserializeJsonValue(JsonValue jsonValue, Class<?> clazz) throws IllegalAccessException, InstantiationException {
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
            return DeserializeJsonArray((JsonArray) jsonValue, clazz);
        } else if (jsonValue.getClass() == JsonObject.class) {
            return DeserializeJsonObject((JsonObject) jsonValue, clazz);
        }

        throw new DeserializerException("unknown type: " + clazz);
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

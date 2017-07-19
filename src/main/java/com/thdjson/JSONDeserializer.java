/*
 * Copyright 2017 ThdLee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONDeserializerException;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author ThdLee
 */
public class JSONDeserializer {

    private int features;
    /**
     * Initializes an Json Deserializer.
     * Default {@code features} is 0;
     */
    public JSONDeserializer() {
        features = 0;
    }

    /**
     * Initializes an Json Deserializer with the specified features.
     * @param features {@code CASE_INSENSITIVE} deserialize fields with case insensitive
     *              {@code ONLY_PUBLIC} only deserialize public fields of object
     */
    public JSONDeserializer(JSONDeserializerFeature... features) {
        for (JSONDeserializerFeature feature : features) {
            this.features |= feature.getMask();
        }
    }

    /**
     * Deserialize string with json format to class instance.
     * @param json string with json format
     * @param clazz class type to return
     * @param <T> the type of the desired object
     * @return Generic T instance
     * @throws JSONDeserializerException if class wrong
     */
    public <T> T deserializeToObject(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser(inFeatures(JSONDeserializerFeature.CaseInsensitive));
        return deserializeToObject(parser.parseObject(json), clazz);
    }

    /**
     * Deserialize json format to class instance.
     * @param jsonObject instance of JSONObject
     * @param clazz class type to return
     * @param <T> the type of the desired object
     * @return Generic T instance
     * @throws JSONDeserializerException if class wrong
     */
    public <T> T deserializeToObject(JSONObject jsonObject, Class<T> clazz) {
        if (jsonObject == null) return null;
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()) || clazz.isArray())
            throw new JSONDeserializerException("wrong type: " + clazz);
        T result = null;
        try {
            result = deserializeJsonObject((JSONObject) jsonObject, clazz);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return result;
    }

    /**
     * Deserialize string with json format to map.
     * @param json string with json format
     * @param clazz value type in map
     * @param <T> the type of the map value
     * @return instance of HashMap, String is key type
     * @throws JSONDeserializerException if class wrong
     */
    public <T> Map<String, T> deserializeToMap(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser(inFeatures(JSONDeserializerFeature.CaseInsensitive));
        return deserializeToMap(parser.parseObject(json), clazz);
    }

    /**
     * Deserialize json format to map.
     * @param jsonObject instance of JSONObject
     * @param clazz value type in map
     * @param <T> the type of the map value
     * @return instance of HashMap, String is key type
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> deserializeToMap(JSONObject jsonObject, Class<T> clazz) {
        if (jsonObject == null) return null;
        Map<String, T> map = new HashMap<>();
        try {
            for (String key : jsonObject.keySet()) {
                JSONValue value = jsonObject.get(key);
                map.put(key, (T) deserializeJsonValue(value, clazz));
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return map;
    }

    /**
     * Deserialize string with json format to map.
     * @param json string with json format
     * @param clazz value types in map
     * @return instance of HashMap
     * @throws JSONDeserializerException if class wrong
     */
    public Map<String, Object> deserializeToMap(String json, Class<?>[] clazz) {
        JSONParser parser = new JSONParser(inFeatures(JSONDeserializerFeature.CaseInsensitive));
        return deserializeToMap(parser.parseObject(json), clazz);
    }

    /**
     * Deserialize json format to map.
     * @param jsonObject instance of JSONObject
     * @param clazz value types in map
     * @return instance of HashMap
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> deserializeToMap(JSONObject jsonObject, Class<?>[] clazz) {
        if (jsonObject == null) return null;
        Map<String, Object> map = new HashMap<>();
        try {
            int i = 0;
            for (String key : jsonObject.keySet()) {
                JSONValue value = jsonObject.get(key);
                Class<?> cla;
                if (i < clazz.length) cla = clazz[i++];
                else                  cla = String.class;
                map.put(key, deserializeJsonValue(value, cla));
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return map;
    }

    /**
     * Deserialize string with json format to array.
     * @param json string with json format
     * @param clazz class type to return
     * @param <T> the type of the array
     * @throws JSONDeserializerException if class wrong
     */ 
    public <T> T deserializeToArray(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser(inFeatures(JSONDeserializerFeature.CaseInsensitive));
        return deserializeToArray(parser.parseArray(json), clazz);
    }

    /**
     * Deserialize json format to array.
     * @param jsonArray instance of JSONArray
     * @param clazz class type to return
     * @param <T> the type of the array
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public <T> T deserializeToArray(JSONArray jsonArray, Class<T> clazz) {
        if (jsonArray == null) return null;
        if (!clazz.isArray())
            throw new JSONDeserializerException("wrong type: " + clazz);
        T result = null;
        try {
            result = deserializeJsonArray((JSONArray) jsonArray, clazz);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return result;
    }

    /**
     * Deserialize string with json format to List.
     * @param json instance of JSONArray
     * @param clazz element type in List
     * @param <T> the type of the element of list
     * @return instance of ArrayList
     * @throws JSONDeserializerException if class wrong
     */
    public <T> List<T> deserializeToList(String json, Class<T> clazz) {
        JSONParser parser = new JSONParser(inFeatures(JSONDeserializerFeature.CaseInsensitive));
        return deserializeToList(parser.parseArray(json), clazz);
    }

    /**
     * Deserialize json format to List.
     * @param jsonArray instance of JSONArray
     * @param clazz element type in List
     * @param <T> the type of the element of list
     * @return instance of ArrayList
     * @throws JSONDeserializerException if class wrong
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> deserializeToList(JSONArray jsonArray, Class<T> clazz) {
        if (jsonArray == null) return null;
        List<T> list = new ArrayList<>();
        try {
            for (Object jsonValue : jsonArray) {
                list.add((T)deserializeJsonValue((JSONValue) jsonValue, clazz));
            }
        }  catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new JSONDeserializerException(e.getMessage());
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private <T> T deserializeJsonArray(JSONArray jsonArray, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object array = Array.newInstance(clazz.getComponentType(), jsonArray.size());

        int i = 0;
        for (Object jsonValue : jsonArray) {
            Array.set(array, i, deserializeJsonValue((JSONValue) jsonValue, clazz.getComponentType()));
            i++;
        }
        return (T) array;
    }

    @SuppressWarnings("unchecked")
    private <T> T deserializeJsonObject(JSONObject jsonObject, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<?> cons[] = clazz.getConstructors();
        Class<?> classes[] = cons[0].getParameterTypes();

        Object params[] = new Object[classes.length];
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].isPrimitive()) {
                if (classes[i] == boolean.class) {
                    params[i] = false;
                } else {
                    params[i] = 0;
                }
            } else {
                params[i] = null;
            }

        }
        T obj = (T) cons[0].newInstance(params);

        Field[] fields = null;
        for (Class<?> cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            fields = cla.getDeclaredFields();
            for (Field field : fields) {
                if (!inFeatures(JSONDeserializerFeature.AllowNonPublic) &&
                        (field.getModifiers() & Modifier.PUBLIC) == 0)
                    continue;

                String name = field.getName();

                JSONValue JSONValue = inFeatures(JSONDeserializerFeature.CaseInsensitive) ?
                        jsonObject.get(name.toLowerCase()) :
                        jsonObject.get(name);

                if (JSONValue == null) {
                    if (inFeatures(JSONDeserializerFeature.IgnoreNotMatch)) continue;
                    throw new JSONDeserializerException("field \"" + name + "\" cannot match");
                }

                Object val = deserializeJsonValue(JSONValue, field.getType());
                field.setAccessible(true);
                field.set(obj, val);
            }
        }
        return obj;
    }

    private Object deserializeJsonValue(JSONValue jsonValue, Class<?> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (jsonValue == null) return null;

        if (clazz == String.class) {
            if (jsonValue instanceof JSONElement)
                return ((JSONElement) jsonValue).getString();
            return jsonValue.toString();
        }

        JSONValueType type = jsonValue.getType();

        if (jsonValue instanceof JSONElement) {

            String value = ((JSONElement) jsonValue).getString();

            if (type == JSONValueType.INT) {

                if (clazz == BigInteger.class) return new BigInteger(value);

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

                if (clazz == BigDecimal.class) return new BigDecimal(value);

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

    private boolean inFeatures(JSONDeserializerFeature feature) {
        return (features & feature.getMask()) == feature.getMask();
    }

}

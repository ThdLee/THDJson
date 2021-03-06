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
import com.thdjson.exception.JSONSerializerException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * @author ThdLee
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
     * @param features specified serializer features
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
        if (obj == null) return null;
        JSONObject jsonObject = new JSONObject();
        JSONValue value = null;
        Field[] fields = null;
        try {
            for (Class<?> cla = obj.getClass(); cla != Object.class; cla = cla.getSuperclass()) {
                fields = cla.getDeclaredFields();
                for (Field field : fields) {
                    if (!inFeatures(JSONSerializerFeature.AllowNonPublic) &&
                            (field.getModifiers() & Modifier.PUBLIC) == 0)
                        continue;

                    field.setAccessible(true);
                    String name = inFeatures(JSONSerializerFeature.CaseInsensitive) ?
                            field.getName().toLowerCase() :
                            field.getName();

                    value = serializeValue(field.get(obj), field.getType());
                    jsonObject.put(name, value);
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
        if (map == null) return null;
        JSONObject jsonObject = new JSONObject();
        try {
            for (Object key : map.keySet()) {
                Object val = map.get(key);
                JSONValue jsonValue = serializeValue(val, val.getClass());
                String k = inFeatures(JSONSerializerFeature.CaseInsensitive) ?
                        key.toString().toLowerCase() :
                        key.toString();
                jsonObject.put(k, jsonValue);
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
        if (array == null) return null;
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
        if (list == null) return null;
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

    private boolean inFeatures(JSONSerializerFeature feature) {
        return (features & feature.getMask()) == feature.getMask();
    }
}

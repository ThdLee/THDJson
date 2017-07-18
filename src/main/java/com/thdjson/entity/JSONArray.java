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

package com.thdjson.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author ThdLee
 */
public class JSONArray extends JSONFormat implements List<JSONValue>, Cloneable, RandomAccess, Serializable {

    private JSONValueType type;

    private ArrayList<JSONValue> list;

    public JSONArray() {
        list = new ArrayList<JSONValue>();
        type = JSONValueType.ARRAY;
    }

    public JSONArray(ArrayList<JSONValue> list) {
        this.list = list;
        type = JSONValueType.ARRAY;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public Iterator<JSONValue> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public boolean add(JSONValue e) {
        return list.add(e);
    }

    public JSONArray fluentAdd(JSONValue e) {
        list.add(e);
        return this;
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public JSONArray fluentRemove(Object o) {
        list.remove(o);
        return this;
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends JSONValue> c) {
        return list.addAll(c);
    }

    public JSONArray fluentAddAll(Collection<? extends JSONValue> c) {
        list.addAll(c);
        return this;
    }

    public boolean addAll(int index, Collection<? extends JSONValue> c) {
        return list.addAll(index, c);
    }

    public JSONArray fluentAddAll(int index, Collection<? extends JSONValue> c) {
        list.addAll(index, c);
        return this;
    }

    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    public JSONArray fluentRemoveAll(Collection<?> c) {
        list.removeAll(c);
        return this;
    }

    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    public JSONArray fluentRetainAll(Collection<?> c) {
        list.retainAll(c);
        return this;
    }

    public void clear() {
        list.clear();
    }

    public JSONArray fluentClear() {
        list.clear();
        return this;
    }

    public JSONValue set(int index, JSONValue element) {
        if (index == -1) {
            list.add(element);
            return null;
        }

        if (list.size() <= index) {
            for (int i = list.size(); i < index; ++i) {
                list.add(null);
            }
            list.add(element);
            return null;
        }

        return list.set(index, element);
    }

    public JSONArray fluentSet(int index, JSONValue element) {
        set(index, element);
        return this;
    }

    public void add(int index, JSONValue element) {
        list.add(index, element);
    }

    public JSONArray fluentAdd(int index, JSONValue element) {
        list.add(index, element);
        return this;
    }

    public JSONValue remove(int index) {
        return list.remove(index);
    }

    public JSONArray fluentRemove(int index) {
        list.remove(index);
        return this;
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<JSONValue> listIterator() {
        return list.listIterator();
    }

    public ListIterator<JSONValue> listIterator(int index) {
        return list.listIterator(index);
    }

    public List<JSONValue> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public void addValue(JSONValue value) {
        list.add(value);
    }


    public JSONValueType getType() {
        return type;
    }

    public Byte getByte(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getByte();
        return null;
    }

    public byte getByteValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getByteValue();
        return 0;
    }

    public Short getShort(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getShort();
        return null;
    }

    public short getShortValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getShortValue();
        return 0;
    }

    public Integer getInt(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getInt();
        return null;
    }

    public int getIntValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getIntValue();
        return 0;
    }

    public Long getLong(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getLong();
        return null;
    }

    public long getLongValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getLongValue();
        return 0;
    }

    public Boolean getBoolean(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBoolean();
        return null;
    }

    public boolean getBooleanValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBooleanValue();
        return false;
    }

    public Float getFloat(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getFloat();
        return null;
    }

    public float getFloatValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getFloatValue();
        return 0;
    }

    public Double getDouble(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getDouble();
        return null;
    }

    public double getDoubleValue(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getDoubleValue();
        return 0;
    }

    public BigDecimal getBigDecimal(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBigDecimal();
        return null;
    }

    public BigInteger getBigInteger(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBigInteger();
        return null;
    }

    public String getString(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getString();
        if (value == null) return null;
        return value.toString();
    }

    public JSONArray getJSONArray(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        return null;
    }

    public JSONObject getJSONObject(int index) {
        JSONValue value = list.get(index);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        return null;
    }

    public JSONValue get(int index) {
        return list.get(index);
    }

    public String toString() {
        return format(1);
    }

    @Override
    String format(int layer) {
        StringBuilder str = new StringBuilder();
        str.append("[\n");
        for (JSONValue value : list) {
            str.append(withSpace(layer));
            str.append(matchFormat(value, layer+1));
            str.append(",\n");
        }
        int lastIndex = str.length() - 2;
        if (str.charAt(lastIndex) == ',') str.deleteCharAt(lastIndex);
        str.append(withSpace(layer-1));
        str.append("]");
        return str.toString();
    }
}

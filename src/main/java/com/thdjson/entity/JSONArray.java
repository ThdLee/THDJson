package com.thdjson.entity;

import java.util.LinkedList;

/**
 * Created by ThdLee on 2017/6/7.
 */
public class JSONArray extends JSONFormat {

    private JSONValueType type;

    private LinkedList<JSONValue> array;

    public JSONArray() {
        array = new LinkedList<JSONValue>();
        type = JSONValueType.ARRAY;
    }

    public void addValue(JSONValue value) {
        array.add(value);
    }

    public JSONValue getValue(int index) {
        JSONValue value = null;
        try {
            value = array.get(index);
        } catch (IndexOutOfBoundsException e) {
            value = null;
        }

        return value;
    }

    @Override
    public String toString() {
        return format(1);
    }

    public JSONValueType getType() {
        return type;
    }

    public int size() {
        return array.size();
    }

    public Iterable getArray() {
        return array;
    }

    @Override
    String format(int layer) {
        StringBuilder str = new StringBuilder();
        str.append("[\n");
        for (JSONValue value : array) {
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

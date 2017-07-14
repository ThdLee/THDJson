package com.thdjson.entity;

import java.util.LinkedList;

/**
 * Created by Theodore on 2017/6/7.
 */
public class JsonArray extends JsonFormat {

    private JsonValueType type;

    private LinkedList<JsonValue> array;

    public JsonArray() {
        array = new LinkedList<JsonValue>();
        type = JsonValueType.ARRAY;
    }

    public void addValue(JsonValue value) {
        array.add(value);
    }

    public JsonValue getValue(int index) {
        JsonValue value = null;
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

    public JsonValueType getType() {
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
        for (JsonValue value : array) {
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

package com.thdjson.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Theodore on 2017/6/7.
 */
public class JsonObject extends JsonFormat{

    private JsonValueType type;

    private Map<String, JsonValue> map;

    private Map<String, String> stringMap;

    public JsonObject() {
        map = new HashMap<String, JsonValue>();
        stringMap = new HashMap<String, String>();
        type = JsonValueType.OBJECT;
    }

    public void addKeyAndValue(String key, JsonValue value) {
        map.put(key, value);
        stringMap.put(key.toLowerCase(), key);
    }

    public JsonValue getValue(String key) {
        return map.get(key);
    }

    public JsonValue getValueWithCaseInsensitive(String key) {
        return map.get(stringMap.get(key.toLowerCase()));
    }

    @Override
    public String toString() {
        return format(1);
    }

    public JsonValueType getType() {
        return type;
    }

    @Override
    String format(int layer) {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        for (Map.Entry entry : map.entrySet()) {
            str.append(withSpace(layer));
            str.append("\"" + entry.getKey() + "\"");
            str.append(": ");
            str.append(matchFormat(entry.getValue(), layer+1));
            str.append(",\n");
        }
        int lastIndex = str.length() - 2;
        if (str.charAt(lastIndex) == ',') str.deleteCharAt(lastIndex);
        str.append(withSpace(layer-1));
        str.append("}");
        return str.toString();
    }
}
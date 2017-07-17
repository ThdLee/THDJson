package com.thdjson.entity;

import com.thdjson.JSONToken;
import com.thdjson.exception.JSONParserException;

/**
 * Created by ThdLee on 2017/6/7.
 */
public class JSONElement implements JSONValue {

    private JSONValueType type;

    private String value;

    public JSONElement(JSONToken jsonToken) throws JSONParserException {
        switch (jsonToken) {
            case STRING:
                type = JSONValueType.STRING;
                break;
            case NULL:
                type = JSONValueType.NULL;
                break;
            case TRUE:
            case FALSE:
                type = JSONValueType.BOOL;
                break;
            case FLOAT:
                type = JSONValueType.FLOAT;
                break;
            case INT:
                type = JSONValueType.INT;
                break;
            default:
                throw new JSONParserException();
        }
        value = jsonToken.getData();
    }

    public JSONElement(String value, JSONValueType type) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (type == JSONValueType.STRING) return "\"" + value + "\"";
        return value;
    }

    public JSONValueType getType() {
        return type;
    }
}

package com.thdjson.entity;

import com.thdjson.JSONToken;
import com.thdjson.exception.JSONParserException;

/**
 * Created by Theodore on 2017/6/7.
 */
public class JSONElement implements JSONValue {

    private JSONValueType type;

    private String value;

    public JSONElement(JSONToken JSONToken) throws JSONParserException {
        switch (JSONToken) {
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
        value = JSONToken.getData().toString();
        if (type == JSONValueType.STRING) {
            value = "\"" + value + "\"";
        }
    }

    public JSONElement(String value, JSONValueType type) {
        this.type = type;
        if (type == JSONValueType.STRING) {
            this.value = '\"' + value + '\"';
        } else {
            this.value = value;
        }
    }

    public String getValue() {
        if (type == JSONValueType.STRING) return value.substring(1,value.length()-1);
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public JSONValueType getType() {
        return type;
    }
}

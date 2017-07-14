package com.thdjson.entity;

import com.thdjson.Token;
import com.thdjson.exception.JsonParserException;

/**
 * Created by Theodore on 2017/6/7.
 */
public class JsonElement implements JsonValue {

    private JsonValueType type;

    private String value;

    public JsonElement(Token token) throws JsonParserException {
        switch (token) {
            case STRING:
                type = JsonValueType.STRING;
                break;
            case NULL:
                type = JsonValueType.NULL;
                break;
            case TRUE:
            case FALSE:
                type = JsonValueType.BOOL;
                break;
            case FLOAT:
                type = JsonValueType.FLOAT;
                break;
            case INT:
                type = JsonValueType.INT;
                break;
            default:
                throw new JsonParserException();
        }
        value = token.getData().toString();
        if (type == JsonValueType.STRING) {
            value = "\"" + value + "\"";
        }
    }

    public JsonElement(String value, JsonValueType type) {
        this.type = type;
        if (type == JsonValueType.STRING) {
            this.value = '\"' + value + '\"';
        } else {
            this.value = value;
        }
    }

    public String getValue() {
        if (type == JsonValueType.STRING) return value.substring(1,value.length()-1);
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public JsonValueType getType() {
        return type;
    }
}

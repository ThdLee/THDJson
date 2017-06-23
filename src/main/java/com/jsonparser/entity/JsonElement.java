package com.jsonparser.entity;

import com.jsonparser.Token;
import com.jsonparser.exception.JsonParserException;

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

    public String getValue() {
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

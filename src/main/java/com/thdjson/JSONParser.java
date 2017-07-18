package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONParserException;

/**
 * @author ThdLee
 */
public class JSONParser {

    private JSONLexer lexer;

    private JSONFormat jsonFormat;

    private JSONToken token;    // current token to handle

    private boolean isCaseInsensitive;

    public JSONParser() {
        isCaseInsensitive = false;
    }

    public JSONParser(boolean caseInsensitive) {
        isCaseInsensitive = caseInsensitive;
    }

    /**
     * Covert json string to JSONFormat.
     * @param json string with json format
     */
    public JSONFormat parseJson(String json) {
        if (json == null) return null;
        init(json);
        nextToken();
        if (token == JSONToken.LBRACE) {
            jsonFormat = parseObject();
        } else if (token == JSONToken.LBRACKET) {
            jsonFormat = parseArray();
        } else {
            jsonFormat = null;
        }
        return jsonFormat;
    }

    /**
     * Covert json string to JSONArray.
     * @param json string with json format
     */
    public JSONArray parseArray(String json) {
        if (json == null) return null;
        JSONFormat jsonFormat = parseJson(json);
        assert jsonFormat instanceof JSONArray;
        return (JSONArray) jsonFormat;
    }

    /**
     * Covert json string to JSONObject.
     * @param json string with json format
     */
    public JSONObject parseObject(String json) {
        if (json == null) return null;
        JSONFormat jsonFormat = parseJson(json);
        assert jsonFormat instanceof JSONObject;
        return (JSONObject) jsonFormat;
    }

    /**
     * Init class before parse json token.
     * @param json json string
     */
    private void init(String json) {
        lexer = new JSONLexer(json);
        token = null;
        jsonFormat = null;
    }

    private JSONToken nextToken() {
        return token = lexer.nextToken();
    }

    private JSONObject parseObject() {
        JSONObject object = new JSONObject();

        String key;
        JSONValue value = null;

        boolean isFirst = true;
        for (;;) {
            nextToken();
            if (token == JSONToken.RBRACE) {
                return object;
            } else if (token == JSONToken.COMMA && !isFirst) {
                nextToken();
            } else if (token != JSONToken.COMMA && !isFirst) {
                throw new JSONParserException("expect \",\"");
            }
            if (token == JSONToken.STRING) {
                key = token.getData();
            } else {
                throw new JSONParserException("expect string type");
            }
            nextToken();
            if (token != JSONToken.COLON) {
                throw new JSONParserException("expect \":\"");
            }
            nextToken();
            if (token == JSONToken.LBRACE) {
                value = parseObject();
            } else if (token == JSONToken.LBRACKET) {
                value = parseArray();
            } else {
                value = new JSONElement(token);
            }
            object.put(isCaseInsensitive ? key.toLowerCase() : key, value);
            if (isFirst) isFirst = false;
        }
    }

    private JSONArray parseArray() {
        JSONArray array = new JSONArray();

        boolean isFirst = true;
        for (;;) {
            nextToken();
            if (token == JSONToken.RBRACKET) {
                return array;
            } else if (token == JSONToken.COMMA && !isFirst) {
                nextToken();
            } else if (token != JSONToken.COMMA && !isFirst) {
                throw new JSONParserException("expect \",\"");
            }
            if (token == JSONToken.LBRACKET) {
                array.addValue(parseArray());
            } else if (token == JSONToken.LBRACE) {
                array.addValue(parseObject());
            } else {
                array.addValue(new JSONElement(token));
            }
            if (isFirst) isFirst = false;
        }
    }


}

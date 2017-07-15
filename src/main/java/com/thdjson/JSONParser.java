package com.thdjson;

import com.thdjson.entity.*;
import com.thdjson.exception.JSONParserException;

/**
 * Created by Theodore on 2017/6/7.
 */
public class JSONParser {

    private JSONLexer JSONLexer;

    private JSONFormat JSONFormat;

    private JSONToken JSONToken;    // current token to handle

    /**
     * Covert json string to JsonFormat class.
     * @param json string with json format
     * @return formatted json class
     */
    public JSONFormat parseJson(String json) {
        init(json);
        nextToken();
        if (JSONToken == JSONToken.LBRACE) {
            JSONFormat = parseObject();
        } else if (JSONToken == JSONToken.LBRACKET) {
            JSONFormat = parseArray();
        } else {
            JSONFormat = null;
        }
        return JSONFormat;
    }

    /**
     * Init class before parse json token.
     * @param json json string
     */
    private void init(String json) {
        JSONLexer = new JSONLexer(json);
        JSONToken = null;
        JSONFormat = null;
    }

    private JSONToken nextToken() {
        return JSONToken = JSONLexer.nextToken();
    }

    private JSONObject parseObject() {
        JSONObject object = new JSONObject();

        String key;
        JSONValue value = null;

        boolean isFirst = true;
        for (;;) {
            nextToken();
            if (JSONToken == JSONToken.RBRACE) {
                return object;
            } else if (JSONToken == JSONToken.COMMA && !isFirst) {
                nextToken();
            } else if (JSONToken != JSONToken.COMMA && !isFirst) {
                throw new JSONParserException("expect \",\"");
            }
            if (JSONToken == JSONToken.STRING) {
                key = JSONToken.getData();
            } else {
                throw new JSONParserException("expect string type");
            }
            nextToken();
            if (JSONToken != JSONToken.COLON) {
                throw new JSONParserException("expect \":\"");
            }
            nextToken();
            if (JSONToken == JSONToken.LBRACE) {
                value = parseObject();
            } else if (JSONToken == JSONToken.LBRACKET) {
                value = parseArray();
            } else {
                value = new JSONElement(JSONToken);
            }
            object.addKeyAndValue(key, value);
            if (isFirst) isFirst = false;
        }
    }

    private JSONArray parseArray() {
        JSONArray array = new JSONArray();

        boolean isFirst = true;
        for (;;) {
            nextToken();
            if (JSONToken == JSONToken.RBRACKET) {
                return array;
            } else if (JSONToken == JSONToken.COMMA && !isFirst) {
                nextToken();
            } else if (JSONToken != JSONToken.COMMA && !isFirst) {
                throw new JSONParserException("expect \",\"");
            }
            if (JSONToken == JSONToken.LBRACKET) {
                array.addValue(parseArray());
            } else if (JSONToken == JSONToken.LBRACE) {
                array.addValue(parseObject());
            } else {
                array.addValue(new JSONElement(JSONToken));
            }
            if (isFirst) isFirst = false;
        }
    }


}

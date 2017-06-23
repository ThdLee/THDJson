package com.jsonparser;

import com.jsonparser.entity.*;
import com.jsonparser.exception.JsonException;
import com.jsonparser.exception.JsonParserException;

/**
 * Created by Theodore on 2017/6/7.
 */
public class Parser {

    private Lexer lexer;

    private JsonFormat jsonFormat;

    private Token token;    // current token to handle

    /**
     * Covert json string to JsonFormat class
     * @param json
     * @return formatted json class
     * @throws JsonException
     */
    public JsonFormat parseJson(String json) throws JsonException {
        init(json);
        nextToken();
        if (token == Token.LBRACE) {
            jsonFormat = parseObject();
        } else if (token == Token.LBRACKET) {
            jsonFormat = parseArray();
        } else {
            jsonFormat = null;
        }
        return jsonFormat;
    }

    /**
     * Init class before parse json token
     * @param json
     */
    private void init(String json) {
        lexer = new Lexer(json);
        token = null;
        jsonFormat = null;
    }

    private Token nextToken() throws JsonException {
        return token = lexer.nextToken();
    }

    private JsonObject parseObject() throws JsonException {
        JsonObject object = new JsonObject();

        String key;
        JsonValue value = null;

        boolean isFirst = true;
        for (;;) {
            nextToken();
            if (token == Token.RBRACE) {
                return object;
            } else if (token == Token.COMMA && !isFirst) {
                nextToken();
            } else if (token != Token.COMMA && !isFirst) {
                throw new JsonParserException("expect \",\"");
            }
            if (token == Token.STRING) {
                key = token.getData();
            } else {
                throw new JsonParserException("expect string type");
            }
            nextToken();
            if (token != Token.COLON) {
                throw new JsonParserException("expect \":\"");
            }
            nextToken();
            if (token == Token.LBRACE) {
                value = parseObject();
            } else if (token == Token.LBRACKET) {
                value = parseArray();
            } else {
                value = new JsonElement(token);
            }
            object.addKeyAndValue(key, value);
            if (isFirst) isFirst = false;
        }
    }

    private JsonArray parseArray() throws JsonException {
        JsonArray array = new JsonArray();

        boolean isFirst = true;
        for (;;) {
            nextToken();
            if (token == Token.RBRACKET) {
                return array;
            } else if (token == Token.COMMA && !isFirst) {
                nextToken();
            } else if (token != Token.COMMA && !isFirst) {
                throw new JsonParserException("expect \",\"");
            }
            if (token == Token.LBRACKET) {
                array.addValue(parseArray());
            } else if (token == Token.LBRACE) {
                array.addValue(parseObject());
            } else {
                array.addValue(new JsonElement(token));
            }
            if (isFirst) isFirst = false;
        }
    }


}

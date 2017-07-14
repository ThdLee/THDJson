package com.thdjson.exception;

/**
 * Created by Theodore on 2017/6/6.
 */
public class JsonLexerException extends JsonException {

    public JsonLexerException() {
        super();
    }

    public JsonLexerException(String msg) {
        super(msg);
    }

    public JsonLexerException(char ch) {
        super("unable to recognize: " + ch);
    }

}

package com.thdjson.exception;

/**
 * @author ThdLee
 */
public class JSONLexerException extends JSONException {

    public JSONLexerException() {
        super();
    }

    public JSONLexerException(String msg) {
        super(msg);
    }

    public JSONLexerException(char ch) {
        super("unable to recognize: " + ch);
    }

}

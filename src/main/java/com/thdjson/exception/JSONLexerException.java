package com.thdjson.exception;

/**
 * Created by ThdLee on 2017/6/6.
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

package com.thdjson;

/**
 * Created by ThdLee on 2017/6/6.
 */
public enum JSONToken {
    /* json token */
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    TRUE("true", "true"),
    FALSE("false", "false"),
    NULL("null", "null"),
    LBRACE("{", "{"),
    RBRACE("}", "}"),
    LBRACKET("[", "["),
    RBRACKET("]", "]"),
    COMMA(",", ","),
    COLON(":", ":"),;

    /*********************/
    private JSONToken(String name) {
        this.name = name;
    }

    private JSONToken(String name, String data) {
        this.name = name;
        this.data = data;
    }

    private String name;
    private String data;

    public String getName() {
        return name;
    }

    public String  getData() {
        return data;
    }

    public JSONToken addData(String data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return data;
    }
}

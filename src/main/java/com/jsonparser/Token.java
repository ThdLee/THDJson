package com.jsonparser;

/**
 * Created by Theodore on 2017/6/6.
 */
public enum Token {
    ERROR("error"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    TRUE("true", "true"),
    FALSE("false", "false"),
    NULL("null", "null"),
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{", "{"),
    RBRACE("}", "}"),
    LBRACKET("[", "["),
    RBRACKET("]", "]"),
    COMMA(",", ","),
    COLON(":", ":"),
    SEMICOLON(";"),
    DOT("."),
    IDENTIFIER("identifier"),
    UNDEFINED("undefined"),
    END("end");

    /*********************/
    private Token(String name) {
        this.name = name;
    }

    private Token(String name, String data) {
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

    public Token addData(String data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return data;
    }
}

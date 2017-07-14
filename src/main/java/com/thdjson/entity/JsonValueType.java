package com.thdjson.entity;

/**
 * Created by Theodore on 2017/6/9.
 */
public enum JsonValueType {
    OBJECT("object"),
    ARRAY("array"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    BOOL("bool"),
    NULL("null"),;


    /*********************/

    private String type;

    private JsonValueType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

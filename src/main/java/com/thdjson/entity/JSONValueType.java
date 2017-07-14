package com.thdjson.entity;

/**
 * Created by Theodore on 2017/6/9.
 */
public enum JSONValueType {
    OBJECT("object"),
    ARRAY("array"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    BOOL("bool"),
    NULL("null"),;


    /*********************/

    private String type;

    private JSONValueType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

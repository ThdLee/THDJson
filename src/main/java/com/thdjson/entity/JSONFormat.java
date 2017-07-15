package com.thdjson.entity;

/**
 * Created by Theodore on 2017/6/7.
 */
public abstract class JSONFormat implements JSONValue {

    protected String space = "    ";

    protected String withSpace(int layer) {
        StringBuilder strbuf = new StringBuilder();
        for(int i = 0; i < layer; i++) {
            strbuf.append(space);
        }
        return strbuf.toString();
    }

    /**
     *  Formatting json string
     */
    protected String matchFormat(Object object, int layer) {
        if (object instanceof JSONFormat) {
            return ((JSONFormat) object).format(layer);
        }
        return object.toString();
    }

    abstract String format(int layer);

}

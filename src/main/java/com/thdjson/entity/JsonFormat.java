package com.thdjson.entity;

/**
 * Created by Theodore on 2017/6/7.
 */
public abstract class JsonFormat implements JsonValue {

    protected String space = "    ";

    protected String withSpace(int layer) {
        StringBuilder strbuf = new StringBuilder();
        for(int i = 0; i < layer; i++) {
            strbuf.append(space);
        }
        return strbuf.toString();
    }

    protected String matchFormat(Object object, int layer) {
        if (object instanceof JsonFormat) {
            return ((JsonFormat) object).format(layer);
        }
        return object.toString();
    }

    abstract String format(int layer);

}

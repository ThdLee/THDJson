package com.thdjson;

/**
 * Created by Theodore on 2017/7/17.
 */
public enum  JSONSerializerFeature {
    /* Serialize fields with lower case */
    CASE_INSENSITIVE,
    /* Only Serialize public fields of object */
    ONLY_PUBLIC;

    public final int mask;

    JSONSerializerFeature() {
        mask = 1 << ordinal();
    }

    public final int getMask() {
        return mask;
    }
}

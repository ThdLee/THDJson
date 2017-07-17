package com.thdjson;

/**
 * Created by ThdLee on 2017/7/17.
 */
public enum  JSONSerializerFeature {
    /* Serialize fields with lower case */
    CaseInsensitive,
    /* Only Serialize fields with modifiers except public */
    AllowNonPublic;

    public final int mask;

    JSONSerializerFeature() {
        mask = 1 << ordinal();
    }

    public final int getMask() {
        return mask;
    }
}

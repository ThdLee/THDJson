package com.thdjson;

/**
 * @author ThdLee
 */
public enum  JSONSerializerFeature {
    /* Serialize key string with lower case */
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

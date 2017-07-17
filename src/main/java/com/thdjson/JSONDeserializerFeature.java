package com.thdjson;

/**
 * Created by ThdLee on 2017/7/17.
 */
public enum JSONDeserializerFeature {
    /* Deserialize key string with case insensitive */
    CaseInsensitive,
    /* Only deserialize fields with modifiers except public */
    AllowNonPublic,
    /* */
    IgnoreNotMatch;

    public final int mask;

    JSONDeserializerFeature() {
        mask = 1 << ordinal();
    }

    public final int getMask() {
        return mask;
    }
}

package com.thdjson;

/**
 * @author ThdLee
 */
public enum JSONDeserializerFeature {
    /* Deserialize key string with lower case */
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

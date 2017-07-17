package com.thdjson;

/**
 * Created by Theodore on 2017/7/17.
 */
public enum JSONDeserializerFeature {
    /* Deserialize fields with case insensitive */
    CASE_INSENSITIVE,
    /* Only deserialize public fields of object */
    ONLY_PUBLIC;

    public final int mask;

    JSONDeserializerFeature() {
        mask = 1 << ordinal();
    }

    public final int getMask() {
        return mask;
    }
}

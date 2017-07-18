/*
 * Copyright 2017 ThdLee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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

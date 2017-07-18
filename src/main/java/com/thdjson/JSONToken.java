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
public enum JSONToken {
    /* json token */
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    TRUE("true", "true"),
    FALSE("false", "false"),
    NULL("null", "null"),
    LBRACE("{", "{"),
    RBRACE("}", "}"),
    LBRACKET("[", "["),
    RBRACKET("]", "]"),
    COMMA(",", ","),
    COLON(":", ":"),;

    /*********************/
    private JSONToken(String name) {
        this.name = name;
    }

    private JSONToken(String name, String data) {
        this.name = name;
        this.data = data;
    }

    private String name;
    private String data;

    public String getName() {
        return name;
    }

    public String  getData() {
        return data;
    }

    public JSONToken addData(String data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return data;
    }
}

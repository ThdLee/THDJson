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

package com.thdjson.entity;

/**
 * @author ThdLee
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

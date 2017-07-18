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

import com.thdjson.JSONToken;
import com.thdjson.exception.JSONParserException;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author ThdLee
 */
public class JSONElement implements JSONValue {

    private JSONValueType type;

    private String value;

    public JSONElement(JSONToken jsonToken) throws JSONParserException {
        switch (jsonToken) {
            case STRING:
                type = JSONValueType.STRING;
                break;
            case NULL:
                type = JSONValueType.NULL;
                break;
            case TRUE:
            case FALSE:
                type = JSONValueType.BOOL;
                break;
            case FLOAT:
                type = JSONValueType.FLOAT;
                break;
            case INT:
                type = JSONValueType.INT;
                break;
            default:
                throw new JSONParserException();
        }
        value = jsonToken.getData();
    }

    public JSONElement(String value, JSONValueType type) {
        this.type = type;
        this.value = value;
    }

    public Byte getByte() {
        if (type == JSONValueType.FLOAT) {
            return Byte.parseByte(value.substring(0, value.indexOf('.')));
        }
        if (type != JSONValueType.INT || value == null) {
            return null;
        }
        return Byte.parseByte(value);
    }

    public byte getByteValue() {
        if (type == JSONValueType.FLOAT) {
            return Byte.parseByte(value.substring(0, value.indexOf('.')));
        }
        if (type != JSONValueType.INT)
            return Byte.parseByte(value);
        return 0;
    }

    public Short getShort() {
        if (type == JSONValueType.FLOAT) {
            return Short.parseShort(value.substring(0, value.indexOf('.')));
        }
        if (type != JSONValueType.INT) {
            return null;
        }
        return Short.parseShort(value);
    }

    public short getShortValue() {
        if (type == JSONValueType.FLOAT) {
            return Short.parseShort(value.substring(0, value.indexOf('.')));
        }
        if (type == JSONValueType.INT)
            return Short.parseShort(value);
        return 0;
    }

    public Integer getInt() {
        if (type == JSONValueType.FLOAT) {
            return Integer.parseInt(value.substring(0, value.indexOf('.')));
        }
        if (type != JSONValueType.INT) {
            return null;
        }
        return Integer.parseInt(value);
    }

    public int getIntValue() {
        if (type == JSONValueType.FLOAT) {
            return Integer.parseInt(value.substring(0, value.indexOf('.')));
        }
        if (type == JSONValueType.INT)
            return Integer.parseInt(value);
        return 0;
    }

    public Long getLong() {
        if (type == JSONValueType.FLOAT) {
            return Long.parseLong(value.substring(0, value.indexOf('.')));
        }
        if (type != JSONValueType.INT) {
            return null;
        }
        return Long.parseLong(value);
    }

    public long getLongValue() {
        if (type == JSONValueType.FLOAT) {
            return Long.parseLong(value.substring(0, value.indexOf('.')));
        }
        if (type == JSONValueType.INT)
            return Long.parseLong(value);
        return 0;
    }

    public Boolean getBoolean() {
        if (type != JSONValueType.BOOL) {
            return null;
        }
        return Boolean.parseBoolean(value);
    }

    public boolean getBooleanValue() {
        if (type == JSONValueType.BOOL)
            return Boolean.parseBoolean(value);
        return false;
    }

    public Float getFloat() {
        if (type != JSONValueType.FLOAT && type != JSONValueType.INT) {
            return null;
        }
        return Float.parseFloat(value);
    }

    public float getFloatValue() {
        if (type == JSONValueType.FLOAT || type == JSONValueType.INT)
            return Float.parseFloat(value);
        return 0;
    }

    public Double getDouble() {
        if (type != JSONValueType.FLOAT && type != JSONValueType.INT) {
            return null;
        }
        return Double.parseDouble(value);
    }

    public double getDoubleValue() {
        if (type == JSONValueType.FLOAT || type == JSONValueType.INT)
            return Double.parseDouble(value);
        return 0;
    }

    public BigDecimal getBigDecimal() {
        if (type != JSONValueType.FLOAT && type != JSONValueType.INT) {
            return null;
        }
        return new BigDecimal(value);
    }

    public BigInteger getBigInteger() {
        if (type == JSONValueType.FLOAT) {
            return new BigInteger(value.substring(0, value.indexOf('.')));
        }
        if (type != JSONValueType.INT) {
            return null;
        }
        return new BigInteger(value);
    }

    public String getString() {
        if (type == JSONValueType.NULL)
            return null;
        return value;
    }

    @Override
    public String toString() {
        if (type == JSONValueType.STRING) return "\"" + value + "\"";
        return value;
    }

    public JSONValueType getType() {
        return type;
    }
}

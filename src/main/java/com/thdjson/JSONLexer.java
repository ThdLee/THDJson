package com.thdjson;

import com.thdjson.exception.JSONLexerException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by ThdLee on 2017/6/6.
 */
public class JSONLexer {
    private static final char EOI = 0x1A;

    private static final char[] FilterChar = new char[] {
            '\b', '\f', '\r', ' ', '\t',
    };
    /**
     * Characters to end token
     */
    private final static HashSet<Character> EndSigns = new HashSet<Character>() {{
        add(' ');
        add(',');
        add('}');
        add(']');
        add('\n');
        add('\r');
        add('\t');
        add(EOI);
        add('\f');
        add('\b');
    }};
    /**
     * Escape characters in string to map
     */
    private final static Map<Character, Character> EscapeChars = new HashMap<Character, Character>() {{
        put('b', '\b');
        put('t', '\t');
        put('n', '\n');
        put('v', '\u0008');
        put('f', '\f');
        put('F', '\f');
        put('r', '\r');
        put('"', '\"');
        put('\'', '\'');
        put('/', '/');
        put('\\', '\\');
    }};


    private String jsonString;  // json to analyse
    private int len;    // json string length

    private int cur = -1;   // current position for json string

    private JSONToken JSONToken;

    private char ch = '\0';

    private StringBuilder readBuffer = null;    // record token string

    public JSONLexer(String json) {
        this.jsonString = json;
        len = json.length();
    }

    /**
     * Get Token from JsonFormat String successively/
     * @return Token or null(no token can be found)
     * @throws JSONLexerException
     */
    public JSONToken nextToken() throws JSONLexerException {
        nextChar();

        while (include(FilterChar, ch) || ch == '\n') {
            nextChar();
        }
        if (isEOF()) {
            JSONToken = null;
        } else if (ch == '{') {
            JSONToken = JSONToken.LBRACE;
        } else if (ch == '}') {
            JSONToken = JSONToken.RBRACE;
        } else if (ch == '[') {
            JSONToken = JSONToken.LBRACKET;
        } else if (ch == ']') {
            JSONToken = JSONToken.RBRACKET;
        } else if (ch == ':') {
            JSONToken = JSONToken.COLON;
        } else if (ch == ',') {
            JSONToken = JSONToken.COMMA;
        } else if (ch == 'n') {
            scanNull();
        } else if (ch == 'f') {
            scanFalse();
        } else if (ch == 't') {
            scanTrue();
        } else if (inNumberSet(ch)) {
            scanNumber();
        } else if (ch == '\"') {
            scanString();
        } else {
            throw new JSONLexerException(ch);
        }

        return JSONToken;
    }

    private char nextChar() throws JSONLexerException {
        if (cur >= len - 1) {
            return ch = '\0';
        }
        ch = jsonString.charAt(++cur);
        return ch;
    }

    private void scanString() throws JSONLexerException {
        nextChar();
        refreshBuffer();
        while (ch != '\"') {

            if (ch == EOI || ch == '\0') {
                if (!isEOF()) {
                    readBuffer.append(ch);
                    continue;
                }
                throw new JSONLexerException("unclosed string : " + readBuffer.toString());
            } else if (ch == '\\') {
                nextChar();

                if (EscapeChars.containsKey(ch)) {
                    readBuffer.append(EscapeChars.get(ch));
                } else if (ch == 'x') {
                    char x1 = nextChar();
                    char x2 = nextChar();
                    int x_val = digits[x1] * 16 + digits[x2];
                    char x_char = (char) x_val;
                    readBuffer.append(x_char);
                } else if (ch == 'u') {
                    char u1 = nextChar();
                    char u2 = nextChar();
                    char u3 = nextChar();
                    char u4 = nextChar();
                    int val = Integer.parseInt(new String(new char[] { u1, u2, u3, u4 }), 16);
                    readBuffer.append((char)val);
                } else {
                    throw new JSONLexerException("error string : " + readBuffer.toString());
                }
            } else {
                readBuffer.append(ch);
            }
            nextChar();
        }
        JSONToken = JSONToken.STRING.addData(readBuffer.toString());
    }

    private void scanTrue() throws JSONLexerException {
        nextChar();
        if (ch != 'r') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 'u') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 'e') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (EndSigns.contains(ch)) {
            JSONToken = JSONToken.TRUE;
            cur--;
        } else {
            throw new JSONLexerException(ch);
        }
    }

    private void scanFalse() throws JSONLexerException {
        nextChar();
        if (ch != 'a') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 'l') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 's') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 'e') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (EndSigns.contains(ch)) {
            JSONToken = JSONToken.FALSE;
            cur--;
        } else {
            throw new JSONLexerException(ch);
        }
    }

    private void scanNull() throws JSONLexerException {
        nextChar();
        if (ch != 'u') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 'l') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (ch != 'l') {
            throw new JSONLexerException(ch);
        }
        nextChar();
        if (EndSigns.contains(ch)) {
            JSONToken = JSONToken.NULL;
            cur--;
        } else {
            throw new JSONLexerException(ch);
        }
    }

    private void scanNumber() throws JSONLexerException {
        refreshBuffer();
        if (ch == '+' || ch == '-' ) {
            readBuffer.append(ch);
            nextChar();
        }
        while (ch >= '0' && ch <= '9') {
            readBuffer.append(ch);
            nextChar();
        }
        boolean isDouble = false;

        if (ch == '.') {
            readBuffer.append(ch);
            nextChar();
            isDouble = true;

            while (ch >= '0' && ch <= '9') {
                readBuffer.append(ch);
                nextChar();
            }
        }

        if (ch == 'e' || ch == 'E') {
            readBuffer.append(ch);
            nextChar();

            if (ch == '+' || ch == '-' ) {
                readBuffer.append(ch);
                nextChar();
            }

            while (ch >= '0' && ch <= '9') {
                readBuffer.append(ch);
                nextChar();
            }
            isDouble = true;
        }
        cur--;
        if (isDouble) {
            JSONToken = JSONToken.FLOAT.addData(readBuffer.toString());
        } else {
            JSONToken = JSONToken.INT.addData(readBuffer.toString());
        }
    }

    private void refreshBuffer(char c) {
        readBuffer = new StringBuilder();
        readBuffer.append(c);
    }

    private void refreshBuffer() {
        readBuffer = new StringBuilder();
    }

    private boolean inNumberSet(char c) {
        return (c >= '0' & c <= '9' ) || c == '+' || c == '-';
    }

    private boolean isEOF() {
        return cur >= len || ch == EOI || ch == '\0';
    }

    private boolean include(char[] range, char c) {
        boolean include = false;
        for(int i = 0; i < range.length; i++) {
            if(range[i] == c) {
                include = true;
                break;
            }
        }
        return include;
    }

    private final static int[] digits = new int[(int)'f' + 1];

    static {
        for (int i = '0'; i <= '9'; ++i) {
            digits[i] = i - '0';
        }
        for (int i = 'a'; i <= 'f'; ++i) {
            digits[i] = (i - 'a') + 10;
        }
        for (int i = 'A'; i <= 'F'; ++i) {
            digits[i] = (i - 'A') + 10;
        }
    }
}

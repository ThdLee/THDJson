import com.thdjson.JSONLexer;
import com.thdjson.JSONParser;
import com.thdjson.JSONToken;
import org.junit.Test;

/**
 * Created by Theodore on 2017/6/7.
 */
public class JSONParserTrueTest {

    @Test
    public void testA() {
        System.out.println("***********************************");
        String json = "{\n" +
                "    \"users\":[\n" +
                "        {\n" +
                "            \"name\":\"Alice\",\n" +
                "            \"sex\":\"female\"\n" +
                "        },\n" +
                "\t\t{\n" +
                "            \"name\":\"Bob\",\n" +
                "            \"sex\":\"male\"\n" +
                "        }\n" +
                "    ]\n" +
                "}}";
        JSONLexer lexer = new JSONLexer(json);
        JSONToken token;
        while ((token = lexer.nextToken()) != null) {
            System.out.println(true);
        }
        JSONParser JSONParser = new JSONParser();
        System.out.println(JSONParser.parseJson(json));
        System.out.println("***********************************");
    }

    @Test
    public void testB() {
        System.out.println("***********************************");
        String json = "{\n" +
                "    \"users\":[\n" +
                "        {\n" +
                "            \"name\":\"Alice\",\n" +
                "            \"sex\":\"female\",\n" +
                "            \"age\":20,\n" +
                "            \"married\":false,\n" +
                "            \"vip\":true,\n" +
                "            \"introduction\":null\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONLexer lexer = new JSONLexer(json);
        JSONToken token;
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        JSONParser parser = new JSONParser();
            System.out.println(parser.parseJson(json));
        System.out.println("***********************************");
    }

    @Test
    public void testC() {
        System.out.println("***********************************");
        String json = "{\n" +
                "    \"users\":[\n" +
                "        {\n" +
                "            \"name\":\"Alice\",\n" +
                "            \"sex\":\"female\",\n" +
                "            \"age\":20,\n" +
                "            \"married\":false,\n" +
                "            \"vip\":true,\n" +
                "            \"introduction\":null,\n" +
                "            \"accounts\":2.3333333\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONLexer lexer = new JSONLexer(json);
        JSONToken token;
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        JSONParser parser = new JSONParser();
        System.out.println(parser.parseJson(json));
        System.out.println("***********************************");
    }

    @Test
    public void testD() {
        System.out.println("***********************************");
        String json = "{\n" +
                "    \"users\":[\n" +
                "        {\n" +
                "            \"name\":\"Alice\",\n" +
                "            \"sex\":\"female\",\n" +
                "            \"age\":20,\n" +
                "            \"married\":false,\n" +
                "            \"vip\":true,\n" +
                "            \"introduction\":null,\n" +
                "            \"accounts\":2.333e+233\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONLexer lexer = new JSONLexer(json);
        JSONToken token;
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        JSONParser JSONParser = new JSONParser();
        System.out.println(JSONParser.parseJson(json));
        System.out.println("***********************************");
    }

}

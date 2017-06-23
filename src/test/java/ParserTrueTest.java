import com.jsonparser.Lexer;
import com.jsonparser.Parser;
import com.jsonparser.Token;
import org.junit.Test;

/**
 * Created by Theodore on 2017/6/7.
 */
public class ParserTrueTest {

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
        Lexer lexer = new Lexer(json);
        Token token;
        try {
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        Parser parser = new Parser();
            System.out.println(parser.parseJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Lexer lexer = new Lexer(json);
        Token token;
        try {
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        Parser parser = new Parser();
            System.out.println(parser.parseJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Lexer lexer = new Lexer(json);
        Token token;
        try {
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        Parser parser = new Parser();
            System.out.println(parser.parseJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Lexer lexer = new Lexer(json);
        Token token;
        try {
        while ((token = lexer.nextToken()) != null) {
            System.out.println(token);
        }
        Parser parser = new Parser();
            System.out.println(parser.parseJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("***********************************");
    }

}

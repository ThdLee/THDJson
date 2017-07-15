import com.thdjson.JSONParser;
import org.junit.Test;

/**
 * All tests in this class will fail
 * Created by Theodore on 2017/6/7.
 */
public class JSONParserFalseTest {

    @Test
    public void testA() {
        System.out.println("***********************************");
        String json = "{\n" +
                "    \"users\"[\n" +
                "        {\n" +
                "            \"name\":\"Alice\",\n" +
                "            \"sex\":\"female\"\n" +
                "        }\n" +
                "\t\t{\n" +
                "            \"name\":\"Bob\",\n" +
                "            \"sex\":\"male\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONParser parser = new JSONParser();
        System.out.println(parser.parseJson(json));
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
                "            \"age\":2hh0,\n" +
                "            \"married\":flase,\n" +
                "            \"vip\":ture,\n" +
                "            \"introduction\":nul\n" +
                "        }\n" +
                "    ]\n" +
                "}";

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
                "            \"accounts\":2.3333333.333\n" +
                "        }\n" +
                "    ]\n" +
                "}";
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
                "            \"accounts\":2.333e23.3\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONParser parser = new JSONParser();
        System.out.println(parser.parseJson(json));
        System.out.println("***********************************");
    }
}

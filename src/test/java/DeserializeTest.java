import com.jsonparser.*;
import com.jsonparser.entity.JsonFormat;
import org.junit.Test;

/**
 * Created by Theodore on 2017/7/12.
 */
public class DeserializeTest {

    @Test
    public void test() {
        String json ="[\n" +
                "        {\n" +
                "            \"name\":\"Alice\",\n" +
                "            \"sex\":\"female\",\n" +
                "            \"age\":20,\n" +
                "            \"married\":false,\n" +
                "            \"vip\":true,\n" +
                "            \"introduction\":null\n" +
                "        }\n" +
                " ]";

        Lexer lexer = new Lexer(json);
        Token token;
        try {
            Parser parser = new Parser();
            JsonFormat jsonFormat = parser.parseJson(json);
            Deserializer deserializer = new Deserializer();
            System.out.println(User[].class);
            User[] user = deserializer.deserializable(jsonFormat, User[].class);
            System.out.println(user[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

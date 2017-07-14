import com.thdjson.*;
import com.thdjson.entity.JsonFormat;
import org.junit.Test;

/**
 * Created by Theodore on 2017/7/12.
 */
public class DeserializeTest {

    static final         String json ="{\n" +
            "    \"RECORDS\": [\n" +
            "        {\n" +
            "            \"Id\": 1,\n" +
            "            \"countryname\": \"Angola\",\n" +
            "            \"countrycode\": \"AO\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 2,\n" +
            "            \"countryname\": \"Afghanistan\",\n" +
            "            \"countrycode\": \"AF\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 3,\n" +
            "            \"countryname\": \"Albania\",\n" +
            "            \"countrycode\": \"AL\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 4,\n" +
            "            \"countryname\": \"Algeria\",\n" +
            "            \"countrycode\": \"DZ\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 5,\n" +
            "            \"countryname\": \"Andorra\",\n" +
            "            \"countrycode\": \"AD\"\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";

    @Test
    public void testOnlyPublic() {

        try {
            Parser parser = new Parser();
            JsonFormat jsonFormat = parser.parseJson(json);
            Deserializer deserializer = new Deserializer(true, true);
            Information test = deserializer.deserialize(jsonFormat, Information.class);
            System.out.println(test);
            Serializer serializer = new Serializer(true, true);
            System.out.println(serializer.serialize(test, Information.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotOnlyPublic() {

        try {
            Parser parser = new Parser();
            JsonFormat jsonFormat = parser.parseJson(json);
            Deserializer deserializer = new Deserializer(true, false);
            Information test = deserializer.deserialize(jsonFormat, Information.class);
            System.out.println(test);
            Serializer serializer = new Serializer(true, false);
            System.out.println(serializer.serialize(test, Information.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Information {
        public User[] records;

        @Override
        public String toString() {
            String str = "";
            for (User user : records) {
                str += user;
            }
            return str;
        }
    }

    public static class User {
        public int Id;
        public String countrycode;
        private String countryname;

        public User() {}

        @Override
        public String toString() {
            return "" + Id + " " + countrycode + " " + countryname + "\n" ;
        }
    }

}



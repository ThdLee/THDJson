import com.jsonparser.Parser;
import org.junit.Test;

/**
 * Created by Theodore on 2017/6/7.
 */
public class ParserPrettyTest {

    @Test
    public void formattingTest() {
        System.out.println("***********************************");
        String json = "{\"RECORDS\":[{\"Id\":1,\n" +
                "\"countryname\":\"Angola\",\"countrycode\":\"AO\"},{\"Id\":2,\"countryname\":\"Afghanistan\",\n" +
                "\"countrycode\":\"AF\"},{\"Id\":3,\"countryname\":\"Albania\",\n" +
                "\"countrycode\":\"AL\"},{\"Id\":4,\"countryname\":\"Algeria\",\"countrycode\":\"DZ\"},{\"Id\":5,\"countryname\":\"Andorra\",\"countrycode\":\"AD\"},{\"Id\":6,\"countryname\":\"Anguilla\",\"countrycode\":\"AI\"},{\"Id\":7,\"countryname\":\"Antigua and Barbuda\",\"countrycode\":\"AG\"},{\"Id\":8,\n" +
                "\"countryname\":\"Argentina\",\n" +
                "\"countrycode\":\"AR\"},{\n" +
                "\"Id\":9,\n" +
                "\"countryname\":\"Armenia\",\n" +
                "\"countrycode\":\"AM\"}]}";
        Parser parser = new Parser();
        try {
            System.out.println(parser.parseJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("***********************************");
    }
}

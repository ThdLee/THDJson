import com.jsonparser.*;
import com.jsonparser.entity.JsonFormat;
import org.junit.Test;

/**
 * Created by Theodore on 2017/7/12.
 */
public class DeserializeTest {

    @Test
    public void test() {
        String json ="{\n" +
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
                "        },\n" +
                "        {\n" +
                "            \"Id\": 6,\n" +
                "            \"countryname\": \"Anguilla\",\n" +
                "            \"countrycode\": \"AI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 7,\n" +
                "            \"countryname\": \"Antigua and Barbuda\",\n" +
                "            \"countrycode\": \"AG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 8,\n" +
                "            \"countryname\": \"Argentina\",\n" +
                "            \"countrycode\": \"AR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 9,\n" +
                "            \"countryname\": \"Armenia\",\n" +
                "            \"countrycode\": \"AM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 10,\n" +
                "            \"countryname\": \"Australia\",\n" +
                "            \"countrycode\": \"AU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 11,\n" +
                "            \"countryname\": \"Austria\",\n" +
                "            \"countrycode\": \"AT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 12,\n" +
                "            \"countryname\": \"Azerbaijan\",\n" +
                "            \"countrycode\": \"AZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 13,\n" +
                "            \"countryname\": \"Bahamas\",\n" +
                "            \"countrycode\": \"BS\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 14,\n" +
                "            \"countryname\": \"Bahrain\",\n" +
                "            \"countrycode\": \"BH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 15,\n" +
                "            \"countryname\": \"Bangladesh\",\n" +
                "            \"countrycode\": \"BD\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 16,\n" +
                "            \"countryname\": \"Barbados\",\n" +
                "            \"countrycode\": \"BB\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 17,\n" +
                "            \"countryname\": \"Belarus\",\n" +
                "            \"countrycode\": \"BY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 18,\n" +
                "            \"countryname\": \"Belgium\",\n" +
                "            \"countrycode\": \"BE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 19,\n" +
                "            \"countryname\": \"Belize\",\n" +
                "            \"countrycode\": \"BZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 20,\n" +
                "            \"countryname\": \"Benin\",\n" +
                "            \"countrycode\": \"BJ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 21,\n" +
                "            \"countryname\": \"Bermuda Is.\",\n" +
                "            \"countrycode\": \"BM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 22,\n" +
                "            \"countryname\": \"Bolivia\",\n" +
                "            \"countrycode\": \"BO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 23,\n" +
                "            \"countryname\": \"Botswana\",\n" +
                "            \"countrycode\": \"BW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 24,\n" +
                "            \"countryname\": \"Brazil\",\n" +
                "            \"countrycode\": \"BR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 25,\n" +
                "            \"countryname\": \"Brunei\",\n" +
                "            \"countrycode\": \"BN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 26,\n" +
                "            \"countryname\": \"Bulgaria\",\n" +
                "            \"countrycode\": \"BG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 27,\n" +
                "            \"countryname\": \"Burkina-faso\",\n" +
                "            \"countrycode\": \"BF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 28,\n" +
                "            \"countryname\": \"Burma\",\n" +
                "            \"countrycode\": \"MM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 29,\n" +
                "            \"countryname\": \"Burundi\",\n" +
                "            \"countrycode\": \"BI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 30,\n" +
                "            \"countryname\": \"Cameroon\",\n" +
                "            \"countrycode\": \"CM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 31,\n" +
                "            \"countryname\": \"Canada\",\n" +
                "            \"countrycode\": \"CA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 32,\n" +
                "            \"countryname\": \"Central African Republic\",\n" +
                "            \"countrycode\": \"CF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 33,\n" +
                "            \"countryname\": \"Chad\",\n" +
                "            \"countrycode\": \"TD\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 34,\n" +
                "            \"countryname\": \"Chile\",\n" +
                "            \"countrycode\": \"CL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 35,\n" +
                "            \"countryname\": \"China\",\n" +
                "            \"countrycode\": \"CN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 36,\n" +
                "            \"countryname\": \"Colombia\",\n" +
                "            \"countrycode\": \"CO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 37,\n" +
                "            \"countryname\": \"Congo\",\n" +
                "            \"countrycode\": \"CG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 38,\n" +
                "            \"countryname\": \"Cook Is.\",\n" +
                "            \"countrycode\": \"CK\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 39,\n" +
                "            \"countryname\": \"Costa Rica\",\n" +
                "            \"countrycode\": \"CR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 40,\n" +
                "            \"countryname\": \"Cuba\",\n" +
                "            \"countrycode\": \"CU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 41,\n" +
                "            \"countryname\": \"Cyprus\",\n" +
                "            \"countrycode\": \"CY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 42,\n" +
                "            \"countryname\": \"Czech Republic\",\n" +
                "            \"countrycode\": \"CZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 43,\n" +
                "            \"countryname\": \"Denmark\",\n" +
                "            \"countrycode\": \"DK\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 44,\n" +
                "            \"countryname\": \"Djibouti\",\n" +
                "            \"countrycode\": \"DJ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 45,\n" +
                "            \"countryname\": \"Dominica Rep.\",\n" +
                "            \"countrycode\": \"DO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 46,\n" +
                "            \"countryname\": \"Ecuador\",\n" +
                "            \"countrycode\": \"EC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 47,\n" +
                "            \"countryname\": \"Egypt\",\n" +
                "            \"countrycode\": \"EG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 48,\n" +
                "            \"countryname\": \"EI Salvador\",\n" +
                "            \"countrycode\": \"SV\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 49,\n" +
                "            \"countryname\": \"Estonia\",\n" +
                "            \"countrycode\": \"EE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 50,\n" +
                "            \"countryname\": \"Ethiopia\",\n" +
                "            \"countrycode\": \"ET\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 51,\n" +
                "            \"countryname\": \"Fiji\",\n" +
                "            \"countrycode\": \"FJ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 52,\n" +
                "            \"countryname\": \"Finland\",\n" +
                "            \"countrycode\": \"FI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 53,\n" +
                "            \"countryname\": \"France\",\n" +
                "            \"countrycode\": \"FR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 54,\n" +
                "            \"countryname\": \"French Guiana\",\n" +
                "            \"countrycode\": \"GF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 55,\n" +
                "            \"countryname\": \"Gabon\",\n" +
                "            \"countrycode\": \"GA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 56,\n" +
                "            \"countryname\": \"Gambia\",\n" +
                "            \"countrycode\": \"GM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 57,\n" +
                "            \"countryname\": \"Georgia\",\n" +
                "            \"countrycode\": \"GE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 58,\n" +
                "            \"countryname\": \"Germany\",\n" +
                "            \"countrycode\": \"DE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 59,\n" +
                "            \"countryname\": \"Ghana\",\n" +
                "            \"countrycode\": \"GH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 60,\n" +
                "            \"countryname\": \"Gibraltar\",\n" +
                "            \"countrycode\": \"GI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 61,\n" +
                "            \"countryname\": \"Greece\",\n" +
                "            \"countrycode\": \"GR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 62,\n" +
                "            \"countryname\": \"Grenada\",\n" +
                "            \"countrycode\": \"GD\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 63,\n" +
                "            \"countryname\": \"Guam\",\n" +
                "            \"countrycode\": \"GU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 64,\n" +
                "            \"countryname\": \"Guatemala\",\n" +
                "            \"countrycode\": \"GT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 65,\n" +
                "            \"countryname\": \"Guinea\",\n" +
                "            \"countrycode\": \"GN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 66,\n" +
                "            \"countryname\": \"Guyana\",\n" +
                "            \"countrycode\": \"GY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 67,\n" +
                "            \"countryname\": \"Haiti\",\n" +
                "            \"countrycode\": \"HT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 68,\n" +
                "            \"countryname\": \"Honduras\",\n" +
                "            \"countrycode\": \"HN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 69,\n" +
                "            \"countryname\": \"Hongkong\",\n" +
                "            \"countrycode\": \"HK\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 70,\n" +
                "            \"countryname\": \"Hungary\",\n" +
                "            \"countrycode\": \"HU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 71,\n" +
                "            \"countryname\": \"Iceland\",\n" +
                "            \"countrycode\": \"IS\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 72,\n" +
                "            \"countryname\": \"India\",\n" +
                "            \"countrycode\": \"IN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 73,\n" +
                "            \"countryname\": \"Indonesia\",\n" +
                "            \"countrycode\": \"ID\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 74,\n" +
                "            \"countryname\": \"Iran\",\n" +
                "            \"countrycode\": \"IR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 75,\n" +
                "            \"countryname\": \"Iraq\",\n" +
                "            \"countrycode\": \"IQ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 76,\n" +
                "            \"countryname\": \"Ireland\",\n" +
                "            \"countrycode\": \"IE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 77,\n" +
                "            \"countryname\": \"Israel\",\n" +
                "            \"countrycode\": \"IL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 78,\n" +
                "            \"countryname\": \"Italy\",\n" +
                "            \"countrycode\": \"IT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 79,\n" +
                "            \"countryname\": \"Jamaica\",\n" +
                "            \"countrycode\": \"JM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 80,\n" +
                "            \"countryname\": \"Japan\",\n" +
                "            \"countrycode\": \"JP\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 81,\n" +
                "            \"countryname\": \"Jordan\",\n" +
                "            \"countrycode\": \"JO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 82,\n" +
                "            \"countryname\": \"Kampuchea (Cambodia )\",\n" +
                "            \"countrycode\": \"KH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 83,\n" +
                "            \"countryname\": \"Kazakstan\",\n" +
                "            \"countrycode\": \"KZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 84,\n" +
                "            \"countryname\": \"Kenya\",\n" +
                "            \"countrycode\": \"KE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 85,\n" +
                "            \"countryname\": \"Korea\",\n" +
                "            \"countrycode\": \"KR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 86,\n" +
                "            \"countryname\": \"Kuwait\",\n" +
                "            \"countrycode\": \"KW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 87,\n" +
                "            \"countryname\": \"Kyrgyzstan\",\n" +
                "            \"countrycode\": \"KG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 88,\n" +
                "            \"countryname\": \"Laos\",\n" +
                "            \"countrycode\": \"LA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 89,\n" +
                "            \"countryname\": \"Latvia\",\n" +
                "            \"countrycode\": \"LV\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 90,\n" +
                "            \"countryname\": \"Lebanon\",\n" +
                "            \"countrycode\": \"LB\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 91,\n" +
                "            \"countryname\": \"Lesotho\",\n" +
                "            \"countrycode\": \"LS\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 92,\n" +
                "            \"countryname\": \"Liberia\",\n" +
                "            \"countrycode\": \"LR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 93,\n" +
                "            \"countryname\": \"Libya\",\n" +
                "            \"countrycode\": \"LY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 94,\n" +
                "            \"countryname\": \"Liechtenstein\",\n" +
                "            \"countrycode\": \"LI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 95,\n" +
                "            \"countryname\": \"Lithuania\",\n" +
                "            \"countrycode\": \"LT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 96,\n" +
                "            \"countryname\": \"Luxembourg\",\n" +
                "            \"countrycode\": \"LU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 97,\n" +
                "            \"countryname\": \"Macao\",\n" +
                "            \"countrycode\": \"MO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 98,\n" +
                "            \"countryname\": \"Madagascar\",\n" +
                "            \"countrycode\": \"MG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 99,\n" +
                "            \"countryname\": \"Malawi\",\n" +
                "            \"countrycode\": \"MW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 100,\n" +
                "            \"countryname\": \"Malaysia\",\n" +
                "            \"countrycode\": \"MY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 101,\n" +
                "            \"countryname\": \"Maldives\",\n" +
                "            \"countrycode\": \"MV\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 102,\n" +
                "            \"countryname\": \"Mali\",\n" +
                "            \"countrycode\": \"ML\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 103,\n" +
                "            \"countryname\": \"Malta\",\n" +
                "            \"countrycode\": \"MT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 104,\n" +
                "            \"countryname\": \"Mauritius\",\n" +
                "            \"countrycode\": \"MU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 105,\n" +
                "            \"countryname\": \"Mexico\",\n" +
                "            \"countrycode\": \"MX\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 106,\n" +
                "            \"countryname\": \"Moldova, Republic of\",\n" +
                "            \"countrycode\": \"MD\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 107,\n" +
                "            \"countryname\": \"Monaco\",\n" +
                "            \"countrycode\": \"MC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 108,\n" +
                "            \"countryname\": \"Mongolia\",\n" +
                "            \"countrycode\": \"MN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 109,\n" +
                "            \"countryname\": \"Montserrat Is\",\n" +
                "            \"countrycode\": \"MS\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 110,\n" +
                "            \"countryname\": \"Morocco\",\n" +
                "            \"countrycode\": \"MA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 111,\n" +
                "            \"countryname\": \"Mozambique\",\n" +
                "            \"countrycode\": \"MZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 112,\n" +
                "            \"countryname\": \"Namibia\",\n" +
                "            \"countrycode\": \"NA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 113,\n" +
                "            \"countryname\": \"Nauru\",\n" +
                "            \"countrycode\": \"NR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 114,\n" +
                "            \"countryname\": \"Nepal\",\n" +
                "            \"countrycode\": \"NP\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 115,\n" +
                "            \"countryname\": \"Netherlands\",\n" +
                "            \"countrycode\": \"NL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 116,\n" +
                "            \"countryname\": \"New Zealand\",\n" +
                "            \"countrycode\": \"NZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 117,\n" +
                "            \"countryname\": \"Nicaragua\",\n" +
                "            \"countrycode\": \"NI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 118,\n" +
                "            \"countryname\": \"Niger\",\n" +
                "            \"countrycode\": \"NE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 119,\n" +
                "            \"countryname\": \"Nigeria\",\n" +
                "            \"countrycode\": \"NG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 120,\n" +
                "            \"countryname\": \"North Korea\",\n" +
                "            \"countrycode\": \"KP\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 121,\n" +
                "            \"countryname\": \"Norway\",\n" +
                "            \"countrycode\": \"NO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 122,\n" +
                "            \"countryname\": \"Oman\",\n" +
                "            \"countrycode\": \"OM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 123,\n" +
                "            \"countryname\": \"Pakistan\",\n" +
                "            \"countrycode\": \"PK\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 124,\n" +
                "            \"countryname\": \"Panama\",\n" +
                "            \"countrycode\": \"PA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 125,\n" +
                "            \"countryname\": \"Papua New Cuinea\",\n" +
                "            \"countrycode\": \"PG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 126,\n" +
                "            \"countryname\": \"Paraguay\",\n" +
                "            \"countrycode\": \"PY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 127,\n" +
                "            \"countryname\": \"Peru\",\n" +
                "            \"countrycode\": \"PE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 128,\n" +
                "            \"countryname\": \"Philippines\",\n" +
                "            \"countrycode\": \"PH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 129,\n" +
                "            \"countryname\": \"Poland\",\n" +
                "            \"countrycode\": \"PL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 130,\n" +
                "            \"countryname\": \"French Polynesia\",\n" +
                "            \"countrycode\": \"PF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 131,\n" +
                "            \"countryname\": \"Portugal\",\n" +
                "            \"countrycode\": \"PT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 132,\n" +
                "            \"countryname\": \"Puerto Rico\",\n" +
                "            \"countrycode\": \"PR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 133,\n" +
                "            \"countryname\": \"Qatar\",\n" +
                "            \"countrycode\": \"QA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 134,\n" +
                "            \"countryname\": \"Romania\",\n" +
                "            \"countrycode\": \"RO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 135,\n" +
                "            \"countryname\": \"Russia\",\n" +
                "            \"countrycode\": \"RU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 136,\n" +
                "            \"countryname\": \"Saint Lueia\",\n" +
                "            \"countrycode\": \"LC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 137,\n" +
                "            \"countryname\": \"Saint Vincent\",\n" +
                "            \"countrycode\": \"VC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 138,\n" +
                "            \"countryname\": \"San Marino\",\n" +
                "            \"countrycode\": \"SM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 139,\n" +
                "            \"countryname\": \"Sao Tome and Principe\",\n" +
                "            \"countrycode\": \"ST\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 140,\n" +
                "            \"countryname\": \"Saudi Arabia\",\n" +
                "            \"countrycode\": \"SA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 141,\n" +
                "            \"countryname\": \"Senegal\",\n" +
                "            \"countrycode\": \"SN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 142,\n" +
                "            \"countryname\": \"Seychelles\",\n" +
                "            \"countrycode\": \"SC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 143,\n" +
                "            \"countryname\": \"Sierra Leone\",\n" +
                "            \"countrycode\": \"SL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 144,\n" +
                "            \"countryname\": \"Singapore\",\n" +
                "            \"countrycode\": \"SG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 145,\n" +
                "            \"countryname\": \"Slovakia\",\n" +
                "            \"countrycode\": \"SK\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 146,\n" +
                "            \"countryname\": \"Slovenia\",\n" +
                "            \"countrycode\": \"SI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 147,\n" +
                "            \"countryname\": \"Solomon Is\",\n" +
                "            \"countrycode\": \"SB\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 148,\n" +
                "            \"countryname\": \"Somali\",\n" +
                "            \"countrycode\": \"SO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 149,\n" +
                "            \"countryname\": \"South Africa\",\n" +
                "            \"countrycode\": \"ZA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 150,\n" +
                "            \"countryname\": \"Spain\",\n" +
                "            \"countrycode\": \"ES\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 151,\n" +
                "            \"countryname\": \"Sri Lanka\",\n" +
                "            \"countrycode\": \"LK\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 152,\n" +
                "            \"countryname\": \"St.Lucia\",\n" +
                "            \"countrycode\": \"LC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 153,\n" +
                "            \"countryname\": \"St.Vincent\",\n" +
                "            \"countrycode\": \"VC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 154,\n" +
                "            \"countryname\": \"Sudan\",\n" +
                "            \"countrycode\": \"SD\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 155,\n" +
                "            \"countryname\": \"Suriname\",\n" +
                "            \"countrycode\": \"SR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 156,\n" +
                "            \"countryname\": \"Swaziland\",\n" +
                "            \"countrycode\": \"SZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 157,\n" +
                "            \"countryname\": \"Sweden\",\n" +
                "            \"countrycode\": \"SE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 158,\n" +
                "            \"countryname\": \"Switzerland\",\n" +
                "            \"countrycode\": \"CH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 159,\n" +
                "            \"countryname\": \"Syria\",\n" +
                "            \"countrycode\": \"SY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 160,\n" +
                "            \"countryname\": \"Taiwan\",\n" +
                "            \"countrycode\": \"TW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 161,\n" +
                "            \"countryname\": \"Tajikstan\",\n" +
                "            \"countrycode\": \"TJ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 162,\n" +
                "            \"countryname\": \"Tanzania\",\n" +
                "            \"countrycode\": \"TZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 163,\n" +
                "            \"countryname\": \"Thailand\",\n" +
                "            \"countrycode\": \"TH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 164,\n" +
                "            \"countryname\": \"Togo\",\n" +
                "            \"countrycode\": \"TG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 165,\n" +
                "            \"countryname\": \"Tonga\",\n" +
                "            \"countrycode\": \"TO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 166,\n" +
                "            \"countryname\": \"Trinidad and Tobago\",\n" +
                "            \"countrycode\": \"TT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 167,\n" +
                "            \"countryname\": \"Tunisia\",\n" +
                "            \"countrycode\": \"TN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 168,\n" +
                "            \"countryname\": \"Turkey\",\n" +
                "            \"countrycode\": \"TR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 169,\n" +
                "            \"countryname\": \"Turkmenistan\",\n" +
                "            \"countrycode\": \"TM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 170,\n" +
                "            \"countryname\": \"Uganda\",\n" +
                "            \"countrycode\": \"UG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 171,\n" +
                "            \"countryname\": \"Ukraine\",\n" +
                "            \"countrycode\": \"UA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 172,\n" +
                "            \"countryname\": \"United Arab Emirates\",\n" +
                "            \"countrycode\": \"AE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 173,\n" +
                "            \"countryname\": \"United Kiongdom\",\n" +
                "            \"countrycode\": \"GB\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 174,\n" +
                "            \"countryname\": \"United States of America\",\n" +
                "            \"countrycode\": \"US\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 175,\n" +
                "            \"countryname\": \"Uruguay\",\n" +
                "            \"countrycode\": \"UY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 176,\n" +
                "            \"countryname\": \"Uzbekistan\",\n" +
                "            \"countrycode\": \"UZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 177,\n" +
                "            \"countryname\": \"Venezuela\",\n" +
                "            \"countrycode\": \"VE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 178,\n" +
                "            \"countryname\": \"Vietnam\",\n" +
                "            \"countrycode\": \"VN\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 179,\n" +
                "            \"countryname\": \"Yemen\",\n" +
                "            \"countrycode\": \"YE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 180,\n" +
                "            \"countryname\": \"Yugoslavia\",\n" +
                "            \"countrycode\": \"YU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 181,\n" +
                "            \"countryname\": \"Zimbabwe\",\n" +
                "            \"countrycode\": \"ZW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 182,\n" +
                "            \"countryname\": \"Zaire\",\n" +
                "            \"countrycode\": \"ZR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Id\": 183,\n" +
                "            \"countryname\": \"Zambia\",\n" +
                "            \"countrycode\": \"ZM\"\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";

        Lexer lexer = new Lexer(json);
        Token token;
        try {
            Parser parser = new Parser();
            JsonFormat jsonFormat = parser.parseJson(json);
            Deserializer deserializer = new Deserializer();
            TestClass test = deserializer.deserializable(jsonFormat, TestClass.class, true);
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

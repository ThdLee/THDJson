import com.thdjson.JSON;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ThdLee on 2017/7/17.
 */
public class JSONTest {

    @Test
    public void test() {
        User son = new User("Ted", 12, null);
        User daughter = new User("Cherry", 6, null);
        User[] users = {son, daughter};
        User father = new User("Harry", 38, users);
        String json = JSON.toJSONString(father);
        System.out.println(json);
        User user = JSON.parseObject(json, User.class);
        System.out.println(user);
    }
}

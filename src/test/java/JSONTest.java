import com.thdjson.JSON;
import com.thdjson.entity.JSONArray;
import com.thdjson.entity.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by ThdLee on 2017/7/17.
 */
public class JSONTest {

    String jsonObject;
    String jsonArray;

    @Before
    public void before() {
        User son = new User("Ted", 12, null);
        User daughter = new User("Cherry", 6, null);
        User[] users = {son, daughter};
        User father = new User("Harry", 38, users);
        jsonObject = JSON.toJSONString(father);
        jsonArray = JSON.toJSONString(users);
    }

    @Test
    public void objectTest() {
        User user = JSON.parseObject(jsonObject, User.class);
        System.out.println(user);
    }

    @Test
    public void mapTest() {
        Class<?> classes[] = {String.class, int.class, User[].class};
        Map<String, Object> map = JSON.parseObjectToMap(jsonObject, classes);
        System.out.println(map);
    }

    @Test
    public void arrayTest() {
        User[] users = JSON.parseArray(jsonArray, User[].class);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void listTest() {
        List<User> list = JSON.parseList(jsonArray, User.class);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void jsonObjectTest() {
        JSONObject json = JSON.parseObject(jsonObject);
        System.out.println(json.getString("name"));
        System.out.println(json.getInt("age"));
        System.out.println(json.get("children"));
    }

    @Test
    public void jsonArrayTest() {
        JSONArray json = JSON.parseArray(jsonArray);
        System.out.println(json.getJSONObject(0).getString("name"));
        System.out.println(json.getJSONObject(1).getString("name"));
    }
}

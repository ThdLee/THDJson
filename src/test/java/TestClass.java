/**
 * Created by Theodore on 2017/7/13.
 */
public class TestClass {
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

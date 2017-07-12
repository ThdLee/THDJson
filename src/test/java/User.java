/**
 * Created by Theodore on 2017/7/12.
 */

public class User {
    public String name;
    public String sex;
    public int age;
    public boolean married;
    public boolean vip;
    public String introduction;

    public User() {}

    @Override
    public String toString() {
        return name + sex + age + married + vip + introduction;
    }
}

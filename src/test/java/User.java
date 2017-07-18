/**
 * Created by Theodore on 2017/7/17.
 */
public class User {

    public String name;
    public int age;
    public User[] children;

//    public User() {
//
//    }

    public User(String name, int age, User[] children) {
        this.name = name;
        this.age = age;
        this.children = children;
    }

    public String toString() {
        String str = "name: " + name + " age: " + age;
        if (children == null) return str;
        for (User user : children) {
            str += "\n   " + user;
        }
        return str;
    }
}

/**
 * Created by Theodore on 2017/7/12.
 */

public class User {
    public int Id;
    public String countrycode;
    public String countryname;
    public User() {}

    @Override
    public String toString() {
        return "" + Id + " " + countrycode + " " + countryname + "\n" ;
    }
}

package map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class UserTest {

    @Test
    public void whenPutToMap() {
        final Calendar birthday = Calendar.getInstance();
        birthday.set(1945, Calendar.MAY, 9);
        final User one = new User("Suzy", 2, birthday);
        final User two = new User("Suzy", 2, birthday);
        final Map<User, Object> catalog = new HashMap<>();
        catalog.put(one, "first");
        catalog.put(two, "second");
        System.out.println(catalog);
        System.out.println(System.identityHashCode(one));
        System.out.println(System.identityHashCode(two));
    }

}
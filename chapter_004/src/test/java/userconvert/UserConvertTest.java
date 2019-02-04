package userconvert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * UserConvertTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class UserConvertTest {

    @Test
    public void whenListProcessToMap() {
        UserConvert user = new UserConvert();
        User one = new User("Doyle", "London", 1);
        User two = new User("Holmes", "London", 2);
        User three = new User("Moriarty", "London", 3);
        List<User> list = new ArrayList<>(Arrays.asList(one, two, three));
        System.out.println("это лист");
        System.out.println(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(one.getId(), new User(one.getName(), one.getCity()));
        expected.put(two.getId(), new User(two.getName(), two.getCity()));
        expected.put(three.getId(), new User(three.getName(), three.getCity()));
        Map<Integer, User> result = user.process(list);
        System.out.println("это ожидание");
        System.out.println(expected);
        System.out.println("это результат");
        System.out.println(result);
        assertThat(result.toString(), is(expected.toString()));
    }
}
package userconvert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenListProcessToMap() {
        UserConvert user = new UserConvert();
        User one = new User("Doyle", "London", 1);
        User two = new User("Holmes", "London", 2);
        User three = new User("Moriarty", "London", 3);
        List<User> list = new ArrayList<>(List.of(one, two, three));
        HashMap<Integer, User> expected = new HashMap<>(Map.of(
                one.getId(), new User(one.getName(), one.getCity()),
                two.getId(), new User(two.getName(), two.getCity()),
                three.getId(), new User(three.getName(), three.getCity())));
        Map<Integer, User> result = user.process(list);
        assertThat(result.toString(), is(expected.toString()));
    }
}
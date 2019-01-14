package sortuser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SortUserTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class SortUserTest {

    @Test
    public void whenListToSetAndSort() {
        SortUser users = new SortUser();
        List<User> catalog = new ArrayList<>(
                Arrays.asList(
                        new User("Karlson", 200),
                        new User("On", 150),
                        new User("Roof", 100)));
        TreeSet<User> result = users.sort(catalog);
        TreeSet<User> expected = new TreeSet<>();
        expected.add(new User("Roof", 100));
        expected.add(new User("On", 150));
        expected.add(new User("Karlson", 200));
        assertThat(result.toString(), is(expected.toString()));
    }
}
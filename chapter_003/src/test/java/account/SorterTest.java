package account;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SorterTest {

    @Test
    public void sort() {
        Sorter sorter = new Sorter();
        List<User> input = new ArrayList<>(Arrays.asList(
                new User("Facebook", 70),
                new User("Tweeter", 60),
                new User("Github", 80)
        ));
        Set<User> result = sorter.sort(input);
        Set<User> expected = new TreeSet<>(input);
        assertThat(result, is(expected));

    }

    @Test
    public void sortByNameLength() {
        Sorter sorter = new Sorter();
        List<User> input = new ArrayList<>(Arrays.asList(
                new User("Facebook", 70),
                new User("Tweeter", 60),
                new User("Github", 80)
        ));
        List<User> expected = new ArrayList<>(Arrays.asList(
                new User("Github", 80),
                new User("Tweeter", 60),
                new User("Facebook", 70)));
        List<User> result = sorter.sortByNameLength(input);
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test
    public void sortByName() {
        Sorter sorter = new Sorter();
        List<User> input = new ArrayList<>(Arrays.asList(
                new User("Facebook", 70),
                new User("Tweeter", 60),
                new User("Github", 80)
        ));
        List<User> expected = new ArrayList<>(Arrays.asList(
                new User("Facebook", 70),
                new User("Github", 80),
                new User("Tweeter", 60)
        ));
        List<User> result = sorter.sortByNameAndAge(input);
        assertThat(result.toString(), is(expected.toString()));
    }
}
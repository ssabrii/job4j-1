package sortuser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

        @Test
        public void whenListToSetAndSort() {
            SortUser users = new SortUser();
            List<User> catalog = List.of(
                            new User("Karlan", 200),
                            new User("On", 150),
                            new User("Roof", 100));
            TreeSet<User> result = users.sort(catalog);
            TreeSet<User> expected = new TreeSet<>(Set.of(
                    new User("Roof", 100),
                    new User("On", 150),
                    new User("Karlan", 200)));
            assertThat(result.toString(), is(expected.toString()));
        }

        @Test
        public void whenListSortByNameLength() {
            SortUser users = new SortUser();
            List<User> catalog = new ArrayList<>(List.of(
                    new User("Winnie-the-Poof", 95),
                    new User("Bear", 95),
                    new User("A.A.Milne", 150)));
            List<User> result = users.sortNameLength(catalog);
            List<User> expected = List.of(
                            new User("Bear", 95),
                            new User("A.A.Milne", 150),
                            new User("Winnie-the-Poof", 95));
            assertThat(result.toString(), is(expected.toString()));
        }

        @Test
        public void whenSortByAllFields() {
            SortUser users = new SortUser();
            List<User> catalog = new ArrayList<>(
                    List.of(
                            new User("Сергей", 25),
                            new User("Иван", 30),
                            new User("Сергей", 20),
                            new User("Иван", 25)));
            List<User> expected = List.of(
                            new User("Иван", 25),
                            new User("Иван", 30),
                            new User("Сергей", 20),
                            new User("Сергей", 25));
            List<User> result = users.sortByAllFields(catalog);
            assertThat(result.toString(), is(expected.toString()));
        }
    }
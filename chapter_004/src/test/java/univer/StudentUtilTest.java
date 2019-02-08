package univer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StudentUtilTest {
    private List<Student> catalog;

    @Before
    public void fillCatalog() {
        catalog = new ArrayList<>(Arrays.asList(
                new Student("A", 10),
                null,
                new Student("B", 0),
                null,
                new Student("C", 50),
                new Student("D", 60)));

    }

    @After
    public void cleanCatalog() {
        catalog = new ArrayList<>();
    }

    @Test
    public void whenBoundGreaterTen() {
        StudentUtil util = new StudentUtil();
        final List<Student> result = util.levelOf(catalog, 10);
        final List<Student> expected = List.of(
                new Student("D", 60),
                new Student("C", 50)
        );
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test
    public void whenBoundGreaterSixty() {
        StudentUtil util = new StudentUtil();
        final List<Student> result = util.levelOf(catalog, 60);
        final List<Student> expected = List.of();
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test
    public void whenBoundLessNull() {
        StudentUtil util = new StudentUtil();
        final List<Student> result = util.levelOf(catalog, -1);
        final List<Student> expected = List.of();
        assertThat(result.toString(), is(expected.toString()));
    }
}
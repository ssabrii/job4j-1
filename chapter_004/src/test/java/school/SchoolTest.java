package school;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SchoolTest {
    private final School school = new School();
    private List<Student> students = new ArrayList<>();
    private final Predicate<Student> ss10A;
    private final Predicate<Student> ss10B;
    private final Predicate<Student> ss10C;

    {
        ss10A = student -> student.getScore() >= 70 && student.getScore() <= 100;
        ss10B = student -> student.getScore() >= 50 && student.getScore() <= 70;
        ss10C = student -> student.getScore() >= 0 && student.getScore() <= 50;
    }


    @Before
    public void beforeFillList() {
        students = new ArrayList<>();
        students.add(new Student(10, "Donald"));
        students.add(new Student(20, "Barrack"));
        students.add(new Student(30, "Marcel"));
        students.add(new Student(60, "Dart"));
        students.add(new Student(70, "Carry"));
        students.add(new Student(80, "Soul"));
        students.add(new Student(90, "Messy"));
        students.add(new Student(100, "Idea"));
    }

    @Test
    public void whenCollect10AFrom70To100() {
        final List<Student> result = school.collect(this.students, ss10A);
        assertThat(result.size(), is(4));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(students.get(4).toString())
                .append(", ")
                .append(students.get(5).toString())
                .append(", ")
                .append(students.get(6).toString())
                .append(", ")
                .append(students.get(7).toString())
                .append("]")
                .toString()
        ));
    }

    @Test
    public void whenCollect10BFrom50To70() {
        final List<Student> result = school.collect(this.students, ss10B);
        assertThat(result.size(), is(2));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(students.get(3).toString())
                .append(", ")
                .append(students.get(4).toString())
                .append("]")
                .toString()
        ));
    }

    @Test
    public void whenCollect10CFrom0TO50() {
        final List<Student> result = school.collect(this.students, ss10C);
        assertThat(result.size(), is(3));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(students.get(0).toString())
                .append(", ")
                .append(students.get(1).toString())
                .append(", ")
                .append(students.get(2).toString())
                .append("]")
                .toString()
        ));
    }

    @Test
    public void whenListToMap() {
        final Map<String, Student> resultMap = school.listToMap(students);
        final Map<String, Student> expectedMap = new HashMap<>(Map.of(
                "Soul", new Student(80, "Soul"),
                "Idea", new Student(100, "Idea"),
                "Carry", new Student(70, "Carry"),
                "Barrack", new Student(20, "Barrack"),
                "Marcel", new Student(30, "Marcel"),
                "Messy", new Student(90, "Messy"),
                "Donald", new Student(10, "Donald"),
                "Dart", new Student(60, "Dart")));
        assertTrue(resultMap.entrySet().containsAll(expectedMap.entrySet()));
        assertTrue(expectedMap.entrySet().containsAll(resultMap.entrySet()));
    }

    @Test(expected = IllegalStateException.class)
    public void whenListToMapFall() {
        students.add(new Student(80, "Soul"));
        school.listToMap(students);
    }

}


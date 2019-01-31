package school;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    private final School school = new School();
    private final List<Student> students = new ArrayList<>();
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
        students.add(new Student(10));
        students.add(new Student(20));
        students.add(new Student(30));
        students.add(new Student(40));
        students.add(new Student(50));
        students.add(new Student(60));
        students.add(new Student(70));
        students.add(new Student(80));
        students.add(new Student(90));
        students.add(new Student(100));
    }

    @Test
    public void whenCollect10AFrom70To100() {
        final List<Student> result = school.collect(this.students, ss10A);
        assertThat(result.size(), is(4));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(students.get(6).toString())
                .append(", ")
                .append(students.get(7).toString())
                .append(", ")
                .append(students.get(8).toString())
                .append(", ")
                .append(students.get(9).toString())
                .append("]")
                .toString()
        ));
    }

    @Test
    public void whenCollect10BFrom50To70() {
        final List<Student> result = school.collect(this.students, ss10B);
        assertThat(result.size(), is(3));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(students.get(4).toString())
                .append(", ")
                .append(students.get(5).toString())
                .append(", ")
                .append(students.get(6).toString())
                .append("]")
                .toString()
        ));
    }

    @Test
    public void whenCollect10CFrom0TO50() {
        final List<Student> result = school.collect(this.students, ss10C);
        assertThat(result.size(), is(5));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(students.get(0).toString())
                .append(", ")
                .append(students.get(1).toString())
                .append(", ")
                .append(students.get(2).toString())
                .append(", ")
                .append(students.get(3).toString())
                .append(", ")
                .append(students.get(4).toString())
                .append("]")
                .toString()
        ));
    }
}


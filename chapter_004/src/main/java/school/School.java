package school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * School.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class School {
    /**
     * Method collect by predicate filter to list.
     *
     * @param students list students
     * @param predict  predicate
     * @return list by predicate
     */
    final List<Student> collect(final List<Student> students,
                                final Predicate<Student> predict) {
        return students.stream()
                .filter(predict)
                .collect(Collectors.toList());
    }

    /**
     * Method list to map.
     *
     * @param list list of users
     * @return map of users
     */
    final Map<String, Student> listToMap(final List<Student> list) {
        return list.stream()
                .collect(Collectors.toMap(Student::getSecondName, k -> k));
    }
}

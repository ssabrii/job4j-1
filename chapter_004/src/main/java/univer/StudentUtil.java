package univer;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * studentUtil.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class StudentUtil {
    /**
     * Method return list without null-objects.
     *
     * @param students catalog students
     * @param bound    bound
     * @return list without null-objects.
     */
    final List<Student> levelOf(final List<Student> students, final int bound) {
        Objects.requireNonNull(students, "must not be null");
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.reverseOrder())
                .takeWhile(z -> z.getScope() > bound && bound >= 0)
                .collect(Collectors.toList());
    }
}

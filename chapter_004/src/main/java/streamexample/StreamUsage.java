package streamexample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StreamUsages.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
 public final class StreamUsage {
    /**
     * Constructor.
     */
    private StreamUsage() {
    }

    /**
     * Inner class.
     */
    public static class Task {
        /**
         * name.
         */
        private final String name;
        /**
         * spent.
         */
        private final long spent;

        /**
         * Constructor.
         *
         * @param aName  name
         * @param aSpent spent
         */
        public Task(final String aName, final long aSpent) {
            this.name = aName;
            this.spent = aSpent;
        }

        @Override
        public final String toString() {
            return "Task{"
                    + "name='"
                    + name
                    + '\''
                    + ", spent="
                    + spent
                    + '}';
        }
    }

    /**
     * point to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        final int spent = 100;
        List<Task> tasks = Arrays.asList(
                new Task("Bug #1", spent),
                new Task("Task #2", spent),
                new Task("Bug #3", spent)
        );
        //фильтрация
        List<Task> bugs = tasks
                .stream()
                .filter(task -> task.name.contains("Bug"))
                .collect(Collectors.toList());
        bugs.forEach(System.out::println);
        //преобразование
        List<String> names = tasks
                .stream()
                .map(task -> task.name)
                .collect(Collectors.toList());
        names.forEach(System.out::println);
        //упрощение
        long total = tasks
                .stream()
                .map(task -> task.spent)
                .reduce(0L, Long::sum);
        System.out.println(total);
    }
}

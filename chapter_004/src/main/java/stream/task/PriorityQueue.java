package stream.task;

import java.util.LinkedList;

/**
 * PriorityQueue.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class PriorityQueue {
    /**
     * List tasks.
     */
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод использует add(int index, E value).
     *
     * @param task задача
     */
    public final void put(final Task task) {
        var index = (int) tasks.stream()
                .filter(t -> t.getPriority() < task.getPriority())
                .count();
        tasks.add(index, task);
    }

    /**
     * Method get first task from list.
     *
     * @return first task from list tasks.
     */
    public final Task takeFirst() {
        return this.tasks.pollFirst();
    }

    /**
     * Method get last task from list.
     *
     * @return last task from list.
     */
    public final Task takeLast() {
        return this.tasks.pollLast();
    }
}

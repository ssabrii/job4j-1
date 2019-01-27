package stream.task;

import java.util.LinkedList;

/**
 * PriorityQueue.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("ALL")
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
    public final void put1(final Task task) {
        int count = tasks.size();
        for (int index = 0; index < tasks.size(); index++) {
            if (tasks.get(index).getPriority() > task.getPriority()) {
                count = index;
                break;
            }
        }
        tasks.add(count, task);
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

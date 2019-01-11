package task;

import java.util.LinkedList;

/**
 * PriorityQueue.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class PriorityQueue {
    /**
     * List tasks.
     */
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяет по полю приоритет.
     * Для вставки использовать add(int index, E value)
     *
     * @param task задача
     */
    public final void put(final Task task) {
        //TODO добавить вставку в связанный список.
        if (tasks.isEmpty()) {
            tasks.add(task);
        } else {
            int index = 0;
            if (tasks.get(index).getPriority() < task.getPriority()) {
                tasks.add(index + 1, task);
            } else {
                Task tmp = tasks.get(index);
                tasks.add(index, task);
                tasks.add(index + 1, tmp);
            }
        }
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

    public final LinkedList<Task> all() {
        return tasks;
    }
}

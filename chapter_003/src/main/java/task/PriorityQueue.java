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
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяет по полю приоритет.
     * Для вставки использовать add(int index, E value)
     *
     * @param task задача
     */
    public final void put(final Task task) {
        //TODO добавить вставку в связанный список.
    }

    /**
     * Method take poll from list task.
     *
     * @return task from list tasks.
     */
    public final Task take() {
        return this.tasks.poll();
    }

}

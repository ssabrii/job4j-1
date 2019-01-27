package stream.task;

import java.util.LinkedList;

/**
 * PriorityQueue.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
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
        int index = (int) tasks.stream()
                //.filter(t -> t.getPriority() > task.getPriority())
                //почему со знаком больше '>' не работает,а только со знаком '<' меньше,
                // ведь t.task -это тот же tasks.get(index).getPriority() из put2?
                //а он больше чем входящий таск.
                .filter(t -> t.getPriority() > task.getPriority())
                .count();
                //long count​()
                //Возвращает количество элементов стрима.
                // count я рассматривал, но он же по описанию возвращает количество,
                //а не конкретное значение. как он может вернуть именно индекс?
                // т.е если в filter по предикату нашлось значение,оно помещается в стрим
                // и count возвращает из это стрима количество найденных элементов, т.е.сколько их
                // а не какое их значение.
        //не понятно а если ничего не найдено, как этот вариант обрабатывается?
        //не понятно, а если task==null?

            tasks.add(index, task);
    }
    public final void put2(final Task task) {
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

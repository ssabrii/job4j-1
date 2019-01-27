package stream.task;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

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
    public final void put1(final Task task) {
        int count = tasks.size();
        //шляпа какая то получается.
        int middle = Optional.of(tasks.stream()
                .filter(x -> x.getPriority() > task.getPriority())
                // filter вернет да или нет
                //не понятно что делать после filter
                // как получить индекс для вставки
                // если совпадение есть
                // и если совпдадения нет.
               ;
        if (middle.is) {
            tasks.add(middle, task);
        } else {
            tasks.add(count, task);
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
}

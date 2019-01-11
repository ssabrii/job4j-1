package task;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PriorityQueueTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put1(new Task("low", 5));
        queue.put1(new Task("urgent", 1));
        queue.put1(new Task("middle", 3));
        Task result = queue.takeFirst();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenLowerPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put1(new Task("low", 5));
        queue.put1(new Task("urgent", 1));
        queue.put1(new Task("middle", 3));
        Task result = queue.takeLast();
        assertThat(result.getDesc(), is("low"));
    }
}
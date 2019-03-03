package list.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * .
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 3/3/2019
 */
public class QueueTest {
    Queue<Integer> queue = new Queue<>();

    @Test
    public void whenPushOk() {
        this.queue.push(1);
        this.queue.push(2);
        assertThat(this.queue.poll(), is(2));
    }
    @Test
    public void whenPollOk() {
        this.queue.push(1);
        assertThat(this.queue.poll(), is(1));
    }
    @Test(expected = UnsupportedOperationException.class)
    public void whenPollFallUOE() {
        this.queue.push(1);
        this.queue.push(2);
        this.queue.poll();
        this.queue.poll();
        this.queue.poll();

    }

}
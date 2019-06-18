package list.queue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * .
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/25/2019
 */
public class SimpleQueueTest {
    private final SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Before
    public void setUpBefore() {
        this.queue.add(1);
        this.queue.add(2);
        this.queue.add(3);
    }

    @Test
    public void addOk() {
        this.queue.add(4);
        this.queue.remove();
        var result = this.queue.remove();
        assertThat(result, is(2));
    }

    @Test
    public void removeOk() {
        var result = this.queue.remove();
        assertThat(result, is(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeFallUOE() {
        this.queue.remove();
        this.queue.remove();
        this.queue.remove();
        this.queue.remove();
    }


}

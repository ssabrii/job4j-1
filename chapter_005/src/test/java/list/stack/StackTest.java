package list.stack;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StackTest {
    Stack<Integer> stack = new Stack<>();

    @Test
    public void whenPushOk() {
        this.stack.push(1);
        var result = this.stack.poll();
        assertThat(result, is(1));
    }

    @Test
    public void whenPollOk() {
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        var result = this.stack.poll();
        assertThat(result, is(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPollFallUOE() {
        this.stack.poll();
    }

}

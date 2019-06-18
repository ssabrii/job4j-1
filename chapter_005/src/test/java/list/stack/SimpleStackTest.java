package list.stack;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SimpleStackTest {
    private final SimpleStack stack = new SimpleStack(4);

    @Before
    public void setUpBefore() {
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);

    }

    @Test
    public void whenPushOk() {
        this.stack.push(4);
        var result = this.stack.poll();
        assertThat(result, is(4));
    }

    @Test
    public void whenPushDoubleSize() {
        this.stack.push(4);
        this.stack.push(5);
        var resultValue = this.stack.get(4);
        assertThat(resultValue, is(5));
        var resultSize = this.stack.getSize();
        assertThat(resultSize, is(8));
    }

    @Test
    public void whenPollOk() {
        var result = this.stack.poll();
        assertThat(result, is(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPollUOE() {
        this.stack.poll();
        this.stack.poll();
        this.stack.poll();
        this.stack.poll();
    }
}

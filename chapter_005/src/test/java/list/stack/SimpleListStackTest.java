package list.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * .
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/26/2019
 */
public class SimpleListStackTest {
    SimpleListStack<Integer> stack = new SimpleListStack<>();

    @Before
    public void setUpBefore() {
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
    }

    @Test
    public void push() {
        this.stack.push(4);
        var result = this.stack.get(0);
        assertThat(result, is(4));
    }

    @Test
    public void whenPollOk() {
        var result = this.stack.poll();
        assertThat(result, is(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPollFallUOE() {
        assertThat(this.stack.poll(), is(3));
        assertThat(this.stack.poll(), is(2));
        assertThat(this.stack.poll(), is(1));
        assertThat(this.stack.poll(), is(3));
    }

    @Test
    public void whenGetOk() {
        var result = this.stack.get(0);
        assertThat(result, is(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenGetOutIndexSize() {
        var result = this.stack.get(10);
    }

    @Test
    public void iterator() {
        Iterator<Integer> iterator = this.stack.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertFalse(iterator.hasNext());
    }
}

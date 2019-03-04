package set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    private final SimpleSet<Integer> set = new SimpleSet<>();

    @Test
    public void whenAddOk() {
        this.set.add(1);
        var result = this.set.iterator().next();
        assertThat(result, is(1));
    }

    @Test
    public void whenAddNullOk() {
        this.set.add(null);
        var result = this.set.iterator().next();
        assertNull(result);
    }

    @Test
    public void whenAddNullOk2() {
        this.set.add(null);
        this.set.add(null);
        final Iterator<Integer> iterator = this.set.iterator();
        assertNull(iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenAddFallUOE() {
        this.set.add(1);
        this.set.add(2);
        Iterator<Integer> iterator = this.set.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddFall() {
        this.set.add(1);
        this.set.add(1);
        Iterator<Integer> iterator = this.set.iterator();
        iterator.next();
        iterator.next();
    }
}
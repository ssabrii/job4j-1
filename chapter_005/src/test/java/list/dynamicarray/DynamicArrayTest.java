package list.dynamicarray;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DynamicArrayTest {
    private final DynamicArray<Integer> dynamic = new DynamicArray<>(5);

    @Before
    public void setUpBefore() {
        this.dynamic.add(1);
        this.dynamic.add(2);
        this.dynamic.add(3);
    }

    @Test
    public void add() {
        this.dynamic.add(4);
        var result = this.dynamic.get(3);
        assertThat(result, is(4));
    }

    @Test
    public void whenAddAndFullSize() {
        this.dynamic.add(4);
        this.dynamic.add(5);
        this.dynamic.add(6);
        this.dynamic.add(7);
        var result = this.dynamic.get(6);
        assertThat(result, is(7));
    }

    @Test
    public void getOk() {
        var result = this.dynamic.get(0);
        assertThat(result, is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetOutIndexDown() {
        this.dynamic.get(-1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetOutIndexUp() {
        this.dynamic.get(10);
    }

    @Test
    public void whenIteratorNext() {
        Iterator<Integer> iterator = this.dynamic.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorHasNextAfterModification() {
        Iterator<Integer> iterator = this.dynamic.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        this.dynamic.add(5);
        assertTrue(iterator.hasNext());
    }
}

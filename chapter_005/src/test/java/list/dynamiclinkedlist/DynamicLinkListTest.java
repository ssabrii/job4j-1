package list.dynamiclinkedlist;

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
 * @since 2/22/2019
 */
public class DynamicLinkListTest {
    DynamicLinkList<Integer> dynamic = new DynamicLinkList<>();

    @Before
    public void setUpBefore() {
        this.dynamic.add(1);
        this.dynamic.add(2);
        this.dynamic.add(3);
    }

    @Test
    public void add() {
        this.dynamic.add(4);
        var result = this.dynamic.get(0);
        assertThat(result, is(4));
    }

    @Test
    public void removeOk() {
        var result = this.dynamic.removeFirst();
        assertThat(result, is(3));
        assertThat(this.dynamic.get(0), is(2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeFallUOE() {
        this.dynamic.removeFirst();
        this.dynamic.removeFirst();
        this.dynamic.removeFirst();
        this.dynamic.removeFirst();

    }

    @Test
    public void whenGetOk() {
        var result = this.dynamic.get(0);
        assertThat(result, is(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenGetOutIndexSize() {
        var result = this.dynamic.get(10);
    }

    @Test
    public void iterator() {
        Iterator<Integer> iterator = this.dynamic.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertFalse(iterator.hasNext());
    }
}

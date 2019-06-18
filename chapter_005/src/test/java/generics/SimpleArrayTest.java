package generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleArrayTest {
    private SimpleArray<String> storage;
    private String one;
    private String two;
    private String three;
    private String four;

    @Before
    public void beforeAction() {
        this.storage = new SimpleArray<>(4);
        one = "AAA";
        two = "BBB";
        three = "CCC";
        four = "XXX";
        this.storage.add(null);
        this.storage.add(two);
        this.storage.add(three);
    }

    @Test
    public void addOK() {
        this.storage.add(four);
        String result = this.storage.get(3);
        assertThat(result, is("XXX"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addUOEbySize() {
        this.storage.add(this.four);
        this.storage.add(this.four);
    }

    @Test
    public void setOK() {
        this.storage.set(0, this.one);
        var result = this.storage.get(0).equals(this.one);
        assertTrue(result);
    }

    @Test
    public void setOKbyNull() {
        this.storage.set(0, null);
        assertNull(this.storage.get(0));

    }

    @Test(expected = NoSuchElementException.class)
    public void setUOEbySize() {
        this.storage.set(5, null);
    }

    @Test
    public void removeOK() {
        this.storage.remove(0);
        var result = this.storage.get(0).equals(this.one);
        assertThat(result, is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void removeUOEbyNull() {
        this.storage.remove(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeUOEbySize() {
        this.storage.remove(10);
    }

    @Test
    public void getOK() {
        String result = this.storage.get(1);
        assertThat(result, is("BBB"));
    }

    @Test(expected = NoSuchElementException.class)
    public void getUOEbySize() {
        this.storage.get(5);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUOEbyNull() {
        this.storage.get(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUOEbyEmpty() {
        this.storage = new SimpleArray<>(0);
        this.storage.get(0);
    }

    @Test
    public void iterator() {
        Iterator<String> iterator = this.storage.iterator();
        assertTrue(iterator.hasNext());
        assertNull(iterator.next());
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("BBB"));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("CCC"));
        assertFalse(iterator.hasNext());
        //assertTrue(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void anotherIterator() {
        for (String a : this.storage.theSameIterator()) {
            System.out.println(a);
        }
    }

    @Test
    public void whenCheckIndexOK() {
        this.storage.checkIndex(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCheckIndexNEE() {
        this.storage.checkIndex(5);
    }
}

package generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArray<T>.
 *
 * @param <T> param T
 * @author Maxim Vanny
 * @version 5.0
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * array<T>.
     */
    private T[] array;
    /**
     * size array.
     */
    private int size;

    /**
     * Constructor.
     *
     * @param cells cells
     */
    @SuppressWarnings("unchecked")
    public SimpleArray(final int cells) {

        this.array = (T[]) new Object[cells];
    }

    /**
     * Another iterator.
     *
     * @return iterator
     */
    public final Iterable<T> theSameIterator() {
        return () -> new Iterator<>() {
            private int count;

            public boolean hasNext() {
                return count < size;
            }

            public T next() {
                return array[this.count++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * add element to storage.
     *
     * @param model object
     * @throws UnsupportedOperationException exception
     */
    public final void add(final T model) {
        if (this.size >= this.array.length) {
            throw new UnsupportedOperationException("Storage is full");
        }
        this.array[this.size++] = model;
    }

    /**
     * set in storage.
     *
     * @param index index
     * @param model object
     * @throws NoSuchElementException exception
     */
    public final void set(final int index, final T model) {
        checkIndex(index);
        this.array[index] = model;
    }

    /**
     * remove object from storage.
     *
     * @param index index object
     * @throws UnsupportedOperationException exception
     */
    public final void remove(final int index) {
        checkIndex(index);
        int numMoved = this.size - index - 1;
        System.arraycopy(this.array, index + 1, this.array, index, numMoved);
        this.array[this.size - 1] = null;
        this.size--;
    }

    /**
     * get object from storage.
     *
     * @param index index object
     * @return T object
     * @throws NoSuchElementException exception
     */
    public final T get(final int index) {
        checkIndex(index);
        if (this.size == 0) {
            throw new NoSuchElementException("Index is out in storage");
        }
        return this.array[index];
    }

    /**
     * Check index in or out in border size.
     *
     * @param index index
     * @throws NoSuchElementException exception
     */
    protected final void checkIndex(final int index) {
        if (index > this.size) {
            throw new NoSuchElementException("Index is out in storage");
        }
    }

    @Override
    public final Iterator<T> iterator() {
        // return Arrays.stream(this.array).iterator();//all massive
        //return List.of(this.array).iterator();//missive with not null
        // return Arrays.asList(this.array).iterator();all massive
        //clean way this my array
        return new Iterator<>() {
            private int count;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                return array[this.count++];
            }
        };
    }
}

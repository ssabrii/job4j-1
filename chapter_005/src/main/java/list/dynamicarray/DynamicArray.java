package list.dynamicarray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * DynamicArray.
 *
 * @param <T> T param
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/22/2019
 */
public class DynamicArray<T> implements Iterable<T> {
    /**
     * Container.
     */
    private Object[] container;
    /**
     * Size.
     */
    private int size;
    /**
     * Count mode.
     */
    private int modCount;

    /**
     * Constructor.
     *
     * @param aSize size
     */
    public DynamicArray(final int aSize) {
        this.container = new Object[aSize];
    }

    /**
     * Method add.
     *
     * @param value value
     */
    public final void add(final T value) {
        Objects.requireNonNull(value, "must not be null");
        if (this.size == this.container.length - 1) {
            addSizeArray();
        }
        this.container[size++] = value;
        this.modCount++;
    }

    /**
     * Method add double size.
     */
    private void addSizeArray() {
        int doubleSize = this.container.length * 2;
        Object[] newArray = new Object[doubleSize];
        System.arraycopy(this.container, 0, newArray, 0, this.container.length);
        this.container = new Object[doubleSize];
        System.arraycopy(newArray, 0, this.container, 0, doubleSize);
    }

    /**
     * Method get.
     *
     * @param index index.
     * @return element from container.
     */
    @SuppressWarnings("unchecked")
    public final T get(final int index) {
        if (index > this.size - 1 || index < 0) {
            throw new NoSuchElementException("Element missing");
        }
        return (T) this.container[index];
    }

    @Override
    public final Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedCountMod = modCount;
            //сделал локальную переменную
            private int cursor;

            @Override
            public boolean hasNext() {
                if (modCount != this.expectedCountMod) {
                    throw new ConcurrentModificationException();
                }
                return this.cursor < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException("Element missing");
                }
                return (T) container[this.cursor++];
            }
        };
    }
}

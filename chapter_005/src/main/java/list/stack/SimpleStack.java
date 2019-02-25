package list.stack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SimpleStack.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/23/2019
 */
public class SimpleStack implements Iterable {
    /**
     * Container.
     */
    private Object[] stack;

    /**
     * Count mode.
     */
    private int modCount;

    /**
     * field top of stack.
     */
    private int tos;

    /**
     * Constructor.
     *
     * @param size the size of stack
     */
    public SimpleStack(final int size) {
        this.stack = new Object[size];
        this.tos = -1;
    }

    /**
     * Push.
     *
     * @param value value
     */
    public final void push(final Object value) {
        Objects.requireNonNull(value, "must not be null");
        if (this.tos == this.stack.length - 1) {
            addSizeArray();
        }
        this.stack[++this.tos] = value;
        this.modCount++;
    }

    /**
     * Poll.
     *
     * @return any T
     */
    public final Object poll() {
        if (this.tos < 0) {
            throw new UnsupportedOperationException("Stack is empty");
        }
        return this.stack[this.tos--];
    }

    /**
     * Method add double size.
     */
    private void addSizeArray() {
        int doubleSize = this.stack.length * 2;
        Object[] newArray = new Object[doubleSize];
        System.arraycopy(this.stack, 0, newArray, 0, this.stack.length);
        this.stack = new Object[doubleSize];
        System.arraycopy(newArray, 0, this.stack, 0, doubleSize);
    }

    /**
     * Method get.
     *
     * @param index index.
     * @return element from container.
     */
    public final Object get(final int index) {
        if (index > this.tos || index < 0) {
            throw new NoSuchElementException("Element missing");
        }
        return this.stack[index];
    }

    /**
     * Get size stack.
     *
     * @return size
     */
    public final int getSize() {
        return this.stack.length;
    }

    @Override
    public final Iterator<Object> iterator() {
        return new Iterator<>() {
            private final int expectedCountMod = modCount;
            private int cursor;

            @Override
            public boolean hasNext() {
                if (modCount != this.expectedCountMod) {
                    throw new ConcurrentModificationException();
                }
                return this.cursor < tos;
            }

            @Override
            public Object next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException("Element missing");
                }
                return stack[this.cursor++];
            }
        };
    }
}


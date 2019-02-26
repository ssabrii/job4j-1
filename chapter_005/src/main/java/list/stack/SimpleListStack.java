package list.stack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * .
 *
 * @param <T> any T
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/26/2019
 */
@SuppressWarnings("Duplicates")
public class SimpleListStack<T> implements Iterable<T> {
    /**
     * Cursor.
     */
    private int cursor;
    /**
     * First Node.
     */
    private Node<T> first = null;

    /**
     * Method add.
     *
     * @param value value
     */
    public final void push(final T value) {
        Node<T> temp = new Node<>(value);
        temp.next = this.first;
        this.first = temp;
        this.cursor++;
    }

    /**
     * Method return and delete first element in stack.
     *
     * @return new value.
     */
    public final T poll() {
        if (this.first == null) {
            throw new UnsupportedOperationException("Stack is empty");
        }
        T data = this.first.data;
        this.first = this.first.next;
        return data;
    }

    /**
     * Method Get.
     *
     * @param index index
     * @return data
     */
    public final T get(final int index) {
        if (index > this.cursor) {
            throw new UnsupportedOperationException("Element missing");
        }
        Node<T> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public final Iterator<T> iterator() {
        return new Iterator<>() {
            private final int modificationCountMod = cursor;
            private Node<T> temp = first;

            @Override
            public final boolean hasNext() {
                if (cursor != this.modificationCountMod) {
                    throw new ConcurrentModificationException();
                }
                return this.temp != null;
            }

            @Override
            public final T next() {
                if (!this.hasNext()) {
                    throw new UnsupportedOperationException();
                }
                T data = this.temp.data;
                this.temp = this.temp.next;
                return data;
            }
        };
    }

    /**
     * class Node.
     *
     * @param <T> any Т
     */
    private static class Node<T> {
        /**
         * field Node.
         */
        private Node<T> next;
        /**
         * field data.
         */
        private final T data;

        /**
         * Constructor.
         *
         * @param aData data
         */
        Node(final T aData) {
            this.data = aData;
        }
    }
}

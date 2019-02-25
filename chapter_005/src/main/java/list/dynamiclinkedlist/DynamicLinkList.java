package list.dynamiclinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * DynamicLinkList.
 *
 * @param <T> param T.
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/22/2019
 */
public class DynamicLinkList<T> implements Iterable<T> {
    /**
     * countMod.
     */
    private int countMod;
    /**
     * Size.
     */
    private int size;
    /**
     * First Node.
     */
    private Node<T> first;

    /**
     * Method add.
     *
     * @param value value
     */
    public final void add(final T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
        this.countMod++;
    }

    /**
     * Method Get.
     *
     * @param index index
     * @return data
     */
    public final T get(final int index) {
        if (index > this.size) {
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
            private final int modificationCountMod = countMod;
            private Node<T> result = first;
            private int count = 0;

            @Override
            public final boolean hasNext() {
                if (countMod != this.modificationCountMod) {
                    throw new ConcurrentModificationException();
                }
                return first.next != null;
            }

            @Override
            public final T next() {
                if (!this.hasNext()) {
                    throw new UnsupportedOperationException();
                }
                // вот здесь как оформить переход по нодам
                // для получения значения data следующего по порядку обьекта??

                return this.result.data;
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

package list.stack;

import list.dynamiclinkedlist.DynamicLinkList;

/**
 * Stack.
 *
 * @param <T> any T
 * @author Maxim Vanny
 * @version 5.0
 * @since 3/1/2019
 */
public class Stack<T> {
    /**
     * list.
     */
    private final DynamicLinkList<T> list = new DynamicLinkList<>();
    /**
     * field first.
     */
    private Node<T> first = null;

    /**
     * Method add.
     *
     * @param value value
     */
    @SuppressWarnings("unchecked")
    public final void push(final T value) {
        Node<T> temp = new Node<>(value);
        temp.next = this.first;
        this.first = temp;
        this.list.add((T) this.first);
    }

    /**
     * Method return and delete first element in stack.
     *
     * @return new value.
     */
    @SuppressWarnings("Duplicates")
    public final T poll() {
        if (this.first == null) {
            throw new UnsupportedOperationException("Stack is empty");
        }
        T data = this.first.data;
        this.first = this.first.next;
        return data;
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

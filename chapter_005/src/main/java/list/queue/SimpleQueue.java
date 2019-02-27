package list.queue;

/**
 * SimpleQueue.
 *
 * @param <T> any T
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/25/2019
 */

public class SimpleQueue<T> {
    /**
     * field head.
     */
    private Node<T> head;
    /**
     * field tail.
     */
    private Node<T> tail;

    /**
     * Method add.
     *
     * @param value value
     */
    public final void add(final T value) {
        Node<T> temp = new Node<>(value);
        if (this.head == null) {
            this.head = temp;
        } else if (this.tail == null) {
            this.tail = temp;
            this.head.next = this.tail;
        } else {
            this.tail.next = temp;
            this.tail = temp;
        }
    }

    /**
     * Method remove.
     *
     * @return value
     */
    public final T remove() {
        if (this.head == null) {
            throw new UnsupportedOperationException("Queue is empty");
        }
        Node<T> tmp = this.head;
        this.head = this.head.next;
        tmp.next = null;
        return tmp.data;
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

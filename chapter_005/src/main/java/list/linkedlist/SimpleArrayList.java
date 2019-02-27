package list.linkedlist;

/**
 * SimpleArrayList.
 *
 * @param <T> param
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/21/2019
 */
public class SimpleArrayList<T> {
    /**
     * size.
     */
    private int size;
    /**
     * first node.
     */
    private Node<T> first;

    /**
     * * Метод вставляет в начало списка данные.
     *
     * @param date object for add.
     */

    public final void add(final T date) {
        Node<T> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемента в списке.
     *
     * @return new value.
     */
    public final T delete() {
        T data = this.first.date;
        this.first = this.first.next;
        this.size--;
        return data;
    }

    /**
     * Метод получения элемента по индексу.
     *
     * @param index index.
     * @return new value.
     */
    public final T get(final int index) {
        Node<T> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     *
     * @return size
     */
    public final int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     *
     * @param <T> any T
     */
    private static class Node<T> {
        /**
         * data.
         */
        private final T date;
        /**
         * next link.
         */
        private Node<T> next;

        /**
         * Constructor.
         *
         * @param aDate data
         */
        Node(final T aDate) {
            this.date = aDate;
        }
    }
}


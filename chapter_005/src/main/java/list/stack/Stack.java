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
     * Method add.
     *
     * @param value value
     */
    public final void push(final T value) {
        this.list.add(value);
    }

    /**
     * Method return and delete first element in stack.
     *
     * @return new value.
     */
    public final T poll() {
        return this.list.removeFirst();
    }
}

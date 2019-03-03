package list.queue;

import list.stack.Stack;

/**
 * Queue.
 *
 * @param <T> any T
 * @author Maxim Vanny
 * @version 5.0
 * @since 3/3/2019
 */
public class Queue<T> {
    /**
     * field out.
     */
    private Stack<T> out = new Stack<>();
    /**
     * field in.
     */
    private Stack<T> in = new Stack<>();

    /**
     * Push.
     *
     * @param item item
     */
    public final void push(final T item) {
        in.push(item);
    }

    /**
     * Poll.
     *
     * @return result
     */
    public final T poll() {
        out.push(in.poll());
        return out.poll();
    }

}


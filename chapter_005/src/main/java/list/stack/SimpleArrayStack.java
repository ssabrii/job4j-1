package list.stack;

/**
 * SimpleStack.
 *
 * @param <T> any T
 * @author Maxim Vanny
 * @version 5.0
 * @since 2/23/2019
 */
public class SimpleArrayStack<T> {
    /**
     * field stack.
     */
    private final T[] stack;
    /**
     * field top of stack.
     */
    private int tos;

    /**
     * Constructor.
     *
     * @param size the size of stack
     */
    @SuppressWarnings("unchecked")
    public SimpleArrayStack(final int size) {
        this.stack = (T[]) new Object[size];
        this.tos = -1;
    }

    /**
     * Push.
     *
     * @param value value
     */
    public final void push(final T value) {
        if (this.tos == this.stack.length - 1) {
            throw new UnsupportedOperationException("Stack full");
        } else {
            this.stack[++this.tos] = value;
        }
    }

    /**
     * Poll.
     *
     * @return any T
     */
    public final T poll() {
        if (this.tos < 0) {
            throw new UnsupportedOperationException("Stack is empty");
        }
        return this.stack[this.tos--];
    }
}

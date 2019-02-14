package iterators.eveniterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * EventIterator.
 *
 * @author Maxim Vanny.
 * @version 5.0
 * @since 0.1
 */
public class EvenIterator implements Iterator<Integer> {
    /**
     * values.
     */
    private final int[] values;
    /**
     * count.
     */
    private int count = 0;

    /**
     * Constructor.
     *
     * @param aValues array
     */
    public EvenIterator(final int[] aValues) {
        this.values = aValues;
    }

    @Override
    public final boolean hasNext() {
        boolean is = true;
        int length = this.values.length - 1;
        if (this.count == length && this.values[this.count] % 2 != 0) {
            is = false;
        }
        int odd = 0;
        for (int number : this.values) {
            if (number % 2 != 0) {
                odd++;
                if (odd == this.values.length - 1) {
                    is = false;
                    break;
                }
            }
        }

        if (this.count > length) {
            is = false;
        }
        return is;
    }

    @Override
    public final Integer next() {
        int cells;
        do {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            cells = this.count;
            this.count++;
        }
        while (this.values[cells] % 2 != 0);
        return this.values[cells];
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void forEachRemaining(final Consumer<? super Integer> action) {
        throw new UnsupportedOperationException();
    }
}

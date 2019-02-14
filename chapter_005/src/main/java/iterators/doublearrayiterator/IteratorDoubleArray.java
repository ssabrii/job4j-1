package iterators.doublearrayiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * DoubleArrayIterator.
 *
 * @author Maxim Vanny.
 * @version 5.0
 * @since 0.1
 */
public class IteratorDoubleArray implements Iterator<Integer> {
    /**
     * array.
     */
    private final int[][] array;
    /**
     * rows.
     */
    private int rows = 0;
    /**
     * cells.
     */
    private int cells = 0;

    /**
     * Constructor.
     *
     * @param aArray array
     */
    public IteratorDoubleArray(final int[][] aArray) {
        this.array = aArray;
    }

    @Override
    public final boolean hasNext() {
        return this.rows < this.array.length;
    }

    @Override
    public final Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = this.array[this.rows][this.cells++];
        if (this.cells == this.array[this.rows].length) {
            this.rows++;
            this.cells = 0;
        }
        return result;
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

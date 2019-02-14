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
        boolean is = true;
        if (this.rows == this.array.length) {
            is = false;
        }
        return is;
    }

    @Override
    public final Integer next() {
        final int inSize = array[this.rows].length;
        int tom = 0;
        int jerry = 0;
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.cells != inSize) {
            tom = this.rows;
            jerry = this.cells;
            this.cells++;
        }
        if (this.cells == inSize) {
            tom = this.rows;
            this.cells = 0;
            this.rows++;
        }
        return this.array[tom][jerry];
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

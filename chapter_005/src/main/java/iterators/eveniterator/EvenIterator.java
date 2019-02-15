package iterators.eveniterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    /**
     * list evens.
     */
    private List<Integer> list;

    /**
     * loop for check evens.
     */
    private void loop() {
        int index = 0;
        this.list = new ArrayList<>();
        for (int number : this.values) {
            if (number % 2 == 0) {
                this.list.add(index);
            }
            index++;
        }
    }

    @Override
    public final boolean hasNext() {

        boolean is = true;
        loop();
        if (this.list.isEmpty()) {
            is = false;
        }
        if (this.count > list.size() - 1) {
            is = false;
        }
        return is;
    }


    @Override
    public final Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.values[this.list.get(this.count++)];
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

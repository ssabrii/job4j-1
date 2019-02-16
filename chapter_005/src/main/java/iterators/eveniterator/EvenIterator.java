package iterators.eveniterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.IntStream;

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
    private int index = 0;

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
        int count = this.index;
        boolean is = false;

        OptionalInt result = IntStream.range(count, values.length)
                .filter(z -> this.values[z] % 2 == 0)
                .filter(z -> this.values[z] != 0)
                .findAny();

        if (result.isPresent()) {
            is = true;
            this.index = result.getAsInt();
        }
        return is;

    }


    @Override
    public final Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.values[index++];
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

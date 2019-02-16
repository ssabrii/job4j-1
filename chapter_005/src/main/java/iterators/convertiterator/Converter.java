package iterators.convertiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter.
 *
 * @author Maxim Vanny.
 * @version 5.0
 * @since 0.1
 */
public class Converter {
    /**
     * Convert.
     *
     * @param it iterator
     * @return sequence
     */
    final Iterator<Integer> convert(final Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> iter = it.next();

            @Override
            public final boolean hasNext() {
                while (!this.iter.hasNext() && !it.hasNext()) {
                    iter = it.next();
                    break;
                }
                return this.iter.hasNext();
            }

            @Override
            public final Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.iter.next();
            }
        };
    }
}

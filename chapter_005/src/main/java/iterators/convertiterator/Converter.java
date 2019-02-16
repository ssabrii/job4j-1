package iterators.convertiterator;

import java.util.ArrayList;
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
            private Iterator<Integer> iter =
                    new ArrayList<Integer>().iterator();

            @Override
            public final boolean hasNext() {
                while (it.hasNext() && !iter.hasNext()) {
                    iter = it.next();
                }
                return iter.hasNext();
            }

            @Override
            public final Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return iter.next();
            }
        };

    }
}

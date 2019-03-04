package set;

import list.dynamicarray.DynamicArray;

import java.util.Iterator;

/**
 * SimpleSet.
 *
 * @param <T> any T
 * @author Maxim Vanny
 * @version 5.0
 * @since 3/4/2019
 */
public class SimpleSet<T> implements Iterable<T> {
    /**
     * field set.
     */
    private final DynamicArray<T> set = new DynamicArray<>(5);

    /**
     * Method add.
     *
     * @param value value
     */
    public final void add(final T value) {

        if (!this.set.isExist(value)) {
            this.set.add(value);
        }
    }

    @Override
    public final Iterator<T> iterator() {
        return this.set.iterator();
    }
}

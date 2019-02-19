package generics;

import java.util.NoSuchElementException;

/**
 * AbstractStore.
 *
 * @param <E> generic
 * @author Max Vanny.
 * @version 5.0
 * @since 0.1
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {
    /**
     * Storage Base.
     */
    private final SimpleArray<E> storageUser;

    /**
     * Constructor.
     *
     * @param size size store
     */
    protected AbstractStore(final int size) {
        this.storageUser = new SimpleArray<>(size);
    }


    @Override
    public final void add(final E model) {
        this.storageUser.add(model);
    }

    @Override
    public final void replace(final String id, final E model) {
        var byId = this.findById(id);
        var count = 0;
        for (Object user : this.storageUser) {
            if (byId.equals(user)) {
                this.storageUser.set(count, model);
                break;
            }
            count++;
        }
    }

    @Override
    public final void delete(final String id) {
        var byId = this.findById(id);
        var count = 0;
        for (Object user : this.storageUser) {
            if (byId.equals(user)) {
                this.storageUser.remove(count);
                break;
            }
            count++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final E findById(final String id) {
        E get = null;
        for (Object base : this.storageUser) {
            if (((E) base).getId().equals(id)) {
                get = (E) base;
                break;
            }
        }
        if (get == null) {
            throw new NoSuchElementException("Element missing");
        }
        return get;
    }
}

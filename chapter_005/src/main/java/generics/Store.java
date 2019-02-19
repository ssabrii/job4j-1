package generics;

/**
 * Store.
 *
 * @param <E> extend Base
 * @author Max Vanny.
 * @version 5.0
 * @since 0.1
 */
public interface Store<E extends Base> {
    /**
     * add.
     *
     * @param model model
     */
    void add(final E model);

    /**
     * replace.
     *
     * @param id    id
     * @param model model
     */
    void replace(final String id, final E model);

    /**
     * delete.
     *
     * @param id id
     */
    void delete(final String id);

    /**
     * findById.
     *
     * @param id id
     * @return T model
     */
    E findById(final String id);
}


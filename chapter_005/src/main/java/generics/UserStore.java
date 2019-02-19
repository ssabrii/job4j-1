package generics;

/**
 * UserStore.
 *
 * @author Max Vanny.
 * @version 5.0
 * @since 0.1
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Constructor.
     *
     * @param size size store
     */
    public UserStore(final int size) {
        super(size);
    }
}

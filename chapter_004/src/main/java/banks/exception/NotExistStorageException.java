package banks.exception;

/**
 * NotExistStorageException.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class NotExistStorageException extends Exception {
    /**
     * Constructor.
     *
     * @param message message for user
     */
    public NotExistStorageException(final String message) {
        super(message);
    }
}

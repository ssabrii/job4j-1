package banks.exception;

/**
 * NotExistAccount.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class NotExistAccountException extends Exception {
    /**
     * Constructor.
     *
     * @param message message for user
     */

    public NotExistAccountException(final String message) {
        super(message);
    }
}

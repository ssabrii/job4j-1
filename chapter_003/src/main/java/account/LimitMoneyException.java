package account;

/**
 * LimitMoneyOfAccountException.
 *
 * @author Maxim Vanny.
 * @version 3.0
 * @since 0.1
 */
public class LimitMoneyException extends Exception {
    /**
     * Constructor.
     *
     * @param message message for user
     */
    public LimitMoneyException(final String message) {
        super(message);
    }
}

package ru.job4j.exception;

/**
 * MenuOutException.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message message for users.
     */
    public MenuOutException(final String message) {
        super(message);
    }
}

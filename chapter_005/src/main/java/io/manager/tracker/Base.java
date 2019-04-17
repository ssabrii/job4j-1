package io.manager.tracker;

/**
 * Base.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/21/2019
 */
public interface Base {
    /**
     * Method execute input client query.
     *
     * @param query input data
     * @return result
     */
    String execute(String[] query);
}

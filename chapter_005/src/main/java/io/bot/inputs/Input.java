package io.bot.inputs;

import java.io.IOException;

/**
 * Input.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/11/2019
 */
public interface Input {
    /**
     * Method return user side dialog by step.
     *
     * @return user side dialog by step
     *
     * @throws IOException exception
     */
    String askUser() throws IOException;
}

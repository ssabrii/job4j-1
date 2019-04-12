package io.chat.inputs;

import java.io.IOException;

/**
 * ValidateInput.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/11/2019
 */
public class ValidateInput implements Input {
    /**
     * field input.
     */
    private final Input input;

    /**
     * Constructor.
     *
     * @param aInput input for main and test
     */
    public ValidateInput(final Input aInput) {
        this.input = aInput;
    }

    @Override
    public final String fromUser() throws IOException {
        return input.fromUser();
    }
}

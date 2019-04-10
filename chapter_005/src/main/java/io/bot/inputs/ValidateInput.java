package io.bot.inputs;

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
     * @param aInput input
     */
    public ValidateInput(final Input aInput) {
        this.input = aInput;
    }

    /**
     * Method return side user dialog.
     *
     * @return side user dialog
     */
    @Override
    public final String askUser() throws IOException {
        return input.askUser();
    }
}

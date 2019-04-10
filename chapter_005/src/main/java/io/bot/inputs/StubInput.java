package io.bot.inputs;

/**
 * StubInput.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/11/2019
 */


public class StubInput implements Input {
    /**
     * field  has side user dialog.
     */
    private final String[] value;

    /**
     * field position in user dialog.
     */
    private int position;

    /**
     * Constructor.
     *
     * @param aValue value
     */
    public StubInput(final String[] aValue) {
        this.value = aValue;
    }

    /**
     * Method return side user dialog by step.
     *
     * @return side user dialog by step
     */
    @Override
    public final String askUser() {
        return this.value[this.position++];
    }

}



package io.chat.inputs;

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
     * @param aValue value for tests
     */
    public StubInput(final String[] aValue) {
        this.value = aValue;
    }

    @Override
    public final String fromUser() {
        return this.value[this.position++];
    }

}



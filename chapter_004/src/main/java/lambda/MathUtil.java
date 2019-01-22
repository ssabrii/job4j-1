package lambda;

/**
 * MathUtil.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public final class MathUtil {
    /**
     * Constructor.
     */
    private MathUtil() {
    }

    /**
     * Method add.
     *
     * @param left   left
     * @param second second
     * @return result
     */
    public static double add(final int left, final int second) {
        return left + second;
    }

    /**
     * Method div.
     *
     * @param left   left
     * @param second second
     * @return result
     */
    public static double div(final int left, final int second) {
        return left / second;
    }
}

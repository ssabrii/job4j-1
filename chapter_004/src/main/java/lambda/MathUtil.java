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
     * @param left  left
     * @param right right
     * @return result
     */
    public static double add(final int left, final int right) {
        return left + right;
    }

    /**
     * Method div.
     *
     * @param left  left
     * @param right right
     * @return result
     */
    public static double div(final int left, final int right) {
        return left / right;
    }

    /**
     * Method linear addition.
     *
     * @param aDouble enter number
     * @return result
     */
    public static Double addLinear(final Double aDouble) {
        return aDouble * 2 + 1;
    }

    /**
     * Method get sqrt.
     *
     * @param aDouble enter number
     * @return result
     */
    public static Double sqr(final Double aDouble) {
        return Math.sqrt(aDouble);
    }

    /**
     * Method get log.
     *
     * @param aDouble enter number
     * @return result
     */
    public static Double logr(final Double aDouble) {
        return Math.log(aDouble);
    }
}

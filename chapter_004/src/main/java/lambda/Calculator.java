package lambda;

/**
 * Calculator.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class Calculator {
    /**
     * Interface.
     */
    public interface Operation {
        /**
         * SAM calc.
         *
         * @param left  value
         * @param right value
         * @return result
         */
        double calc(int left, int right);
    }

    /**
     * Multiply.
     *
     * @param start  the start border of diapason
     * @param finish the finish border of diapason
     * @param value  value
     * @param op     lint to interface
     */
    public final void multiple(final int start, final int finish,
                               final int value, final Operation op) {
        for (int index = start; index != finish; index++) {
            System.out.println(
                    op.calc(value, index)
            );
        }
    }

    /**
     * Enter to program.
     *
     * @param args string array args
     */
    public static void main(final String[] args) {
        final Calculator calc = new Calculator();
        final int start = 0;
        final int finish = 10;
        final int value = 2;
        calc.multiple(
                start, finish, value,
                new Operation() {
                    @Override
                    public final double calc(final int left, final int right) {
                        return left * right;
                    }
                }
        );
    }
}

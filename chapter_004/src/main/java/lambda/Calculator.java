package lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Calculator.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class Calculator {

    /**
     * Multiply.
     *
     * @param start  the start border of diapason
     * @param finish the finish border of diapason
     * @param value  value
     * @param op     link to interface
     * @param media  link to interface
     */
    public final void multiple(final int start,
                               final int finish,
                               final int value,
                               final BiFunction<Integer, Integer, Double> op,
                               final Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
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
        final int amount = 2;
        calc.multiple(start, finish, amount,
                MathUtil::add,
                System.out::println
        );
    }
}

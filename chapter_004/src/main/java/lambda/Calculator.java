package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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
     * Method count function in diapason.
     *
     * @param start start diapason
     * @param end   end diapason
     * @param func  function
     * @return list diapason
     */
    public final List<Double> diapason(final int start, final int end,
                                       final Function<Double, Double> func) {
        final List<Double> result = new ArrayList<>();
        for (double index = start; index != end; index++) {
            result.add(func.apply(index));
        }
        return result;
    }


    /**
     * Enter to program.
     *
     * @param args string array args
     */
    public static void main(final String[] args) {
        final Calculator calc = new Calculator();
        final int start = 0;
        final int finish = 4;
        final int amount = 2;
        calc.multiple(start, finish, amount,
                MathUtil::add,
                System.out::println
        );
        calc.diapason(start, finish,
                MathUtil::addLinear);
    }
}

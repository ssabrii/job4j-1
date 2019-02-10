package ru.job4j.calculator;

/**
 * Calculator.
 * Simple calculator.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Calculator {
    /**
     * field result.
     */
    private double result;

    /**
     * Method add one number to other number.
     *
     * @param first  one number
     * @param second second number
     */
    public final void add(final double first, final double second) {
        this.result = first + second;
    }

    /**
     * Method take away one number from other number.
     *
     * @param first  one number
     * @param second second number
     */
    public final void minus(final double first, final double second) {
        this.result = first - second;
    }

    /**
     * Method  Multiply one number from other number.
     *
     * @param first  one number
     * @param second second number
     */
    public final void multiply(final double first, final double second) {
        this.result = first * second;
    }

    /**
     * Method Divide one number to other number.
     *
     * @param first  one number
     * @param second second number
     */
    public final void divide(final double first, final double second) {
        this.result = first / second;
    }

    /**
     * Method return result number.
     *
     * @return result
     */
    public final double getResult() {
        return this.result;
    }
}

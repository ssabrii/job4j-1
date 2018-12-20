package ru.job4j.calculator;

/**
 * Calculator.
 * Simple calculator.
 *
 * @author Maxim Vanny
 */

public class Calculator {
    private double result;

    /**
     * Method add one number to other number.
     *
     * @param first  one number
     * @param second second number
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method take away one number from other number.
     *
     * @param first  one number
     * @param second second number
     */
    public void minus(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method  Multiply one number from other number.
     *
     * @param first  one number
     * @param second second number
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method Divide one number to other number.
     *
     * @param first  one number
     * @param second second number
     */
    public void divide(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method return result number.
     *
     * @return result
     */
    public double getResult() {
        return this.result;
    }
}
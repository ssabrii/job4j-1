package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Maxim Vanny
 * @version 1.0
 * @since 0.1
 */
public class CalculatorTest {
    public double result;
    public double expected;

    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();

        calc.add(1D, 1D);
        result = calc.getResult();
        expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMinusOneMinusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.minus(2D, 1D);
        result = calc.getResult();
        expected = 1D;
        assertThat(result, is(expected));
        calc.minus(1D, 2D);
        result = calc.getResult();
        expected = -1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivideOneDivideOneThenTwo() {
        Calculator calc = new Calculator();

        calc.divide(1D, 1D);
        result = calc.getResult();
        expected = 1D;
        assertThat(result, is(expected));

        calc.divide(2D, 1D);
        result = calc.getResult();
        expected = 2D;
        assertThat(result, is(expected));

        calc.divide(1D, 2D);
        result = calc.getResult();
        expected = 0.5D;
        assertThat(result, is(expected));

        calc.divide(1D, 0D);
        result = calc.getResult();
        expected = Double.POSITIVE_INFINITY;
        assertThat(result, is(expected));

        calc.divide(-1D, 0D);
        result = calc.getResult();
        expected = Double.NEGATIVE_INFINITY;
        assertThat(result, is(expected));

        calc.divide(0D, 0D);
        result = calc.getResult();
        expected = Double.NaN;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyOneMultiplyOneThenTwo() {
        Calculator calc = new Calculator();

        calc.multiply(1D, 1D);
        result = calc.getResult();
        expected = 1D;
        assertThat(result, is(expected));

        calc.multiply(2D, 1D);
        result = calc.getResult();
        expected = 2D;
        assertThat(result, is(expected));

        calc.multiply(2D, 0D);
        result = calc.getResult();
        expected = 0D;
        assertThat(result, is(expected));

    }
}

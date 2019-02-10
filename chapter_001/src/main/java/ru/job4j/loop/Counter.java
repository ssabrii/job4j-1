package ru.job4j.loop;

/**
 * Counter.
 * Simple counter.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Counter {
    /**
     * Method adds odd numbers.
     *
     * @param start  first number.
     * @param finish last number.
     * @return sum odd numbers.
     */
    public final int add(final int start, final int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}

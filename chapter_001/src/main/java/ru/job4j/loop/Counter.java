package ru.job4j.loop;

/**
 * Counter.
 * Simple counter.
 *
 * @author Maxim Vanny.
 */
public class Counter {
    /**
     * Method adds odd numbers.
     *
     * @param start  first number.
     * @param finish last number.
     * @return sum odd numbers.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}

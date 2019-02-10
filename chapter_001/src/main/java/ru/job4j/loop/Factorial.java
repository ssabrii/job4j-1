package ru.job4j.loop;

/**
 * Factorial.
 * it seek the factorial.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Factorial {
    /**
     * Method seek the factorial.
     *
     * @param n number for seek the factorial.
     * @return factorial.
     */
    public final int calc(final int n) {
        int multi = 1;
        for (int i = 1; i <= n; i++) {
            multi = multi * i;
        }
        return multi;
    }
}

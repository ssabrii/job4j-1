package ru.job4j.loop;

/**
 * Factorial.
 * it seek the factorial.
 *
 * @author Maxim Vanny.
 */
public class Factorial {
    /**
     * Method seek the factorial.
     *
     * @param n number for seek the factorial.
     * @return factorial.
     */
    public int calc(int n) {
        int multi = 1;
        if (n != 0) {
            for (int i = 1; i <= n; i++) {
                multi = multi * i;
            }
        }
        return multi;
    }
}

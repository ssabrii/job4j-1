package ru.job4j.max;

/**
 * Max.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Max {
    /**
     * Определяет маскимальное число из двух.
     *
     * @param first  Первое число.
     * @param second Второе число.
     * @return Максимальное число.
     */
    public final int max(final int first, final int second) {
        int result;
        if (first >= second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    /**
     * Определяет маскимальное число из трёх.
     *
     * @param first  Первое число.
     * @param second Второе число.
     * @param third  Третье число.
     * @return Максимальное число.
     */

    public final int max(final int first, final int second, final int third) {
        int maximum = this.max(first, second);
        maximum = this.max(maximum, third);
        return maximum;
    }
}



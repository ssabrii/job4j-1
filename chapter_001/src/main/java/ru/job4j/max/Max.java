package ru.job4j.max;

/**
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
    public int max(int first, int second) {
        return (first >= second) ? first : second;
    }


}


package ru.job4j.array;

/**
 * Square.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */

public class Square {
    /**
     * Return the array of numbers in pow.
     *
     * @param bound the length of array.
     * @return array.
     */
    public final int[] calculate(final int bound) {
        int[] rst = new int[bound];
        // заполнить массив через цикл элементами
        // от 1 до bound возведенными в квадрат
        for (int i = 1; i <= rst.length; i++) {
            rst[i - 1] = (int) Math.pow(i, 2);
        }
        return rst;
    }

}

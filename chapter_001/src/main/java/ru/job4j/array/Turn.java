package ru.job4j.array;

/**
 * Turn.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */

public class Turn {
    /**
     * Method read back the array.
     *
     * @param array array.
     * @return backward array.
     */
    public int[] back(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int tmp;
            tmp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = tmp;
        }
        return array;
    }
}



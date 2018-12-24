package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplicate.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * Remove duplicate from array.
     *
     * @param array string array.
     * @return array without duplicate.
     */
    public String[] remove(String[] array) {
        int unique = array.length;

        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}

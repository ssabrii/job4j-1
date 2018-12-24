package ru.job4j.array;

/**
 * Sort with Bubbles.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class BubbleSort {
    /**
     * Sort array with "bubble sort" method.
     *
     * @param array array int
     * @return sorted array;
     */
    public int[] sort(int[] array) {
        int tmp;
        for (int index = array.length - 1; index > 0; index--) {
            for (int runner = 0; runner < index; runner++) {
                if (array[runner] > array[runner + 1]) {
                    tmp = array[runner];
                    array[runner] = array[runner + 1];
                    array[runner + 1] = tmp;
                }
            }
        }
        return array;
    }
}

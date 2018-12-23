package ru.job4j.array;

/**
 * Matrix.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Matrix {
    /**
     * The table of multiply.
     *
     * @param size array size.
     * @return sorted array.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int index = 0; index < table.length; index++) {
            for (int runner = 0; runner < table.length; runner++) {
                table[index][runner] = (index + 1) * (runner + 1);
            }
        }
        return table;
    }
}

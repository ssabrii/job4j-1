package ru.job4j.array;
/**
 * MatrixCheck.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class MatrixCheck {
    /**
     * The check of array.
     *
     * @param data array size.
     * @return result.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if ((i + j) % 2 == 0) {
                    if (data[0][0] != data[i][j]) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
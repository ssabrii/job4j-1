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
            if (data[0][0] != data[i][i]) {
                result = false;
                break;
            }
            if (data[0][data.length - 1] != data[i][data.length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

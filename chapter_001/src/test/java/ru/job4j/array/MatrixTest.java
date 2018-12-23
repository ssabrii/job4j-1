package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Matrix Test.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class MatrixTest {
    @Test
    public void whenMatrix2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expected = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expected));
    }
}
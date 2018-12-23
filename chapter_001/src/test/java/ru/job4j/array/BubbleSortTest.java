package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BubbleSortTest.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class BubbleSortTest {
    @Test
    public void whenArrayByBubblesSort() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[]{5, 3, 2, 1, 4, 0};
        int[] result = bubbleSort.sort(input);
        int[] expected = new int[]{0, 1, 2, 3, 4, 5};
        assertThat(result, is(expected));
    }
}

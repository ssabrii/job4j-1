package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;
/**
 * ArrayDuplicateTest {.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class ArrayDuplicateTest {
    @Test
    public void henRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicates = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] rst = duplicates.remove(input);
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(expect, arrayContainingInAnyOrder(rst));

    }

}
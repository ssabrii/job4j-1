package stream.listtoarray;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertList2ArrayTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void whenAllArraysFromLisToListInteger() {
        ConvertList2Array list = new ConvertList2Array();
        List<int[]> library = new ArrayList<>();
        library.add(new int[]{1, 2});
        library.add(new int[]{3, 4, 5, 6});
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> result = list.convert(library);
        assertThat(result, is(expected));
    }

}
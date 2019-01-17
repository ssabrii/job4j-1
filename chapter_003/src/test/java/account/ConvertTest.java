package account;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertTest {
    @Test
    public void whenArrayToList() {
        Convert convert = new Convert();
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6}
        };

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> result = convert.arrayToList(array);
        assertThat(result, is(expected));
    }

    @Test
    public void whenListToArray() {
        Convert convert = new Convert();
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int[][] result = convert.listToArray(input, 3);
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}};
        assertThat(result, is(expected));
    }

}
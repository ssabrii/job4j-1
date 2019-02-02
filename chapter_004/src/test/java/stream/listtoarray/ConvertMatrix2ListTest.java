package stream.listtoarray;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertMatrix2ListTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ConvertMatrix2ListTest {
    private final ConvertMatrix2List list = new ConvertMatrix2List();

    @Test
    public void when2on2ArrayThenList4() {
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = List.of(
                1, 2, 3, 4
        );
        List<Integer> result = list.arrayToList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenStringArrayToListListString() {
        String[][] source = {
                {"A", "B", "C"},
                {"A", "B", "C"},
        };
        List<List<String>> result = list.stringToList(source);
        List<List<String>> expected = List.of(
                List.of("A", "B", "C"),
                List.of("A", "B", "C")
        );
        assertThat(result, is(expected));
    }
    @Test
    public void whenStringArrayToListString() {
        String[][] source = {
                {"A", "B", "C"},
                {"A", "B", "C"},
        };
        List<String> result = list.stringToFlatList(source);
        List<String> expected = List.of("A", "B", "C", "A", "B", "C");
        assertThat(result, is(expected));
    }
}
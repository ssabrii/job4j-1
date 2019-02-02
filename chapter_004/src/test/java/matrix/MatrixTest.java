package matrix;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    private final Matrix matrix = new Matrix();

    @Test
    public void whenMatrixToList() {
        Integer[][] source = {
                {1, 2},
                {3, 4}
        };
        List<Integer> result = matrix.arraysMatrixToList(source);
        List<Integer> expected = List.of(1, 2, 3, 4);
        assertThat(result, is(expected));
    }

    @Test
    public void listMatrixToList() {
        List<List<Integer>> source = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );
        List<Integer> result = matrix.listMatrixToList(source);
        List<Integer> expected = List.of(1, 2, 3, 4);
        assertThat(result, is(expected));
    }
}
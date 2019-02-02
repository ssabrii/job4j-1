package matrix;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Matrix.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class Matrix {
    /**
     * Method convert array Integer matrix to list.
     *
     * @param matrix matrix
     * @return list
     */
    public final List<Integer> arraysMatrixToList(final Integer[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    /**
     * Method convert List Integer matrix to list.
     *
     * @param matrix matrix
     * @return list
     */
    public final List<Integer> listMatrixToList(
            final List<List<Integer>> matrix) {
        return matrix.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}

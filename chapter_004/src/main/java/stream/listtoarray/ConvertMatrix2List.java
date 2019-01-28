package stream.listtoarray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ConvertMatrix2List.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
public class ConvertMatrix2List {
    /**
     * Method get list from array.
     *
     * @param array array.
     * @return list from array.
     */
    public final List<Integer> toList(final int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());
     /*   List<Integer> list = new ArrayList<>();
        for (int[] out : array) {
            for (int in : out) {
                list.add(in);
            }
        }
        return list;*/
    }
}

package stream.listtoarray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ConvertList2Array.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
public class ConvertList2Array {
    /**
     * Method convert list to array.
     *
     * @param list list Integer.
     * @param rows number rows.
     * @return array from list.
     */
    public final Integer[][] toArray(final List<Integer> list, final int rows) {

        int cells;
        if (list.size() % rows == 0) {
            cells = list.size() / rows;
        } else {
            cells = (list.size() / rows) + 1;
        }
        final int[] count = {0};


        return IntStream.range(0, rows)
                .mapToObj(x -> IntStream.range(0, cells)
                        .mapToObj(y -> count[0] >= list.size() ? 0 : list.get(count[0]++))
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);
    }

    /**
     * Method convert members of arrays from list to general list.
     *
     * @param list the list of arrays[].
     * @return the list of Integers.
     */
    public final List<Integer> convert(final List<int[]> list) {
        return list.stream()
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());
    }
}

package listtoarray;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertMatrix2List.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ConvertMatrix2List {
    /**
     * Method get list from array.
     *
     * @param array array.
     * @return list from array.
     */
    public final List<Integer> toList(final int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] out : array) {
            for (int in : out) {
                list.add(in);
            }
        }
        return list;
    }
}

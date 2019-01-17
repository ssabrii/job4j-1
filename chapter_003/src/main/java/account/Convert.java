package account;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Convert.
 *
 * @author Maxim Vanny.
 * @version 3.0
 * @since 0.1
 */
public class Convert {

    /**
     * Method convert double array to list.
     *
     * @param array double array
     * @return list
     */
    public final List<Integer> arrayToList(final int[][] array) {
        Objects.requireNonNull(array, "array must not be null");
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] out : array) {
            for (int in : out) {
                list.add(in);
            }
        }
        return list;
    }

    /**
     * Method convert list to array.
     *
     * @param list list
     * @param rows set rows of array
     * @return array
     */
    public final int[][] listToArray(final List<Integer> list, final int rows) {
        Objects.requireNonNull(list, "list must not be null");
        Iterator<Integer> iterator = list.iterator();
        int cell = rows + 1;
        if (list.size() % rows != 0) {
            cell = rows;
        }
        int[][] array = new int[rows][cell];
        for (int out = 0; out < rows; out++) {
            for (int in = 0; in < cell; in++) {
                if (iterator.hasNext()) {
                    array[out][in] = iterator.next();
                } else {
                    break;
                }
            }
        }
        return array;
    }
}

package listtoarray;

import java.util.Iterator;
import java.util.List;

/**
 * ConvertList2Array.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ConvertList2Array {
    /**
     * Method convert list to array.
     *
     * @param list list Integer.
     * @param rows number rows.
     * @return array from list.
     */
    public final int[][] toArray(final List<Integer> list, final int rows) {
        int cells = 0;
        for (int index = 0; index < list.size(); index++) {
            if (index % rows == 0) {
                cells++;
            }
        }
        Iterator<Integer> iterator = list.iterator();
        int[][] array = new int[rows][cells];
        for (int out = 0; out < rows; out++) {
            for (int in = 0; in < cells; in++) {
                if (iterator.hasNext()) {
                    array[out][in] = iterator.next();
                }
            }
        }
        return array;
    }
}

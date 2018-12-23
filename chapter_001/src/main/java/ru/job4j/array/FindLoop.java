package ru.job4j.array;
/**
 * Square.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class FindLoop {
    /**
     * Method finds number in array.
     *
     * @param data  array.
     * @param el seek number.
     * @return  index or -1.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
package sortstring;

import java.util.Comparator;

/**
 * StringsCompare.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class StringsCompare implements Comparator<String> {
    /**
     * Method compare string by length and character.
     *
     * @param left  first string
     * @param right second string
     * @return result
     */

    @Override
    public final int compare(final String left, final String right) {
        char[] one = left.toCharArray();
        char[] two = right.toCharArray();
        int result = 0;
        for (int index = 1; index < ((one.length + two.length) / 2) + 1; index++) {
            if ((one[index - 1] != two[index - 1])) {
                if (index - 1 == one.length) {
                    result = 1;
                    break;
                }
                if (index - 1 == two.length) {
                    result = -1;
                    break;
                }
                result = Character.compare(one[index - 1], two[index - 1]);
                break;
            } else {
                result = Integer.compare(one.length, two.length);
            }

        }
        return result;
    }
}

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
        if (one.length != two.length) {
            result = Integer.compare(one.length, two.length);
        } else {
            for (int index = 0; index < one.length; index++) {
                if (one[index] != two[index]) {
                    result = Character.compare(one[index], two[index]);
                    return result;
                }
            }
        }
        return result;
    }
}

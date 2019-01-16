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
    public final int compareT(final String left, final String right) {
        char[] one = left.toCharArray();
        char[] two = right.toCharArray();
        int result = 0;
        for (int ind = 1; ind < ((one.length + two.length) / 2) + 1; ind++) {
            if ((one[ind - 1] != two[ind - 1])) {
                if (ind - 1 == one.length) {
                    result = 1;
                    break;
                }
                if (ind - 1 == two.length) {
                    result = -1;
                    break;
                }
                result = Character.compare(one[ind - 1], two[ind - 1]);
                break;
            } else {
                result = Integer.compare(one.length, two.length);
            }

        }
        return result;
    }

    /**
     * Method compare string by length and character.
     *
     * @param left  first string
     * @param right second string
     * @return result
     */
    @Override
    public int compare(String left, String right) {
        int length = left.length();
        int length1 = right.length();
        int result = length - length1;
        int min = Math.min(length, length1);
        for (int i = 0; i < min; i++) {
            int r = Character.compare(left.charAt(i), right.charAt(i));
            if (r != 0) {
                result = r;
                break;
            }
        }
        return result;
    }
}

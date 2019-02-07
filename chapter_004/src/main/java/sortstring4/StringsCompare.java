package sortstring4;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * StringsCompare.
 *
 * @author Maxim Vanny.
 * @version 4.0
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
        int length = left.length();
        int length1 = right.length();
        int result = length - length1;
        int min = Math.min(length, length1);
        final int first = IntStream.range(0, min)
                .map(z -> Character.compare(left.charAt(z), right.charAt(z)))
                .filter(z -> z != 0)
                .findFirst().orElse(0);
        if (first != 0) {
            result = first;
        }
        return result;
    }
}

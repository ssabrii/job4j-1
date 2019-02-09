package sortstring;

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
    @SuppressWarnings("Duplicates")
    public final int compare(final String left, final String right) {
        int min = Math.min(left.length(), right.length());
        return IntStream.range(0, min)
                .map(z -> Character.compare(left.charAt(z), right.charAt(z)))
                .filter(z -> z != 0)
                .findFirst()
                .orElse(Integer.compare(left.length(), right.length()));
    }
}

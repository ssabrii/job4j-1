package sortuser;

import java.util.Comparator;

/**
 * CompNameUser.
 *
 * @author Maxim Vanny
 * @version 2.0
 * @since 0.1
 */
public class CompNameUser implements Comparator<User> {
    /**
     * Method sort users by name length.
     *
     * @param o1 first user
     * @param o2 second user
     * @return result by int
     */
    @Override
    public final int compare(final User o1, final User o2) {
        int name1 = o1.getName().toCharArray().length;
        int name2 = o2.getName().toCharArray().length;
        return name1 - name2;
    }
}

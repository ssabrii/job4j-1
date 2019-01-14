package sortuser;

import java.util.List;
import java.util.TreeSet;

/**
 * SortUser.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class SortUser {
    /**
     * Method sort users by age.
     *
     * @param list the list of users
     * @return the set of users to sort by age
     */
    public final TreeSet<User> sort(final List<User> list) {
        return new TreeSet<>(list);
    }
}

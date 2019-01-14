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

    /**
     * Method sort users by length name.
     *
     * @param list the list of users
     * @return the list of users sorts by the length of name
     */
    public final List<User> sortNameLength(final List<User> list) {
        list.sort(new CompNameUser());
        return list;
    }

    /**
     * Method sorts users by name and age.
     *
     * @param list the list of users
     * @return the list of users sorts by name and age
     */
    public final List<User> sortByAllFields(final List<User> list) {
        list.sort(new CompNameAndAge());
        return list;
    }
}

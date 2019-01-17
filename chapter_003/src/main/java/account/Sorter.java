package account;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sorter.
 *
 * @author Maxim Vanny.
 * @version 3.0
 * @since 0.1
 */
public class Sorter {
    /**
     * Method sort the list of users.
     *
     * @param list the list  of users
     * @return list is sorted
     */
    public final Set<User> sort(final List<User> list) {
        Objects.requireNonNull(list, "list must not be null");
        return new TreeSet<>(list);
    }

    /**
     * Method sort the list of users be length name.
     *
     * @param list the list of users
     * @return the list is sorted
     */
    public final List<User> sortByNameLength(final List<User> list) {
        Objects.requireNonNull(list, "list must not be null");
        Comparator<User> compByNameLength = new Comparator<User>() {
            @Override
            public final int compare(final User o1, final User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compByNameLength);
        return list;
    }

    /**
     * Method sort user in list by name.
     * and than by age.
     *
     * @param list the list of users
     * @return the list is sorted
     */
    public final List<User> sortByNameAndAge(final List<User> list) {
        Objects.requireNonNull(list, "list must not be null");
        Comparator<User> compByName = new Comparator<User>() {
            @Override
            public final int compare(final User o1, final User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compByAge = new Comparator<User>() {
            @Override
            public final int compare(final User o1, final User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compByName.thenComparing(compByAge));
        return list;
    }
}

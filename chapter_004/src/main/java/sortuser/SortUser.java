package sortuser;

import java.util.List;
import java.util.TreeSet;

/**
 * SortUser.
 *
 * @author Maxim Vanny.
 * @version 4.0
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
        list.sort((o1, o2) -> {
            int name1 = o1.getName().toCharArray().length;
            int name2 = o2.getName().toCharArray().length;
            return name1 - name2;
        });
        return list;
    }

    /**
     * Method sorts users by name and age.
     *
     * @param list the list of users
     * @return the list of users sorts by name and age
     */
    @SuppressWarnings("Duplicates")
    public final List<User> sortByAllFields(final List<User> list) {
        list.sort((o1, o2) -> {
            String name1 = o1.getName();
            String name2 = o2.getName();
            int sComp = name1.compareTo(name2);
            if (sComp != 0) {
                return sComp;
            } else {
                Integer age1 = o1.getAge();
                Integer age2 = o2.getAge();
                return age1.compareTo(age2);
            }
        });
        return list;
    }
}

package sortuser;

import java.util.Comparator;

/**
 * CompNameAndAge.
 *
 * @author Maxim Vanny
 * @version 2.0
 * @since 0.1
 */
public class CompNameAndAge implements Comparator<User> {
    /**
     * Method sort users by name and age.
     *
     * @param o1 first user
     * @param o2 second user
     * @return result by int
     */
    @Override
    public final int compare(final User o1, final User o2) {

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
    }
}


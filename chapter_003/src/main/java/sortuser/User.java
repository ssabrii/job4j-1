package sortuser;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 2.0
 * @since 0.1
 */
public class User implements Comparable {
    /**
     * the field of name user.
     */
    private final String name;


    /**
     * the field of age user.
     */
    private final int age;

    /**
     * Constructor.
     *
     * @param aName the name of user
     * @param aAge  the age of user
     */
    public User(final String aName, final int aAge) {
        this.name = aName;
        this.age = aAge;
    }

    /**
     * Method compare two object by age.
     *
     * @param o second object
     * @return result by int
     */
    @Override
    public final int compareTo(final Object o) {
        return this.age - ((User) o).age;
    }

    /**
     * toString.
     *
     * @return string mapping object
     */
    @Override
    public final String toString() {
        return "User{"
                +
                "name='"
                + name
                + '\''
                +
                ", age="
                + age
                +
                '}';
    }
}

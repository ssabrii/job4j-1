package sortuser;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class User implements Comparable<User> {
    /**
     * the field of name user.
     */
    private final String name;
    /**
     * the field of age user.
     */
    private final int age;

    /**
     * Method get the age of user.
     *
     * @return the age of user
     */
    public final int getAge() {
        return this.age;
    }

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
     * Method gets the name of user.
     *
     * @return the name of user
     */
    public final String getName() {
        return this.name;
    }

    @Override
    public final int compareTo(final User o) {
        return this.age - o.age;
    }

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


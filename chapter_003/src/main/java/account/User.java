package account;

import java.util.Objects;

/**
 * User.
 *
 * @author Maxim Vanny.
 * @version 3.0
 * @since 0.1
 */
public class User implements Comparable<User> {
    /**
     * The name of user.
     */
    private final String name;
    /**
     * The age of user.
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
     * Method get the name of user.
     *
     * @return the name of user
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Method get the age of user.
     *
     * @return the age of user
     */
    public final int getAge() {
        return this.age;
    }

    /**
     * Method compare object User.
     *
     * @param another object User
     * @return result compare
     */
    @Override
    public final int compareTo(final User another) {
        Objects.requireNonNull(another, "another must not be null");
        return this.name.compareTo(another.name);
    }

    /**
     * Method get string's mapping object.
     *
     * @return string's mapping object
     */
    @Override
    public final String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ",age="
                + age
                + '}';
    }
}


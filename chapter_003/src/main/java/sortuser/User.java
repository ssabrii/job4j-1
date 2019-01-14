package sortuser;

import java.util.Objects;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 2.0
 * @since 0.1
 */
public class User {
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
     * Method for equals objects.
     *
     * @param o object
     * @return boolean result
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age;
    }

    /**
     * The hash code of object.
     *
     * @return the hashcode of object
     */
    @Override
    public final int hashCode() {
        return Objects.hash(age);
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

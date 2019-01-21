package bank.models;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 3.0
 * @since 0.1
 */
public class User implements Comparable<User> {
    /**
     * The user's name.
     */
    private final String name;

    /**
     * The user's passport.
     */
    private final String passport;

    /**
     * Method get the name of user.
     *
     * @return the name of user
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Method get the passport of user.
     *
     * @return the passport of user
     */
    public final String getPassport() {
        return this.passport;
    }

    /**
     * Constructor.
     *
     * @param aName     the name of user
     * @param aPassport the passport of user
     */
    public User(final String aName, final String aPassport) {
        this.name = aName;
        this.passport = aPassport;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name)
                && passport.equals(user.passport);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name, this.passport);
    }

    @Override
    public final int compareTo(final User o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("passport='" + passport + "'")
                .toString();
    }
}

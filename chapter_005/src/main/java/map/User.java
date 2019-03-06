package map;

import java.util.Calendar;
import java.util.Objects;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 3/6/2019
 */
public class User {
    /**
     * field name.
     */
    private final String name;
    /**
     * field children.
     */
    private final int children;
    /**
     * field birthday.
     */
    private final Calendar birthday;

    /**
     * Constructor.
     *
     * @param aName     name
     * @param aChildren children
     * @param aBirthday birthday
     */
    public User(final String aName, final int aChildren,
                final Calendar aBirthday) {
        this.name = aName;
        this.children = aChildren;
        this.birthday = aBirthday;
    }
    @Override
    public final int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}

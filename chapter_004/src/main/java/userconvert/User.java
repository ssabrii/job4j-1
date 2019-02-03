package userconvert;

/**
 * User.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */

public class User {
    /**
     * the name of user.
     */
    private final String name;
    /**
     * the address of user.
     */
    private final String city;
    /**
     * ID user.
     */
    private final Integer id;

    /**
     * Constructor.
     *
     * @param aName the name of user
     * @param aCity the address of user
     * @param aId   the id of user
     */
    public User(final String aName, final String aCity, final Integer aId) {
        this.name = aName;
        this.city = aCity;
        this.id = aId;
    }

    /**
     * Constructor.
     *
     * @param aName the name of user
     * @param aCity the address of user
     */
    public User(final String aName, final String aCity) {
        this.name = aName;
        this.city = aCity;
        this.id = null;
    }

    @Override
    public final String toString() {
        return "User{"
                +
                "name='"
                + name
                + '\''
                +
                ", city='"
                + city
                + '\''
                +
                ", id="
                + id
                +
                '}'
                + '\n';
    }

    /**
     * get name.
     *
     * @return name
     */
    public final String getName() {
        return name;
    }

    /**
     * Get id.
     *
     * @return id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * get id.
     *
     * @return city
     */
    public final String getCity() {
        return city;
    }
}

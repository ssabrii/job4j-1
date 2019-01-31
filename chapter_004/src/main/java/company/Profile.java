package company;

/**
 * Profile.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class Profile {
    /**
     * link to Address.
     */
    private final Address address;

    /**
     * Constructor.
     *
     * @param aAddress address
     */
    public Profile(final Address aAddress) {
        this.address = aAddress;
    }

    /**
     * Method ger address.
     *
     * @return address
     */
    public final Address getAddress() {
        return this.address;
    }
}

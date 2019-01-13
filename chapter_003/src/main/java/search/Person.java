package search;

/**
 * Person.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
@SuppressWarnings("unused")
public class Person {
    /**
     * name person.
     */
    private final String name;
    /**
     * surname person.
     */
    private final String surname;
    /**
     * phone person.
     */
    private final String phone;
    /**
     * address person.
     */
    private final String address;

    /**
     * Constructor.
     *
     * @param aName    name person.
     * @param aSurname surname person.
     * @param aPhone   phone person.
     * @param aAddress address person.
     */
    public Person(final String aName, final String aSurname,
                  final String aPhone, final String aAddress) {
        this.name = aName;
        this.surname = aSurname;
        this.phone = aPhone;
        this.address = aAddress;
    }

    /**
     * getName.
     *
     * @return name person.
     */
    public final String getName() {
        return name;
    }

    /**
     * getSurname.
     *
     * @return surname person.
     */
    public final String getSurname() {
        return surname;
    }

    /**
     * getPhone.
     *
     * @return phone person.
     */
    public final String getPhone() {
        return phone;
    }

    /**
     * address person.
     *
     * @return address person.
     */
    public final String getAddress() {
        return address;
    }
}


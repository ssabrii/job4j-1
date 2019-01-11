package search;

/**
 * Person.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
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
     * @param pname    name person.
     * @param psurname surname person.
     * @param pphone   phone person.
     * @param paddress address person.
     */
    public Person(final String pname, final String psurname,
                  final String pphone, final String paddress) {
        this.name = pname;
        this.surname = psurname;
        this.phone = pphone;
        this.address = paddress;
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


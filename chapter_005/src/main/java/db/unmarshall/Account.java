package db.unmarshall;

/**
 * Account.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/1/2019
 */
public class Account {
    /**
     * field id.
     */
    private int id;

    /**
     * field balance.
     */
    private int balance;
    /**
     * field type.
     */
    private String type;
    /**
     * field name.
     */
    private String name;

    /**
     * Getter.
     *
     * @return id
     */
    public final int getId() {
        return this.id;
    }

    /**
     * Setter.
     *
     * @param aId id
     */
    public final void setId(final int aId) {
        this.id = aId;
    }

    /**
     * Getter.
     *
     * @return name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Setter.
     *
     * @param aName name
     */
    public final void setName(final String aName) {
        this.name = aName;
    }

    /**
     * Getter.
     *
     * @return type
     */
    public final String getType() {
        return this.type;
    }

    /**
     * Setter.
     *
     * @param aType type
     */
    public final void setType(final String aType) {
        this.type = aType;
    }

    /**
     * Getter.
     *
     * @return balance
     */
    public final int getBalance() {
        return this.balance;
    }

    /**
     * Setter.
     *
     * @param aBalance balance
     */
    public final void setBalance(final int aBalance) {
        this.balance = aBalance;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Details - ")
                .append("Type:'")
                .append(this.getType())
                .append("', ")
                .append("Name:'")
                .append(this.getName())
                .append("', ")
                .append("Balance:'")
                .append(this.getBalance())
                .append("', ")
                .append("Id:'")
                .append(this.getId())
                .append("'.");
        return sb.toString();
    }
}


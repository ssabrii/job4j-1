package account;

import java.util.Objects;

/**
 * Account.
 *
 * @author Maxim Vanny.
 * @version 3.0
 * @since 0.1
 */
public class Account {
    /**
     * the sum of money in account.
     */
    private double values;
    /**
     * the requisite of account.
     */
    private final String reqs;

    /**
     * Constructor.
     *
     * @param aValues     the sum of money in account
     * @param aRequisites requisite account
     */
    public Account(final double aValues, final String aRequisites) {
        this.values = aValues;
        this.reqs = aRequisites;
    }

    /**
     * Method get values of money in the user's account.
     *
     * @return the values of money
     */
    public final double getValues() {
        return this.values;
    }

    /**
     * The requisite of user's account.
     *
     * @return the requisite
     */

    public final String getReqs() {
        return this.reqs;
    }

    /**
     * Method make transfer amount between account.
     *
     * @param target the target account
     * @param amount amount in account
     * @throws LimitMoneyException exception if limit is out
     */
    public final void transfer(final Account target, final double amount)
            throws LimitMoneyException {
        Objects.requireNonNull(target, "account must not be null");
        if ((this.values - amount) >= 0) {
            this.values -= amount;
            target.values += amount;
        } else {
            throw new LimitMoneyException("limit out");
        }
    }

    /**
     * Method toString.
     *
     * @return get string mapping of object
     */
    @Override
    public final String toString() {
        return "Account{"
                + "reqs='"
                + reqs
                + "',"
                + "values="
                + values
                + "}";
    }

    /**
     * Method equals objects.
     *
     * @param o object for equals
     * @return the result of equals
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return this.reqs.equals(account.reqs);
    }

    /**
     * Method ger hashcode of object.
     *
     * @return the hashcode of object
     */
    public final int hashCode() {
        return this.reqs.hashCode();
    }
}


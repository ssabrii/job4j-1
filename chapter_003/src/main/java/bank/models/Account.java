package bank.models;

import bank.exception.LimitMoneyException;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Account.
 *
 * @author Maxim Vanny
 * @version 3.0
 * @since 0.1
 */
public class Account {
    /**
     * the amount in account.
     */
    private double value;
    /**
     * the requisite of account.
     */
    private final String requisites;

    /**
     * Constructor.
     *
     * @param aValue      the name of user
     * @param aRequisites the passport of user
     */
    public Account(final double aValue, final String aRequisites) {
        this.value = aValue;
        this.requisites = aRequisites;
    }

    /**
     * Method get values on account.
     *
     * @return the sum of account
     */
    public final double getValue() {
        return value;
    }

    /**
     * Method gets the requisite of account.
     *
     * @return the requisite of account
     */
    public final String getRequisites() {
        return requisites;
    }

    /**
     * Method transfer amount between accounts.
     *
     * @param target the requisite of account destination
     * @param amount the sum for transfer
     * @throws LimitMoneyException limit out on recourse account
     */
    public final void transfer(final Account target, final double amount)
            throws LimitMoneyException {
        Objects.requireNonNull(target, "must be not null");
        if (this.value - amount >= 0) {
            this.value -= amount;
            target.value += amount;
        } else {
            throw new LimitMoneyException("limit out");
        }
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .add("requisites='" + requisites + "'")
                .toString();
    }
}


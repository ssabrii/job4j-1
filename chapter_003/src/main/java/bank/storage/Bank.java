package bank.storage;

import bank.exception.ExistAccountException;
import bank.exception.ExistStorageException;
import bank.exception.LimitMoneyException;
import bank.exception.NotExistAccountException;
import bank.exception.NotExistStorageException;
import bank.models.Account;
import bank.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Bank.
 *
 * @author Maxim Vanny
 * @version 3.0
 * @since 0.1
 */
public class Bank {

    /**
     * Storage safe user's account.
     */
    private final Map<User, List<Account>> map = new TreeMap<>();

    /**
     * Method get all user accounts.
     *
     * @return the list of user in storage
     */
    public final List<User> getAllUser() {
        return new ArrayList<>(map.keySet());
    }

    /**
     * Method add new user.
     *
     * @param user new user
     * @throws ExistStorageException user is in storage
     */
    public final void addUser(final User user) throws ExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        if (this.map.containsKey(user)) {
            throw new ExistStorageException("user is in storage.");
        } else {
            this.map.put(user, new ArrayList<>());
        }
    }

    /**
     * Method delete user from storage.
     *
     * @param user user
     * @throws NotExistStorageException user is't in storage
     */
    public final void deleteUser(final User user) throws NotExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        if (!this.map.containsKey(user)) {
            throw new NotExistStorageException("user is't in storage.");
        } else {
            this.map.remove(user);
        }
    }

    /**
     * Method delete the account of user.
     *
     * @param passport the passport of user
     * @param account  the account of user
     * @throws NotExistAccountException account is't in storage
     */
    public final void deleteAccountFromUser(final String passport, final Account account)
            throws NotExistAccountException {
        Objects.requireNonNull(passport, "passport must not be null");
        Objects.requireNonNull(account, "account must not be null");
        for (Map.Entry<User, List<Account>> entry : this.map.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                if (entry.getValue().contains(account)) {
                    entry.getValue().remove(account);
                    return;
                } else {
                    throw new NotExistAccountException("account is't in storage");
                }
            }
        }
    }


    /**
     * Method add account for user.
     *
     * @param passport the passport of user
     * @param account  new account for user
     * @throws ExistAccountException account is in storage
     */
    public final void addAccountToUser(final String passport, final Account account)
            throws ExistAccountException {
        Objects.requireNonNull(passport, "passport must not be null");
        Objects.requireNonNull(account, "account must not be null");
        for (Map.Entry<User, List<Account>> entry : this.map.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                if (!entry.getValue().contains(account)) {
                    entry.getValue().add(account);
                    return;
                } else {
                    throw new ExistAccountException("account is in storage");
                }
            }
        }
    }

    /**
     * Method get all user account.
     *
     * @param passport the passport of user
     * @return all user account
     */
    public final List<Account> getUserAccounts(final String passport) {
        Objects.requireNonNull(passport, "passport must not be null");
        List<Account> result = new ArrayList<>();
        for (Map.Entry<User, List<Account>> entry : this.map.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                result = entry.getValue();
                break;
            }
        }
        return result;
    }

    /**
     * Method transfer amount between accounts.
     *
     * @param srcPass the passport of user
     * @param srcReq  the requisite of account user
     * @param dstPass the passport destination
     * @param dstReq  the requisite destination
     * @param amount  the sum for transfer
     */
    public final void transferMoney(final String srcPass, final String srcReq,
                                    final String dstPass, final String dstReq,
                                    final double amount) {
        Objects.requireNonNull(srcPass, "passport must not be null");
        Objects.requireNonNull(dstPass, "passport must not be null");
        Objects.requireNonNull(srcReq, "requisite must not be null");
        Objects.requireNonNull(dstReq, "requisite must not be null");
        if (!this.map.isEmpty()) {
            try {
                final int ndxSrs = this.getIndexAccount(srcPass, srcReq);
                final int ndxDst = this.getIndexAccount(dstPass, dstReq);
                final Account one = getUserAccounts(srcPass).get(ndxSrs);
                final Account two = getUserAccounts(dstPass).get(ndxDst);
                one.transfer(two, amount);
            } catch (LimitMoneyException lme) {
                System.out.println("Limit out. Check balance.");
            } catch (NotExistAccountException nae) {
                System.out.println("Account is't in storage.");
            }
        } else {
            System.out.println("Storage is empty.");
        }
    }

    /**
     * Method gets index account in storage.
     *
     * @param passport  the user's passport
     * @param requisite the user's requisite
     * @return the index in storage
     * @throws NotExistAccountException account is't storage
     */
    protected final int getIndexAccount(final String passport, final String requisite)
            throws NotExistAccountException {
        int ndx = -1;
        for (Map.Entry<User, List<Account>> entry : this.map.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                for (Account account : entry.getValue()) {
                    if (account.getRequisites().equals(requisite)) {
                        ndx = entry.getValue().indexOf(account);
                        break;
                    }
                }
            }
        }
        if (ndx == -1) {
            throw new NotExistAccountException("account is't in storage.");
        }
        return ndx;
    }
}

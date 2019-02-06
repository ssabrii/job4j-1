package banks.storage;

import banks.exception.ExistAccountException;
import banks.exception.ExistStorageException;
import banks.exception.LimitMoneyException;
import banks.exception.NotExistAccountException;
import banks.exception.NotExistStorageException;
import banks.models.Account;
import banks.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Bank.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
public class Bank {

    /**
     * Storage safe user's account.
     */
    private final Map<User, List<Account>> map = new HashMap<>();

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
     * Method add account for user.
     *
     * @param passport the passport of user
     * @param account  new account for user
     * @throws ExistAccountException    account is in storage
     * @throws NotExistAccountException account is in storage
     */
    public final void addAccountToUser(final String passport,
                                       final Account account)
            throws ExistAccountException, NotExistAccountException {
        Objects.requireNonNull(passport, "passport must not be null");
        Objects.requireNonNull(account, "account must not be null");
        if (checkAccounts(passport, account)) {
            throw new ExistAccountException("account is in storage");
        } else {
            getUserAccounts(passport).add(account);
        }
    }

    /**
     * Method delete user from storage.
     *
     * @param user user
     * @throws NotExistStorageException user is't in storage
     */
    public final void deleteUser(final User user)
            throws NotExistStorageException {
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
    public final void deleteAccountFromUser(final String passport,
                                            final Account account)
            throws NotExistAccountException {
        Objects.requireNonNull(passport, "passport must not be null");
        Objects.requireNonNull(account, "account must not be null");
        if (!checkAccounts(passport, account)) {
            throw new NotExistAccountException("account is't in storage");
        } else {
            getUserAccounts(passport).remove(account);
        }
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
        try {
            final Account resource = getUserAccount(srcPass, srcReq);
            final Account target = getUserAccount(dstPass, dstReq);
            resource.transfer(target, amount);
        } catch (LimitMoneyException lme) {
            System.out.println("Limit out. Check balance.");
        } catch (NotExistAccountException nae) {
            System.out.println("Account is't in storage.");
        }
    }

    /**
     * Method get all user account.
     *
     * @param passport the passport of user
     * @return all user account
     * @throws NotExistAccountException account is in storage
     */
    public final List<Account> getUserAccounts(final String passport)
            throws NotExistAccountException {
        Objects.requireNonNull(passport, "passport must not be null");
        return this.map.entrySet().stream()
                .filter(e -> e.getKey().getPassport().equals(passport))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow(
                        () -> new NotExistAccountException(
                                "Account is't in storage."));
    }

    /**
     * Method gets index account in storage.
     *
     * @param passe the user's passport
     * @param req   the user's requisite
     * @return the index in storage
     * @throws NotExistAccountException account is't storage
     */
    protected final Account getUserAccount(final String passe, final String req)
            throws NotExistAccountException {
        final Set<String> catalogSet = getUserAccounts(passe).stream()
                .map(Account::getRequisites)
                .collect(Collectors.toSet());
        return getUserAccounts(passe).stream()
                .filter((z) -> catalogSet.contains(req))
                .takeWhile(Objects::nonNull)
                .findFirst()
                .orElseThrow(
                        () -> new NotExistAccountException(
                                "account is't in storage."));
    }

    /**
     * Method check contain account in user.
     *
     * @param passport passport
     * @param account  account
     * @return boolean is contain accounts
     * @throws NotExistAccountException not exist account in storage
     */
    private boolean checkAccounts(final String passport, final Account account)
            throws NotExistAccountException {
        final Set<String> catalogSet = getUserAccounts(passport).stream()
                .map(Account::getRequisites)
                .collect(Collectors.toSet());
        return getUserAccounts(passport).stream()
                .anyMatch((z) -> catalogSet.contains(account.getRequisites()));
    }

    /**
     * Method check contain user in storage.
     * Method not used.
     *
     * @param user user
     * @return boolean is contain users
     */
    private boolean checkUsers(final User user) {
        return this.map.entrySet().stream()
                .map(e -> e.getKey().getPassport())
                .anyMatch(z -> z.contains(user.getPassport()));

    }
}

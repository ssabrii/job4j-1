package account;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Bank.
 *
 * @author Maxim Vanny.
 * @version 3.0
 * @since 0.1
 */
public class Bank {
    /**
     * TreeMap storage for user' account.
     */
    private final TreeMap<User, ArrayList<Account>> map = new TreeMap<>();

    /**
     * Method add user to storage.
     *
     * @param user user
     * @throws ExistStorageException storage is the user
     */
    public final void addUser(final User user) throws ExistStorageException {
        Objects.requireNonNull(user, "user must be not null");
        if (!this.map.containsKey(user)) {
            this.map.put(user, new ArrayList<>());
        } else {
            throw new ExistStorageException("The user is in storage");
        }
    }

    /**
     * Method delete user from storage.
     *
     * @param user user
     * @throws NotExistStorageException user is'tin storage
     */
    public final void delete(final User user) throws NotExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        if (this.map.containsKey(user)) {
            this.map.remove(user);
        } else {
            throw new NotExistStorageException("The user is't in storage");
        }
    }

    /**
     * Method add account from user.
     *
     * @param user    user
     * @param account account
     * @throws NotExistStorageException the user is't in storage
     */
    public final void add(final User user, final Account account) throws NotExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        Objects.requireNonNull(user, "account must not be null");
        if (this.map.containsKey(user)) {
            this.map.get(user).add(account);
        } else {
            throw new NotExistStorageException("The user is't in storage");
        }
    }

    /**
     * Method get actual user's account in storage.
     *
     * @param user    user
     * @param account actual account
     * @return the list of user's account in storage
     * @throws NotExistStorageException the user is't in storage
     */
    public final Account getUserAccount(final User user, final Account account) throws NotExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        Objects.requireNonNull(user, "account must not be null");
        ArrayList<Account> list;
        if (this.map.containsKey(user)) {
            list = this.map.get(user);
        } else {
            throw new NotExistStorageException("The user is't in storage");
        }
        return list.get(list.indexOf(account));
    }

    /**
     * Method delete user' account from storage.
     *
     * @param user    user
     * @param account user's account
     * @throws NotExistStorageException the user is't in storage
     */
    public final void deleteAccount(final User user, final Account account) throws NotExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        Objects.requireNonNull(user, "account must not be null");
        if (this.map.containsKey(user)) {
            this.map.get(user).remove(account);
        } else {
            throw new NotExistStorageException("The user is't in storage");
        }
    }

    /**
     * Method get user's accounts from storage.
     *
     * @param user user
     * @return all user's account from storage
     * @throws NotExistStorageException the user is't in storage
     */
    public final List<Account> getAccounts(final User user) throws NotExistStorageException {
        Objects.requireNonNull(user, "user must not be null");
        if (this.map.containsKey(user)) {
            return this.map.get(user);
        } else {
            throw new NotExistStorageException("The user is't in storage");
        }
    }

    /**
     * Method get users accounts from storage.
     *
     * @return all users accounts from storage
     */
    public final List<User> getAllUsers() {
        return new ArrayList<>(this.map.keySet());
    }

    /**
     * Method transfer amount  between account diff users.
     *
     * @param user1    user1
     * @param account1 user1's account
     * @param user2    user2
     * @param account2 user2's account
     * @param amount   amount
     * @throws NotExistStorageException user is't in storage
     * @throws NotExistAccountException user have't account
     */
    public final void transferTo(final User user1, final Account account1,
                                 final User user2, final Account account2,
                                 final double amount) throws NotExistStorageException, NotExistAccountException {
        Objects.requireNonNull(user1, "user1 must not be null");
        Objects.requireNonNull(user2, "user2 must not be null");
        Objects.requireNonNull(account1, "account1 must not be null");
        Objects.requireNonNull(account2, "account2 must not be null");
        if (this.map.containsKey(user1) && this.map.containsKey(user2)) {
            try {
                if (this.map.get(user1).contains(account1)
                        && this.map.get(user2).contains(account2)) {
                    getUserAccount(user1, account1).transfer(
                            getUserAccount(user2, account2), amount);
                } else {
                    throw new NotExistAccountException("user have't account");
                }
            } catch (LimitMoneyException lme) {
                System.out.println("You are out from limit.");
            }
        } else {
            throw new NotExistStorageException("User is't in storage.");
        }
    }
    /**
     * Method toString.
     *
     * @return the string mapping object
     */
    @Override
    public final String toString() {
        return "Bank{"
                + "accounts="
                + map
                + "}";
    }
}

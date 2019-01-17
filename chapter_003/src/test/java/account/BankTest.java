package account;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private final Bank bank = new Bank();
    private static final Account A_DONALD = new Account(100D, "01");
    private static final Account A_BARRACK = new Account(100D, "02");
    private static final Account A_DICKENS = new Account(100D, "03");
    private static final User U_DONALD = new User("Donald", 70);
    private static final User U_BARRACK = new User("Barrack", 60);
    private static final User U_DICKENS = new User("Dickens", 80);

    @Before
    public void addUserBefore() throws Exception {
        List<User> users = bank.getAllUsers();
        for (User user : users) {
            bank.delete(user);
        }
        bank.addUser(U_DONALD);
        bank.addUser(U_BARRACK);
        bank.addUser(U_DICKENS);
        bank.add(U_DONALD, A_DONALD);
        bank.add(U_BARRACK, A_BARRACK);
        bank.add(U_DICKENS, A_DICKENS);
    }

    @Test
    public void addNewUserToStorageOK() throws ExistStorageException {
        User uMorgan = new User("Morgan", 120);
        bank.addUser(uMorgan);
        assertThat(bank.getAllUsers().contains(uMorgan), is(true));

    }

    @Test(expected = ExistStorageException.class)
    public void addUserToStorageFall() throws Exception {
        User uDonald = new User("Donald", 70);
        bank.addUser(uDonald);
    }

    @Test
    public void getAllUsers() {
        List<User> result = bank.getAllUsers();
        List<User> expected = new ArrayList<>(
                Arrays.asList(
                        new User("Barrack", 60),
                        new User("Dickens", 80),
                        new User("Donald", 70)
                ));
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test
    public void addUserAndAccountOk() throws ExistStorageException, NotExistStorageException {
        User uMorgan = new User("Morgan", 120);
        Account aMorgan = new Account(100D, "04");
        bank.addUser(uMorgan);
        bank.add(uMorgan, aMorgan);
        assertThat(bank.getAllUsers().contains(uMorgan), is(true));
    }

    @Test(expected = NotExistStorageException.class)
    public void addUserAndAccountFall() throws Exception {
        User uGun = new User("Gun", 120);
        Account aGun = new Account(100D, "04");
        bank.add(uGun, aGun);
    }

    @Test
    public void whenDeleteUserOk() throws Exception {
        bank.delete(U_BARRACK);
        assertThat(bank.getAllUsers().contains(U_BARRACK), is(false));

    }

    @Test(expected = NotExistStorageException.class)
    public void whenDeleteUserFall() throws Exception {
        bank.delete(U_BARRACK);
        bank.delete(U_BARRACK);
    }

    @Test
    public void whenDeleteAccountOk() throws Exception {
        bank.deleteAccount(U_BARRACK, A_BARRACK);
        List<Account> expected = new ArrayList<>();
        assertThat(bank.getAccounts(U_BARRACK), is(expected));
    }

    @Test(expected = NotExistStorageException.class)
    public void whenDeleteAccountFall() throws Exception {
        User uGun = new User("Gun", 120);
        Account aGun = new Account(100D, "05");
        bank.deleteAccount(uGun, aGun);
    }

    @Test
    public void whenGetUserAccountOk() throws Exception {
        Account expected = new Account(100, "01");
        Account result = bank.getUserAccount(U_DONALD, A_DONALD);
        assertThat(result, is(expected));
    }

    @Test
    public void whenGetUserNaveNotAccount() throws Exception {
        bank.deleteAccount(U_BARRACK, A_BARRACK);
        List<Account> expected = new ArrayList<>();
        assertThat(bank.getAccounts(U_BARRACK), is(expected));
    }

    @Test(expected = NotExistStorageException.class)
    public void whenGetUserAccountFall() throws Exception {
        User uGun = new User("Gun", 120);
        Account aGun = new Account(100D, "05");
        bank.getUserAccount(uGun, aGun);
    }

    @Test
    public void whenGetAccountsOk() throws Exception {
        bank.add(U_DONALD, new Account(200D, "02"));
        List<Account> result = bank.getAccounts(U_DONALD);
        List<Account> expected = new ArrayList<>(
                Arrays.asList(
                        new Account(100D, "01"),
                        new Account(200D, "02")
                ));
        assertThat(result, is(expected));
    }

    @Test(expected = NotExistStorageException.class)
    public void whenGetAccountsFall() throws Exception {
        User uGun = new User("Gun", 120);
        bank.getAccounts(uGun);

    }

    @Test
    public void whenTransferToOk() throws Exception {
        bank.transferTo(U_DONALD, A_DONALD, U_BARRACK, A_BARRACK, 50D);
        assertThat(bank.getUserAccount(U_DONALD, A_DONALD).getValues(), is(50D));
        assertThat(bank.getUserAccount(U_BARRACK, A_BARRACK).getValues(), is(150D));
    }

    @Test(expected = NotExistStorageException.class)
    public void whenOneFallTransferToFallNEE() throws Exception {
        User uGun = new User("Gun", 120);
        bank.transferTo(uGun, A_DONALD, U_BARRACK, A_BARRACK, 50D);
    }

    @Test(expected = NotExistStorageException.class)
    public void whenOneFallsTransferToFallNEE() throws Exception {
        User uGun = new User("Gun", 120);
        Account aGun = new Account(100D, "05");
        bank.transferTo(uGun, aGun, U_BARRACK, A_BARRACK, 50D);
    }

    @Test(expected = NotExistStorageException.class)
    public void whenSecondTransferToFallNEE() throws Exception {
        User uGun = new User("Gun", 120);
        Account aGun = new Account(100D, "05");
        bank.transferTo(U_DONALD, A_DONALD, uGun, aGun, 50D);
    }

    @Test(expected = NotExistStorageException.class)
    public void whenBothTransferToFallNEE() throws Exception {
        User uGun = new User("Gun", 120);
        Account aGun = new Account(100D, "05");
        bank.transferTo(uGun, aGun, uGun, aGun, 50D);
    }

    @Test
    public void whenTransferToFallLMO() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(bos));
        bank.transferTo(U_DONALD, A_DONALD, U_BARRACK, A_BARRACK, 50000000D);
        assertThat(new String(bos.toByteArray()), is(new StringBuilder()
                .append("You are out from limit.")
                .append(System.lineSeparator())
                .toString()));
        System.setOut(out);
    }

    @Test(expected = NotExistAccountException.class)
    public void whenOneTransferToFallNAE() throws Exception {
        bank.deleteAccount(U_DONALD, A_DONALD);
        bank.transferTo(U_DONALD, A_DONALD, U_BARRACK, A_BARRACK, 50000000D);
    }

    @Test(expected = NotExistAccountException.class)
    public void whenSecondTransferToFallNAE() throws Exception {
        bank.deleteAccount(U_BARRACK, A_BARRACK);
        bank.transferTo(U_DONALD, A_DONALD, U_BARRACK, A_BARRACK, 50000000D);
    }

    @Test(expected = NotExistAccountException.class)
    public void whenBothTransferToFallNAE() throws Exception {
        bank.deleteAccount(U_DONALD, A_DONALD);
        bank.deleteAccount(U_BARRACK, A_BARRACK);
        bank.transferTo(U_DONALD, A_DONALD, U_BARRACK, A_BARRACK, 50000000D);
    }
}
package bank.storage;

import bank.exception.ExistAccountException;
import bank.exception.ExistStorageException;
import bank.exception.NotExistAccountException;
import bank.exception.NotExistStorageException;
import bank.models.Account;
import bank.models.User;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private final Bank bank = new Bank();
    private final User uHomeland;
    private final User uCarry;
    private final User uSoul;
    private final Account aHomeland;
    private final Account aCarry;
    private final Account aSoul;


    {
        uHomeland = new User("Homeland", "AI-111");
        uCarry = new User("Carry", "AI-222");
        uSoul = new User("Soul", "AI-333");
        aHomeland = new Account(100D, "H01");
        aCarry = new Account(100D, "C02");
        aSoul = new Account(100D, "S03");
    }

    @Before
    public void setUp() throws Exception {
        List<User> list = bank.getAllUser();
        for (User user : list) {
            bank.deleteUser(user);
        }
        bank.addUser(uHomeland);
        bank.addUser(uCarry);
        bank.addUser(uSoul);
        bank.addAccountToUser(uHomeland.getPassport(), aHomeland);
        bank.addAccountToUser(uCarry.getPassport(), aCarry);
        bank.addAccountToUser(uSoul.getPassport(), aSoul);
    }

    @Test
    public void whenAddUserOK() throws Exception {
        User uDark = new User("Dark", "OO-555");
        bank.addUser(uDark);
        boolean result = bank.getAllUser().contains(uDark);
        assertThat(result, is(true));
    }

    @Test(expected = ExistStorageException.class)
    public void whenAddUserFall() throws Exception {
        User uDARK = new User("Dark", "OO-555");
        bank.addUser(uDARK);
        bank.addUser(uDARK);
    }

    @Test
    public void whenDeleteUserOK() throws Exception {
        bank.deleteUser(uHomeland);
        boolean result = bank.getAllUser().contains(uHomeland);
        assertThat(result, is(false));
    }

    @Test(expected = NotExistStorageException.class)
    public void whenDeleteUserFall() throws Exception {
        bank.deleteUser(uHomeland);
        bank.deleteUser(uHomeland);
    }

    @Test
    public void whenDeleteAccountFromUserOK() throws Exception {
        bank.deleteAccountFromUser("AI-111", aHomeland);
        List<Account> result = bank.getUserAccounts("AI-111");
        List<Account> expected = new ArrayList<>();
        assertThat(result, is(expected));
    }

    @Test(expected = NotExistAccountException.class)
    public void whenDeleteAccountFromUserFall() throws Exception {
        bank.deleteAccountFromUser("AI-111", aHomeland);
        bank.deleteAccountFromUser("AI-111", aHomeland);
    }

    @Test
    public void whenAddAccountToUserOK() throws Exception {
        Account aHomeland2 = new Account(200D, "H11");
        bank.addAccountToUser("AI-111", aHomeland2);
        List<Account> result = bank.getUserAccounts("AI-111");
        List<Account> expected = new ArrayList<>(
                Arrays.asList(
                        new Account(100D, "H01"),
                        new Account(200D, "H11")
                ));
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test(expected = ExistAccountException.class)
    public void whenAddAccountToUserFall() throws Exception {
        Account aHomeland2 = new Account(200D, "H11");
        bank.addAccountToUser("AI-111", aHomeland2);
        bank.addAccountToUser("AI-111", aHomeland2);
    }

    @Test
    public void getUserAccountsOK() {
        List<Account> result = bank.getUserAccounts("AI-111");
        List<Account> expected = new ArrayList<>(
                Collections.singletonList(
                        new Account(100D, "H01")
                ));
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test
    public void whenGetUserAccountsFall() {
        List<Account> result = bank.getUserAccounts("AI-999");
        List<Account> expected = new ArrayList<>();
        assertThat(result, is(expected));
    }

    @Test
    public void whenTransferMoneyOK() {
        bank.transferMoney("AI-111", "H01",
                "AI-222", "C02", 50D);
        List<Account> listHome = bank.getUserAccounts("AI-111");
        int idxH = listHome.indexOf(aHomeland);
        double resHome = bank.getUserAccounts("AI-111").get(idxH).getValue();
        double excHome = 50D;
        List<Account> listCarry = bank.getUserAccounts("AI-222");
        int idxC = listCarry.indexOf(aCarry);
        double resCarry = bank.getUserAccounts("AI-222").get(idxC).getValue();
        double exCarry = 150D;
        assertThat(resHome, is(excHome));
        assertThat(resCarry, is(exCarry));
    }

    @Test
    public void whenTransferMoneyLimitFall() {
        ByteArrayOutputStream bos = systemChangeStream();
        bank.transferMoney("AI-111", "H01",
                "AI-222", "C02", 5000000000D);
        assertThat(new String(bos.toByteArray()), is(
                new StringBuilder("Limit")
                        .append(" out. Check balance.")
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(System.out);
    }

    @Test
    public void whenOneTransferMoneyAccountFall() {
        ByteArrayOutputStream bos = systemChangeStream();
        bank.transferMoney("AI-000", "H01",
                "AI-222", "C02", 5D);
        assertThat(new String(bos.toByteArray()), is(
                new StringBuilder("Account")
                        .append(" is't in storage.")
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(System.out);
    }

    @Test
    public void whenSecondTransferMoneyAccountFall() {
        ByteArrayOutputStream bos = systemChangeStream();
        bank.transferMoney("AI-111", "H01",
                "AI-000", "C02", 5D);
        assertThat(new String(bos.toByteArray()), is(
                new StringBuilder("Account")
                        .append(" is't in storage.")
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(System.out);
    }

    @Test
    public void whenStorageIsEmptyTransferMoneyAccountFall() throws NotExistStorageException {
        ByteArrayOutputStream bos = systemChangeStream();
        bank.deleteUser(uHomeland);
        bank.deleteUser(uCarry);
        bank.deleteUser(uSoul);
        bank.transferMoney("AI-111", "H01",
                "AI-000", "C02", 5D);
        assertThat(new String(bos.toByteArray()), is(
                new StringBuilder("Storage ")
                        .append("is empty.")
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(System.out);
    }

    @Test
    public void getIndexOk() throws NotExistAccountException {
        final int result = bank.getIndexAccount("AI-111", "H01");
        final int expected = 0;
        assertThat(result, is(expected));
    }

    @Test(expected = NotExistAccountException.class)
    public void getIndexPassFall() throws NotExistAccountException {
        bank.getIndexAccount("AI-000", "H01");
    }

    @Test(expected = NotExistAccountException.class)
    public void getIndexRequisiteFall() throws NotExistAccountException {
        bank.getIndexAccount("AI-111", "H05");
    }

    private ByteArrayOutputStream systemChangeStream() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        return bos;
    }
}
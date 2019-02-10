package banks.storage;

import banks.exception.ExistAccountException;
import banks.exception.ExistStorageException;
import banks.exception.NotExistAccountException;
import banks.exception.NotExistStorageException;
import banks.models.Account;
import banks.models.User;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        List<User> list = this.bank.getAllUser();
        for (User user : list) {
            this.bank.deleteUser(user);
        }
        this.bank.addUser(this.uHomeland);
        this.bank.addUser(this.uCarry);
        this.bank.addUser(this.uSoul);
        this.bank.addAccountToUser(this.uHomeland.getPassport(), this.aHomeland);
        this.bank.addAccountToUser(this.uCarry.getPassport(), this.aCarry);
        this.bank.addAccountToUser(this.uSoul.getPassport(), this.aSoul);
    }

    @Test
    public void whenAddUserOK() throws Exception {
        User uDark = new User("Dark", "OO-555");
        this.bank.addUser(uDark);
        boolean result = this.bank.getAllUser().contains(uDark);
        assertThat(result, is(true));
    }

 /*   @Benchmark
    public void measureName() throws NotExistStorageException {
        this.bank.deleteUser(this.uHomeland);
        boolean result = this.bank.getAllUser().contains(this.uHomeland);
        assertThat(result, is(false));
    }*/

    @Test(expected = ExistStorageException.class)
    public void whenAddUserFall() throws Exception {
        User uDARK = new User("Dark", "OO-555");
        this.bank.addUser(uDARK);
        this.bank.addUser(uDARK);
    }

    @Test
    public void whenDeleteUserOK() throws Exception {
        this.bank.deleteUser(this.uHomeland);
        boolean result = this.bank.getAllUser().contains(this.uHomeland);
        assertThat(result, is(false));
    }

    @Test(expected = NotExistStorageException.class)
    public void whenDeleteUserFall() throws Exception {
        bank.deleteUser(this.uHomeland);
        bank.deleteUser(this.uHomeland);
    }

    @Test
    public void whenDeleteAccountFromUserOK()
            throws NotExistAccountException {
        this.bank.deleteAccountFromUser("AI-111", this.aHomeland);
        List<Account> result = this.bank.getUserAccounts("AI-111");
        List<Account> expected = List.of();
        assertThat(result, is(expected));
    }

    @Test(expected = NotExistAccountException.class)
    public void whenDeleteAccountFromUserFall() throws Exception {
        Account aHomeland3 = new Account(300D, "H99");
        this.bank.deleteAccountFromUser("AI-111", aHomeland3);
    }

    @Test(expected = NotExistAccountException.class)
    public void whenDeleteNotExistAccountOk() throws Exception {
        this.bank.deleteAccountFromUser("AI-111", this.aHomeland);
        this.bank.deleteAccountFromUser("AI-111", this.aHomeland);
    }

    @Test
    public void whenAddAccountToUserOK() throws Exception {
        Account aHomeland2 = new Account(200D, "H11");
        this.bank.addAccountToUser("AI-111", aHomeland2);
        List<Account> result = this.bank.getUserAccounts("AI-111");
        List<Account> expected = List.of(
                new Account(100D, "H01"),
                new Account(200D, "H11")
        );
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test(expected = ExistAccountException.class)
    public void whenAddAccountToUserFall() throws Exception {
        this.bank.addAccountToUser("AI-111", this.aHomeland);
    }

    @Test
    public void getUserAccountsOK() throws NotExistAccountException {
        List<Account> result = this.bank.getUserAccounts("AI-111");
        List<Account> expected = List.of(
                new Account(100D, "H01")
        );
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test(expected = NotExistAccountException.class)
    public void whenGetUserAccountsFall() throws NotExistAccountException {
        this.bank.getUserAccounts("AI-999");
    }

    @Test
    public void whenTransferMoneyOK() throws NotExistAccountException {
        this.bank.transferMoney("AI-111", "H01",
                "AI-222", "C02", 50D);
        Account oHome = bank.getUserAccount("AI-111", "H01");
        Account oCarry = bank.getUserAccount("AI-222", "C02");
        double resHome = oHome.getValue();
        double resCarry = oCarry.getValue();
        double expHome = 50D;
        double expCarry = 150D;
        assertThat(resHome, is(expHome));
        assertThat(resCarry, is(expCarry));
    }

    @Test
    public void whenTransferMoneyLimitFall() {
        ByteArrayOutputStream bos = systemChangeStream();
        this.bank.transferMoney("AI-111", "H01",
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
        this.bank.transferMoney("AI-000", "H01",
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
        this.bank.transferMoney("AI-111", "H01",
                "AI-000", "C02", 5D);
        assertThat(new String(bos.toByteArray()), is(
                new StringBuilder("Account")
                        .append(" is't in storage.")
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(System.out);
    }

    @Test
    public void whenStorageIsEmptyTransferMoneyAccountFall()
            throws NotExistStorageException {
        ByteArrayOutputStream bos = systemChangeStream();
        this.bank.deleteUser(this.uHomeland);
        this.bank.deleteUser(this.uCarry);
        this.bank.deleteUser(this.uSoul);
        this.bank.transferMoney("AI-111", "H01",
                "AI-000", "C02", 5D);
        assertThat(new String(bos.toByteArray()), is(
                new StringBuilder("Account ")
                        .append("is't in storage.")
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(System.out);
    }

    @Test
    public void getIndexOk() throws NotExistAccountException {
        Account result = this.bank.getUserAccount(
                "AI-111", "H01");
        Account expected = new Account(100D, "H01");
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test(expected = NotExistAccountException.class)
    public void getIndexPassFall() throws NotExistAccountException {
        this.bank.getUserAccount("AI-000", "H01");
    }

    @Test(expected = NotExistAccountException.class)
    public void getIndexRequisiteFall() throws NotExistAccountException {
        this.bank.getUserAccount("AI-111", "H05");
    }

    private ByteArrayOutputStream systemChangeStream() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        return bos;
    }
}
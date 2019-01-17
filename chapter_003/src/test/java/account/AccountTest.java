package account;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    private final Account account = new Account(1000D, "01");
    private final Account target = new Account(500D, "02");

    @Test
    public void whenTransferOk() throws LimitMoneyException {
        account.transfer(target, 500D);
        assertThat(account.getValues(), is(500D));
        assertThat(target.getValues(), is(1000D));

    }

    @Test(expected = LimitMoneyException.class)
    public void whenTransferFall() throws Exception {
        account.transfer(target, 150000000D);
    }
}
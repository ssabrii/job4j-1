package bank.models;

import bank.exception.LimitMoneyException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    private static final Account A_CARRY;
    private static final Account A_SOUL;

    static {
        A_CARRY = new Account(100D, "c02");
        A_SOUL = new Account(100D, "s03");
    }

    @Test
    public void whenTransferOK() throws LimitMoneyException {
        A_CARRY.transfer(A_SOUL, 50D);
        final Account expCarry = new Account(50D, "c02");
        final Account expSoul = new Account(150D, "s03");
        assertThat(A_CARRY.toString(), is(expCarry.toString()));
        assertThat(A_SOUL.toString(), is(expSoul.toString()));
    }

    @Test(expected = LimitMoneyException.class)
    public void whenTransferFall() throws LimitMoneyException {
        A_CARRY.transfer(A_SOUL, 5000000000D);
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    public static final int MONEY_TO_DEPOSIT = 10;
    public static final int MONEY_TO_WITHDRAW = 10;

    Account checkings;

    @BeforeEach
    void setUp() {
        checkings = new Checkings("12345678", 0.3);
    }

    @Test
    public void checkings_has_apr_as_given() {
        double actual = checkings.getAPR();

        assertEquals(0.3, actual);
    }

    @Test
    public void checkings_has_an_id(){
        String actual = checkings.getID();

        assertEquals("12345678", actual);
    }

    @Test
    public void deposit_money_into_checkings() {
        checkings.deposit(MONEY_TO_DEPOSIT);
        double actual = checkings.getBalance();

        assertEquals(10, actual);
    }

    @Test
    public void withdraw_money_from_checkings() {
        checkings.deposit(MONEY_TO_DEPOSIT);
        checkings.withdraw(MONEY_TO_WITHDRAW);
        double actual = checkings.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void balance_cannot_be_negative() {
        checkings.deposit(MONEY_TO_DEPOSIT);
        checkings.withdraw(MONEY_TO_WITHDRAW * 2.0);
        double actual = checkings.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void can_deposit_twice_from_same_account() {
        checkings.deposit(MONEY_TO_DEPOSIT);
        checkings.deposit(MONEY_TO_DEPOSIT);
        double actual = checkings.getBalance();

        assertEquals(20, actual);
    }

    @Test
    public void can_withdraw_twice_from_same_account() {
        checkings.deposit(MONEY_TO_DEPOSIT);
        checkings.withdraw(MONEY_TO_WITHDRAW / 2.0);
        checkings.withdraw(MONEY_TO_WITHDRAW / 2.0);
        double actual = checkings.getBalance();

        assertEquals(0, actual);
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    public static final String SAMPLE_ID = "12345678";
    public static final double APR = 0.3;
    public static final int HUNDRED = 100;
    public static final int FIFTY = 50;
    public static final int ONE = 1;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_starts_with_no_accounts() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void add_checkings_account(){
        bank.addCheckingAccount(SAMPLE_ID, APR);

        assertEquals(SAMPLE_ID, bank.getAccounts().get(SAMPLE_ID).getID());
        assertEquals(ONE, bank.getAccounts().size());
    }

    @Test
    void add_savings_account(){
        bank.addSavingAccount(SAMPLE_ID, APR);

        assertEquals(SAMPLE_ID, bank.getAccounts().get(SAMPLE_ID).getID());
        assertEquals(ONE, bank.getAccounts().size());
    }

    @Test
    void add_cd_account(){
        bank.addCdAccount(SAMPLE_ID, APR, 1000);

        assertEquals(SAMPLE_ID, bank.getAccounts().get(SAMPLE_ID).getID());
        assertEquals(ONE, bank.getAccounts().size());
    }

    @Test
    void add_two_accounts_to_bank(){
        bank.addCheckingAccount(SAMPLE_ID, APR);
        bank.addSavingAccount("87654321", APR);

        assertEquals(2,bank.getAccounts().size());
    }

    @Test
    void deposit_money_into_account(){
        bank.addCheckingAccount(SAMPLE_ID, APR);
        bank.depositIntoAccount(SAMPLE_ID, HUNDRED);

        assertEquals(HUNDRED, bank.getAccounts().get(SAMPLE_ID).getBalance());
    }

    @Test
    void withdraw_money_from_account(){
        bank.addCheckingAccount(SAMPLE_ID, APR);
        bank.depositIntoAccount(SAMPLE_ID, HUNDRED);
        bank.withdrawFromAccount(SAMPLE_ID, FIFTY);

        assertEquals(FIFTY, bank.getAccounts().get(SAMPLE_ID).getBalance());
    }

    @Test
    void two_deposits_from_account(){
        bank.addCheckingAccount(SAMPLE_ID, APR);
        bank.depositIntoAccount(SAMPLE_ID, HUNDRED);
        bank.depositIntoAccount(SAMPLE_ID, HUNDRED);

        assertEquals(200, bank.getAccounts().get(SAMPLE_ID).getBalance());

    }

    @Test
    void two_withdrawals_from_account(){
        bank.addSavingAccount(SAMPLE_ID, APR);
        bank.depositIntoAccount(SAMPLE_ID, HUNDRED);
        bank.withdrawFromAccount(SAMPLE_ID, FIFTY);
        bank.withdrawFromAccount(SAMPLE_ID, FIFTY);

        assertEquals(0, bank.getAccounts().get(SAMPLE_ID).getBalance());
    }
}

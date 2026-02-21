import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandValidatorTest {
    DepositCommandValidator depositCommandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositCommandValidator = new DepositCommandValidator(bank);
        bank.addSavingAccount("12345678", 0.5);
    }

    @Test
    void valid_deposit_command(){
        boolean actual = depositCommandValidator.validate("deposit 12345678 1000");
        assertTrue(actual);
    }

    @Test
    void misspell_deposit(){
        boolean actual = depositCommandValidator.validate("diposit 12345678 1000");
        assertFalse(actual);
    }

    @Test
    void command_missing_id(){
        boolean actual = depositCommandValidator.validate("deposit 1000");
        assertFalse(actual);
    }

    @Test
    void command_missing_amount(){
        boolean actual = depositCommandValidator.validate("deposit 12345678");
        assertFalse(actual);
    }

    @Test
    void command_with_no_parameters() {
        boolean actual = depositCommandValidator.validate("deposit");
        assertFalse(actual);
    }

    @Test
    void id_has_to_be_a_number(){
        boolean actual = depositCommandValidator.validate("deposit main 1000");
        assertFalse(actual);
    }

    @Test
    void id_has_more_than_8_digits(){
        boolean actual = depositCommandValidator.validate("deposit 123456789 1000");
        assertFalse(actual);
    }

    @Test
    void id_has_less_than_8_digits(){
        boolean actual = depositCommandValidator.validate("deposit 1234567 1000");
        assertFalse(actual);
    }

    @Test
    void amount_cant_be_negative(){
        boolean actual = depositCommandValidator.validate("deposit 12345678 -1000");
        assertFalse(actual);
    }

    @Test
    void amount_can_be_zero(){
        boolean actual = depositCommandValidator.validate("deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    void does_account_exist(){
        boolean actual = depositCommandValidator.validate("deposit 12345678 2000");
        assertTrue(actual);
    }

    @Test
    void cant_deposit_more_than_2500_into_savings(){
        boolean actual = depositCommandValidator.validate("deposit 12345678 2600");
        assertFalse(actual);
    }

    @Test
    void can_deposit_2500_into_savings(){
        boolean actual = depositCommandValidator.validate("deposit 12345678 2500");
        assertFalse(actual);
    }

    @Test
    void cant_deposit_more_than_1000_into_checking(){
        bank.addCheckingAccount("22345678", 0.5);
        boolean actual = depositCommandValidator.validate("deposit 22345678 1100");
        assertFalse(actual);
    }

    @Test
    void can_deposit_1000_into_checking(){
        bank.addCheckingAccount("22345678", 0.5);
        boolean actual = depositCommandValidator.validate("deposit 22345678 1000");
        assertFalse(actual);
    }

    @Test
    void cant_deposit_into_cd_account(){
        bank.addCdAccount("22345678", 0.5, 1000);
        boolean actual = depositCommandValidator.validate("deposit 22345678 1000");
        assertFalse(actual);
    }
}

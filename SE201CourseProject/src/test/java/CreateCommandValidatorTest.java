import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {
    CreateCommandValidator createCommandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandValidator = new CreateCommandValidator(bank);
    }

    @Test
    void valid_create_checking_account_command() {
        boolean actual = createCommandValidator.validate("create checking 12345678 0.4");
        assertTrue(actual);
    }

    @Test
    void valid_create_savings_account_command() {
        boolean actual =  createCommandValidator.validate("create savings 12345678 0.2");
        assertTrue(actual);
    }

    @Test
    void valid_create_cd_account_command() {
        boolean actual = createCommandValidator.validate("create cd 12345678 0.4 1500");
        assertTrue(actual);
    }

    @Test
    void misspell_create_command() {
        boolean actual = createCommandValidator.validate("crete checking 12345678 0.7");
        assertFalse(actual);
    }

    @Test
    void misspell_account_type() {
        boolean actual = createCommandValidator.validate("create cheking 12345678 0.5");
        assertFalse(actual);
    }

    @Test
    void invalid_account_type() {
        boolean actual = createCommandValidator.validate("create credit 12345678 0.3");
        assertFalse(actual);
    }

    @Test
    void command_without_account_type() {
        boolean actual = createCommandValidator.validate("create 12345678 0.4");
        assertFalse(actual);
    }

    @Test
    void command_without_an_id() {
        boolean actual = createCommandValidator.validate("create savings 0.4");
        assertFalse(actual);
    }

    @Test
    void command_without_an_apr() {
        boolean actual = createCommandValidator.validate("create savings 12345678");
        assertFalse(actual);
    }

    @Test
    void command_without_any_parameters() {
        boolean actual = createCommandValidator.validate("create");
        assertFalse(actual);
    }

    @Test
    void command_with_parameters_out_of_order() {
        boolean actual = createCommandValidator.validate("create 12345678 checking 0.5");
        assertFalse(actual);
    }

    @Test
    void id_has_to_be_a_number() {
        boolean actual = createCommandValidator.validate("create checking main 0.3");
        assertFalse(actual);
    }

    @Test
    void id_cant_have_less_than_8_digits() {
        boolean actual = createCommandValidator.validate("create cd 1234567 0.2 1400");
        assertFalse(actual);
    }

    @Test
    void id_cant_have_more_than_8_digits() {
        boolean actual = createCommandValidator.validate("create savings 123456789 0.5");
        assertFalse(actual);
    }

    @Test
    void apr_cant_be_negative() {
        boolean actual = createCommandValidator.validate("create checking 12345678 -0.6");
        assertFalse(actual);
    }

    @Test
    void apr_cant_be_more_than_10() {
        boolean actual = createCommandValidator.validate("create savings 12345678 11");
        assertFalse(actual);
    }

    @Test
    void apr_can_be_zero() {
        boolean actual = createCommandValidator.validate("create cd 12345678 0 1400");
        assertTrue(actual);
    }

    @Test
    void apr_can_be_ten() {
        boolean actual = createCommandValidator.validate("create checking 12345678 10");
        assertTrue(actual);
    }

    @Test
    void apr_has_to_be_a_number(){
        boolean actual = createCommandValidator.validate("create savings 12345678 three");
        assertFalse(actual);
    }

    @Test
    void two_accounts_cant_have_the_same_id(){
        bank.addCheckingAccount("12345678", 0.4);
        boolean actual = createCommandValidator.validate("create checking 12345678 0.3");
        assertFalse(actual);
    }

    @Test
    void cant_create_a_checking_account_with_a_specified_balance(){
        boolean actual = createCommandValidator.validate("create checking 12345678 0.4 1000");
        assertFalse(actual);
    }

    @Test
    void cant_create_a_savings_account_with_a_specified_balance(){
        boolean actual = createCommandValidator.validate("create checking 12345678 0.4 1000");
        assertFalse(actual);
    }

    @Test
    void cd_account_needs_a_specified_balance(){
        boolean actual = createCommandValidator.validate("create cd 12345678 0.5");
        assertFalse(actual);
    }

    @Test
    void cant_create_cd_account_with_a_negative_amount(){
        boolean actual = createCommandValidator.validate("create cd 12345678 0.4 -1000");
        assertFalse(actual);
    }

    @Test
    void cant_create_a_cd_account_with_an_amount_over_10000(){
        boolean actual = createCommandValidator.validate("create cd 12345678 0.4 50000");
        assertFalse(actual);
    }

    @Test
    void cant_create_a_cd_account_with_an_amount_less_than_1000(){
        boolean actual = createCommandValidator.validate("create cd 12345678 0.4 500");
        assertFalse(actual);
    }

    @Test
    void can_create_cd_account_with_a_balance_of_1000(){
        boolean actual = createCommandValidator.validate("create cd 12345678 0.5 1000");
        assertTrue(actual);
    }

    @Test
    void can_create_cd_account_with_a_balance_of_10000(){
        boolean actual = createCommandValidator.validate("create cd 12345678 0.5 10000");
        assertTrue(actual);
    }
}

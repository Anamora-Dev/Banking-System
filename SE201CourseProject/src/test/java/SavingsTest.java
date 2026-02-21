import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {

    @Test
    public void savings_has_a_start_balance_of_0() {
        Savings savings = new Savings("12345678", 0.3);
        double actual = savings.getBalance();

        assertEquals(0, actual);
    }
}

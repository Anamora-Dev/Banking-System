import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingsTest {

    @Test
    public void checkings_has_a_start_balance_of_0() {
        Checkings checkings = new Checkings("12345678", 0.3);
        double actual = checkings.getBalance();

        assertEquals(0, actual);
    }
}

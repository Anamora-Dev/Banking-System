import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CdTest {

    @Test
    public void cd_has_a_specified_start_balance() {
        Cd cd = new Cd("12345678", 0.3, 1000);
        double actual = cd.getBalance();

        assertEquals(1000, actual);
    }
}

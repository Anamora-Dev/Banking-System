import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {
    CommandValidator commandValidator;

    @BeforeEach
    void setUp(){
        commandValidator = new CommandValidator();
    }

    @Test
    void invalid_command() {
        boolean actual = commandValidator.valid("make checking 12345678 0.4");
        assertFalse(actual);
    }

    @Test
    void command_is_case_insensitive(){
        boolean actual = commandValidator.valid("CREATE CHECKING 12345678 0.6");
        assertTrue(actual);
    }
}

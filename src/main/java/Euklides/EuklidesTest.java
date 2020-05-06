package Euklides;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EuklidesTest {

    @Test
    void isInvModuloFunctionCorrect() {
        assertAll("Scores", () -> assertEquals(73, Euklides.modInverse(337, 123)),
                () -> assertEquals(309, Euklides.modInverse(4321, 1234)),
                () -> assertEquals(-1, Euklides.modInverse(432, 321)));
    }
}

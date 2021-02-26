package mediaDB.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumericalTest {

    @Test
    void isNumericalTrue() {
        assertTrue(Numerical.check("100100"));
    }

    @Test
    void isNumericalFals() {
        assertFalse(Numerical.check("100100a"));
    }
}
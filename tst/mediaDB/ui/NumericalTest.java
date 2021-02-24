package mediaDB.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumericalTest {

    @Test
    void isNumericalTrue() {
        assertTrue(Numerical.isNumerical("100100"));
    }

    @Test
    void isNumericalFals() {
        assertFalse(Numerical.isNumerical("100100a"));
    }
}
package mediaDB.domain_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SizeObservableTest {
    SizeObservable sizeObservable;

    @BeforeEach
    void setUp() {
        sizeObservable = new SizeObservable();
    }

    @Test
    void addSize() {
    }

    @Test
    void subtractSize() {
    }

    @Test
    void capacityAvailableFalse() {
        sizeObservable.setMAX_CAPACITY(new BigDecimal(4000000));
        assertFalse(sizeObservable.capacityAvailable(new BigDecimal(4000001)));
    }

    @Test
    void capacityAvailableTrue() {
        sizeObservable.setMAX_CAPACITY(new BigDecimal(4000000));
        assertTrue(sizeObservable.capacityAvailable(new BigDecimal(400000)));
    }
}
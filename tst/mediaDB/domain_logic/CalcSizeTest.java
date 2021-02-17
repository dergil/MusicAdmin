package mediaDB.domain_logic;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CalcSizeTest {

    @Test
    void size() {
        BigDecimal result = CalcSize.size(Duration.ofSeconds(1000), 1000);
        assertEquals(new BigDecimal(1000000), result);
    }
}
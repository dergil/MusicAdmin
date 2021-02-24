package mediaDB.domain_logic.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaTypesTest {

    @Test
    void testToString() {
        assertEquals("InteractiveVideo", MediaTypes.INTERACTIVEVIDEO.toString());
    }
}
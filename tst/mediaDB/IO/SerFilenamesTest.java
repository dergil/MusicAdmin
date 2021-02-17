package mediaDB.IO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerFilenamesTest {

    @Test
    void testToString() {
        assertEquals("currentSize.ser", SerFilenames.CURRENTSIZE.toString());
    }
}
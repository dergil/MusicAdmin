package mediaDB.IO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedMediaFileTest {
    SavedMediaFile savedMediaFile = new SavedMediaFile("Audio", "1", 200, 0);

    @Test
    void getAddress() {
        assertEquals("1", savedMediaFile.getAddress());
    }

    @Test
    void getSize() {
        assertEquals(200, savedMediaFile.getSize());
    }

    @Test
    void getOffset() {
        assertEquals(0, savedMediaFile.getOffset());
    }

    @Test
    void getFileType() {
        assertEquals("Audio", savedMediaFile.getFileType());
    }
}
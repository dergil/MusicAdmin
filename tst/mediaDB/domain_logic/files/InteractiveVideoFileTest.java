package mediaDB.domain_logic.files;

import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.producer.Uploader;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InteractiveVideoFileTest {
    String fileType = MediaTypes.INTERACTIVEVIDEO.toString();
    int samplingRate = 300;
    int width = 400;
    int height = 500;
    String encoding = "DWT";
    List<Tag> al = new ArrayList<Tag>();
    Collection<Tag> tags = al;
    String holder = "Sony";
    long bitrate = 350;
    Duration length = Duration.ofSeconds(667);
    Uploader uploader = new Producer("Gilbert");
    String type = "Abstimmung";
    BigDecimal size = new BigDecimal(length.getSeconds()*bitrate);
    Date date = new Date();
    String address = "1";
    int accesscount = 0;
    InteractiveVideoFile interactiveVideoFile = new InteractiveVideoFile(fileType, type,
            width, height, encoding, address, tags, accesscount, bitrate, length, size, uploader, date);

    @Test
    void getType() {
        assertEquals(type, interactiveVideoFile.getType());
    }

    @Test
    void getWidth() {
        assertEquals(width, interactiveVideoFile.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height, interactiveVideoFile.getHeight());
    }

    @Test
    void getFileType() {
        assertEquals(fileType, interactiveVideoFile.getFileType());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, interactiveVideoFile.getEncoding());
    }

    @Test
    void getAddress() {
        assertEquals(address, interactiveVideoFile.getAddress());
    }

    @Test
    void getTags() {
        assertEquals(tags, interactiveVideoFile.getTags());
    }

    @Test
    void getAccessCount() {
        assertEquals(accesscount, interactiveVideoFile.getAccessCount());
    }

    @Test
    void setAccessCount() {
        interactiveVideoFile.setAccessCount(200);
        assertEquals(200, interactiveVideoFile.getAccessCount());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, interactiveVideoFile.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, interactiveVideoFile.getLength());
    }

    @Test
    void getSize() {
        assertEquals(size, interactiveVideoFile.getSize());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, interactiveVideoFile.getUploader());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, interactiveVideoFile.getUploadDate());
    }

    @Test
    void testToString() {
        String result = interactiveVideoFile.toString();
        assertTrue(result.contains(fileType));
    }
}
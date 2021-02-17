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

class LicensedVideoFileTest {
    String fileType = MediaTypes.LICENSEDVIDEO.toString();
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
    LicensedVideoFile licensedVideoFile = new LicensedVideoFile(fileType, width, height, encoding, address, tags, 
            accesscount, holder, bitrate, length, size, uploader, date);

    @Test
    void getFileType() {
        assertEquals(fileType, licensedVideoFile.getFileType());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, licensedVideoFile.getEncoding());
    }

    @Test
    void getAddress() {
        assertEquals(address, licensedVideoFile.getAddress());
    }

    @Test
    void getTags() {
        assertEquals(tags, licensedVideoFile.getTags());
    }

    @Test
    void getAccessCount() {
        assertEquals(accesscount, licensedVideoFile.getAccessCount());
    }

    @Test
    void setAccessCount() {
        licensedVideoFile.setAccessCount(200);
        assertEquals(200, licensedVideoFile.getAccessCount());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, licensedVideoFile.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, licensedVideoFile.getLength());
    }

    @Test
    void getSize() {
        assertEquals(size, licensedVideoFile.getSize());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, licensedVideoFile.getUploader());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, licensedVideoFile.getUploadDate());
    }

    @Test
    void testToString() {
        String result = licensedVideoFile.toString();
        assertTrue(result.contains(fileType));
    }

    @Test
    void getHolder() {
        assertEquals(holder, licensedVideoFile.getHolder());
    }

    @Test
    void getWidth() {
        assertEquals(width, licensedVideoFile.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height, licensedVideoFile.getHeight());
    }

}
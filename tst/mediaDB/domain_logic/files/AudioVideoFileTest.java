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

class AudioVideoFileTest {
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
    AudioVideoFile audioVideoFile = new AudioVideoFile(MediaTypes.AUDIOVIDEO.toString(), samplingRate, width, height, 
            encoding, address, tags, accesscount, bitrate, length, size, uploader, date);


    @Test
    void getWidth() {
        assertEquals(width, audioVideoFile.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height, audioVideoFile.getHeight());
    }

    @Test
    void getFileType() {
        assertEquals(MediaTypes.AUDIOVIDEO.toString(), audioVideoFile.getFileType());
    }

    @Test
    void getSamplingRate() {
        assertEquals(samplingRate, audioVideoFile.getSamplingRate());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, audioVideoFile.getEncoding());
    }

    @Test
    void getAddress() {
        assertEquals(address, audioVideoFile.getAddress());
    }

    @Test
    void getTags() {
        assertEquals(tags, audioVideoFile.getTags());
    }

    @Test
    void getAccessCount() {
        assertEquals(accesscount, audioVideoFile.getAccessCount());
    }

    @Test
    void setAccessCount() {
        audioVideoFile.setAccessCount(200);
        assertEquals(200, audioVideoFile.getAccessCount());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, audioVideoFile.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, audioVideoFile.getLength());
    }

    @Test
    void getSize() {
        assertEquals(size, audioVideoFile.getSize());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, audioVideoFile.getUploader());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, audioVideoFile.getUploadDate());
    }

    @Test
    void testToString() {
        String result = audioVideoFile.toString();
        assertTrue(result.contains(MediaTypes.AUDIOVIDEO.toString()));
    }
}
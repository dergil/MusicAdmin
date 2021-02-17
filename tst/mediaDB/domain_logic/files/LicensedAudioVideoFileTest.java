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

class LicensedAudioVideoFileTest {
    String fileType = MediaTypes.LICENSEDAUDIOVIDEO.toString();
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
    LicensedAudioVideoFile licensedAudioVideoFile = new LicensedAudioVideoFile(fileType, samplingRate, width, height, 
            encoding, address, tags, accesscount, holder, bitrate, length, size, uploader, date);


    @Test
    void getFileType() {
        assertEquals(fileType, licensedAudioVideoFile.getFileType());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, licensedAudioVideoFile.getEncoding());
    }

    @Test
    void getAddress() {
        assertEquals(address, licensedAudioVideoFile.getAddress());
    }

    @Test
    void getTags() {
        assertEquals(tags, licensedAudioVideoFile.getTags());
    }

    @Test
    void getAccessCount() {
        assertEquals(accesscount, licensedAudioVideoFile.getAccessCount());
    }

    @Test
    void setAccessCount() {
        licensedAudioVideoFile.setAccessCount(200);
        assertEquals(200, licensedAudioVideoFile.getAccessCount());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, licensedAudioVideoFile.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, licensedAudioVideoFile.getLength());
    }

    @Test
    void getSize() {
        assertEquals(size, licensedAudioVideoFile.getSize());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, licensedAudioVideoFile.getUploader());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, licensedAudioVideoFile.getUploadDate());
    }

    @Test
    void testToString() {
        String result = licensedAudioVideoFile.toString();
        assertTrue(result.contains(fileType));
    }

    @Test
    void getSamplingRate() {
        assertEquals(samplingRate, licensedAudioVideoFile.getSamplingRate());
    }

    @Test
    void getHolder() {
        assertEquals(holder, licensedAudioVideoFile.getHolder());
    }

    @Test
    void getWidth() {
        assertEquals(width, licensedAudioVideoFile.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height, licensedAudioVideoFile.getHeight());
    }



}
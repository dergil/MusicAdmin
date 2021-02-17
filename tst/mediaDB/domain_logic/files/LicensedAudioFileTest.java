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

class LicensedAudioFileTest {
    String fileType = MediaTypes.LICENSEDAUDIO.toString();
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
    LicensedAudioFile licensedAudioFile = new LicensedAudioFile(fileType, samplingRate, encoding, address, tags, 
            accesscount, holder, bitrate, length, size, uploader, date);


    @Test
    void getFileType() {
        assertEquals(fileType, licensedAudioFile.getFileType());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, licensedAudioFile.getEncoding());
    }

    @Test
    void getAddress() {
        assertEquals(address, licensedAudioFile.getAddress());
    }

    @Test
    void getTags() {
        assertEquals(tags, licensedAudioFile.getTags());
    }

    @Test
    void getAccessCount() {
        assertEquals(accesscount, licensedAudioFile.getAccessCount());
    }

    @Test
    void setAccessCount() {
        licensedAudioFile.setAccessCount(200);
        assertEquals(200, licensedAudioFile.getAccessCount());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, licensedAudioFile.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, licensedAudioFile.getLength());
    }

    @Test
    void getSize() {
        assertEquals(size, licensedAudioFile.getSize());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, licensedAudioFile.getUploader());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, licensedAudioFile.getUploadDate());
    }

    @Test
    void testToString() {
        String result = licensedAudioFile.toString();
        assertTrue(result.contains(fileType));
    }



    @Test
    void getSamplingRate() {
        assertEquals(samplingRate, licensedAudioFile.getSamplingRate());
    }


    @Test
    void getHolder() {
        assertEquals(holder, licensedAudioFile.getHolder());
    }

    
    
    
    
}
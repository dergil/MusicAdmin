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

class AudioFileTest {
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
    AudioFile audioFile = new AudioFile(MediaTypes.AUDIO.toString(), samplingRate, encoding, address, tags, accesscount, bitrate, length, size, uploader, date);

    //    void testGetter(Class clazz, String memberName, Object expected){
//        Class.callFunction(clazz,"set"+memberName,expected);
//        Object actual = Class.callFunction(clazz,"get"+memberName);
//        assertEquals(expected,actual);
//
//    }

    @Test
    void getFileType() {
        assertEquals(MediaTypes.AUDIO.toString(), audioFile.getFileType());
    }

    @Test
    void getSamplingRate() {
        assertEquals(samplingRate, audioFile.getSamplingRate());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, audioFile.getEncoding());
    }

    @Test
    void getAddress() {
        assertEquals(address, audioFile.getAddress());
    }

    @Test
    void getTags() {
        assertEquals(tags, audioFile.getTags());
    }

    @Test
    void getAccessCount() {
        assertEquals(accesscount, audioFile.getAccessCount());
    }

    @Test
    void setAccessCount() {
        audioFile.setAccessCount(200);
        assertEquals(200, audioFile.getAccessCount());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, audioFile.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, audioFile.getLength());
    }

    @Test
    void getSize() {
        assertEquals(size, audioFile.getSize());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, audioFile.getUploader());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, audioFile.getUploadDate());
    }

    @Test
    void testToString() {
        String result = audioFile.toString();
        assertTrue(result.contains(MediaTypes.AUDIO.toString()));
    }
}
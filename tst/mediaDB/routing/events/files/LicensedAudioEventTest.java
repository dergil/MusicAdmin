package mediaDB.routing.events.files;

import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.enums.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LicensedAudioEventTest {
    int samplingRate = 300;
    int width = 400;
    int height = 500;
    String encoding = "DWT";
    List<Tag> al = new ArrayList<Tag>();
    Collection<Tag> tags = al;
    String holder = "Sony";
    long bitrate = 350;
    Duration length = Duration.ofSeconds(667);
    String uploader = "Gilbert";
    String type = "Abstimmung";
    BigDecimal size = new BigDecimal(length.getSeconds()*bitrate);
    Date date = new Date();
    String address = "1";
    int accesscount = 0;
    String sender = "sender";
    String fileType = MediaTypes.LICENSEDAUDIO.toString();
    String eventName = fileType + "Event";
    LicensedAudioEvent event = new LicensedAudioEvent(this, fileType, samplingRate, encoding, tags, holder, bitrate, length, uploader, sender);

    @Test
    void getFileType() {
        assertEquals(fileType, event.getFileType());
    }

    @Test
    void getSamplingRate() {
        assertEquals(samplingRate, event.getSamplingRate());
    }

    @Test
    void getEncoding() {
        assertEquals(encoding, event.getEncoding());
    }

    @Test
    void getTags() {
        assertEquals(tags, event.getTags());
    }

    @Test
    void getBitrate() {
        assertEquals(bitrate, event.getBitrate());
    }

    @Test
    void getLength() {
        assertEquals(length, event.getLength());
    }

    @Test
    void getUploader() {
        assertEquals(uploader, event.getUploader());
    }

    @Test
    void getEventName() {
        assertEquals(eventName, event.getEventName());
    }

    @Test
    void getSender() {
        assertEquals(sender, event.getSender());
    }

    @Test
    void getHolder() {
        assertEquals(holder, event.getHolder());
    }

}
package mediaDB;

import mediaDB.domain_logic.Tag;
import mediaDB.routing.events.files.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventFactory {
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

    public AudioEvent audioEvent(){
        return new AudioEvent(this, MediaTypesTest.AUDIO.toString(), samplingRate, encoding, tags, bitrate, length, uploader);
    }

    public AudioVideoEvent audioVideoEvent(){
        return new AudioVideoEvent(this, MediaTypesTest.AUDIOVIDEO.toString(), samplingRate, width, height, encoding, tags, bitrate, length, uploader);
    }

    public InteractiveVideoEvent interactiveVideoEvent(){
        return new InteractiveVideoEvent(this, MediaTypesTest.INTERACTIVEVIDEO.toString(), type, width, height, encoding, bitrate, length, tags, uploader);
    }

    public LicensedAudioEvent licensedAudioEvent(){
        return new LicensedAudioEvent(this, MediaTypesTest.LICENSEDAUDIO.toString(), samplingRate, encoding, tags, holder, bitrate, length, uploader);
    }

    public LicensedAudioVideoEvent licensedAudioVideoEvent(){
        return new LicensedAudioVideoEvent(this, MediaTypesTest.LICENSEDAUDIOVIDEO.toString(), samplingRate, width, height, encoding, holder, bitrate, length, tags, uploader);
    }

    public LicensedVideoEvent licensedVideoEvent(){
        return new LicensedVideoEvent(this, MediaTypesTest.LICENSEDVIDEO.toString(), width, height, encoding, tags, holder, bitrate, length, uploader);
    }
}

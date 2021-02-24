package mediaDB.ui.cli;

import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.routing.events.files.*;
import mediaDB.routing.events.misc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventFactoryTest {
    String samplingRate = "300";
    String width = "400";
    String height = "500";
    String encoding = "DWT";
    String  tags = "Animal";
    String holder = "Sony";
    String  bitrate = "350";
    String  length = "667";
    String uploader = "Gilbert";
    String type = "Abstimmung";
    String sender = "sender";
    EventFactory eventFactory = new EventFactory(sender);

//    TODO: qualitäten der events testen, instanceof kann mit notnull ersetzt werden
    @Test
    void audioEvent() {
        String[] input = new String[]{
                MediaTypes.AUDIO.toString(),
                uploader, tags, bitrate, length, encoding, samplingRate
        };
        assertTrue(eventFactory.audioEvent(input) instanceof AudioEvent);
    }

    @Test
    void audioVideoEvent() {
        String[] input = new String[]{
                MediaTypes.AUDIOVIDEO.toString(),
                uploader, tags, bitrate, length, encoding, height, width, samplingRate
        };
        assertTrue(eventFactory.audioVideoEvent(input) instanceof AudioVideoEvent);
    }

    @Test
    void interactiveVideoEvent() {
        String[] input = new String[]{
                MediaTypes.INTERACTIVEVIDEO.toString(),
                uploader, tags, bitrate, length, encoding, height, width, type
        };
        assertTrue(eventFactory.interactiveVideoEvent(input) instanceof InteractiveVideoEvent);
    }

    @Test
    void licensedAudioEvent() {
        String[] input = new String[]{
                MediaTypes.LICENSEDAUDIO.toString(),
                uploader, tags, bitrate, length, encoding, samplingRate, holder
        };
        assertTrue(eventFactory.licensedAudioEvent(input) instanceof LicensedAudioEvent);
    }

    @Test
    void licensedAudioVideoEvent() {
        String[] input = new String[]{
                MediaTypes.LICENSEDAUDIOVIDEO.toString(),
                uploader, tags, bitrate, length, encoding, height, width, samplingRate, holder
        };
        assertTrue(eventFactory.licensedAudioVideoEvent(input) instanceof LicensedAudioVideoEvent);
    }

    @Test
    void licensedVideoEvent() {
        String[] input = new String[]{
                MediaTypes.LICENSEDVIDEO.toString(),
                uploader, tags, bitrate, length, encoding, height, width, holder
        };
        assertTrue(eventFactory.licensedVideoEvent(input) instanceof LicensedVideoEvent);
    }

    @Test
    void stringEvent() {
        assertTrue(eventFactory.stringEvent("mode", "command", "option") instanceof StringEvent);
    }

    @Test
    void producerEvent() {
        assertTrue(eventFactory.producerEvent("name", "command") instanceof ProducerEvent);
    }

    @Test
    void displayEvent() {
        assertTrue(eventFactory.displayEvent("topic", "option") instanceof DisplayEvent);
    }

    @Test
    void persistenceEvent() {
        assertTrue(eventFactory.persistenceEvent("command", "option") instanceof PersistenceEvent);
    }

    @Test
    void serverResponseEvent() {
        assertTrue(eventFactory.serverResponseEvent("type", "response") instanceof ServerResponseEvent);
    }
}
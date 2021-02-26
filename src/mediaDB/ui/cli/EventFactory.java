package mediaDB.ui.cli;

import mediaDB.domain_logic.enums.Tag;
import mediaDB.routing.events.files.*;
import mediaDB.routing.events.misc.*;

import java.time.Duration;
import java.util.Collection;

public class EventFactory {
    private String sender;

    public EventFactory(String sender) {
        this.sender = sender;
    }

    public AudioEvent audioEvent(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int samplingRate = Integer.parseInt(input[6]);
        return new AudioEvent(this, fileType, samplingRate, encoding, tags, bitrate, length, uploader, sender);
    }

    public AudioVideoEvent audioVideoEvent(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int height = Integer.parseInt(input[6]);
        int width = Integer.parseInt(input[7]);
        int samplingRate = Integer.parseInt(input[8]);
        return new AudioVideoEvent(this, fileType, samplingRate, width, height, encoding, tags, bitrate, length, uploader, sender);
    }

    public InteractiveVideoEvent interactiveVideoEvent(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int height = Integer.parseInt(input[6]);
        int width = Integer.parseInt(input[7]);
        String type = input[8];
        return new InteractiveVideoEvent(this, fileType, type, width, height, encoding, bitrate, length, tags, uploader, sender);
    }

    public LicensedAudioEvent licensedAudioEvent(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int samplingRate = Integer.parseInt(input[6]);
        String holder = input[7];
        return new LicensedAudioEvent(this, fileType, samplingRate, encoding, tags, holder, bitrate, length, uploader, sender);
    }

    public LicensedAudioVideoEvent licensedAudioVideoEvent(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int height = Integer.parseInt(input[6]);
        int width = Integer.parseInt(input[7]);
        int samplingRate = Integer.parseInt(input[8]);
        String holder = input[9];
        return new LicensedAudioVideoEvent(this, fileType, samplingRate, width, height, encoding, holder, bitrate, length, tags, uploader, sender);
    }

    public LicensedVideoEvent licensedVideoEvent(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int height = Integer.parseInt(input[6]);
        int width = Integer.parseInt(input[7]);
        String holder = input[8];
        return new LicensedVideoEvent(this, fileType, width, height, encoding, tags, holder, bitrate, length, uploader, sender);
    }

    public StringEvent stringEvent(String mode, String command, String option){
        return new StringEvent(this, mode, command, option, sender);
    }

    public ProducerEvent producerEvent(String name, String command) {
        return new ProducerEvent(this, command, name, sender);
    }

    public DisplayEvent displayEvent(String topic, String option){
        return new DisplayEvent(this, topic, option, sender);
    }

    public PersistenceEvent persistenceEvent(String command, String option){
        return new PersistenceEvent(this, command, option, sender);
    }

    public ServerResponseEvent serverResponseEvent(String type, String response){
        return new ServerResponseEvent(this, type, response, sender);
    }

    public ExitEvent exitEvent(){
        return new ExitEvent(this, sender);
    }
}

package mediaDB.ui.cli.event_creation;

import mediaDB.domain_logic.Tag;
import mediaDB.routing.AudioEvent;
import mediaDB.ui.cli.ExtractTags;

import java.time.Duration;
import java.util.Collection;

public class CreateAudioEvent {
    public AudioEvent process(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int samplingRate = Integer.parseInt(input[6]);
        return new AudioEvent(this, fileType, samplingRate, encoding, tags, bitrate, length, uploader);
    }
}
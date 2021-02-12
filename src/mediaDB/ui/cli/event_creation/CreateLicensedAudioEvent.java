package mediaDB.ui.cli.event_creation;

import mediaDB.domain_logic.Tag;

import mediaDB.routing.LicensedAudioEvent;
import mediaDB.ui.cli.ExtractTags;

import java.time.Duration;
import java.util.Collection;

public class CreateLicensedAudioEvent {
    public LicensedAudioEvent process(String[] input){
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int samplingRate = Integer.parseInt(input[6]);
        String holder = input[7];
        return new LicensedAudioEvent(this, fileType, samplingRate, encoding, tags, holder, bitrate, length, uploader);
    }
}

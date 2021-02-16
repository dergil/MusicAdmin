package mediaDB.ui.cli.event_creation;

import mediaDB.domain_logic.Tag;
import mediaDB.routing.events.files.LicensedAudioVideoEvent;
import mediaDB.ui.cli.ExtractTags;

import java.time.Duration;
import java.util.Collection;

public class CreateLicensedAudioVideoEvent {
    public LicensedAudioVideoEvent process(String[] input) {
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
        return new LicensedAudioVideoEvent(this, fileType, samplingRate, width, height, encoding, holder, bitrate, length, tags, uploader);
    }
}

package mediaDB.ui.cli.event_creation;

import mediaDB.domain_logic.Tag;
import mediaDB.routing.events.files.LicensedVideoEvent;
import mediaDB.ui.cli.ExtractTags;

import java.time.Duration;
import java.util.Collection;

public class CreateLicensedVideoEvent {
    public LicensedVideoEvent process(String[] input) {
        String fileType = input[0];
        String uploader = input[1];
        Collection<Tag> tags = ExtractTags.extract(input[2]);
        long bitrate = Long.parseLong(input[3]);
        Duration length = Duration.ofSeconds(Long.parseLong(input[4]));
        String encoding = input[5];
        int height = Integer.parseInt(input[6]);
        int width = Integer.parseInt(input[7]);
        String holder = input[8];
        return new LicensedVideoEvent(this, fileType, width, height, encoding, tags, holder, bitrate, length, uploader);
    }
}

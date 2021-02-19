//package mediaDB.ui.gui;
//
//import mediaDB.domain_logic.*;
//import mediaDB.domain_logic.enums.Tag;
//import mediaDB.routing.events.files.LicensedAudioVideoEvent;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class CreateLicensedAudioVideoEventGUI {
//    int samplingRate;
//    int width;
//    int height;
//    String encoding;
//    String holder;
//    long bitrate;
//    Duration length;
//    Collection<Tag> tags;
//    String uploader;
//
//    public LicensedAudioVideoEvent create(String samplingRate, String width, String height,
//                                          String encoding, String holder, String bitrate,
//                                          String length, String tagsInput, String uploader){
//        this.samplingRate = Integer.parseInt(samplingRate);
//        this.width = Integer.parseInt(width);
//        this.height = Integer.parseInt(height);
//        this.encoding = encoding;
//        this.bitrate = Long.parseLong(bitrate);
//        this.length = Duration.ofSeconds(Long.parseLong(length));
//        String[] splitTags = tagsInput.split(",");
//        this.tags = new ArrayList<>();
//        for (String splitTag : splitTags) {
//            tags.add(Tag.valueOf(splitTag));
//        }
//        this.uploader = uploader;
//        this.holder = holder;
//        return new LicensedAudioVideoEvent(this, this.samplingRate, this.width, this.height, this.encoding,
//                this.holder, this.bitrate, this.length, this.tags, this.uploader);
//    }
//}

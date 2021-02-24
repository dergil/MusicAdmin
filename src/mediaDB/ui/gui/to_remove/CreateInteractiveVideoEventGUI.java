//package mediaDB.ui.gui;
//
//import mediaDB.domainLogic.Tag;
//import mediaDB.routing.InteractiveVideoEvent;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class CreateInteractiveVideoEventGUI {
//    private String type;
//    private int width;
//    private int height;
//    private String encoding;
//    private long bitrate;
//    private Duration length;
//    private Collection<Tag> tags;
//    private String uploader;
//
//    public InteractiveVideoEvent create(String type, String width, String height, String encoding, String bitrate,
//                                        String length, String tagsInput, String uploader){
//        this.type = type;
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
//        return new InteractiveVideoEvent(this, this.type, this.width, this.height, this.encoding, this.bitrate,
//                this.length, this.tags, this.uploader);
//
//    }
//}

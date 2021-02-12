package mediaDB.routing;

import mediaDB.domain_logic.Tag;

import java.time.Duration;
import java.util.Collection;
import java.util.EventObject;

public class InteractiveVideoEvent extends EventObject implements NetworkEvent{
    private String fileType;
    private String type;
    private int width;
    private int height;
    private String encoding;
    private long bitrate;
    private Duration length;
    private Collection<Tag> tags;
    private String uploader;

    public InteractiveVideoEvent(Object o, String fileType, String type, int width, int height, String encoding, long bitrate,
                                 Duration length, Collection<Tag> tags, String uploader) {
        super(o);
        this.fileType = fileType;
        this.type = type;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.bitrate = bitrate;
        this.length = length;
        this.tags = tags;
        this.uploader = uploader;
    }

    public String getFileType() {
        return fileType;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getEncoding() {
        return encoding;
    }

    public long getBitrate() {
        return bitrate;
    }

    public Duration getLength() {
        return length;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public String getUploader() {
        return uploader;
    }

    @Override
    public String getEventName() {
        return "InteractiveVideoEvent";
    }

}

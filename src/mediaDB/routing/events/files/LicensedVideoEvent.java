package mediaDB.routing.events.files;

import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.EventObject;

public class LicensedVideoEvent extends EventObject implements NetworkEvent, Serializable {
    private String fileType;
    private int width;
    private int height;
    private String encoding;
    private Collection<Tag> tags;
    private String holder;
    private long bitrate;
    private Duration length;
    private String uploader;
    private String sender;

    public LicensedVideoEvent(Object source, String fileType, int width, int height, String encoding, Collection<Tag> tags, String holder, long bitrate, Duration length, String uploader, String sender) {
        super(source);
        this.fileType = fileType;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.tags = tags;
        this.holder = holder;
        this.bitrate = bitrate;
        this.length = length;
        this.uploader = uploader;
        this.sender = sender;
    }

    public String getFileType() {
        return fileType;
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

    public Collection<Tag> getTags() {
        return tags;
    }

    public String getHolder() {
        return holder;
    }

    public long getBitrate() {
        return bitrate;
    }

    public Duration getLength() {
        return length;
    }

    public String getUploader() {
        return uploader;
    }

    @Override
    public String getEventName() {
        return MediaTypes.LICENSEDVIDEO.toString() + "Event";
    }

    @Override
    public String getSender() {
        return sender;
    }
}

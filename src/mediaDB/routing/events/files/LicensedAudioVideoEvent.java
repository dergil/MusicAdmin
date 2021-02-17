package mediaDB.routing.events.files;

import mediaDB.domain_logic.enums.Tag;
import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.EventObject;

public class LicensedAudioVideoEvent extends EventObject implements NetworkEvent, Serializable {
    private String fileType;
    int samplingRate;
    int width;
    int height;
    String encoding;
    String holder;
    long bitrate;
    Duration length;
    Collection<Tag> tags;
    String uploader;

    public LicensedAudioVideoEvent(Object o, String fileType, int samplingRate, int width, int height, String encoding,
                                   String holder, long bitrate, Duration length, Collection<Tag> tags,
                                   String uploader) {
        super(o);
        this.fileType = fileType;
        this.samplingRate = samplingRate;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.holder = holder;
        this.bitrate = bitrate;
        this.length = length;
        this.tags = tags;
        this.uploader = uploader;
    }

    public String getFileType() {
        return fileType;
    }

    public int getSamplingRate() {
        return samplingRate;
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

    public String getHolder() {
        return holder;
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
        return "LicensedAudioVideoEvent";
    }

}

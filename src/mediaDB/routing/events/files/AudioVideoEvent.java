package mediaDB.routing.events.files;

import mediaDB.domain_logic.Tag;
import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.EventObject;

public class AudioVideoEvent extends EventObject implements NetworkEvent, Serializable {

    private String fileType;
    private int samplingRate;
    private int width;
    private int height;
    private String encoding;
    private Collection<Tag> tags;
    private long bitrate;
    private Duration length;
    private String uploader;

    public AudioVideoEvent(Object source, String fileType, int samplingRate, int width, int height,
                           String encoding, Collection<Tag> tags, long bitrate, Duration length, String uploader) {
        super(source);
        this.fileType = fileType;
        this.samplingRate = samplingRate;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.tags = tags;
        this.bitrate = bitrate;
        this.length = length;
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

    public Collection<Tag> getTags() {
        return tags;
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
        return "AudioVideoEvent";
    }

}

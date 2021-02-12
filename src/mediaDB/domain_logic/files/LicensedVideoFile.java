package mediaDB.domain_logic.files;

import mediaDB.domain_logic.file_interfaces.LicensedVideo;
import mediaDB.domain_logic.Tag;
import mediaDB.domain_logic.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LicensedVideoFile implements LicensedVideo {
    private String fileType;
    private int width;
    private int height;
    private String encoding;
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String holder;
    private long bitrate;
    private Duration length;
    private BigDecimal size;
    private Uploader uploader;
    private Date uploadDate;

    public LicensedVideoFile(String fileType, int width, int height, String encoding, String address,
                             Collection<Tag> tags, long accessCount, String holder, long bitrate, Duration length,
                             BigDecimal size, Uploader uploader, Date uploadDate) {
        this.fileType = fileType;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.holder = holder;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.uploader = uploader;
        this.uploadDate = uploadDate;
    }

    @Override
    public String getFileType() {
        return fileType;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Collection<Tag> getTags() {
        return tags;
    }

    @Override
    public long getAccessCount() {
        return accessCount;
    }

    @Override
    public void setAccessCount(long newCount) {
        accessCount = newCount;
    }

    @Override
    public String getHolder() {
        return holder;
    }

    @Override
    public long getBitrate() {
        return bitrate;
    }

    @Override
    public Duration getLength() {
        return length;
    }

    @Override
    public BigDecimal getSize() {
        return size;
    }

    @Override
    public Uploader getUploader() {
        return uploader;
    }

    @Override
    public Date getUploadDate() {
        return uploadDate;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }
}

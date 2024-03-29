package mediaDB.domain_logic.files;

import mediaDB.domain_logic.file_interfaces.LicensedAudio;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.producer.Uploader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LicensedAudioFile implements LicensedAudio, Serializable {
    private String fileType;
    private int samplingRate;
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

    public LicensedAudioFile(String fileType, int samplingRate, String encoding, String address, Collection<Tag> tags,
                             long accessCount, String holder, long bitrate, Duration length, BigDecimal size,
                             Uploader uploader, Date uploadDate) {
        this.fileType = fileType;
        this.samplingRate = samplingRate;
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
    public int getSamplingRate() {
        return samplingRate;
    }

    @Override
    public String getEncoding() {
        return encoding;
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
    public String toString() {
        return "LicensedAudioFile{" +
                "fileType='" + fileType + '\'' +
                ", samplingRate=" + samplingRate +
                ", encoding='" + encoding + '\'' +
                ", address='" + address + '\'' +
                ", tags=" + tags +
                ", accessCount=" + accessCount +
                ", holder='" + holder + '\'' +
                ", bitrate=" + bitrate +
                ", length=" + length +
                ", size=" + size +
                ", uploader=" + uploader +
                ", uploadDate=" + uploadDate +
                '}';
    }
}

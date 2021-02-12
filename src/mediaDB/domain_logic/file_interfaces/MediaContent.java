package mediaDB.domain_logic.file_interfaces;

import java.math.BigDecimal;
import java.time.Duration;

public interface MediaContent extends Content {
    String getFileType();
    long getBitrate();
    Duration getLength();
    BigDecimal getSize();
}

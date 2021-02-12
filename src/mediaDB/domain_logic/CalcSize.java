package mediaDB.domain_logic;

import java.math.BigDecimal;
import java.time.Duration;

public class CalcSize {
    public static BigDecimal size(Duration length, long bitrate){
        return new BigDecimal(length.getSeconds() * bitrate);
    }
}

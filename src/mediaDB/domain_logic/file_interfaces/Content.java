package mediaDB.domain_logic.file_interfaces;

import mediaDB.domain_logic.Tag;

import java.util.Collection;

public interface Content {
    String getAddress();
    Collection<Tag> getTags();
    long getAccessCount();
    void setAccessCount(long newCount);
}

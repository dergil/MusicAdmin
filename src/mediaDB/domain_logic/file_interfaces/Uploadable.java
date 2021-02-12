package mediaDB.domain_logic.file_interfaces;

import mediaDB.domain_logic.Uploader;

import java.util.Date;

public interface Uploadable {
    Uploader getUploader();
    Date getUploadDate();
    String getFileType();
}

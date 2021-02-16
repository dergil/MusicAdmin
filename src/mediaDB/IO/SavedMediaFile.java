package mediaDB.IO;

public class SavedMediaFile {
    String fileType;
    String address;
    int size;
    int offset;

    public SavedMediaFile(String fileType, String address, int size, int offset) {
        this.fileType = fileType;
        this.address = address;
        this.size = size;
        this.offset = offset;
    }

    public String getAddress() {
        return address;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return offset;
    }

    public String getFileType() {
        return fileType;
    }
}

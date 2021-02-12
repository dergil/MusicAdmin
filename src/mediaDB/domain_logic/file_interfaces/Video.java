package mediaDB.domain_logic.file_interfaces;

public interface Video extends MediaContent,Uploadable{
    int getWidth();
    int getHeight();
    String getEncoding();
}

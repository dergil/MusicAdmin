package mediaDB.domain_logic.file_interfaces;

public interface Audio extends MediaContent,Uploadable{
    int getSamplingRate();
    String getEncoding();
}

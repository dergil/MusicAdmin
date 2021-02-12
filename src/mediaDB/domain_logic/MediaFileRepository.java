package mediaDB.domain_logic;

import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.MediaContent;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.tempserver.ServerToClientMessenger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaFileRepository {
    ServerToClientMessenger toClient;
//    TODO: nullpointer, when MAX_CAPACITY is set via constructor
    private SizeObservable sizeObservable;
    private TagObservable tagObservable;
    List<Uploadable> mediaFiles = Collections.synchronizedList(new ArrayList<>());

    public MediaFileRepository(ServerToClientMessenger toClient, SizeObservable sizeObservable, TagObservable tagObservable) {
        this.toClient = toClient;
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
    }

    public void create(Uploadable mediaFile){
        mediaFiles.add(mediaFile);
        sizeObservable.addSize(((MediaContent)mediaFile).getSize());
        tagObservable.add(((Content) mediaFile).getTags());
    }

    public List<Uploadable> read(){
        return mediaFiles;
    }

    public void delete(Uploadable mediaFile){
        mediaFiles.remove(mediaFile);
        sizeObservable.subtractSize(((MediaContent)mediaFile).getSize());
        tagObservable.remove(((Content) mediaFile).getTags());
    }

    public void delete(String address){
        Uploadable mediaFile = findByAddress(address);
        mediaFiles.remove(mediaFile);
        assert mediaFile != null;
        sizeObservable.subtractSize(((MediaContent)mediaFile).getSize());
        tagObservable.remove(((Content) mediaFile).getTags());
    }

    protected Uploadable findByAddress(String address){
        for (Uploadable uploadable: mediaFiles)
            if (((Content)uploadable).getAddress().equals(address))
                return uploadable;
        return null;
    }

    protected boolean contains(String address){
        for (Uploadable uploadable: mediaFiles){
            if (((Content)uploadable).getAddress().equals(address))
                return true;
        }
        return false;
    }

    public boolean capacityAvailable(BigDecimal size){
        return sizeObservable.capacityAvailable(size);
    }

    protected boolean contains(Uploadable uploadable){
        return mediaFiles.contains(uploadable);
    }

    public void incrementAccessCount(String address) throws IOException {
        if (contains(address)){
            Uploadable file = findByAddress(address);
            Content content = ((Content)file);
            content.setAccessCount(content.getAccessCount() + 1);
        }
        else toClient.fileNotListet();
    }

//    TODO: call by value, call by reference? (testen)
    public void incrementAccessCount(Uploadable file) throws IOException {
        if (contains(file)){
            Content content = ((Content)file);
            content.setAccessCount(content.getAccessCount() + 1);
        }
        else toClient.fileNotListet();
    }

    protected void incrementAccessCountForList(List<Uploadable> list) throws IOException {
        for (Uploadable uploadable : list){
            incrementAccessCount(uploadable);
        }
    }

    public TagObservable getTagObservable() {
        return tagObservable;
    }

    public SizeObservable getSizeObservable() {
        return sizeObservable;
    }
}

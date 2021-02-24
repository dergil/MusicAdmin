package mediaDB.domain_logic;

import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.MediaContent;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.observables.UploadsObservable;
import mediaDB.net.server.ToClientMessenger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaFileRepository {
    ToClientMessenger toClient;
//    TODO: nullpointer, when MAX_CAPACITY is set via constructor
    private SizeObservable sizeObservable;
    private TagObservable tagObservable;
    private UploadsObservable uploadsObservable;
    List<Uploadable> mediaFiles = Collections.synchronizedList(new ArrayList<>());

    public MediaFileRepository(ToClientMessenger toClient, SizeObservable sizeObservable, TagObservable tagObservable) {
        this.toClient = toClient;
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
    }

    public synchronized void create(Uploadable mediaFile){
        mediaFiles.add(mediaFile);
        sizeObservable.addSize(((MediaContent)mediaFile).getSize());
        tagObservable.add(((Content) mediaFile).getTags());
        changeInUploads("File added", ((MediaContent) mediaFile).getAddress());
    }

    public synchronized List<Uploadable> read(){
        return mediaFiles;
    }

    public synchronized void delete(Uploadable mediaFile){
        mediaFiles.remove(mediaFile);
        sizeObservable.subtractSize(((MediaContent)mediaFile).getSize());
        tagObservable.remove(((Content) mediaFile).getTags());
        changeInUploads("File deleted", ((MediaContent) mediaFile).getAddress());
    }

    public synchronized void delete(String address){
        Uploadable mediaFile = findByAddress(address);
        assert mediaFile != null;
        mediaFiles.remove(mediaFile);
        sizeObservable.subtractSize(((MediaContent)mediaFile).getSize());
        tagObservable.remove(((Content) mediaFile).getTags());
        changeInUploads("File deleted", address);
    }

    public synchronized Uploadable findByAddress(String address){
        for (Uploadable uploadable: mediaFiles)
            if (((Content)uploadable).getAddress().equals(address))
                return uploadable;
        return null;
    }

    public synchronized boolean contains(String address){
        for (Uploadable uploadable: mediaFiles){
            if (((Content)uploadable).getAddress().equals(address))
                return true;
        }
        return false;
    }

    public synchronized boolean capacityAvailable(BigDecimal size){
        return sizeObservable.capacityAvailable(size);
    }

    public synchronized boolean contains(Uploadable uploadable){
        return mediaFiles.contains(uploadable);
    }

    public synchronized void incrementAccessCount(String address) {
        if (contains(address)){
            Uploadable file = findByAddress(address);
            Content content = ((Content)file);
            content.setAccessCount(content.getAccessCount() + 1);
            changeInUploads("Access count changed", address);
        }
//        TODO: antwort geben?
//        else toClient.fileNotListet();
    }

//    TODO: call by value, call by reference? (testen)
    public synchronized void incrementAccessCount(Uploadable file) {
        if (contains(file)){
            Content content = ((Content)file);
            content.setAccessCount(content.getAccessCount() + 1);
            changeInUploads("Access count changed", ((Content) file).getAddress());
        }
//        else toClient.fileNotListet();
    }

    public synchronized void incrementAccessCountForList(List<Uploadable> list) {
        for (Uploadable uploadable : list){
            incrementAccessCount(uploadable);
        }
//        changeInUploads();
    }

    private synchronized void changeInUploads(String type, String address){
//        if (uploadsObservable != null)
//            uploadsObservable.change(type, address);
    }

    public TagObservable getTagObservable() {
        return tagObservable;
    }

    public SizeObservable getSizeObservable() {
        return sizeObservable;
    }

    public void setMediaFiles(List<Uploadable> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    public void setUploadsObservable(UploadsObservable uploadsObservable) {
        this.uploadsObservable = uploadsObservable;
    }
}

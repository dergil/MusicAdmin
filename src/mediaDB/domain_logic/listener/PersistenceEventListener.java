package mediaDB.domain_logic.listener;

import mediaDB.IO.Deserialize;
import mediaDB.IO.DeserializeDomainContent;
import mediaDB.IO.RandomAccess;
import mediaDB.IO.Serialize;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.ui.Numerical;

import java.io.IOException;
import java.util.List;

public class PersistenceEventListener implements EventListener<PersistenceEvent> {
    MediaFileRepository mediaFileRepository;
    Serialize serialize;
    Deserialize deserialize;
    DeserializeDomainContent deserializeDomainContent;
    RandomAccess randomAccess;
    ToClientMessenger toClient;

    public PersistenceEventListener(MediaFileRepository mediaFileRepository, Serialize serialize, Deserialize deserialize, DeserializeDomainContent deserializeDomainContent, RandomAccess randomAccess, ToClientMessenger toClient) {
        this.mediaFileRepository = mediaFileRepository;
        this.serialize = serialize;
        this.deserialize = deserialize;
        this.deserializeDomainContent = deserializeDomainContent;
        this.randomAccess = randomAccess;
        this.toClient = toClient;
    }

    @Override
    public void onMediaEvent(PersistenceEvent event) throws IOException {
        String option = event.getOption();
        switch (event.getCommand()){
            case "saveJOS":
                serialize.serialize();
                break;
            case "loadJOS":
                deserialize.deserialize();
                toClient.dataChange();
                break;
            case "save":
                if (option != null && Numerical.check(option)){
                    if (randomAccess.isEmpty()){
                        saveMediaFileRepositoryInRandomAccessList();
                        return;
                    }
                    Uploadable uploadable = mediaFileRepository.findByAddress(option);
                    if (uploadable != null)
                        randomAccess.save(uploadable);
                }
                else System.out.println("Syntax error");
                break;
            case "load":
                if (option != null && Numerical.check(option) && !fileExisting(option)){
                    try {
                        Uploadable loadedFile = randomAccess.load(option);
                        if (loadedFile != null){
                            mediaFileRepository.create(loadedFile);
                            toClient.dataChange();
                        }

                    } catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
                else System.out.println("Syntax error");
                break;
            default:
                System.out.println("Syntax error.");
        }
    }

    private boolean fileExisting(String address){
        return mediaFileRepository.findByAddress(address) != null;
    }

    private void saveMediaFileRepositoryInRandomAccessList() throws IOException {
        List<Uploadable> mediaFiles = mediaFileRepository.read();
        for (Uploadable uploadable : mediaFiles){
            randomAccess.save(uploadable);
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PersistenceEvent.class);
    }
}

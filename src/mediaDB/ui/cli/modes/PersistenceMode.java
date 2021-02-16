package mediaDB.ui.cli.modes;

import mediaDB.IO.Deserialize;
import mediaDB.IO.DeserializeDomainContent;
import mediaDB.IO.RandomAccess;
import mediaDB.IO.Serialize;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.ui.Numerical;
import mediaDB.ui.cli.Console;

import java.io.IOException;
import java.util.List;

public class PersistenceMode {
    EventListener<NetworkEvent> serverEventBus;
    MediaFileRepository mediaFileRepository;
    Serialize serialize;
    Deserialize deserialize;
    DeserializeDomainContent deserializeDomainContent;
    RandomAccess randomAccess;
    String input;
    String[] splitInput = null;

    public PersistenceMode(EventListener<NetworkEvent> serverEventBus, MediaFileRepository mediaFileRepository,
                           Serialize serialize, Deserialize deserialize,
                           DeserializeDomainContent deserializeDomainContent, RandomAccess randomAccess) {
        this.serverEventBus = serverEventBus;
        this.mediaFileRepository = mediaFileRepository;
        this.serialize = serialize;
        this.deserialize = deserialize;
        this.deserializeDomainContent = deserializeDomainContent;
        this.randomAccess = randomAccess;
    }


    public void start() throws IOException, ClassNotFoundException {
        System.out.println(
                "saveJOS\n" +
                "loadJOS\n" +
                "save [Abrufadresse]\n" +
                "load [Abrufadresse]\n");

//        do {
        getAndVerifyInput();
//        } while (!input.equals("0")) ;
    }

    private void getAndVerifyInput() throws IOException, ClassNotFoundException {
        input = Console.prompt("Persistence mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("saveJOS")) {
            serialize.serialize();
            return;
        }
        if (splitInput[0].equals("loadJOS")) {
            deserialize.deserialzie();
            return;
        }
        if (splitInput[0].equals("save") && Numerical.isNumerical(splitInput[1])) {
            if (randomAccess.isEmpty()){
                saveMediaFileRepositoryInRandomAccessList();
                return;
            }
            Uploadable uploadable = mediaFileRepository.findByAddress(splitInput[1]);
            if (uploadable != null)
                randomAccess.save(uploadable);
        }
        switch (splitInput[0]){
            case "saveJOS":
                serialize.serialize();
                break;
            case "loadJOS":
                deserialize.deserialzie();
                break;
            case "save":
                if (splitInput.length > 1 && Numerical.isNumerical(splitInput[1])){
                    if (randomAccess.isEmpty()){
                        saveMediaFileRepositoryInRandomAccessList();
                        return;
                    }
                    Uploadable uploadable = mediaFileRepository.findByAddress(splitInput[1]);
                    if (uploadable != null)
                        randomAccess.save(uploadable);
                }
                else System.out.println("Syntax error");
                break;
            case "load":
                if (splitInput.length > 1 && Numerical.isNumerical(splitInput[1])){
                    Uploadable loadedFile = randomAccess.load(splitInput[1]);
                    if (loadedFile != null)
                        mediaFileRepository.create(loadedFile);
                }
                else System.out.println("Syntax error");
                break;
            default:
                System.out.println("Syntax error.");
        }
    }

    private void saveMediaFileRepositoryInRandomAccessList() throws IOException {
        List<Uploadable> mediaFiles = mediaFileRepository.read();
        for (Uploadable uploadable : mediaFiles){
            randomAccess.save(uploadable);
        }
    }
}




import mediaDB.IO.*;
import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.display.DisplayEventListener;
import mediaDB.domain_logic.listener.display.DisplayModeProcessing;
import mediaDB.domain_logic.listener.display.GenerateDisplayContent;
import mediaDB.domain_logic.listener.files.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.listener.*;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.EventBus;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.*;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;
import mediaDB.observer.SizeObserver;
import mediaDB.observer.TagObserver;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class App {


    public static void main(String[] args) throws IOException {
//        Server
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository, toClient);

        AudioEventListener audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        AudioVideoEventListener audioVideoEventListener = new AudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        InteractiveVideoEventListener interactiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioEventListener licensedAudioEventListener = new LicensedAudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioVideoEventListener licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedVideoEventListener licensedVideoEventListener = new LicensedVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        ProducerEventListener producerEventListener = new ProducerEventListener(producerRepository, toClient);
        GenerateDisplayContent generateDisplayContent = new GenerateDisplayContent();
        DisplayModeProcessing displayModeProcessing = new DisplayModeProcessing(generateDisplayContent, producerRepository, mediaFileRepository);
        DisplayEventListener displayEventListener = new DisplayEventListener(displayModeProcessing, mediaFileRepository, toClient);
        StringEventListener stringEventListener = new StringEventListener(mediaFileRepository, producerRepository, toClient);
        Serialize serialize = new Serialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository);
        DeserializeDomainContent deserializeDomainContent = new DeserializeDomainContent();
        Deserialize deserialize = new Deserialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, deserializeDomainContent);
        RandomAccessFile randomAccessFile = new RandomAccessFile("RandomAccessFile", "rw");
        ArrayList<SavedMediaFile> savedMediaFiles = new ArrayList<>();
        RandomAccess randomAccess = new RandomAccess(randomAccessFile, savedMediaFiles);
        PersistenceEventListener persistenceEventListener = new PersistenceEventListener(mediaFileRepository, serialize, deserialize, deserializeDomainContent, randomAccess, toClient);

        EventBus serverEventBus = new EventBus();
        serverEventBus.register(audioEventListener);
        serverEventBus.register(audioVideoEventListener);
        serverEventBus.register(interactiveVideoEventListener);
        serverEventBus.register(licensedAudioEventListener);
        serverEventBus.register(licensedAudioVideoEventListener);
        serverEventBus.register(licensedVideoEventListener);
        serverEventBus.register(producerEventListener);
        serverEventBus.register(displayEventListener);
        serverEventBus.register(stringEventListener);
        serverEventBus.register(persistenceEventListener);

//        Client

        EventBus clientEventBus = new EventBus();
        String clientName = "client1";
        EventHandler clientEventHandler = new EventHandler();
        clientEventHandler.add(serverEventBus);
        EventFactory eventFactory = new EventFactory(clientName);
        InsertMode insertMode = new InsertMode(clientEventHandler, eventFactory);
        DisplayMode displayMode = new DisplayMode(clientEventHandler, eventFactory);
        DeletionMode deletionMode = new DeletionMode(clientEventHandler, eventFactory);
        ChangeMode changeMode = new ChangeMode(clientEventHandler, eventFactory);
        ConfigMode configMode = new ConfigMode(mediaFileRepository);
        PersistenceMode persistenceMode = new PersistenceMode(clientEventHandler, eventFactory);
        CLIAdmin cliAdmin = new CLIAdmin(insertMode, displayMode, deletionMode, changeMode, configMode, persistenceMode);
//        Observers
        SizeObserver sizeObserver = new SizeObserver(sizeObservable);
        sizeObservable.register(sizeObserver);
        TagObserver tagObserver = new TagObserver(tagObservable);
        tagObservable.register(tagObserver);

        cliAdmin.start();


    }




}

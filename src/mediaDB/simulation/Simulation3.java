package mediaDB.simulation;

import mediaDB.IO.Deserialize;
import mediaDB.IO.DeserializeDomainContent;
import mediaDB.IO.RandomAccess;
import mediaDB.IO.Serialize;
import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.*;
import mediaDB.net.server.ServerEventBus;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.ui.cli.CLIAdmin;
import mediaDB.ui.cli.modes.*;
import mediaDB.ui.cli.observer.SizeObserver;
import mediaDB.ui.cli.observer.TagObserver;

import java.io.FileNotFoundException;

public class Simulation3 {
    public static void main(String[] args) throws FileNotFoundException {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);

        AudioEventListener audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        AudioVideoEventListener audioVideoEventListener = new AudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        InteractiveVideoEventListener interactiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioEventListener licensedAudioEventListener = new LicensedAudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioVideoEventListener licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedVideoEventListener licensedVideoEventListener = new LicensedVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        ProducerEventListener producerEventListener = new ProducerEventListener(producerRepository);
        GenerateDisplayContent generateDisplayContent = new GenerateDisplayContent(mediaFileRepository);
        DisplayModeServer displayModeServer = new DisplayModeServer(generateDisplayContent, producerRepository, mediaFileRepository);
        DisplayEventListener displayEventListener = new DisplayEventListener(displayModeServer, mediaFileRepository, toClient);
        StringEventListener stringEventListener = new StringEventListener(mediaFileRepository, producerRepository);

        ServerEventBus serverEventBus = new ServerEventBus();
        serverEventBus.register(audioEventListener);
        serverEventBus.register(audioVideoEventListener);
        serverEventBus.register(interactiveVideoEventListener);
        serverEventBus.register(licensedAudioEventListener);
        serverEventBus.register(licensedAudioVideoEventListener);
        serverEventBus.register(licensedVideoEventListener);
        serverEventBus.register(producerEventListener);
        serverEventBus.register(displayEventListener);
        serverEventBus.register(stringEventListener);

//        Client
        InsertModeInputProcessing insertModeInputProcessing = new InsertModeInputProcessing(serverEventBus);
        InsertMode insertMode = new InsertMode(insertModeInputProcessing, serverEventBus);
        DisplayMode displayMode = new DisplayMode(serverEventBus);
        DeletionMode deletionMode = new DeletionMode(serverEventBus);
        ChangeMode changeMode = new ChangeMode(serverEventBus);
        ConfigMode configMode = new ConfigMode(mediaFileRepository);
        Serialize serialize = new Serialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository);
        DeserializeDomainContent deserializeDomainContent = new DeserializeDomainContent();
        Deserialize deserialize = new Deserialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, deserializeDomainContent);
        RandomAccess randomAccess = new RandomAccess();
        PersistenceMode persistenceMode = new PersistenceMode(serverEventBus, mediaFileRepository, serialize, deserialize, deserializeDomainContent, randomAccess);
        CLIAdmin cliAdmin = new CLIAdmin(insertMode, displayMode, deletionMode, changeMode, configMode, persistenceMode);
//        Observer
        SizeObserver sizeObserver = new SizeObserver(sizeObservable);
        sizeObservable.register(sizeObserver);
        TagObserver tagObserver = new TagObserver(tagObservable);
        tagObservable.register(tagObserver);


        BlockingQueue queue = new BlockingQueue(mediaFileRepository, sizeObservable);
        ThreadCreateS2 create1 = new ThreadCreateS2(producerRepository, mediaFileRepository, queue);
        ThreadCreateS2 create2 = new ThreadCreateS2(producerRepository, mediaFileRepository, queue);
        ThreadDeleteS3 delete1 = new ThreadDeleteS3(mediaFileRepository, queue);
        ThreadDeleteS3 delete2 = new ThreadDeleteS3(mediaFileRepository, queue);
        create1.start();
        create2.start();
        delete1.start();
        delete2.start();
    }
}

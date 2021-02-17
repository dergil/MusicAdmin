package mediaDB.simulation;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.display.DisplayEventListener;
import mediaDB.domain_logic.listener.display.DisplayModeServer;
import mediaDB.domain_logic.listener.display.GenerateDisplayContent;
import mediaDB.domain_logic.listener.files.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.listener.*;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ServerEventBus;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.CLIAdmin;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;
import mediaDB.observer.SizeObserver;
import mediaDB.observer.TagObserver;

import java.io.FileNotFoundException;

public class Simulation3 {
    public static void main(String[] args) throws FileNotFoundException {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
//        AddressRepository addressRepository = new AddressRepository();
//        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);

//        AudioEventListener audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
//        AudioVideoEventListener audioVideoEventListener = new AudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
//        InteractiveVideoEventListener interactiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
//        LicensedAudioEventListener licensedAudioEventListener = new LicensedAudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
//        LicensedAudioVideoEventListener licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
//        LicensedVideoEventListener licensedVideoEventListener = new LicensedVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
//        ProducerEventListener producerEventListener = new ProducerEventListener(producerRepository);
//        GenerateDisplayContent generateDisplayContent = new GenerateDisplayContent(mediaFileRepository);
//        DisplayModeServer displayModeServer = new DisplayModeServer(generateDisplayContent, producerRepository, mediaFileRepository);
//        DisplayEventListener displayEventListener = new DisplayEventListener(displayModeServer, mediaFileRepository, toClient);
//        StringEventListener stringEventListener = new StringEventListener(mediaFileRepository, producerRepository);
//
//        ServerEventBus serverEventBus = new ServerEventBus();
//        serverEventBus.register(audioEventListener);
//        serverEventBus.register(audioVideoEventListener);
//        serverEventBus.register(interactiveVideoEventListener);
//        serverEventBus.register(licensedAudioEventListener);
//        serverEventBus.register(licensedAudioVideoEventListener);
//        serverEventBus.register(licensedVideoEventListener);
//        serverEventBus.register(producerEventListener);
//        serverEventBus.register(displayEventListener);
//        serverEventBus.register(stringEventListener);
//
////        Client
//        EventHandler eventHandler = new EventHandler();
//        eventHandler.add(serverEventBus);
//        EventFactory eventFactory = new EventFactory();
//        InsertMode insertMode = new InsertMode(eventHandler, eventFactory);
//        DisplayMode displayMode = new DisplayMode(eventHandler, eventFactory);
//        DeletionMode deletionMode = new DeletionMode(eventHandler, eventFactory);
//        ChangeMode changeMode = new ChangeMode(eventHandler, eventFactory);
//        ConfigMode configMode = new ConfigMode(mediaFileRepository);
//        PersistenceMode persistenceMode = new PersistenceMode(eventHandler, eventFactory);
//        CLIAdmin cliAdmin = new CLIAdmin(insertMode, displayMode, deletionMode, changeMode, configMode, persistenceMode);
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

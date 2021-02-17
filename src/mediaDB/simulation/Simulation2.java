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
import mediaDB.ui.cli.CLIAdmin;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;
import mediaDB.observer.SizeObserver;
import mediaDB.observer.TagObserver;

import java.io.FileNotFoundException;

public class Simulation2 {

    public static void main(String[] args) throws FileNotFoundException {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
//        AddressRepository addressRepository = new AddressRepository();
//        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);
//
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

//        Client
//        EventFactory eventFactory = new EventFactory();
//        InsertMode insertMode = new InsertMode(serverEventBus, eventFactory);
//        DisplayMode displayMode = new DisplayMode(serverEventBus, eventFactory);
//        DeletionMode deletionMode = new DeletionMode(serverEventBus, eventFactory);
//        ChangeMode changeMode = new ChangeMode(serverEventBus, eventFactory);
//        ConfigMode configMode = new ConfigMode(mediaFileRepository);
//        PersistenceMode persistenceMode = new PersistenceMode(serverEventBus, eventFactory);
//        CLIAdmin cliAdmin = new CLIAdmin(insertMode, displayMode, deletionMode, changeMode, configMode, persistenceMode);
//        Observer
        SizeObserver sizeObserver = new SizeObserver(sizeObservable);
        sizeObservable.register(sizeObserver);
        TagObserver tagObserver = new TagObserver(tagObservable);
        tagObservable.register(tagObserver);


        BlockingQueue queue = new BlockingQueue(mediaFileRepository, sizeObservable);
        ThreadCreateS2 thread1 = new ThreadCreateS2(producerRepository, mediaFileRepository, queue);
        ThreadDeleteS2 thread2 = new ThreadDeleteS2(mediaFileRepository, queue);
        ThreadAccessCountS2 thread3 = new ThreadAccessCountS2(mediaFileRepository);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

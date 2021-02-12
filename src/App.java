import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.*;
import mediaDB.net.server.ServerEventBus;
import mediaDB.routing.*;
import mediaDB.tempserver.ServerToClientMessenger;
import mediaDB.ui.cli.*;
import mediaDB.ui.cli.DisplayMode;

import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException {
//        Server
        ServerToClientMessenger toClient= new ServerToClientMessenger();
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
        EventHandlers eventHandlers = new EventHandlers();
        InsertMode insertMode = new InsertMode(eventHandlers);
        DisplayMode displayMode = new DisplayMode(eventHandlers);
        DeletionMode deletionMode = new DeletionMode(eventHandlers);
        ChangeMode changeMode = new ChangeMode(eventHandlers);
        ConfigMode configMode = new ConfigMode(eventHandlers, mediaFileRepository);
        CLIAdmin cliAdmin = new CLIAdmin(insertMode, displayMode, deletionMode, changeMode, configMode);
//      InsertMode
//        EventHandler<ProducerEvent> producerEventEventHandler = new EventHandler<>();
//        producerEventEventHandler.add(serverEventBus);
////        producerEventEventHandler.add(producerEventListener);
//        eventHandlers.setProducerEventEventHandler(producerEventEventHandler);
//
//        EventHandler<AudioEvent> audioEventEventHandler = new EventHandler<>();
//        audioEventEventHandler.add(serverEventBus);
//        eventHandlers.setAudioEventHandler(audioEventEventHandler);
//
//        EventHandler<AudioVideoEvent> audioVideoEventHandler = new EventHandler<>();
//        audioVideoEventHandler.add(serverEventBus);
//        eventHandlers.setAudioVideoEventHandler(audioVideoEventHandler);
//
//        EventHandler<InteractiveVideoEvent> interactiveVideoEventEventHandler = new EventHandler<>();
//        interactiveVideoEventEventHandler.add(serverEventBus);
//        eventHandlers.setInteractiveVideoEventHandler(interactiveVideoEventEventHandler);
//
//        EventHandler<LicensedAudioEvent> licensedAudioEventEventHandler = new EventHandler<>();
//        licensedAudioEventEventHandler.add(serverEventBus);
//        eventHandlers.setLicensedAudioEventHandler(licensedAudioEventEventHandler);
//
//        EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventEventHandler = new EventHandler<>();
//        licensedAudioVideoEventEventHandler.add(serverEventBus);
//        eventHandlers.setLicensedAudioVideoEventHandler(licensedAudioVideoEventEventHandler);
//
//        EventHandler<LicensedVideoEvent> licensedVideoEventEventHandler = new EventHandler<>();
//        licensedVideoEventEventHandler.add(serverEventBus);
//        eventHandlers.setLicensedVideoEventHandler(licensedVideoEventEventHandler);
////      DisplayMode
//        EventHandler<DisplayEvent> displayModeEventHandler = new EventHandler<>();
//        displayModeEventHandler.add(serverEventBus);
//        eventHandlers.setDisplayEventEventHandler(displayModeEventHandler);
////      String events
//        EventHandler<StringEvent> stringEventEventHandler = new EventHandler<>();
//        stringEventEventHandler.add(serverEventBus);
//        eventHandlers.setStringEventEventHandler(stringEventEventHandler);
//
//        SizeObserver sizeObserver = new SizeObserver(administration.getMediaFileRepository());
//        administration.getMediaFileRepository().register(sizeObserver);

//        TagObserver tagObserver = new TagObserver(administration.getMediaFileRepository().getTagObservable());
//        administration.getMediaFileRepository().getTagObservable().register(tagObserver);

        cliAdmin.start();


    }




}

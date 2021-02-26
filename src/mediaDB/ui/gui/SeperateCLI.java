package mediaDB.ui.gui;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.net.EventBus;
import mediaDB.observer.SizeObserver;
import mediaDB.observer.TagObserver;
import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.CLIAdmin;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;

import java.io.IOException;

public class SeperateCLI extends Thread{
    EventBus serverEventBus;
    MediaFileRepository mediaFileRepository;
    SizeObservable sizeObservable;
    TagObservable tagObservable;

    public SeperateCLI (EventBus serverEventBus, MediaFileRepository mediaFileRepository, SizeObservable sizeObservable, TagObservable tagObservable){
        this.serverEventBus = serverEventBus;
        this.mediaFileRepository = mediaFileRepository;
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
    }
//todo: oberserver sollten nicht automatisch aktiviert sein
    public void run(){
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
        try {
            cliAdmin.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
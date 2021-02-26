package mediaDB.ui.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mediaDB.IO.*;
import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileFactory;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.listener.PersistenceEventListener;
import mediaDB.domain_logic.listener.ProducerEventListener;
import mediaDB.domain_logic.listener.StringEventListener;
import mediaDB.domain_logic.listener.display.DisplayEventListener;
import mediaDB.domain_logic.listener.display.DisplayModeProcessing;
import mediaDB.domain_logic.listener.display.GenerateDisplayContent;
import mediaDB.domain_logic.listener.files.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.EventBus;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.EventFactory;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GUIDataBinding extends Application {
    ListView<Uploadable> uploadsListView;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;

    @Override
    public void start(Stage stage) throws Exception {
        String clientName = "client1";
        EventFactory eventFactory = new EventFactory(clientName);
        EventHandler eventHandler = new EventHandler();
        GUIPersist guiPersist = new GUIPersist(eventFactory, eventHandler);

        Thread.setDefaultUncaughtExceptionHandler((Thread thread, Throwable thrown) -> {
            System.out.println(thread.getName() + ":" + thrown.getMessage());
            thrown.printStackTrace();
        });
        ToClientMessenger toClient = new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        mediaFileRepository = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        producerRepository = new ProducerRepository();
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

        EventBus eventBus = new EventBus();
        eventBus.register(audioEventListener);
        eventBus.register(audioVideoEventListener);
        eventBus.register(interactiveVideoEventListener);
        eventBus.register(licensedAudioEventListener);
        eventBus.register(licensedAudioVideoEventListener);
        eventBus.register(licensedVideoEventListener);
        eventBus.register(producerEventListener);
        eventBus.register(displayEventListener);
        eventBus.register(stringEventListener);
        eventBus.register(persistenceEventListener);
        eventHandler.add(eventBus);

        uploadsListView = new ListView<>();
        ObservableList<Uploadable > uploadableObservableList = FXCollections.observableArrayList(mediaFileRepository.read());
        uploadsListView.setItems(uploadableObservableList);

        SeperateCLI seperateCLI = new SeperateCLI(eventBus, mediaFileRepository, sizeObservable, tagObservable);
        seperateCLI.start();

        VBox vBox = new VBox(uploadsListView);
        /* creating scene */
        Scene scene = new Scene(vBox, 220, 270);
        /* adding scene to stage */
        stage.setScene(scene);
        /* display scene for shfxml/owing output */
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

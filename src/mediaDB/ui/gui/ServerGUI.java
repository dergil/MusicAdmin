package mediaDB.ui.gui;

/*
Quellen:
https://www.educba.com/javafx-listview/
https://o7planning.org/en/11533/opening-a-new-window-in-javafx
https://stackoverflow.com/questions/30814258/javafx-pass-parameters-while-instantiating-controller-class
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mediaDB.IO.*;
import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileFactory;
import mediaDB.domain_logic.MediaFileRepository;
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
import javafx.event.EventHandler;
import mediaDB.net.EventBus;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.gui.controllers.*;
import mediaDB.ui.gui.listener.ServerResponseEventListener;

import java.io.*;
import java.util.ArrayList;
//todo: Notiz bei alles GUI mains, dass evtl. proxy main verwendet werden muss

// Bei einem Teil der Buttons muss ein Element aus einer Liste ausgewählt sein
//
public class ServerGUI extends Application {
    ListView<String> uploadsListView;
    ListView<String> producerListView;
    ListView<String> displayListView;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;

    @Override
    public void start(Stage displayScreen) throws Exception {
        String guiClientName = "gui";
        EventFactory guiEventFactory = new EventFactory(guiClientName);
        mediaDB.routing.EventHandler eventHandler = new mediaDB.routing.EventHandler();
        GUIPersist guiPersist = new GUIPersist(guiEventFactory, eventHandler);

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
        ArrayList<SavedMediaFile> savedMediaFiles = new ArrayList<>();
        RandomAccessFile randomAccessFile = new RandomAccessFile("RandomAccessFile", "rw");
        RandomAccess randomAccess = new RandomAccess(randomAccessFile, savedMediaFiles);
        Serialize serialize = new Serialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, randomAccess);
        DeserializeDomainContent deserializeDomainContent = new DeserializeDomainContent();
        Deserialize deserialize = new Deserialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, deserializeDomainContent, randomAccess);
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


        /* create list object */
        uploadsListView = new ListView<>();
        producerListView = new ListView<>();
        displayListView = new ListView<>();

        ServerResponseEventListener serverResponseEventListener = new ServerResponseEventListener(uploadsListView, producerListView, displayListView, guiEventFactory, eventHandler);
        toClient.setEventEventListener(serverResponseEventListener);

        Button addAudioButton = new Button("Add Audio");
        Button addAudioVideoButton = new Button("Add AudioVideo");
        Button addInteractiveVideoButton = new Button("Add InteractiveVideo");
        Button addLicensedAudioButton = new Button("Add LicensedAudio");
        Button addLicensedAudioVideoButton = new Button("Add LicensedAudioVideo");
        Button addLicensedVideoButton = new Button("Add LicensedVideo");
        Button deleteUpload = new Button("Delete file");
        Button deleteProducer = new Button("Delete producer");
        Button addProducerButton = new Button("Add Producer");
        Button sortByAddress = new Button("Sort by address");
        Button sortByAccessCount = new Button("Sort by access count");
        Button sortByProducer = new Button("Sort by producer");
        Button incrementAccessCount = new Button("Increment access count");
        Button saveJOS = new Button("SaveJOS");
        Button loadJOS = new Button("LoadJOS");
        Button saveByAddress = new Button("Save by address");
        Button loadByAddress = new Button("Load by address");
        Button displayUploadsByType = new Button("Display uploads by type");
        Button displayTagsByType = new Button("Display tags");

        ExtractDataFromString extractDataFromString = new ExtractDataFromString();
        SortUploads sortUploads = new SortUploads(extractDataFromString);

        addAudioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAudio.fxml"));
                AddAudioController controller = new AddAudioController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addAudioRoot = null;
                try {
                    addAudioRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addAudioRoot != null;
                Scene secondScene = new Scene(addAudioRoot);

                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addAudioVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAudioVideo.fxml"));
                AddAudioVideoController controller = new AddAudioVideoController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addAudioVideoRoot = null;
                try {
                    addAudioVideoRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addAudioVideoRoot != null;
                Scene secondScene = new Scene(addAudioVideoRoot);

                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addInteractiveVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addInteractiveVideo.fxml"));
                AddInteractiveVideoController controller = new AddInteractiveVideoController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addInteractiveVideoRoot = null;
                try {
                    addInteractiveVideoRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addInteractiveVideoRoot != null;
                Scene secondScene = new Scene(addInteractiveVideoRoot);

                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addLicensedAudioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedAudio.fxml"));
                AddLicensedAudioController controller = new AddLicensedAudioController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addLicensedAudioRoot = null;
                try {
                    addLicensedAudioRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addLicensedAudioRoot != null;
                Scene secondScene = new Scene(addLicensedAudioRoot);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addLicensedAudioVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedAudioVideo.fxml"));
                AddLicensedAudioVideoController controller = new AddLicensedAudioVideoController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addLicensedAudioVideoRoot = null;
                try {
                    addLicensedAudioVideoRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addLicensedAudioVideoRoot != null;
                Scene secondScene = new Scene(addLicensedAudioVideoRoot);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");

                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addLicensedVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedVideo.fxml"));
                AddLicensedVideoController controller = new AddLicensedVideoController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addLicensedVideoRoot = null;
                try {
                    addLicensedVideoRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addLicensedVideoRoot != null;
                Scene secondScene = new Scene(addLicensedVideoRoot);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });
        deleteUpload.setOnAction(event -> {
            int selectedFile = uploadsListView.getSelectionModel().getSelectedIndex();
            System.out.println(uploadsListView.getItems().toString());
            String uploadString = uploadsListView.getItems().get(selectedFile);
            String address = String.valueOf(extractDataFromString.getAddress(uploadString));
            System.out.println(address);
            try {
                eventHandler.handle(guiEventFactory.stringEvent("Deletion", "address", address));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteProducer.setOnAction(actionEvent -> {
            int selectedProducer = producerListView.getSelectionModel().getSelectedIndex();
            System.out.println(producerListView.getItems().toString());
            String producerString = producerListView.getItems().get(selectedProducer);
            String producer = extractDataFromString.getProducerDisplayVersion(producerString);
            ProducerEvent event = guiEventFactory.producerEvent(producer, "remove");
            try {
                eventHandler.handle(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        incrementAccessCount.setOnAction(actionEvent -> {
            int selectedFile = uploadsListView.getSelectionModel().getSelectedIndex();
            System.out.println(uploadsListView.getItems().toString());
            String uploadString = uploadsListView.getItems().get(selectedFile);
            String address = String.valueOf(extractDataFromString.getAddress(uploadString));
            StringEvent stringEvent = guiEventFactory.stringEvent("Change", "address", address);
            try {
                eventHandler.handle(stringEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addProducerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addProducer.fxml"));
                AddProducerController controller = new AddProducerController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent addProducerRoot = null;
                try {
                    addProducerRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addProducerRoot != null;
                Scene secondScene = new Scene(addProducerRoot, 200, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        saveByAddress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/saveByAddress.fxml"));
                SaveByAddressController controller = new SaveByAddressController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent saveByAddressRoot = null;
                try {
                    saveByAddressRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert saveByAddressRoot != null;
                Scene secondScene = new Scene(saveByAddressRoot, 200, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        loadByAddress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/loadByAddress.fxml"));
                LoadByAddressController controller = new LoadByAddressController(guiEventFactory, eventHandler);
                // Set it in the FXMLLoader
                loader.setController(controller);
                Parent loadByAddressRoot = null;
                try {
                    loadByAddressRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadByAddressRoot != null;
                Scene secondScene = new Scene(loadByAddressRoot, 200, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        displayUploadsByType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/uploadsByType.fxml"));
                UploadsByTypeController controller = new UploadsByTypeController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent uploadsByType = null;
                try {
                    uploadsByType = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert uploadsByType != null;
                Scene secondScene = new Scene(uploadsByType, 200, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        displayTagsByType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/displayTags.fxml"));
                DisplayTagsController controller = new DisplayTagsController(guiEventFactory, eventHandler);
                loader.setController(controller);
                Parent tagsByType = null;
                try {
                    tagsByType = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert tagsByType != null;
                Scene secondScene = new Scene(tagsByType, 200, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        sortByAddress.setOnAction(actionEvent -> sortUploads.address(uploadsListView));
        sortByAccessCount.setOnAction(actionEvent -> sortUploads.accessCount(uploadsListView));
        sortByProducer.setOnAction(actionEvent -> sortUploads.producer(uploadsListView));
        saveJOS.setOnAction(actionEvent -> guiPersist.saveJOS());
        loadJOS.setOnAction(actionEvent -> guiPersist.loadJOS());


        HBox insert1 = threeButtonsHBox(addAudioButton, addAudioVideoButton, addInteractiveVideoButton);
        HBox insert2 = threeButtonsHBox(addLicensedAudioButton, addLicensedAudioVideoButton, addLicensedVideoButton);
        HBox sort = threeButtonsHBox(sortByAddress, sortByAccessCount, sortByProducer);
        HBox file = twoButtonsHBox(deleteUpload, incrementAccessCount);
        HBox producer = twoButtonsHBox(addProducerButton, deleteProducer);
        HBox JOS = twoButtonsHBox(saveJOS, loadJOS);
        HBox saveLoadByAddress = twoButtonsHBox(saveByAddress, loadByAddress);
        HBox display = twoButtonsHBox(displayUploadsByType, displayTagsByType);

        VBox vBox = new VBox(uploadsListView, producerListView, insert1, insert2, producer, file, sort, JOS, saveLoadByAddress, display, displayListView);
        Scene scene = new Scene(vBox, 220, 270);
        displayScreen.setScene(scene);
        displayScreen.show();


    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    public HBox threeButtonsHBox(Button one, Button two, Button three) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(one, two, three);
        return hbox;
    }

    public HBox twoButtonsHBox(Button one, Button two) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(one, two);
        return hbox;
    }
}

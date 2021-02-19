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
import javafx.geometry.Insets;
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
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.listener.PersistenceEventListener;
import mediaDB.domain_logic.listener.ProducerEventListener;
import mediaDB.domain_logic.listener.StringEventListener;
import mediaDB.domain_logic.listener.display.DisplayEventListener;
import mediaDB.domain_logic.listener.display.DisplayModeServer;
import mediaDB.domain_logic.listener.display.GenerateDisplayContent;
import mediaDB.domain_logic.listener.files.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import javafx.event.EventHandler;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.net.server.ServerEventBus;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InternalGUIMain extends Application {
    ListView<String> uploadsListView;
    ListView<String> producerListView;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;

    @Override
    public void start(Stage displayScreen) throws Exception {
        EventFactory eventFactory = new EventFactory();
        mediaDB.routing.EventHandler eventHandler = new mediaDB.routing.EventHandler();

        Thread.setDefaultUncaughtExceptionHandler((Thread thread,Throwable thrown)->{
            System.out.println(thread.getName()+":"+thrown.getMessage());
            thrown.printStackTrace();
        });
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        producerRepository = new ProducerRepository();
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
        Serialize serialize = new Serialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository);
        DeserializeDomainContent deserializeDomainContent = new DeserializeDomainContent();
        Deserialize deserialize = new Deserialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, deserializeDomainContent);
        RandomAccessFile randomAccessFile = new RandomAccessFile("RandomAccessFile", "rw");
        ArrayList<SavedMediaFile> savedMediaFiles = new ArrayList<>();
        RandomAccess randomAccess = new RandomAccess(randomAccessFile, savedMediaFiles);
        PersistenceEventListener persistenceEventListener = new PersistenceEventListener(mediaFileRepository, serialize, deserialize, deserializeDomainContent, randomAccess);

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
        serverEventBus.register(persistenceEventListener);
        eventHandler.add(serverEventBus);


        /* create list object */
        uploadsListView = new ListView<>();
        producerListView =new ListView<>();
        updateUploaderList();
        updateProducerList();

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

        SortUploads sortUploads = new SortUploads();

        addAudioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAudio.fxml"));

                // Create a controller instance
                AddAudioController controller = new AddAudioController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
                // Set it in the FXMLLoader
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

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addAudioVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAudioVideo.fxml"));

                // Create a controller instance
                AddAudioVideoController controller = new AddAudioVideoController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
                // Set it in the FXMLLoader
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

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

            addInteractiveVideoButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addInteractiveVideo.fxml"));

                  // Create a controller instance
                  AddInteractiveVideoController controller = new AddInteractiveVideoController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
                  // Set it in the FXMLLoader
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

                  // Specifies the modality for new window.
                  newWindow.initModality(Modality.WINDOW_MODAL);

                  // Specifies the owner Window (parent) for new window
                  newWindow.initOwner(displayScreen);
                  newWindow.show();
              }
          });

        addLicensedAudioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedAudio.fxml"));

                // Create a controller instance
                AddLicensedAudioController controller = new AddLicensedAudioController(eventFactory, eventHandler,  mediaFileRepository, uploadsListView);
                // Set it in the FXMLLoader
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

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        addLicensedAudioVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedAudioVideo.fxml"));

                // Create a controller instance
                AddLicensedAudioVideoController controller = new AddLicensedAudioVideoController(eventFactory, eventHandler,  mediaFileRepository, uploadsListView);
                // Set it in the FXMLLoader
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
                newWindow.setScene(secondScene);

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
            });

        addLicensedVideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedVideo.fxml"));

                // Create a controller instance
                AddLicensedVideoController controller = new AddLicensedVideoController(eventFactory, eventHandler,  mediaFileRepository, uploadsListView);
                // Set it in the FXMLLoader
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

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        deleteUpload.setOnAction(event -> {
            int selectedFile = uploadsListView.getSelectionModel().getSelectedIndex();
            System.out.println(uploadsListView.getItems().toString());
            String uploadString = uploadsListView.getItems().get(selectedFile);
            String[] elements = uploadString.split(",");
            String addressUnprocessed = elements[5];
            System.out.println(addressUnprocessed);
            String address = addressUnprocessed.substring(addressUnprocessed.indexOf("=") + 1);
            address = address.replaceAll("'", "");
            System.out.println(address);
            mediaFileRepository.delete(address);
        });

        deleteProducer.setOnAction(actionEvent -> {
            int selectedProducer = producerListView.getSelectionModel().getSelectedIndex();
            System.out.println(producerListView.getItems().toString());
            String producerString = producerListView.getItems().get(selectedProducer);
            String producer = sortUploads.getProducer(producerString);
            ProducerEvent event = eventFactory.producerEvent(producer, "remove");
            try {
                eventHandler.handle(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateProducerList();
        });

        incrementAccessCount.setOnAction(actionEvent -> {
            int selectedFile = uploadsListView.getSelectionModel().getSelectedIndex();
            System.out.println(uploadsListView.getItems().toString());
            String uploadString = uploadsListView.getItems().get(selectedFile);
            int address = sortUploads.getAddress(uploadString);
            StringEvent stringEvent = eventFactory.stringEvent("Change", "address", String.valueOf(address));
            try {
                eventHandler.handle(stringEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateUploaderList();
        });

        addProducerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addProducer.fxml"));

                // Create a controller instance
                AddProducerController controller = new AddProducerController(eventFactory, eventHandler, producerRepository, producerListView);
                // Set it in the FXMLLoader
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

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(displayScreen);
                newWindow.show();
            }
        });

        sortByAddress.setOnAction(actionEvent -> sortUploads.address(uploadsListView));
        sortByAccessCount.setOnAction(actionEvent -> sortUploads.accessCount(uploadsListView));
        sortByProducer.setOnAction(actionEvent -> sortUploads.producer(uploadsListView));

        HBox insert1 = threeButtonsHBox(addAudioButton, addAudioVideoButton, addInteractiveVideoButton);
        HBox insert2 = threeButtonsHBox(addLicensedAudioButton, addLicensedAudioVideoButton, addLicensedVideoButton);
        HBox sort = threeButtonsHBox(sortByAddress, sortByAccessCount, sortByProducer);

        /* creating vertical box to add item objects */
        VBox vBox = new VBox(uploadsListView, producerListView, insert1, insert2, deleteUpload, deleteProducer, addProducerButton, sort, incrementAccessCount);
        /* creating scene */
        Scene scene = new Scene(vBox, 220, 270);
        /* adding scene to stage */
        displayScreen.setScene(scene);
        /* display scene for shfxml/owing output */
        displayScreen.show();
    }
    public static void main(String[] args) {

        /*launch method calls internally start() method*/
        Application.launch(args);
    }

    private void updateUploaderList(){
        List<Uploadable> list = mediaFileRepository.read();
        uploadsListView.getItems().clear();
        for (Uploadable uploadable : list) {
            uploadsListView.getItems().add(uploadable.toString());
        }
    }

    private void updateProducerList(){
        ArrayList<Uploader> list = producerRepository.getProducers();
        producerListView.getItems().clear();
        for (Uploader uploader : list) {
            producerListView.getItems().add(uploader.toString());
        }
    }

    public HBox threeButtonsHBox(Button one, Button two, Button three) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(one, two, three);
        return hbox;
    }

}

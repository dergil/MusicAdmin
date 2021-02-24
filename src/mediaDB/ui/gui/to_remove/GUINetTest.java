package mediaDB.ui.gui.to_remove;

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
import mediaDB.net.client.ClientEventBus;
import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.gui.controllers.*;
import mediaDB.ui.gui.to_remove.GUIPersist;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GUINetTest extends Application {
    ListView<String> uploadsListView;
    ListView<String> producerListView;
    @Override
    public void start(Stage displayScreen) throws Exception {

        Thread.setDefaultUncaughtExceptionHandler((Thread thread, Throwable thrown) -> {
            System.out.println(thread.getName() + ":" + thrown.getMessage());
            thrown.printStackTrace();
        });
        try (Socket socket = new Socket("localhost", 7777);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
            ClientEventBus clientEventBus = new ClientEventBus(out);
            EventHandler eventHandler = new EventHandler();
            eventHandler.add(clientEventBus);
            String clientName = "client2";
            EventFactory eventFactory = new EventFactory(clientName);
            GUIPersist guiPersist = new GUIPersist(eventFactory, eventHandler);

//            uploadsListView = new ListView<>();
//            producerListView = new ListView<>();
//            updateUploaderList();
//            updateProducerList();
//
//            Button addAudioButton = new Button("Add Audio");
//            Button addAudioVideoButton = new Button("Add AudioVideo");
//            Button addInteractiveVideoButton = new Button("Add InteractiveVideo");
//            Button addLicensedAudioButton = new Button("Add LicensedAudio");
//            Button addLicensedAudioVideoButton = new Button("Add LicensedAudioVideo");
//            Button addLicensedVideoButton = new Button("Add LicensedVideo");
//            Button deleteUpload = new Button("Delete file");
//            Button deleteProducer = new Button("Delete producer");
            Button addProducerButton = new Button("Add Producer");
//            Button sortByAddress = new Button("Sort by address");
//            Button sortByAccessCount = new Button("Sort by access count");
//            Button sortByProducer = new Button("Sort by producer");
//            Button incrementAccessCount = new Button("Increment access count");
//            Button saveJOS = new Button("SaveJOS");
//            Button loadJOS = new Button("LoadJOS");
//            Button saveByAddress = new Button("Save by address");
//            Button loadByAddress = new Button("Load by address");
//
//            SortUploads sortUploads = new SortUploads();
//
//            addAudioButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAudio.fxml"));
//
//                    // Create a controller instance
//                    AddAudioController controller = new AddAudioController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent addAudioRoot = null;
//                    try {
//                        addAudioRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert addAudioRoot != null;
//                    Scene secondScene = new Scene(addAudioRoot);
//
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            addAudioVideoButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAudioVideo.fxml"));
//
//                    // Create a controller instance
//                    AddAudioVideoController controller = new AddAudioVideoController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent addAudioVideoRoot = null;
//                    try {
//                        addAudioVideoRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert addAudioVideoRoot != null;
//                    Scene secondScene = new Scene(addAudioVideoRoot);
//
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            addInteractiveVideoButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addInteractiveVideo.fxml"));
//
//                    // Create a controller instance
//                    AddInteractiveVideoController controller = new AddInteractiveVideoController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent addInteractiveVideoRoot = null;
//                    try {
//                        addInteractiveVideoRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert addInteractiveVideoRoot != null;
//                    Scene secondScene = new Scene(addInteractiveVideoRoot);
//
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            addLicensedAudioButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedAudio.fxml"));
//
//                    // Create a controller instance
//                    AddLicensedAudioController controller = new AddLicensedAudioController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent addLicensedAudioRoot = null;
//                    try {
//                        addLicensedAudioRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert addLicensedAudioRoot != null;
//                    Scene secondScene = new Scene(addLicensedAudioRoot);
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            addLicensedAudioVideoButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedAudioVideo.fxml"));
//
//                    // Create a controller instance
//                    AddLicensedAudioVideoController controller = new AddLicensedAudioVideoController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent addLicensedAudioVideoRoot = null;
//                    try {
//                        addLicensedAudioVideoRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert addLicensedAudioVideoRoot != null;
//                    Scene secondScene = new Scene(addLicensedAudioVideoRoot);
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            addLicensedVideoButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addLicensedVideo.fxml"));
//
//                    // Create a controller instance
//                    AddLicensedVideoController controller = new AddLicensedVideoController(eventFactory, eventHandler, mediaFileRepository, uploadsListView);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent addLicensedVideoRoot = null;
//                    try {
//                        addLicensedVideoRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert addLicensedVideoRoot != null;
//                    Scene secondScene = new Scene(addLicensedVideoRoot);
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            deleteUpload.setOnAction(event -> {
//                int selectedFile = uploadsListView.getSelectionModel().getSelectedIndex();
//                System.out.println(uploadsListView.getItems().toString());
//                String uploadString = uploadsListView.getItems().get(selectedFile);
//                String[] elements = uploadString.split(",");
//                String addressUnprocessed = elements[5];
//                System.out.println(addressUnprocessed);
//                String address = addressUnprocessed.substring(addressUnprocessed.indexOf("=") + 1);
//                address = address.replaceAll("'", "");
//                System.out.println(address);
//                mediaFileRepository.delete(address);
//            });
//
//            deleteProducer.setOnAction(actionEvent -> {
//                int selectedProducer = producerListView.getSelectionModel().getSelectedIndex();
//                System.out.println(producerListView.getItems().toString());
//                String producerString = producerListView.getItems().get(selectedProducer);
//                String producer = sortUploads.getProducer(producerString);
//                ProducerEvent event = eventFactory.producerEvent(producer, "remove");
//                try {
//                    eventHandler.handle(event);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                updateProducerList();
//            });
//
//            incrementAccessCount.setOnAction(actionEvent -> {
//                int selectedFile = uploadsListView.getSelectionModel().getSelectedIndex();
//                System.out.println(uploadsListView.getItems().toString());
//                String uploadString = uploadsListView.getItems().get(selectedFile);
//                int address = sortUploads.getAddress(uploadString);
//                StringEvent stringEvent = eventFactory.stringEvent("Change", "address", String.valueOf(address));
//                try {
//                    eventHandler.handle(stringEvent);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                updateUploaderList();
//            });
//
            addProducerButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addProducer.fxml"));

                    // Create a controller instance
//                    AddProducerController controller = new AddProducerController(eventFactory, eventHandler, producerRepository, producerListView);
                    AddProducerController controller = new AddProducerController(eventFactory, eventHandler);
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
//
//            saveByAddress.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/saveByAddress.fxml"));
//
//                    // Create a controller instance
//                    SaveByAddressController controller = new SaveByAddressController(eventFactory, eventHandler);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent saveByAddressRoot = null;
//                    try {
//                        saveByAddressRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert saveByAddressRoot != null;
//                    Scene secondScene = new Scene(saveByAddressRoot, 200, 100);
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            loadByAddress.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/loadByAddress.fxml"));
//
//                    // Create a controller instance
//                    LoadByAddressController controller = new LoadByAddressController(eventFactory, eventHandler);
//                    // Set it in the FXMLLoader
//                    loader.setController(controller);
//                    Parent loadByAddressRoot = null;
//                    try {
//                        loadByAddressRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    assert loadByAddressRoot != null;
//                    Scene secondScene = new Scene(loadByAddressRoot, 200, 100);
//                    Stage newWindow = new Stage();
//                    newWindow.setTitle("Second Stage");
//                    newWindow.setScene(secondScene);
//
//                    // Specifies the modality for new window.
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//
//                    // Specifies the owner Window (parent) for new window
//                    newWindow.initOwner(displayScreen);
//                    newWindow.show();
//                }
//            });
//
//            sortByAddress.setOnAction(actionEvent -> sortUploads.address(uploadsListView));
//            sortByAccessCount.setOnAction(actionEvent -> sortUploads.accessCount(uploadsListView));
//            sortByProducer.setOnAction(actionEvent -> sortUploads.producer(uploadsListView));
//            saveJOS.setOnAction(actionEvent -> guiPersist.saveJOS());
//            loadJOS.setOnAction(actionEvent -> guiPersist.loadJOS());
//
//
//            HBox insert1 = threeButtonsHBox(addAudioButton, addAudioVideoButton, addInteractiveVideoButton);
//            HBox insert2 = threeButtonsHBox(addLicensedAudioButton, addLicensedAudioVideoButton, addLicensedVideoButton);
//            HBox sort = threeButtonsHBox(sortByAddress, sortByAccessCount, sortByProducer);
//            HBox file = twoButtonsHBox(deleteUpload, incrementAccessCount);
//            HBox producer = twoButtonsHBox(addProducerButton, deleteProducer);
//            HBox JOS = twoButtonsHBox(saveJOS, loadJOS);
//            HBox saveLoadByAddress = twoButtonsHBox(saveByAddress, loadByAddress);
//
            /* creating vertical box to add item objects */
//            VBox vBox = new VBox(uploadsListView, producerListView, insert1, insert2, producer, file, sort, JOS, saveLoadByAddress);
            HBox hBox = new HBox(addProducerButton);
            VBox vBox = new VBox(hBox);
            /* creating scene */
            Scene scene = new Scene(vBox, 220, 270);
            /* adding scene to stage */
            displayScreen.setScene(scene);
            /* display scene for shfxml/owing output */
            displayScreen.show();


//            try {
//                while (true) {
//                    ServerResponseEvent serverResponseEvent = (ServerResponseEvent) in.readObject();
//                    if (serverResponseEvent.getSender().equals(clientName))
//                        serverResponseEventListener.onMediaEvent(serverResponseEvent);
//                    System.out.println(serverResponseEvent.getEventName());
//                }
//            }
//            catch (EOFException | ClassNotFoundException e ) {
//                e.printStackTrace();
//            }

        }


    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void updateUploaderList(){
//        List<Uploadable> list = mediaFileRepository.read();
//        uploadsListView.getItems().clear();
//        for (Uploadable uploadable : list) {
//            uploadsListView.getItems().add(uploadable.toString());
//        }
    }

    private void updateProducerList(){
//        ArrayList<Uploader> list = producerRepository.getProducers();
//        producerListView.getItems().clear();
//        for (Uploader uploader : list) {
//            producerListView.getItems().add(uploader.toString());
//        }
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

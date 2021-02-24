//package mediaDB.ui.gui;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import mediaDB.domain_logic.AddressRepository;
//import mediaDB.domain_logic.MediaFileFactory;
//import mediaDB.domain_logic.MediaFileRepository;
//import mediaDB.domain_logic.file_interfaces.Uploadable;
//import mediaDB.domain_logic.observables.SizeObservable;
//import mediaDB.domain_logic.observables.TagObservable;
//import mediaDB.domain_logic.producer.ProducerRepository;
//import mediaDB.net.server.ToClientMessenger;
//import mediaDB.simulation.RandomMediadfileInstances;
//import mediaDB.ui.cli.EventFactory;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class TestClass extends Application {
//    @Override
//    public void start(Stage stage) {
//        ToClientMessenger toClient= new ToClientMessenger();
//        SizeObservable sizeObservable = new SizeObservable();
//        TagObservable tagObservable = new TagObservable();
//        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
//        ProducerRepository producerRepository = new ProducerRepository();
//        AddressRepository addressRepository = new AddressRepository();
//        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);
//        EventFactory eventFactory = new EventFactory();
//        mediaDB.routing.EventHandler eventHandler = new mediaDB.routing.EventHandler();
//
//        RandomMediadfileInstances randomMediadfileInstances = new RandomMediadfileInstances();
//        mediaFileRepository.create(randomMediadfileInstances.randomInteractiveVideoFile());
//        mediaFileRepository.create(randomMediadfileInstances.randomLicensedAudioVideoFile());
//
//        ListView<String> listView = new ListView<>();
//        updateList(mediaFileRepository, listView);
//
//        Button addInteractiveVideoButton = new Button("Add InteractiveVideo");
//        addInteractiveVideoButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addInteractiveVideo.fxml"));
//
//                // Create a controller instance
//                AddInteractiveVideoController controller = new AddInteractiveVideoController(eventFactory, eventHandler, mediaFileRepository, listView);
//                // Set it in the FXMLLoader
//                loader.setController(controller);
//                Parent addInteractiveVideoRoot = null;
//                try {
//                    addInteractiveVideoRoot = loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                assert addInteractiveVideoRoot != null;
//                Scene secondScene = new Scene(addInteractiveVideoRoot);
//
//                Stage newWindow = new Stage();
//                newWindow.setTitle("Second Stage");
//                newWindow.setScene(secondScene);
//
//                // Specifies the modality for new window.
//                newWindow.initModality(Modality.WINDOW_MODAL);
//
//                // Specifies the owner Window (parent) for new window
//                newWindow.initOwner(stage);
//                newWindow.show();
//            }
//        });
//
//        VBox vBox = new VBox(listView, addInteractiveVideoButton);
//        //Instantiating the Scene class
//        Scene scene = new Scene(vBox, 595, 300, Color.BEIGE);
//        //Setting the scene to the Stage
//        stage.setScene(scene);
//        //Setting Title to the stage
//        stage.setTitle("JavFX Basic Application");
//        //Displaying the contents of the stage
//        stage.show();
//    }
//    public static void main(String[] args) {
////inside JVM launch method calls start method
//        Application.launch(TestClass.class, args);
//    }
//
//    private void updateList(MediaFileRepository mediaFileRepository, ListView<String> listViewReference){
//        List<Uploadable> list = mediaFileRepository.read();
//        listViewReference.getItems().clear();
//        for (Uploadable uploadable : list) {
//            listViewReference.getItems().add(uploadable.toString());
//        }
//    }
//
//}

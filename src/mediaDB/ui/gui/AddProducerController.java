package mediaDB.ui.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.ui.cli.EventFactory;

import java.util.ArrayList;

public class AddProducerController {
    public TextField nameTextField;
    String name;

    EventFactory eventFactory;
    EventHandler eventHandler;
    ProducerRepository producerRepository;
    ListView<String> uploaderListView;

    public AddProducerController(EventFactory eventFactory, EventHandler eventHandler, ProducerRepository producerRepository, ListView<String> uploaderListView) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
        this.producerRepository = producerRepository;
        this.uploaderListView = uploaderListView;
    }

    public void onAtionEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        try {
            this.name = nameTextField.getText();
            ProducerEvent event = eventFactory.producerEvent(name, "add");
            eventHandler.handle(event);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        updateProducerList();
        stage.close();
    }

    private void updateProducerList(){
        ArrayList<Uploader> list = producerRepository.getProducers();
        uploaderListView.getItems().clear();
        for (Uploader uploader : list) {
            uploaderListView.getItems().add(uploader.toString());
        }
    }
}

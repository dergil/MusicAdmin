package mediaDB.ui.gui.controllers;

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

//public class AddProducerControllerNet {
//    public TextField nameTextField;
//    String name;
//
//    EventFactory eventFactory;
//    EventHandler eventHandler;
//
//    public AddProducerControllerNet(EventFactory eventFactory, EventHandler eventHandler) {
//        this.eventFactory = eventFactory;
//        this.eventHandler = eventHandler;
//    }
//
//    public void onAtionEvent(ActionEvent actionEvent) {
//        Stage stage = (Stage) nameTextField.getScene().getWindow();
//        try {
//            this.name = nameTextField.getText();
//            ProducerEvent event = eventFactory.producerEvent(name, "add");
//            eventHandler.handle(event);
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//        stage.close();
//    }
//}

package mediaDB.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.ui.cli.EventFactory;

public class SaveByAddressController {
    public TextField addressTextField;
    String address;

    EventFactory eventFactory;
    EventHandler eventHandler;

    public SaveByAddressController(EventFactory eventFactory, EventHandler eventHandler) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
    }

    public void onActionEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) addressTextField.getScene().getWindow();
        try {
            this.address = addressTextField.getText();
            PersistenceEvent event = eventFactory.persistenceEvent("save", address);
            eventHandler.handle(event);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        stage.close();
    }
}

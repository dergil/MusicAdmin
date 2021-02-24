package mediaDB.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.ui.cli.EventFactory;

public class UploadsByTypeController {
    public TextField nameTextField;
    String type = null;

    EventFactory eventFactory;
    EventHandler eventHandler;

    public UploadsByTypeController(EventFactory eventFactory, EventHandler eventHandler) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
    }

    public void onActionEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        try {
            this.type = nameTextField.getText();
            DisplayEvent event = eventFactory.displayEvent("content", type);
            eventHandler.handle(event);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        stage.close();
    }
}

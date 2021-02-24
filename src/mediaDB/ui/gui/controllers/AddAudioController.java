package mediaDB.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.files.AudioEvent;
import mediaDB.ui.cli.EventFactory;

import java.util.List;

public class AddAudioController {
    public TextField samplingRateTextField;
    public TextField encodingTextField;
    public TextField bitrateTextField;
    public TextField lengthTextField;
    public TextField tagsTextField;
    public TextField uploaderTextField;


    EventFactory eventFactory;
    EventHandler eventHandler;

    public AddAudioController(EventFactory eventFactory, EventHandler eventHandler) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
    }

    public void onActionEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) samplingRateTextField.getScene().getWindow();
        try {
            String[] input = new String[7];
            input[0] = MediaTypes.AUDIO.toString();
            input[1] = uploaderTextField.getText();
            input[2] = tagsTextField.getText();
            input[3] = bitrateTextField.getText();
            input[4] = lengthTextField.getText();
            input[5] = encodingTextField.getText();
            input[6] = samplingRateTextField.getText();
            AudioEvent event = eventFactory.audioEvent(input);
            eventHandler.handle(event);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        stage.close();
    }
}

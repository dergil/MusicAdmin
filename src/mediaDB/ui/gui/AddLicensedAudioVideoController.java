package mediaDB.ui.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.files.LicensedAudioVideoEvent;
import mediaDB.ui.cli.EventFactory;

import java.util.List;

public class AddLicensedAudioVideoController {
    public TextField samplingRateTextField;
    public TextField widthTextField;
    public TextField heightTextField;
    public TextField encodingTextField;
    public TextField bitrateTextField;
    public TextField lengthTextField;
    public TextField tagsTextField;
    public TextField uploaderTextField;
    public TextField holderTextField;


    EventFactory eventFactory;
    EventHandler eventHandler;
    MediaFileRepository mediaFileRepository;
    ListView<String> listView;

    public AddLicensedAudioVideoController(EventFactory eventFactory, EventHandler eventHandler, MediaFileRepository mediaFileRepository, ListView<String> listView) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
        this.mediaFileRepository = mediaFileRepository;
        this.listView = listView;
    }

    public void onActionEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) samplingRateTextField.getScene().getWindow();
        try {
            String[] input = new String[10];
            input[0] = MediaTypes.LICENSEDAUDIOVIDEO.toString();
            input[1] = uploaderTextField.getText();
            input[2] = tagsTextField.getText();
            input[3] = bitrateTextField.getText();
            input[4] = lengthTextField.getText();
            input[5] = encodingTextField.getText();
            input[6] = heightTextField.getText();
            input[7] = widthTextField.getText();
            input[8] = samplingRateTextField.getText();
            input[9] = holderTextField.getText();
            LicensedAudioVideoEvent event = eventFactory.licensedAudioVideoEvent(input);
            eventHandler.handle(event);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        updateList(mediaFileRepository, listView);
        stage.close();
    }

    private void updateList(MediaFileRepository mediaFileRepository, ListView<String> listView){
        List<Uploadable> list = mediaFileRepository.read();
        listView.getItems().clear();
        for (Uploadable uploadable : list) {
            listView.getItems().add(uploadable.toString());
        }
    }
}

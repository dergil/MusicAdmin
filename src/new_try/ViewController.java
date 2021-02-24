package new_try;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class ViewController {
    @FXML private TextField input;
    @FXML private Label text;
    SimpleModel model;

    public ViewController(SimpleModel model) {
        this.model = model;
    }

    @FXML private void initialize() { this.updateView(); }

    private void updateView(){
        this.text.setText(this.model.getText());
    }

    public void buttonClick(javafx.event.ActionEvent actionEvent) {
        this.model.setText(input.getText());
        updateView();
    }
}

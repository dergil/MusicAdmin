package mediaDB.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.TextField;
import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTagsControllerTest {
    EventFactory eventFactory;
    EventHandler eventHandler;
    DisplayTagsController displayTagsController;

    @BeforeEach
    void setUp() {
        eventFactory = mock(EventFactory.class);
        eventHandler = mock(EventHandler.class);
        displayTagsController = new DisplayTagsController(eventFactory, eventHandler);
        displayTagsController.nameTextField = new TextField("test");
    }

    @Test
    void onActionEvent() {
        ActionEvent e=new ActionEvent(null,null);
        displayTagsController.onActionEvent(e);
        verify(eventFactory).displayEvent("tag", "test");
    }
}
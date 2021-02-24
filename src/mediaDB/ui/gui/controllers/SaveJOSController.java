package mediaDB.ui.gui.controllers;

import mediaDB.routing.EventHandler;
import mediaDB.ui.cli.EventFactory;

public class SaveJOSController {
    EventFactory eventFactory;
    EventHandler eventHandler;

    public SaveJOSController(EventFactory eventFactory, EventHandler eventHandler) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
    }


}

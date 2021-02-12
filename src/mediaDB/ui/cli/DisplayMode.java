package mediaDB.ui.cli;

import mediaDB.domain_logic.MediaTypes;
import mediaDB.routing.DisplayEvent;
import mediaDB.routing.EventHandler;

import java.io.IOException;

public class DisplayMode {
    EventHandlers eventHandlers;
//    TODO: input verifikation (2 args etc.)
    String mediaTypes = MediaTypes.ALL_TYPES.toString();
    String input;
    String[] splitInput = null;

    public DisplayMode(EventHandlers eventHandlers) {
        this.eventHandlers = eventHandlers;
    }



    public void start() throws IOException {
        System.out.println("uploader");
        System.out.println("content [[Typ]]");
        System.out.println("tag [enthalten (i) / nicht enthalten (e)]");
        System.out.println("Zurück: 0");
        do {
            getAndVerifyInput();
        }
            while (!input.equals("0")) ;
    }

    public void getAndVerifyInput() throws IOException {
        input = Console.prompt("Display mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("content")) {
            contentDisplay();
            return;
        }
        if (splitInput[0].equals("uploader")) {
            uploaderDisplay();
            return;
        }
        if (splitInput[0].equals("tag")) {
            tagDisplay();
        }
        else System.out.println("Syntax error.");
    }

    private void contentDisplay() throws IOException {
        DisplayEvent event;
        if (splitInput.length > 1 && mediaTypes.contains(splitInput[1])){
            event = new DisplayEvent(this, "content", splitInput[1]);
        } else {
            event  = new DisplayEvent(this, "content", null);

        }
        eventHandlers.getDisplayEventEventHandler().handle(event);
    }

    private void uploaderDisplay() throws IOException {
        DisplayEvent event = new DisplayEvent(this, "uploader", null);
        eventHandlers.getDisplayEventEventHandler().handle(event);
    }

    private void tagDisplay() throws IOException {
        DisplayEvent event;
        if (splitInput.length > 1 && (splitInput[1].equals("i") || splitInput[1].equals("e"))){
            event = new DisplayEvent(this, "tag", splitInput[1]);
            eventHandlers.getDisplayEventEventHandler().handle(event);
        } else System.out.println("Syntax error.");

        }
}

package mediaDB.ui.cli.modes;

import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;

public class DisplayMode implements CLIMode {
    EventListener<NetworkEvent> serverEventBus;
    EventHandler eventHandler;
    EventFactory eventFactory;
    String input;
    String[] splitInput = null;

    public DisplayMode(EventHandler eventHandler, EventFactory eventFactory) {
        this.eventHandler = eventHandler;
        this.eventFactory = eventFactory;
    }

    public boolean start() throws IOException {
        System.out.println("uploader");
        System.out.println("content [[Typ]]");
        System.out.println("tag [enthalten (i) / nicht enthalten (e)]");
        System.out.println("Zurück: 0");
            return getAndVerifyInput();
    }

    private boolean getAndVerifyInput() throws IOException {
        input = Console.prompt("Display mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("content")) {
            contentDisplay();
            return true;
        }
        if (splitInput[0].equals("uploader")) {
            uploaderDisplay();
            return true;
        }
        if (splitInput[0].equals("tag")) {
            tagDisplay();
            return true;
        }
        else {
            System.out.println("Syntax error.");
            return false;
        }
    }

    private void contentDisplay() throws IOException {
        DisplayEvent event;
        if (splitInput.length > 1 && validMediaType(splitInput[1])){
            event = eventFactory.displayEvent("content", splitInput[1]);
        } else {
            event  = eventFactory.displayEvent( "content", null);

        }
        eventHandler.handle(event);
    }

    private void uploaderDisplay() throws IOException {
        DisplayEvent event = eventFactory.displayEvent( "uploader", null);
        eventHandler.handle(event);
    }

    private void tagDisplay() throws IOException {
        DisplayEvent event;
        if (splitInput.length > 1 && (splitInput[1].equals("i") || splitInput[1].equals("e"))){
            event = eventFactory.displayEvent( "tag", splitInput[1]);
            eventHandler.handle(event);
        } else {
            System.out.println("Syntax error.");
            start();
        }
    }

    private boolean validMediaType(String input){
        return input.equalsIgnoreCase("Audio") || input.equalsIgnoreCase("AudioVideo") ||
                input.equalsIgnoreCase("InteractiveVideo") || input.equalsIgnoreCase("LicensedAudio") ||
                input.equalsIgnoreCase("LicensedAudioVideo") || input.equalsIgnoreCase("LicensedVideo");
    }
}

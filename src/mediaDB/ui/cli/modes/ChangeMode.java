package mediaDB.ui.cli.modes;

import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.Console;

import java.io.IOException;

public class ChangeMode {
    String[] splitInput = null;
    String input = null;
    EventListener<NetworkEvent> serverEventBus;

    public ChangeMode(EventListener<NetworkEvent> serverEventBus) {
        this.serverEventBus = serverEventBus;
    }

    public void start() throws IOException {
        System.out.println("[Produzentenname]");
        System.out.println("Zurück: 0");
//        do {
            getAndVerifyInput();
//        } while (!input.equals("0"));
    }

    private void getAndVerifyInput() throws IOException {
        input = Console.prompt("Change mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("0"))
            return;
        if (checkIfNumeric(splitInput[0]))
            address();
    }

    //    TODO: testen
    private boolean checkIfNumeric(String input){
        return input.matches("[0-9]+");
    }

    private void address() throws IOException {
        StringEvent stringEvent = new StringEvent(this, "Change", "address", splitInput[0]);
        serverEventBus.onMediaEvent(stringEvent);
    }
}

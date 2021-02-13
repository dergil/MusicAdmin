package mediaDB.ui.cli.modes;

import mediaDB.net.server.ServerEventBus;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.StringEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventHandlers;

import java.io.IOException;

public class DeletionMode {
    EventListener<NetworkEvent> serverEventBus;

    String[] splitInput = null;
    String input = null;

    public DeletionMode(EventListener<NetworkEvent> serverEventBus) {
        this.serverEventBus = serverEventBus;
    }

    public void start() throws IOException {
        System.out.println("[Produzentenname]");
        System.out.println("[Abrufadresse]");
        System.out.println("Zurück: 0");
//        do {
            getAndVerifyInput();
//        } while (!input.equals("0"));
    }

    private void getAndVerifyInput() throws IOException {
        input = Console.prompt("Deletion mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("0"))
            return;
        if (checkIfNumeric(splitInput[0]))
            address();
        else producer();

    }

    //    TODO: testen
    private boolean checkIfNumeric(String input){
        return input.matches("[0-9]+");
    }

    private void address() throws IOException {
        StringEvent stringEvent = new StringEvent(this, "Deletion", "address", splitInput[0]);
        serverEventBus.onMediaEvent(stringEvent);
    }

    private void producer() throws IOException {
        StringEvent stringEvent = new StringEvent(this, "Deletion", "producer", splitInput[0]);
        serverEventBus.onMediaEvent(stringEvent);
    }
}

package mediaDB.ui.cli.modes;

import mediaDB.routing.EventHandler;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;

public class DeletionMode implements CLIMode {
    EventListener<NetworkEvent> serverEventBus;
    EventHandler eventHandler;
    EventFactory eventFactory;

    String[] splitInput = null;
    String input = null;

    public DeletionMode(EventHandler eventHandler, EventFactory eventFactory) {
        this.eventHandler = eventHandler;
        this.eventFactory = eventFactory;
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

//    TODO: mit producerevent ersetzen
    private void address() throws IOException {
        StringEvent stringEvent = eventFactory.stringEvent("Deletion", "address", splitInput[0]);
        eventHandler.handle(stringEvent);
    }

    private void producer() throws IOException {
        StringEvent stringEvent = eventFactory.stringEvent("Deletion", "producer", splitInput[0]);
        eventHandler.handle(stringEvent);
    }
}

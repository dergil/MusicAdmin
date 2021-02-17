package mediaDB.ui.cli.modes;

import mediaDB.routing.EventHandler;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;

public class ChangeMode implements CLIMode{
    String[] splitInput = null;
    String input = null;
    EventListener<NetworkEvent> eventBus;
    EventHandler eventHandler;
    EventFactory eventFactory;

//    public ChangeMode(EventListener<NetworkEvent> eventBus, EventFactory eventFactory) {
//        this.eventBus = eventBus;
//        this.eventFactory = eventFactory;
//    }


    public ChangeMode(EventHandler eventHandler, EventFactory eventFactory) {
        this.eventHandler = eventHandler;
        this.eventFactory = eventFactory;
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
        StringEvent stringEvent = eventFactory.stringEvent("Change", "address", splitInput[0]);
        eventHandler.handle(stringEvent);
    }
}

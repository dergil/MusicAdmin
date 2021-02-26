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

    public boolean start() throws IOException {
        System.out.println("[Abrufadresse]");
        System.out.println("Zurück: 0");
            return getAndVerifyInput();
    }

    private boolean getAndVerifyInput() throws IOException {
        input = Console.prompt("Change mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("0"))
            return false;
        if (checkIfNumeric(splitInput[0])){
            address();
            return true;
        }
        return false;
    }

    private boolean checkIfNumeric(String input){
        return input.matches("[0-9]+");
    }

    private void address() throws IOException {
        StringEvent stringEvent = eventFactory.stringEvent("Change", "address", splitInput[0]);
        eventHandler.handle(stringEvent);
    }
}

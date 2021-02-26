package mediaDB.ui.cli.modes;

import mediaDB.routing.EventHandler;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;

public class PersistenceMode implements CLIMode {
    EventListener<NetworkEvent> serverEventBus;
    EventHandler eventHandler;
    EventFactory eventFactory;
    String input;
    String[] splitInput = null;

    public PersistenceMode(EventHandler eventHandler, EventFactory eventFactory) {
        this.eventHandler = eventHandler;
        this.eventFactory = eventFactory;
    }

    public boolean start() throws IOException {
        System.out.println(
                "saveJOS\n" +
                "loadJOS\n" +
                "save [Abrufadresse]\n" +
                "load [Abrufadresse]\n" +
                "Zurück: 0 \n");
        return getAndVerifyInput();
    }

    private boolean getAndVerifyInput() throws IOException {
        input = Console.prompt("Persistence mode ");
        splitInput = input.split(" ");
        String command = splitInput[0];
        if (command.equals("saveJOS") || command.equals("loadJOS") || command.equals("save") || command.equals("load")){
            PersistenceEvent persistenceEvent = eventFactory.persistenceEvent(command, option(splitInput));
            eventHandler.handle(persistenceEvent);
            return true;
        }
        else {
            System.out.println("Syntax error");
            return false;
        }
    }

    private String option(String[] splitInput){
        if (splitInput.length > 1)
            return splitInput[1];
        return null;
    }
}




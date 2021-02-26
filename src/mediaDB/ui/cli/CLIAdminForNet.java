package mediaDB.ui.cli;

import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.modes.*;

import java.io.IOException;

public class CLIAdminForNet {
    InsertMode insertMode;
    DisplayMode displayMode;
    DeletionMode deleteMode;
    ChangeMode changeMode;
    PersistenceMode persistenceMode;
    EventHandler eventHandler;
    EventFactory eventFactory;

    public CLIAdminForNet(InsertMode insertMode, DisplayMode displayMode, DeletionMode deleteMode, ChangeMode changeMode,
                          PersistenceMode persistenceMode, EventHandler eventHandler, EventFactory eventFactory) {
        this.insertMode = insertMode;
        this.displayMode = displayMode;
        this.deleteMode = deleteMode;
        this.changeMode = changeMode;
        this.persistenceMode = persistenceMode;
        this.eventHandler = eventHandler;
        this.eventFactory = eventFactory;
    }

    public boolean start() throws IOException {
        String input;
        while (true){
            input = Console.getMode();
            String firstChar = String.valueOf(input.charAt(0));
            if (firstChar.equals(":")){
                return modes(input);
//                if (modes(input))
//                    return;
            }
            else if (firstChar.equals("0")){
                eventHandler.handle(eventFactory.exitEvent());
                System.exit(0);
            }
            else System.out.println("Unknown command");
        }
    }

    private boolean modes(String mode) throws IOException {
        switch (mode){
            case ":c":
                return insertMode.start();
            case ":d":
                return deleteMode.start();
            case ":r":
                return displayMode.start();
            case ":u":
                return changeMode.start();
            case ":p":
                return persistenceMode.start();
            default:
                System.out.println("Unknown mode.");
                return false;
        }
    }

}

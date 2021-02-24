package mediaDB.ui.cli;

import mediaDB.ui.cli.modes.*;

import java.io.IOException;

public class CLIAdmin {
    InsertMode insertMode;
    DisplayMode displayMode;
    DeletionMode deleteMode;
    ChangeMode changeMode;
    ConfigMode configMode;
    PersistenceMode persistenceMode;

    public CLIAdmin(InsertMode insertMode, DisplayMode displayMode, DeletionMode deleteMode, ChangeMode changeMode, ConfigMode configMode, PersistenceMode persistenceMode) {
        this.insertMode = insertMode;
        this.displayMode = displayMode;
        this.deleteMode = deleteMode;
        this.changeMode = changeMode;
        this.configMode = configMode;
        this.persistenceMode = persistenceMode;
    }

    public void start() throws IOException {
        Console.greeting();
        String input;
        while (true){
            input = Console.getMode();
            String firstChar = String.valueOf(input.charAt(0));
            if (firstChar.equals(":"))
                modes(input);
            else if (firstChar.equals("0"))
                System.exit(0);
            else System.out.println("Unknown command");
        }
    }

    private void modes(String mode) throws IOException {
        switch (mode){
            case ":c":
                insertMode.start();
                break;
            case ":d":
                deleteMode.start();
                break;
            case ":r":
                displayMode.start();
                break;
            case ":u":
                changeMode.start();
                break;
            case ":p":
                persistenceMode.start();
                break;
            case ":config":
                configMode.start();
                break;
            default:
                System.out.println("Unknown mode.");
                break;
        }
    }

}


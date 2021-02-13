package mediaDB.ui.cli;

import mediaDB.ui.cli.modes.*;

import java.io.IOException;

public class CLIAdminForNet {
    InsertMode insertMode;
    DisplayMode displayMode;
    DeletionMode deleteMode;
    ChangeMode changeMode;


    public CLIAdminForNet(InsertMode insertMode, DisplayMode displayMode, DeletionMode deleteMode, ChangeMode changeMode) {
        this.insertMode = insertMode;
        this.displayMode = displayMode;
        this.deleteMode = deleteMode;
        this.changeMode = changeMode;
    }

    public void start() throws IOException {
//        Console.greeting();
        String input;
//        while (true){
            input = Console.getMode();
            String firstChar = String.valueOf(input.charAt(0));
            if (firstChar.equals(":"))
                modes(input);
            else if (firstChar.equals("0"))
                System.exit(0);
            else System.out.println("Unknown command");
//        }
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
//            case ":p":
//                Console.persistenceMode();
//                break;
            default:
                System.out.println("Unknown mode.");
                break;
        }
    }

}

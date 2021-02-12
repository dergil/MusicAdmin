package mediaDB.ui.cli;

import mediaDB.routing.StringEvent;

import java.io.IOException;

public class DeletionMode {
    EventHandlers eventHandlers;

    String[] splitInput = null;
    String input = null;

    public DeletionMode(EventHandlers eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    protected void start() throws IOException {
        System.out.println("[Produzentenname]");
        System.out.println("[Abrufadresse]");
        System.out.println("Zurück: 0");
        do {
            getAndVerifyInput();
        } while (!input.equals("0"));
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
        eventHandlers.getStringEventEventHandler().handle(stringEvent);
    }

    private void producer() throws IOException {
        StringEvent stringEvent = new StringEvent(this, "Deletion", "producer", splitInput[0]);
        eventHandlers.getStringEventEventHandler().handle(stringEvent);
    }
}

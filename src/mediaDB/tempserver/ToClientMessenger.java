package mediaDB.tempserver;

//import mediaDB.net.server.ServerEventHandler;
//
//import java.io.IOException;

import mediaDB.routing.ServerResponseEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ToClientMessenger {
    ObjectOutputStream out;

    public void producerNotListet() throws IOException {
        sendMessage("Produzent der Datei nicht gelistet.");
    }

    public void fileNotListet() throws IOException {
        sendMessage("Datei nicht gelistet.");
    }


    public void list(String list) throws IOException {
        sendMessage(list);
    }

    public void sendString(String response) throws IOException {
        sendMessage(response);
    }

    private void sendMessage(String message) throws IOException {
        if (out == null){
            System.out.println(message);
        }
        else  out.writeObject(new ServerResponseEvent(this, message));
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
}

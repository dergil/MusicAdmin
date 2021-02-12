package mediaDB.tempserver;

//import mediaDB.net.server.ServerEventHandler;
//
//import java.io.IOException;

import java.io.IOException;

public class ServerToClientMessenger {
//    ServerEventHandler serverEventHandler;
//
//    public ServerToClientMessaging(ServerEventHandler serverEventHandler) {
//        this.serverEventHandler = serverEventHandler;
//    }

    public void producerNotListet() throws IOException {
//        serverEventHandler.sendResponse("Produzent der Datei nicht gelistet.");
        System.out.println("Produzent der Datei nicht gelistet.");
    }

    public void fileNotListet() throws IOException {
//        serverEventHandler.sendResponse("Datei nicht gelistet.");
        System.out.println("Datei nicht gelistet.");
    }

    public void list(String list) throws IOException {
//        serverEventHandler.sendResponse(list);
        System.out.println(list);
    }

    public void sendString(String response) throws IOException {
//        serverEventHandler.sendResponse(response);
        System.out.println(response);
    }
}

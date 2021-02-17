package mediaDB.net.client;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.CLIAdminForNet;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 7777);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){

            ClientEventBus clientEventBus = new ClientEventBus(out);
            EventHandler eventHandler = new EventHandler();
            eventHandler.add(clientEventBus);
            EventFactory eventFactory = new EventFactory();
            InsertMode insertMode = new InsertMode(eventHandler, eventFactory);
            DisplayMode displayMode = new DisplayMode(eventHandler, eventFactory);
            DeletionMode deletionMode = new DeletionMode(eventHandler, eventFactory);
            ChangeMode changeMode = new ChangeMode(eventHandler, eventFactory);
            PersistenceMode persistenceMode = new PersistenceMode(eventHandler, eventFactory);
            CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode, persistenceMode);

            String marker = "client1";
            ServerResponseEvent markerEvent = eventFactory.serverResponseEvent(marker);
            out.writeObject(markerEvent);

            cliAdmin.start();

            while (true){
                try {
                    while (true) {
                        ServerResponseEvent serverResponseEvent = (ServerResponseEvent) in.readObject();
                        String response = serverResponseEvent.getEventName();
                        if (response.equals(marker))
                            break;
                        System.out.println(serverResponseEvent.getEventName());
                    }
                }
                catch (EOFException | ClassNotFoundException e ) {
                    e.printStackTrace();
                }
                cliAdmin.start();
            }
        }

    }
}

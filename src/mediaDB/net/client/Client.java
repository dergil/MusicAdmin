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
            String clientName = "client1";
            EventFactory eventFactory = new EventFactory(clientName);
            InsertMode insertMode = new InsertMode(eventHandler, eventFactory);
            DisplayMode displayMode = new DisplayMode(eventHandler, eventFactory);
            DeletionMode deletionMode = new DeletionMode(eventHandler, eventFactory);
            ChangeMode changeMode = new ChangeMode(eventHandler, eventFactory);
            PersistenceMode persistenceMode = new PersistenceMode(eventHandler, eventFactory);
            CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode, persistenceMode);


            cliAdmin.start();

            while (true){
                try {
                    while (true) {
                        ServerResponseEvent serverResponseEvent = (ServerResponseEvent) in.readObject();
                        if (serverResponseEvent.getSender().equals(clientName)){
                            System.out.println(serverResponseEvent.getEventName());
                            if (serverResponseEvent.getEventName().equals("done")) {
                                break;
                            }
                        }
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

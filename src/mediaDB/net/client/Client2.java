package mediaDB.net.client;

import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.CLIAdminForNet;
import mediaDB.ui.cli.event_creation.CreateServerResponseEvent;
import mediaDB.ui.cli.modes.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client2 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 7777);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){

            ClientEventBus clientEventBus = new ClientEventBus(out);
            InsertModeInputProcessing insertModeInputProcessing = new InsertModeInputProcessing(clientEventBus);
            InsertMode insertMode = new InsertMode(insertModeInputProcessing, clientEventBus);
            DisplayMode displayMode = new DisplayMode(clientEventBus);
            DeletionMode deletionMode = new DeletionMode(clientEventBus);
            ChangeMode changeMode = new ChangeMode(clientEventBus);
            CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode);

            CreateServerResponseEvent createServerResponseEvent = new CreateServerResponseEvent();
            String marker = "client2";
            ServerResponseEvent markerEvent = createServerResponseEvent.process(marker);
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

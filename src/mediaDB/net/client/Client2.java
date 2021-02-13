package mediaDB.net.client;

import mediaDB.routing.ServerResponseEvent;
import mediaDB.ui.cli.CLIAdminForNet;
import mediaDB.ui.cli.modes.*;

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
            cliAdmin.start();

            while (true){
                try {
                    ServerResponseEvent serverResponseEvent = (ServerResponseEvent) in.readObject();
                    System.out.println(serverResponseEvent.getEventName());
                    String response = serverResponseEvent.getEventName();
                    if (response.equals("End"))
                        break;
                } catch (Exception e){
                    e.printStackTrace();
                }

                cliAdmin.start();
            }
        }

    }
}

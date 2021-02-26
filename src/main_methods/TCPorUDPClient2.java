package main_methods;

import mediaDB.net.client.ClientEventBus;
import mediaDB.net.client.ClientEventBusUDP;
import mediaDB.net.client.UDPClient;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.CLIAdminForNet;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;

public class TCPorUDPClient2 {
    public static void main(String[] args) throws IOException {
//        Siehe readme bzgl. capacity
        BigDecimal capacity = null;
        String protocol = null;
        if (args.length < 1){
            System.out.println("Usage: protocol (TCP / UDP) as argument.");
            return;
        }

        protocol = checkProtocol(args[0]);
        if (protocol == null)
            protocol = checkProtocol(args[1]);

        assert protocol != null;
        if (protocol.equals("TCP")){
            try (Socket socket = new Socket("localhost", 7777);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

                ClientEventBus clientEventBus = new ClientEventBus(out);
                EventHandler eventHandler = new EventHandler();
                eventHandler.add(clientEventBus);
                String clientName = "client2";
                EventFactory eventFactory = new EventFactory(clientName);
                InsertMode insertMode = new InsertMode(eventHandler, eventFactory);
                DisplayMode displayMode = new DisplayMode(eventHandler, eventFactory);
                DeletionMode deletionMode = new DeletionMode(eventHandler, eventFactory);
                ChangeMode changeMode = new ChangeMode(eventHandler, eventFactory);
                PersistenceMode persistenceMode = new PersistenceMode(eventHandler, eventFactory);
                CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode,
                        persistenceMode, eventHandler, eventFactory);

                cliAdmin.start();

                while (true){
                    try {
                        while (true) {
                            ServerResponseEvent serverResponseEvent = (ServerResponseEvent) in.readObject();
                            if (serverResponseEvent.getSender().equals(clientName)){
                                if (serverResponseEvent.getEventName().equals("done")) {
                                    break;
                                }
                                System.out.println(serverResponseEvent.getEventName());
                            }
                        }
                    }
                    catch (EOFException | ClassNotFoundException e ) {
                        e.printStackTrace();
                    }
                    cliAdmin.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (protocol.equals("UDP")){
            UDPClient udpClient = new UDPClient();
            ClientEventBusUDP clientEventBus = new ClientEventBusUDP(udpClient.getSocket(), udpClient.getAddress(), udpClient.getPort());
            EventHandler eventHandler = new EventHandler();
            eventHandler.add(clientEventBus);
            String clientName = "clientName";
            EventFactory eventFactory = new EventFactory(clientName);
            InsertMode insertMode = new InsertMode(eventHandler, eventFactory);
            DisplayMode displayMode = new DisplayMode(eventHandler, eventFactory);
            DeletionMode deletionMode = new DeletionMode(eventHandler, eventFactory);
            ChangeMode changeMode = new ChangeMode(eventHandler, eventFactory);
            PersistenceMode persistenceMode = new PersistenceMode(eventHandler, eventFactory);
            CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode,
                    persistenceMode, eventHandler, eventFactory);

            while (true){
                cliAdmin.start();
                udpClient.receiveEvent();
            }
        }
        else {
            System.out.println("Unknown protocol");
            System.exit(0);
        }
    }

    public static String checkProtocol(String input){
        if (input.equals("TCP"))
            return input;
        else if (input.equals("UDP"))
            return input;
        else {
            return null;
        }
    }
}

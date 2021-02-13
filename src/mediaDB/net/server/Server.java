package mediaDB.net.server;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.*;
import mediaDB.routing.NetworkEvent;
import mediaDB.tempserver.ToClientMessenger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements Runnable{

    Socket socket;
    ServerEventBus serverEventBus;
    ToClientMessenger toClientMessenger;

    public Server(Socket socket, ServerEventBus serverEventBus, ToClientMessenger toClientMessenger) {
        this.socket = socket;
        this.serverEventBus = serverEventBus;
        this.toClientMessenger = toClientMessenger;
    }

    public void executeSession() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                serverEventBus.setOut(out);
                toClientMessenger.setOut(out);

                NetworkEvent receivedEvent;
                while (true) {
                    System.out.println("start of server while loop");
                    receivedEvent = (NetworkEvent) in.readObject();
                    System.out.println("event received");
                    serverEventBus.onMediaEvent(receivedEvent);


                    toClientMessenger.sendString(" \n");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
//        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
//            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
//
//
//
//
//
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            }

}

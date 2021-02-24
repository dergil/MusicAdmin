package mediaDB.net.server;

import mediaDB.net.EventBus;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.ServerResponseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements Runnable{

    Socket socket;
    EventBus eventBus;
    ToClientMessenger toClientMessenger;

    public Server(Socket socket, EventBus eventBus, ToClientMessenger toClientMessenger) {
        this.socket = socket;
        this.eventBus = eventBus;
        this.toClientMessenger = toClientMessenger;
    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {


                executeSession(in, out);


            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeSession(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
//                serverEventBus.setOut(out);
        toClientMessenger.setOut(out);

        NetworkEvent receivedEvent;
//        receivedEvent = (ServerResponseEvent) in.readObject();
//        String senderName = "server";
        while (true) {
            System.out.println("start of server while loop");
            receivedEvent = (NetworkEvent) in.readObject();
            System.out.println("event received");
            eventBus.onMediaEvent(receivedEvent);
            toClientMessenger.sendString("done", receivedEvent.getSender());
        }
    }
}

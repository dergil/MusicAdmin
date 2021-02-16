package mediaDB.net.server;

import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.ServerResponseEvent;

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
        receivedEvent = (ServerResponseEvent) in.readObject();
        String marker = receivedEvent.getEventName();
        while (true) {
            System.out.println("start of server while loop");
            receivedEvent = (NetworkEvent) in.readObject();
            System.out.println("event received");
            serverEventBus.onMediaEvent(receivedEvent);

//                  if outputstream empty, send " \n"?
            toClientMessenger.sendString(marker);
        }
    }
}

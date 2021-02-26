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
                toClientMessenger.setOut(out);

                NetworkEvent receivedEvent;
                while (true) {
                    receivedEvent = (NetworkEvent) in.readObject();
                    System.out.println("event received");
                    if (receivedEvent.getEventName().equals("ExitEvent")){
                        toClientMessenger.removeOut(out);
                        socket.close();
                        return;
                    }
                    eventBus.onMediaEvent(receivedEvent);
                    toClientMessenger.sendString("done", receivedEvent.getSender());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

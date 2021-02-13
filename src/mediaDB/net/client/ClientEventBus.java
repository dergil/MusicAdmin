package mediaDB.net.client;

import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientEventBus implements EventListener<NetworkEvent> {
    ObjectOutputStream out;
    public ClientEventBus(ObjectOutputStream out) {
        this.out = out;
    }

//    public void sendEvent (NetworkEvent event) throws IOException {
//        try {
//            out.writeObject(event);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onMediaEvent(NetworkEvent event) throws IOException {
        try {
            out.writeObject(event);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}

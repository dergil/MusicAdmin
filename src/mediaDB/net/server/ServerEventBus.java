package mediaDB.net.server;

import mediaDB.routing.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class ServerEventBus implements EventListener<NetworkEvent>{
    List<EventListener<NetworkEvent>> listeners = new LinkedList<>();
    ObjectOutputStream out;
    ServerResponseEvent endEvent = new ServerResponseEvent(this, "End");

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    public void register (EventListener eventListener){
        listeners.add(eventListener);
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ServerResponseEvent getEndEvent() {
        return endEvent;
    }

    public void sendResponse(String response) throws IOException {
        out.writeObject(new ServerResponseEvent(this, response));
    }

    @Override
    public void onMediaEvent(NetworkEvent event) throws IOException {
        for (EventListener<NetworkEvent> listener : listeners) {
            if(listener.supports(event.getClass())){
                listener.onMediaEvent(event);
            }
        }
    }
}

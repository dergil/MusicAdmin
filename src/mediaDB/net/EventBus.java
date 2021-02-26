package mediaDB.net;

import mediaDB.routing.*;
import mediaDB.routing.events.misc.ServerResponseEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EventBus implements EventListener<NetworkEvent>{
    List<EventListener<NetworkEvent>> listeners = new LinkedList<>();

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    public void register (EventListener eventListener){
        listeners.add(eventListener);
    }

    @Override
    public synchronized void onMediaEvent(NetworkEvent event) throws IOException {
        for (EventListener<NetworkEvent> listener : listeners) {
            if(listener.supports(event.getClass())){
                listener.onMediaEvent(event);
            }
        }
    }
}

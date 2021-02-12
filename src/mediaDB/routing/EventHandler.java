package mediaDB.routing;

import mediaDB.net.server.ServerEventBus;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EventHandler<T> {
    private List<ServerEventBus> listenerList = new LinkedList<>();
    public void add(ServerEventBus listener){
        this.listenerList.add(listener);
    }
    public void remove(ServerEventBus listener) {
        this.listenerList.remove(listener);
    }
    public void handle(NetworkEvent event) throws IOException {for (ServerEventBus listener : listenerList) listener.onMediaEvent(event);}

//    private List<EventListener<T>> listenerList = new LinkedList<>();
//    public void add(EventListener<T> listener){
//        this.listenerList.add(listener);
//    }
//    public void remove(EventListener<T> listener) {
//        this.listenerList.remove(listener);
//    }
//    public void handle(T event) throws IOException {for (EventListener<T> listener : listenerList) listener.onMediaEvent(event);}
}

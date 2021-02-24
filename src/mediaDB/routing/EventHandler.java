package mediaDB.routing;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EventHandler {
    private List<EventListener<NetworkEvent>> listenerList = new LinkedList<>();
    public void add(EventListener<NetworkEvent> listener){
        this.listenerList.add(listener);
    }
    public void remove(EventListener<NetworkEvent> listener) {
        this.listenerList.remove(listener);
    }
    public void handle(NetworkEvent event) throws IOException {for (EventListener<NetworkEvent> listener : listenerList) listener.onMediaEvent(event);}

//    private List<EventListener<T>> listenerList = new LinkedList<>();
//    public void add(EventListener<T> listener){
//        this.listenerList.add(listener);
//    }
//    public void remove(EventListener<T> listener) {
//        this.listenerList.remove(listener);
//    }
//    public void handle(T event) throws IOException {for (EventListener<T> listener : listenerList) listener.onMediaEvent(event);}
}

package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class ListEvent extends EventObject implements NetworkEvent, Serializable {
    String topic;

    public ListEvent(Object o, String topic) {
        super(o);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String getEventName() {
        return "ListEvent";
    }

}

package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class ExitEvent extends EventObject implements NetworkEvent, Serializable {
    String sender;

    public ExitEvent(Object source, String sender) {
        super(source);
        this.sender = sender;
    }

    @Override
    public String getEventName() {
        return "ExitEvent";
    }

    @Override
    public String getSender() {
        return sender;
    }
}

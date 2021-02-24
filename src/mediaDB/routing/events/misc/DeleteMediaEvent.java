package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class DeleteMediaEvent extends EventObject implements NetworkEvent, Serializable {
    int index;
    private String sender;

    public DeleteMediaEvent(Object source, int index, String sender) {
        super(source);
        this.index = index;
        this.sender = sender;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String getEventName() {
        return "DeleteMediaEvent";
    }

    @Override
    public String getSender() {
        return sender;
    }
}

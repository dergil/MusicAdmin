package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class DeleteMediaEvent extends EventObject implements NetworkEvent, Serializable {
    int index;

    public DeleteMediaEvent(Object o, int index) {
        super(o);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String getEventName() {
        return "DeleteMediaEvent";
    }

}

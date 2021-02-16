package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class ServerResponseEvent extends EventObject implements NetworkEvent, Serializable {
    String response;
    public ServerResponseEvent(Object o, String response) {
        super(o);
        this.response = response;
    }
    @Override
    public String getEventName() {
        return response;
    }

    @Override
    public String toString() {
        return "ServerResponseEvent{" +
                "response='" + response + '\'' +
                '}';
    }

}

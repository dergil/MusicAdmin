package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class ServerResponseEvent extends EventObject implements NetworkEvent, Serializable {
    String response;
    private String sender;
    String type;

    public ServerResponseEvent(Object source, String response, String sender, String type) {
        super(source);
        this.response = response;
        this.sender = sender;
        this.type = type;
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

    @Override
    public String getSender() {
        return sender;
    }

    public String getType() {
        return type;
    }
}

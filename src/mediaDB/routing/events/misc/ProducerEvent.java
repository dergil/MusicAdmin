package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class ProducerEvent extends EventObject implements NetworkEvent, Serializable {
    String command;
    String name;
    private String sender;

    public ProducerEvent(Object source, String command, String name, String sender) {
        super(source);
        this.command = command;
        this.name = name;
        this.sender = sender;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getEventName() {
        return "ProducerEvent";
    }

    @Override
    public String getSender() {
        return sender;
    }
}

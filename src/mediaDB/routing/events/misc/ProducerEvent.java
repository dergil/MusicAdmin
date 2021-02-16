package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class ProducerEvent extends EventObject implements NetworkEvent, Serializable {
    String command;
    String name;

    public ProducerEvent(Object o, String command, String name) {
        super(o);
        this.command = command;
        this.name = name;
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

}
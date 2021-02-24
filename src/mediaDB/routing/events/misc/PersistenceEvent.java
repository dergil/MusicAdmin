package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class PersistenceEvent extends EventObject implements NetworkEvent, Serializable {
    String command;
    String option;
    private String sender;

    public PersistenceEvent(Object source, String command, String option, String sender) {
        super(source);
        this.command = command;
        this.option = option;
        this.sender = sender;
    }

    @Override
    public String getEventName() {
        return null;
    }

    public String getOption() {
        return option;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String getSender() {
        return sender;
    }
}

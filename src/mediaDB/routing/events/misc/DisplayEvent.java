package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class DisplayEvent extends EventObject implements NetworkEvent, Serializable {
    String topic;
    String option;
    private String sender;

    public DisplayEvent(Object source, String topic, String option, String sender) {
        super(source);
        this.topic = topic;
        this.option = option;
        this.sender = sender;
    }

    public String getTopic() {
        return topic;
    }

    public String getOption() {
        return option;
    }

    @Override
    public String getEventName() {
        return "DisplayEvent";
    }

    @Override
    public String getSender() {
        return sender;
    }
}

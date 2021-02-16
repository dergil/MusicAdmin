package mediaDB.routing.events.misc;

import mediaDB.routing.NetworkEvent;

import java.io.Serializable;
import java.util.EventObject;

public class DisplayEvent extends EventObject implements NetworkEvent, Serializable {
    String topic;
    String option;

    public DisplayEvent(Object source, String topic, String option) {
        super(source);
        this.topic = topic;
        this.option = option;
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

}

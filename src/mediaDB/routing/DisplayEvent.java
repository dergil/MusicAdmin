package mediaDB.routing;

import java.util.EventObject;

public class DisplayEvent extends EventObject implements NetworkEvent{
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

package mediaDB.routing;

import java.util.EventObject;

public class ListEvent extends EventObject implements NetworkEvent{
    String topic;

    public ListEvent(Object o, String topic) {
        super(o);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String getEventName() {
        return "ListEvent";
    }

}

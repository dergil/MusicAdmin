package mediaDB.ui.cli.event_creation;

import mediaDB.routing.events.misc.ProducerEvent;

public class CreateProducerEvent{
    public ProducerEvent process(String name, String command) {
        return new ProducerEvent(this, command, name);
    }
}

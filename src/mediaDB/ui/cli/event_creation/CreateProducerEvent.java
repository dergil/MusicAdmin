package mediaDB.ui.cli.event_creation;

import mediaDB.routing.ProducerEvent;

import java.io.StringReader;

public class CreateProducerEvent{
    public ProducerEvent process(String name, String command) {
        return new ProducerEvent(this, command, name);
    }
}
